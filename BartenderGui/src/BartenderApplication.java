import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.application.Application;

import java.io.IOException;

public class BartenderApplication extends Application
{

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));;

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bar app");
		primaryStage.sizeToScene();
		primaryStage.show();
	}
}
