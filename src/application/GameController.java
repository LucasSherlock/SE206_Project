package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController implements Initializable{

	
	@FXML
	public Button button;
	public Label numberDisplay;
	
	
	public void TestHtk(ActionEvent event) throws Exception {

		useHTK();
		
		
		for(int gameNumber: DataFile.gameNumbers) {
		
		
			
		}


	}
	
	
	private void useHTK() throws Exception{
		
		ProcessBuilder testHtk = new ProcessBuilder("bash","-c","./"+ "GoSpeech");

		testHtk.directory(new File("MaoriNumbers"));

		Process process = testHtk.start();

		process.waitFor();


		FileReader recout = new FileReader("MaoriNumbers/recout.mlf");
		BufferedReader stdoutBuffered = new BufferedReader(recout);

		System.out.println("hi");
		String line;


		while((line = stdoutBuffered.readLine()) != null){

			System.out.println(line);


		}
		
	}



	//function turns number inputs to string maori outputs
	private String numbersToMaori(int number) {

		if(number > 99 || number < 0) {

			return "InValidNumber";

		}


		switch(number) {

		case 0: return "";
		case 1: return "tahi";
		case 2: return "rua";
		case 3: return "toru";
		case 4: return "wha";
		case 5: return "rima";
		case 6: return "ono";
		case 7: return "whitu";
		case 8: return "waru";
		case 9: return "iwa";
		case 10: return "tekau";

		}

		if(number >= 11 && number <= 19) {

			return "tekau ma " + numbersToMaori(number-10);

		}

		if(number > 19 && number <= 99) {

			if(number%10 == 0) {

				return numbersToMaori(number/10) + " tekau";

			}else {

				return numbersToMaori(number/10) + " tekau ma " + numbersToMaori(number%10); 

			}

		}


		return null;




	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String number = Integer.toString(DataFile.gameNumbers[0]);
		numberDisplay.setText(number);
		
	}

}
