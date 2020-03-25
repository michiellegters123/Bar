import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Drink;
import model.DrinkAmount;
import model.Order;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MainController
{
	@FXML
	private Button addToOrder;
	@FXML
	private Button deleteOrdered;
	@FXML
	private Button submitOrder;
	@FXML
	private Button refreshButton;


	@FXML
	private TextField tableNumber;


	@FXML
	private ListView<DrinkAmount> allDrinks;
	@FXML
	private ListView<DrinkAmount> orderList;
	@FXML
	private  ListView<String> completedOrders;

	private ObservableList<DrinkAmount> Dranken = FXCollections.observableArrayList();
	private ObservableList<DrinkAmount> besteldeDranken = FXCollections.observableArrayList();
	private ObservableList<String> afgerond = FXCollections.observableArrayList();
	private Connection connection;

	Order order = new Order("1");

	public void initialize() throws SQLException
	{
		loadAllData();

		allDrinks.setCellFactory(x -> createAmountCell());
		allDrinks.setItems(Dranken);
		orderList.setCellFactory(x -> createAmountCell());
		orderList.setItems(besteldeDranken);
		completedOrders.setItems(afgerond);


		addToOrder.setOnAction(event -> {
			DrinkAmount drink = allDrinks.getSelectionModel().getSelectedItem();
			String aantal= JOptionPane.showInputDialog("Aantal ");
			DrinkAmount da = new DrinkAmount(drink.getDrink(), Integer.parseInt(aantal));
			drink.decreaseAmount(Integer.parseInt(aantal));
			order.addDrink(da);

			besteldeDranken.add(da);
			Dranken.set(Dranken.indexOf(drink), drink);
			//orderList.setItems(drink);
		});

		deleteOrdered.setOnAction(event -> {
			DrinkAmount drink = orderList.getSelectionModel().getSelectedItem();
			besteldeDranken.remove(drink);
		});
		submitOrder.setOnAction(event -> {
			try
			{
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bardb", "root", "");
				Statement stmtDrinks = connection.createStatement();
				stmtDrinks.executeUpdate("INSERT INTO orders "+" (table_code) "+" VALUES ("+tableNumber.getText()+")", Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = stmtDrinks.getGeneratedKeys();
				rs.next();
				int insertOrder = rs.getInt(1);


				System.out.println(insertOrder);


				besteldeDranken.sorted();
				for (int i = 0; i < besteldeDranken.size(); i++)
				{
					int insertJunciton = stmtDrinks.executeUpdate("INSERT INTO oders_drinks_junction "+" (drink_id, order_id, count) "+" VALUES ("+besteldeDranken.get(i).getDrink().getDrinkId()+", "+insertOrder+" ,"+besteldeDranken.get(i).getCount()+")");
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}


		});

		refreshButton.setOnAction(event -> {
			try
			{
				loadAllData();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		});
	}

	private void loadAllData() throws SQLException
	{
		Dranken.clear();
		besteldeDranken.clear();
		afgerond.clear();

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bardb", "root", "");

		Statement stmtDrinks = connection.createStatement();

		ResultSet result = stmtDrinks.executeQuery("SELECT * FROM orders WHERE price_paid IS NOT NULL");
		while(result.next())
		{
			afgerond.add("Tafel: "+result.getString("table_code"));
		}

		ResultSet drankenDb = stmtDrinks.executeQuery("SELECT * FROM drinks");
		while(drankenDb.next())
		{
			Drink drink = new Drink(drankenDb.getString("name"), drankenDb.getInt("price"));
			drink.setDrinkId(drankenDb.getInt("drink_id"));
			Dranken.add(new DrinkAmount(drink, drankenDb.getInt("stored")));

		}
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

				setText(item.getDrink().getName() +  " x " + item.getCount());
			}
		};
	}


}
