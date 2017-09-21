package application;


import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

	
	
	public void MakeGuess(ActionEvent event) throws Exception {
		
	Parent pane = FXMLLoader.load( getClass().getResource("SelectScreen.fxml"));
	Scene scene = new Scene( pane, 600, 600 );
	
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	
	stage.setScene(scene);
	System.out.println("got to select screen");
	}
	
	
	
	public void StartEasySet(ActionEvent event) throws Exception {
		
		Parent pane = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
		Scene scene = new Scene(pane, 600, 600);
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		stage.setScene(scene);
		System.out.println("got to easy set");
	}	
	
	
	public void StartHardSet(ActionEvent event) throws Exception {
		
		Parent pane = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
		Scene scene = new Scene(pane, 600, 600);
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		stage.setScene(scene);
		System.out.println("got to hard set");
	}
}
