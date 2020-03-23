import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Drink;
import model.DrinkAmount;
import model.Order;
import model.OrderQueue;

import java.sql.*;
import java.util.Collections;

public class MainController
{
	@FXML
	private ListView<Order> orders;
	private ObservableList<Order> orderItems = FXCollections.observableArrayList();

	@FXML
	private ListView<DrinkAmount> previewList;
	private ObservableList<DrinkAmount> previewItems = FXCollections.observableArrayList();

	@FXML
	private Label previewPrice, previewDrinkCount, currentTotalPrice;

	@FXML
	private Button takeOrder, completeOrder;

	@FXML
	ListView<DrinkAmount> currentOrderList;
	private ObservableList<DrinkAmount> currentOrderItems = FXCollections.observableArrayList();


	OrderQueue orderQueue = new OrderQueue();
	Order currentOrder = null;
	Connection connection;

	public void initialize() throws SQLException
	{
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bar-db", "root", "");

		takeOrder.setOnAction(e ->
		{
			try
			{
				if (currentOrder != null)
					throw new Exception("You still have not completed your previous order");

				currentOrder = orderQueue.consumeOrder();
				updateQueue();
				updateCurrentOrder();
			} catch (Exception ex)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Queue error");
				alert.setHeaderText(null);
				alert.setContentText(ex.getMessage());

				alert.show();
			}
		});

		completeOrder.setOnAction(e ->
		{
			currentOrder = null;
			updateCurrentOrder();
		});

		orders.setMouseTransparent(true);
		orders.setFocusTraversable(false);
		orders.setCellFactory(x -> createOrderCell());
		orders.setItems(orderItems);

		previewList.setMouseTransparent(true);
		previewList.setFocusTraversable(false);
		previewList.setItems(previewItems);
		previewList.setCellFactory(x -> createAmountCell());

		currentOrderList.setMouseTransparent(true);
		currentOrderList.setFocusTraversable(false);
		currentOrderList.setItems(currentOrderItems);
		currentOrderList.setCellFactory(x -> createAmountCell());

		refreshQueue();
	}

	private void refreshQueue() throws SQLException
	{
		Statement stmt = connection.createStatement();
		Statement stmtDrinks = connection.createStatement();

		ResultSet result = stmt.executeQuery("SELECT * FROM orders WHERE price_paid IS NULL");

		while (result.next())
		{
			ResultSet drinkResult = stmtDrinks.executeQuery("SELECT drinks.*, oders_drinks_junction.count FROM oders_drinks_junction \n" + "LEFT JOIN drinks ON oders_drinks_junction.drink_id = drinks.drink_id\n" + "WHERE order_id = 1");

			Order order = new Order(result.getString("table_code"));

			while (drinkResult.next())
			{
				order.addDrink(new DrinkAmount(new Drink(drinkResult.getString("name"), drinkResult.getInt("price")), drinkResult.getInt("count")));
			}

			orderQueue.addOrder(order);

			drinkResult.close();
		}

		result.close();

		updateQueue();
	}

	private void updateQueue()
	{
		orderItems.setAll(orderQueue.getOrders());
		Collections.reverse(orderItems);

		if (orderQueue.getOrderCount() > 0)
		{
			Order next = orderQueue.getNextOrder();
			previewItems.setAll(next.getDrinks());
			previewPrice.setText(next.getPrice() + "");
			previewDrinkCount.setText(next.getDrinkCount() + "");
		}
		else
		{
			previewItems.clear();
			previewPrice.setText("-");
			previewDrinkCount.setText("-");
		}
	}

	private void updateCurrentOrder()
	{
		if (currentOrder != null)
		{
			completeOrder.setDisable(false);
			currentOrderItems.setAll(currentOrder.getDrinks());
			currentTotalPrice.setText(currentOrder.getPrice() + "");
		}
		else
		{
			completeOrder.setDisable(true);
			currentOrderItems.clear();
			currentTotalPrice.setText("-");
		}
	}

	private ListCell<Order> createOrderCell()
	{
		return new ListCell<Order>()
		{
			@Override
			protected void updateItem(Order item, boolean empty)
			{
				super.updateItem(item, empty);
				if (empty || item == null)
				{
					setText(null);
					return;
				}

				setText(item.getDrinkCount() + " drinks");
			}
		};
	}

	private ListCell<DrinkAmount> createAmountCell()
	{
		return new ListCell<DrinkAmount>()
		{
			@Override
			protected void updateItem(DrinkAmount item, boolean empty)
			{
				super.updateItem(item, empty);
				if (empty || item == null)
				{
					setText(null);
					return;
				}

				setText(item.getDrink().getName() + " x " + item.getCount());
			}
		};
	}
}
