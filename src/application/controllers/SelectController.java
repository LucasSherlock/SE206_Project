package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.DataFile;
import application.models.Game;
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
	public Button helpButton;
	public Button practice;
	public Button highscores;
	public Button hard;
	public Button easy;
	public Button back;
	public VBox gameType;
	public VBox difficulty;
	public Label title;
	
	
	/*
	 * Displays the help page
	 */
	public void getHelp(ActionEvent ae) {
		try {
			Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
			
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/Help.fxml"));
			Parent pane = loader.load();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
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
				Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("application/Question.fxml"));
				
				// Loads the stylesheet
				pane.getStylesheets().add(getClass().getClassLoader().getResource("application/application.css").toString());
				
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				stage.setScene(scene);
			} else {
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/UserGames.fxml"));
				Parent pane = loader.load();
				Scene scene = new Scene(pane);
				stage.setScene(scene);

				// Execute shutdown on window close
				UserGamesController controller = loader.getController();
				stage.setOnHidden(e -> controller.shutdown());
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
			ans = new Random().nextInt(98) + 2;
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

		Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("application/Question.fxml"));
		
		// Loads the stylesheet
		pane.getStylesheets().add(getClass().getClassLoader().getResource("application/application.css").toString());
		
		Scene scene = new Scene(pane);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		stage.setScene(scene);

	}
	
	
	/*
	 * go to highscores screen
	 */
	public void viewHighScore(ActionEvent event) throws Exception{
		
		Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("application/HighScores.fxml"));
		
		// Loads the stylesheet
		pane.getStylesheets().add(getClass().getClassLoader().getResource("application/application.css").toString());
		
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
				Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("application/UserSelect.fxml"));
				
				// Loads the stylesheet
				pane.getStylesheets().add(getClass().getClassLoader().getResource("application/application.css").toString());
				
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow();
				
				stage.setHeight(400);
				stage.setWidth(300);
				stage.setTitle("Select a user");
				
				stage.setScene(scene);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			title.setText("Titai!");
			gameType.setVisible(true);
			difficulty.setVisible(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		title.setText("Titai!");
		gameType.setVisible(true);
		difficulty.setVisible(false);
		
	}
	
}
