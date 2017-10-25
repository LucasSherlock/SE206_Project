package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public Parent root;
	public static Stage mainStage;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			mainStage = primaryStage;
			root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
