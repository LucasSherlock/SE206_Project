package application.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import application.DataFile;
import application.models.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/*
 * This class is the controller for the list view.
 */
public class UserGamesController implements Initializable {	
	public static ArrayList<Game> games;
	public static ObservableList<String> items = FXCollections.observableArrayList();
	
	@FXML
	public Button play;
	public Button delete;
	public Button newList;
	public Button back;
	public ListView<String> QuestionList = new ListView<String>();

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		games = DataFile.user.getGames();
		
		items.clear();
		
		for (Game game: games) {
			items.add(game.getName());
		}
		
		QuestionList.setItems(items);
	}

	
	public void playGame(ActionEvent ae) {
		if(QuestionList.getSelectionModel().isEmpty()) {
			errorPopup();
		} else {
			DataFile.game = DataFile.user.findGame(QuestionList.getSelectionModel().getSelectedItem());
			DataFile.practiceMode = false;
			try {
				Parent pane;
				pane = FXMLLoader.load(getClass().getResource("../Question.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				stage.setScene(scene);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void move(ActionEvent ae) {
		try {
			Parent pane;
			if(ae.getSource() == newList) {
				pane = FXMLLoader.load(getClass().getResource("../GameCreator.fxml"));
			} else {
				pane = FXMLLoader.load(getClass().getResource("../Select.fxml"));
			}
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteList(ActionEvent ae) {
		if(QuestionList.getSelectionModel().isEmpty()) {
			errorPopup();
		} else {
			// Updates the users game list, since the controller holds a reference
			// to the users games the controllers list should update too
			DataFile.user.deleteGame(QuestionList.getSelectionModel().getSelectedItem());
			
			items.remove(QuestionList.getSelectionModel().getSelectedIndex());
		}
	}
	
	public void errorPopup() {
		Alert noSelection = new Alert(AlertType.ERROR);
		noSelection.setTitle("No Selection");
		noSelection.setContentText("Please select a list.");
		noSelection.showAndWait();
	}
	
	/*
	 * Called when the scene is hidden
	 */
	public void shutdown() {
		// We persist any changes made to the users lists
		DataFile.user.saveUser();
	}
}
