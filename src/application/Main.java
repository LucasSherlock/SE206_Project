package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


/*
 * The application class, this is the first class accessed during execution
 */
public class Main extends Application {
	public Parent root;
	public static Stage mainStage;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			mainStage = primaryStage;
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/UserSelect.fxml"));
			
			// Loads the stylesheet
			root.getStylesheets().add(getClass().getClassLoader().getResource("application/application.css").toString());
			
			mainStage.setHeight(400);
			mainStage.setWidth(300);
			mainStage.setTitle("Select a user");
			
			// Sets the Title Screen as the the application scene
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
