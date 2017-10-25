package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class MainController {

	@FXML
	public Button Easy;
	public Button Hard;
	public Button back;
	public Button showInstruc;
	public Label instructions;
	public Label title;
	public Label instrucTitle;
	public SplitPane buttons;
	



	//An array of numbers to guess, should be initialised random upon mode selection
	//currently fixed at 1-10


	public void start(ActionEvent event) throws Exception {

		Parent pane = FXMLLoader.load( getClass().getResource("UserSelect.fxml"));
		Scene scene = new Scene( pane);

		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		stage.setScene(scene);



	}





	public void StartSet(ActionEvent event) throws Exception {

		if(event.getSource().equals(Easy)) {

			System.out.println("easy");
			setLevel(1,9);
			DataFile.difficulty = "EASY";

		}else if(event.getSource().equals(Hard)) {

			System.out.println("easy");
			setLevel(1,99);
			DataFile.difficulty = "HARD";
		
		}
		DataFile.trial = 1;
		DataFile.CorrectAnswer = false;
		DataFile.score = 0;
		DataFile.Level = 0;

		Parent pane = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		stage.setScene(scene);
		System.out.println("got to easy set");
		
		


	}





	private void setLevel(int min, int max) {


		for(int x = 0; x < DataFile.practiceGame.length; x++) {

			DataFile.practiceGame[x] = new Random().nextInt(max - min) + 1 + min;


		}	
		

	}



	public void viewHighScore(ActionEvent event) throws Exception{
		
		Parent pane = FXMLLoader.load( getClass().getResource("HighScores.fxml"));
		Scene scene = new Scene( pane);

		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		System.out.println("got to select screen");
		
	}


	public void instructions(ActionEvent ae) {
		if(ae.getSource() == showInstruc) {
			title.setVisible(false);
			buttons.setVisible(false);
			instrucTitle.setVisible(true);
			instructions.setVisible(true);
			back.setVisible(true);
		} else if(ae.getSource() == back) {
			title.setVisible(true);
			buttons.setVisible(true);
			instrucTitle.setVisible(false);
			instructions.setVisible(false);
			back.setVisible(false);
		}
	}

}
