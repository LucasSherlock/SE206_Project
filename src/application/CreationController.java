package application;

import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreationController implements Initializable {

	public Game newGame;
	
	
	@FXML
	public Button submit1;
	public Button submit2;
	public TextField arg1;
	public TextField arg2;
	public TextField enterName;
	public Label warning;
	public Label qNum;
	public Label plus;
	public Label title;
	
	
	public void addQuestion(ActionEvent ae) {
		
			try {
				if(arg1.getText().equals("") || arg2.getText().equals("")) {
					warning.setVisible(true);
					warning.setText("Please add input.");
				} else if(Integer.parseInt(arg1.getText())+Integer.parseInt(arg2.getText()) > 99 
						|| Integer.parseInt(arg1.getText())+Integer.parseInt(arg2.getText()) < 1) {
					warning.setVisible(true);
					warning.setText("Answer out of range.");
				} else {
					warning.setVisible(false);
					newGame.add(Integer.parseInt(arg1.getText()), Integer.parseInt(arg2.getText()));
					arg1.clear();
					arg2.clear();
					qNum.setText("Question: "+(newGame.size()+1)+"/10");
				}
				
			} catch(NumberFormatException e) {
				warning.setVisible(true);
				warning.setText("Please enter numbers.");
			}
		
		
		if(newGame.size() == 10) {
			try {
				ListController.games.add(newGame);
				Parent pane;
				pane = FXMLLoader.load(getClass().getResource("InitialScreen.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				stage.setScene(scene);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
		
		public void setName(ActionEvent ae) {
			
			if(ListController.items.contains(enterName.getText())) {
				warning.setVisible(true);
				warning.setText("That name is already in use.");
			} else {
				submit1.setDefaultButton(true);  // Button can be activated with enter key
				newGame.setName(enterName.getText());
				warning.setVisible(false);
				enterName.setVisible(false);
				submit1.setVisible(true);
				submit2.setVisible(false);
				arg1.setVisible(true);
				arg2.setVisible(true);
				plus.setVisible(true);
				qNum.setVisible(true);
				title.setText("Enter numbers which add to between 1 and 99:");
				qNum.setText("Question: "+(newGame.size()+1)+"/10");
			}
			
		}
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			title.setText("Enter a name for this list:");
			submit1.setVisible(false);
			submit2.setDefaultButton(true); // Button can be activated with enter key
			newGame = new Game();
		}
}
