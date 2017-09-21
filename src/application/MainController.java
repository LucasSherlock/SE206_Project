package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
	
	//An array of numbers to guess, should be initialised random upon mode selection
	//currently fixed at 1-10
	private int[] numbersToGuess = {1,2,3,4,5,6,7,8,9,10};
	
	//int to store the score
	private int Score = 0;
	
	public void MakeGuess(ActionEvent event) throws Exception {
		
	Parent pane = FXMLLoader.load( getClass().getResource("SelectScreen.fxml"));
	Scene scene = new Scene( pane, 600, 600 );
	
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	
	stage.setScene(scene);
	System.out.println("got to select screen");
	}
	
	
	
	public void StartEasySet(ActionEvent event) throws Exception {
		
		Parent pane = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
		Scene scene = new Scene(pane, 600, 600);
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		stage.setScene(scene);
		System.out.println("got to easy set");
	}	
	
	
	public void StartHardSet(ActionEvent event) throws Exception {
		
		Parent pane = FXMLLoader.load(getClass().getResource("QuestionScreen.fxml"));
		Scene scene = new Scene(pane, 600, 600);
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		stage.setScene(scene);
		System.out.println("got to hard set");
	}
	
	public void TestHtk(ActionEvent event) throws Exception {
		
		
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
}
