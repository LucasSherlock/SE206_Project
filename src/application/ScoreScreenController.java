package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ScoreScreenController implements Initializable{
	
	@FXML
	public Label Difficulty;
	public Label Score;
	public Button Harder;
	public Button Menu;
	
	public void postGame(ActionEvent event) throws Exception {
		
		if(event.getSource() == Menu) {
			
			
			
			Parent pane = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
			stage.setScene(scene);
	
		}else if(event.getSource() == Harder){
			
			DataFile.trial = 1;
			DataFile.CorrentAnswer = false;
			DataFile.score = 0;
			DataFile.Level = 0;
			DataFile.difficulty = "hard";
			for(int x = 0; x < DataFile.gameNumbers.length; x++) {

				DataFile.gameNumbers[x] = new Random().nextInt(99 - 1) + 1 + 1;


			}	
			Parent pane = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
			stage.setScene(scene);
			
		}
		
		
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	
		if(DataFile.difficulty.equals("HARD")) {
			Difficulty.setText(DataFile.difficulty + " (1-99)");
			Difficulty.setTextFill(Color.RED);
			DataFile.hardScores.add(Integer.toString(DataFile.score));
		} else if(DataFile.difficulty.equals("EASY")) {
			Difficulty.setText(DataFile.difficulty + " (1-9)");
			Difficulty.setTextFill(Color.GREEN);
			DataFile.easyScores.add(Integer.toString(DataFile.score));
		}
		
		Score.setText(DataFile.score+"/10");
		if(DataFile.score >= 8) {
			Score.setTextFill(Color.GREEN);
		}
		
		if(DataFile.score <= 8) {
			
			Harder.setDisable(true);
			
		}else {
			
			Harder.setDisable(false);
			
		}
	}

	
	
}
