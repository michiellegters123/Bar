import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


import java.util.List;

public class MainController
{
	@FXML
	private Button addToOrder;
	@FXML
	private Button deleteOrdered;
	@FXML
	private Button submit;


	@FXML
	public ListView<String> allDrinks;
	@FXML
	public ListView<String> orderList;

	private ObservableList<String> Dranken = FXCollections.observableArrayList();
	private ObservableList<String> besteldeDranken = FXCollections.observableArrayList();


	public void initialize()
	{
		Dranken.add("Corona");
		Dranken.add("Bier");
		Dranken.add("Wijn");
		Dranken.add("Cola");
		Dranken.add("Hiv");

		allDrinks.setItems(Dranken);
		orderList.setItems(besteldeDranken);

		addToOrder.setOnAction(event -> {
			String drink = allDrinks.getSelectionModel().getSelectedItem();
			besteldeDranken.add(drink);
		});

		deleteOrdered.setOnAction(event -> {
			String drink = orderList.getSelectionModel().getSelectedItem();
			besteldeDranken.remove(drink);
		});
		submit.setOnAction(event -> {

		});
	}




}
