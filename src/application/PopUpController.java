package application;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpController implements Initializable {

	@FXML
	public Button Retry;
	public Button DontRetry;
	public Label AnswerMessage;
	


	//basically, on button press, set stage back to scene
	public void OnButtonPress(ActionEvent event) throws Exception{
	
		
		if(((Button)event.getSource()).getText().equals("Skip")) {
			
			DataFile.Level++;
			DataFile.trial = 1;
			
		}
			
			Parent pane;
		
		if(DataFile.Level < 9) {
		
			pane  = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
			
			
			
		}else {
			
			pane = FXMLLoader.load(getClass().getResource("ScoreScreen.fxml"));
			
		}
		
		Scene scene = new Scene(pane);
		Main.thestage.setScene(scene);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		stage.close();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Retry.setDisable(false);
		
		if(DataFile.trial == 1) {
			if(DataFile.CorrentAnswer == true) {

				AnswerMessage.setText("Correct answer, GOOD JOB!");
				Retry.setText("Next");
				DontRetry.setText("Next");
				DataFile.Level++;

			}else {


				AnswerMessage.setText("Incorrect, try again or skip");
				Retry.setText("Retry");
				DontRetry.setText("Skip");
				DataFile.trial = 2;
			}
		}else if(DataFile.trial == 2) {
			if(DataFile.CorrentAnswer == true) {

				AnswerMessage.setText("Correct answer, GOOD JOB!");
				Retry.setText("Next");
				DontRetry.setText("Next");
				DataFile.Level++;

			}else {

				Retry.setDisable(true);
				AnswerMessage.setText("Incorrect, No retries left");
				Retry.setText("Retry");
				DontRetry.setText("Next");
				DataFile.trial = 1;
				DataFile.Level++;
			}
		}

	}

}
