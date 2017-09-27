package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameController implements Initializable {


	@FXML
	public Button RecordButton;
	public Button SkipButton;
	public Label message;
	public Label numberDisplay;
	public Label SCORE;
	public Label LEVEL;
	public ProgressBar timeRemaining = new ProgressBar(0);



	public void TestHtk(ActionEvent event) throws Exception {

		if(event.getSource() == RecordButton) {
			message.setText("Say the Number!");
			timeRemaining.setVisible(true);
			SkipButton.setDisable(true);
			RecordButton.setDisable(true);
			QuestionWorker currentQt = new QuestionWorker();
			Thread th = new Thread(currentQt);
			TimerWorker timer = new TimerWorker();
			Thread th2 = new Thread(timer);
			
			th.start();
			th2.start();
			
			
		}else if(event.getSource() == SkipButton) {
			
			Parent pane;
			if(DataFile.Level < 9) {
			
				DataFile.trial = 1;
				DataFile.Level++;
				pane  = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
			
			}else {
				
				pane  = FXMLLoader.load(getClass().getResource("ScoreScreen.fxml"));
				
			}
			
			Scene scene = new Scene(pane);
			Main.thestage.setScene(scene);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 

		}


	}



	//method to process Htk
	private ArrayList<String> useHTK() throws Exception{



		ArrayList<String> RecognisedWords = new ArrayList<String>();
		ProcessBuilder testHtk = new ProcessBuilder("bash","-c","./"+ "GoSpeech");

		testHtk.directory(new File("MaoriNumbers"));

		Process process = testHtk.start();

		process.waitFor();


		FileReader recout = new FileReader("MaoriNumbers/recout.mlf");
		BufferedReader stdoutBuffered = new BufferedReader(recout);


		String line;


		while((line = stdoutBuffered.readLine()) != null){

			if(line.equals("#!MLF!#") || line.equals("\"*/foo.rec\"") ||line.equals("sil") ||line.equals(".")) {

			}else {

				RecognisedWords.add(line);

			}

		}
		stdoutBuffered.close();
		System.out.print("you said:" + RecognisedWords);
		
		return RecognisedWords;

	}



	//function turns number inputs to string maori outputs
	private ArrayList<String> numbersToMaori(int number) {

		ArrayList<String> A = new ArrayList<String>();

		if(number > 99 || number < 0) {

			try {
				throw new Exception("Word Out Of Range");
			} catch (Exception e) {

				e.printStackTrace();
			}
		}



		switch(number) {



		case 0: A.add("");
		break;
		case 1: A.add("tahi");
		break;
		case 2: A.add("rua");
		break;
		case 3: A.add("toru");
		break;
		case 4:	A.add("whaa");
		break;
		case 5: A.add("rima");
		break;
		case 6: A.add("ono");
		break;
		case 7: A.add("whitu");
		break;
		case 8:	A.add("waru");
		break;
		case 9: A.add("iwa");
		break;
		case 10: A.add("tekau");
		break;


		}

		if(number >= 11 && number <= 19) {

			A.add("tekau"); 
			A.add("maa");
			A.addAll(numbersToMaori(number-10));


		}

		if(number > 19 && number <= 99) {

			if(number%10 == 0) {

				A.addAll(numbersToMaori(number/10));
				A.add("tekau");

			}else {

				A.addAll(numbersToMaori(number/10));
				A.add("tekau");
				A.add("maa");
				A.addAll(numbersToMaori(number%10));


			}

		}


		return A;

	}
	//code to display Popup
	public void PopUp() throws Exception{


		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("PopUp.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("My modal window");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(RecordButton.getScene().getWindow());
		stage.showAndWait();


	}


	//Start of scene
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LEVEL.setText("Level: " + Integer.toString(DataFile.Level + 1));
		SCORE.setText("Score: " + Integer.toString(DataFile.score));
		String number = Integer.toString(DataFile.gameNumbers[DataFile.Level]);
		numberDisplay.setText(number);
		timeRemaining.setVisible(false);
		System.out.println(DataFile.gameNumbers[DataFile.Level]);
		
		
	}
	
	
	//inner class to run a thread to increment the progress bar
	public class TimerWorker extends Task<Void> {

		@Override
		protected Void call() throws Exception {
			int time = 0;
			while(time < 31) {
				try{
					timeRemaining.setProgress(((double)time)/30);
					Thread.sleep(100);
				} catch(Exception e) {
					e.printStackTrace();
				}
				time++;
			}
			return null;
		}
		
		
	}


	//inner class which is used by outer class to run a thread to process record to avoid blocking
	public class QuestionWorker extends Task<Void>{

		@Override
		protected Void call() throws Exception {

			ArrayList<String> wordToSay = numbersToMaori(DataFile.gameNumbers[DataFile.Level]);

			System.out.print("Say the Word "+wordToSay);



			ArrayList<String> RecognisedWords = useHTK();
			if(wordToSay.equals(RecognisedWords)) {
				DataFile.CorrentAnswer = true;
				DataFile.score++;
				System.out.print("You Did It");

			}else {
				DataFile.CorrentAnswer = false;

			}
			
			//Sleep for 3s to mimic 3s of recording time
			Thread.sleep(3000);



			Platform.runLater(new Runnable() {
				@Override public void run() {

					try {
						PopUp();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			});


			return null;
		}




	}
	
	



}
