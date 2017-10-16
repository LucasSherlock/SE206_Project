package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

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

public class ListController implements Initializable {
	
	public ObservableList<String> items = FXCollections.observableArrayList("Test 1","Test 2", "Test 3");
	@FXML
	public Button practice;
	public Button playRandom;
	public ListView<String> QuestionList = new ListView<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		QuestionList.setItems(items);
	}
	
	public void setRandom() {
		DataFile.practiceMode = false;
		int arg1;
		int arg2;
		int ans;
		String problem;
		
		for(int i = 0; i < DataFile.gameProblems.length; i++) {
			ans = new Random().nextInt(99) + 1;
			arg1 = new Random().nextInt(ans-1) + 1;
			arg2 = ans - arg1;
			problem = arg1+"+"+arg2;
			DataFile.gameProblems[i] = problem;
			DataFile.gameAnswers[i] = ans;
		}
	}
	
	public void playGame(ActionEvent ae) {
		if(ae.getSource() == playRandom) {
			setRandom();
			try {
				Parent pane;
				pane = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				stage.setScene(scene);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			DataFile.CorrentAnswer = false;
			DataFile.score = 0;
			DataFile.Level = 0;
			DataFile.trial = 1;
			
			
		}
	}
	
	public void practiceMode(ActionEvent ae) {
		
		try {
			
			DataFile.practiceMode = true;
			Parent pane;
			pane = FXMLLoader.load(getClass().getResource("SelectScreen.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
