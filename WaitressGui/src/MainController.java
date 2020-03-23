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

import java.util.List;

public class MainController
{
	@FXML
	private Button addToOrder;
	@FXML
	private Button deleteOrdered;
	@FXML
	private Button submitOrder;

	@FXML
	private TextField tableNumber;


	@FXML
	public ListView<DrinkAmount> allDrinks;
	@FXML
	public ListView<DrinkAmount> orderList;

	private ObservableList<DrinkAmount> Dranken = FXCollections.observableArrayList();
	private ObservableList<DrinkAmount> besteldeDranken = FXCollections.observableArrayList();

	Order order = new Order();

	public void initialize()
	{



		Dranken.add(new DrinkAmount(new Drink("Corona", 5), 112));
		Dranken.add(new DrinkAmount(new Drink("Bier", 4), 112));
		Dranken.add(new DrinkAmount(new Drink("Wijn", 10), 60));
		Dranken.add(new DrinkAmount(new Drink("Cola", 1), 112));
		Dranken.add(new DrinkAmount(new Drink("Fanta", 1), 112));

		allDrinks.setCellFactory(x -> createAmountCell());
		allDrinks.setItems(Dranken);
		orderList.setCellFactory(x -> createAmountCell());
		orderList.setItems(besteldeDranken);

		addToOrder.setOnAction(event -> {
			DrinkAmount drink = allDrinks.getSelectionModel().getSelectedItem();
			DrinkAmount da = new DrinkAmount(drink.getDrink(), 1);
			drink.decreaseAmount(1);
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

		});
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
