package application.controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.DataFile;
import application.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectController implements Initializable {

	@FXML
	public Button custom;
	public Button random;
	public Button practice;
	public Button highscores;
	public Button hard;
	public Button easy;
	public Button back;
	public VBox gameType;
	public VBox difficulty;
	public Label title;
	
	
	/*
	 * actions when custom, random or practice button is pressed
	 */
	public void play(ActionEvent ae) {
		
		DataFile.CorrectAnswer = false;
		DataFile.score = 0;
		DataFile.Level = 0;
		DataFile.trial = 1;
		try {
			if(ae.getSource() == practice) {
				DataFile.practiceMode = true;
				gameType.setVisible(false);
				difficulty.setVisible(true);
				title.setText("Select a difficulty.");
			} else if(ae.getSource() == random) {
				setRandom();
				Parent pane = FXMLLoader.load(getClass().getResource("../Question.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				stage.setScene(scene);
			} else {
				Parent pane = FXMLLoader.load(getClass().getResource("../UserGames.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				stage.setScene(scene);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * create a random game and select it
	 */
	public void setRandom() {
		DataFile.practiceMode = false;
		int arg1;
		int arg2;
		int ans;
		DataFile.game = new Game();
		for(int i = 0; i < 10; i++) {
			ans = new Random().nextInt(99) + 1;
			arg1 = new Random().nextInt(ans-1) + 1;
			arg2 = ans - arg1;
			DataFile.game.add(arg1, arg2);			
		}
	}
	
	/*
	 * begin a practice game with numbers based on difficulty level chosen
	 */
	public void startPracticeSet(ActionEvent event) throws Exception {

		if(event.getSource().equals(easy)) {
			System.out.println("easy");
			setLevel(1,9);
			DataFile.difficulty = "EASY";
		}else if(event.getSource().equals(hard)) {
			System.out.println("easy");
			setLevel(1,99);
			DataFile.difficulty = "HARD";
		}

		Parent pane = FXMLLoader.load(getClass().getResource("../Question.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		stage.setScene(scene);

	}
	
	
	/*
	 * go to highscores screen
	 */
	public void viewHighScore(ActionEvent event) throws Exception{
		
		Parent pane = FXMLLoader.load(getClass().getResource("../HighScores.fxml"));
		Scene scene = new Scene(pane);

		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		
	}
	
	/*
	 * generate a random list of numbers for practice 
	 */
	private void setLevel(int min, int max) {

		for(int x = 0; x < DataFile.practiceGame.length; x++) {
			DataFile.practiceGame[x] = new Random().nextInt(max - min) + 1 + min;
		}	
	}

	public void back(ActionEvent ae) {
		if(gameType.isVisible()) {
			try {
				Parent pane = FXMLLoader.load(getClass().getResource("../UserSelect.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				stage.setScene(scene);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			title.setText("What would you like to do?");
			gameType.setVisible(true);
			difficulty.setVisible(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		title.setText("What would you like to do?");
		gameType.setVisible(true);
		difficulty.setVisible(false);
		
	}
	
}
