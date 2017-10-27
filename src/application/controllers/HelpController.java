package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;


public class HelpController implements Initializable {	
	@FXML
	public Button back;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	public void backToMenu(ActionEvent event) {
		try {
			Parent pane = FXMLLoader.load( getClass().getClassLoader().getResource("application/Select.fxml"));
			
			// Loads the stylesheet
			pane.getStylesheets().add(getClass().getClassLoader().getResource("application/application.css").toString());
			
			Scene scene = new Scene( pane);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}





