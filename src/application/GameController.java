package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController implements Initializable{


	@FXML
	public Button RecordButton;
	public Button SkipButton;
	public Label numberDisplay;


	public void TestHtk(ActionEvent event) throws Exception {

		RecordButton.setDisable(true);
		ArrayList<String> wordToSay = numbersToMaori(DataFile.gameNumbers[DataFile.Level]);
		numberDisplay.setText(Integer.toString(DataFile.gameNumbers[DataFile.Level]));
		System.out.print("Say the Word "+wordToSay);
		ArrayList<String> RecognisedWords = useHTK();
		int Correctness = 0;

		for(String word: wordToSay) {


			if(RecognisedWords.contains(word)) {

				Correctness++;

			}


		}

		if(wordToSay.size() == Correctness) {

			System.out.println("CONGRATS YOU GOT IT");

		}

		for(int gameNumber: DataFile.gameNumbers) {



		}
		RecordButton.setDisable(false);
		System.out.print(DataFile.Level);
		DataFile.Level++;
		numberDisplay.setText(Integer.toString(DataFile.gameNumbers[DataFile.Level]));



	}




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
		System.out.print("you said:" + RecognisedWords);
		System.out.println(RecognisedWords.size());
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




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String number = Integer.toString(DataFile.gameNumbers[0]);
		numberDisplay.setText(number);

	}

}
