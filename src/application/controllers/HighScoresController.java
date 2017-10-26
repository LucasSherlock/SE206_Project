package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import application.*;
import application.DataFile;
import application.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.stage.Stage;


public class HighScoresController implements Initializable {

	public ObservableList<String> highScores = FXCollections.observableArrayList();
	
	@FXML
	public ListView<String> highScoreList;
	public Button back;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateList();
		highScoreList.setItems(highScores);

	}
	
	public void populateList() {
		ArrayList<Game> games = DataFile.user.getGames();
		
		for(Game game : games) {
			highScores.add("Game: " + game.getName() + "\t\t\tHigh Score: " + game.getHighScore());
		}
		
		
	}


	
	public void backToMenu(ActionEvent event) throws Exception{
		Parent pane = FXMLLoader.load( getClass().getResource("../Select.fxml"));
		Scene scene = new Scene( pane);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}
}





