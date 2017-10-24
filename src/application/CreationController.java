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

/*
 * This controller is responsible for handling the creation of question lists.
 */
public class CreationController implements Initializable {
	public Game newGame;
	
	@FXML
	public Button submitQuestion;
	public Button submitListName;
	public TextField lhsNumber; // LHS = left hand side
	public TextField rhsNumber; // RHS = right hand side
	public TextField listName;
	public Label warning;
	public Label qNum;
	public Label plus;
	public Label title;
	
	private static final int finalListLength = 10;  
	
	/*
	 * This method is triggered when a user submits a question for inclusion into the list.
	 */
	public void addQuestion(ActionEvent ae) {
		// Hides any prior warnings
		warning.setVisible(false);
		
		try {
			if (lhsNumber.getText().equals("") || rhsNumber.getText().equals("")) {
				// Input is non-existent
				warning.setVisible(true);
				warning.setText("Please add input.");
			}
			else if (Integer.parseInt(lhsNumber.getText()) + Integer.parseInt(rhsNumber.getText()) > 99 
					|| Integer.parseInt(lhsNumber.getText()) + Integer.parseInt(rhsNumber.getText()) < 1) {
				// Integer is not in range 1-99
				warning.setVisible(true);
				warning.setText("Answer out of range.");
			}
			else {				
				// Pushes the question onto the questions list
				newGame.add(Integer.parseInt(lhsNumber.getText()), Integer.parseInt(rhsNumber.getText()));
				
				// Input validations are successful
				lhsNumber.clear();
				rhsNumber.clear();
				
				// Increments the counter/10
				qNum.setText("Question: "+(newGame.size()+1)+"/10");
			}
		}
		catch(NumberFormatException e) {
			System.out.println(e);
			// Integer failed to parse
			warning.setVisible(true);
			warning.setText("Please enter numbers.");
		}
		
		// Exits the creation view if the required number of questions have been added
		if(newGame.size() == finalListLength) {
			try {
				// Updates the list view to include the newly created game
				ListController.games.add(newGame);
				
				// Returns the user back to the list view
				Parent pane = FXMLLoader.load(getClass().getResource("InitialScreen.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
				stage.setScene(scene);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * This method sets the name for a new list. If the name is valid the list creation process is initiated
	 */
	public void setName(ActionEvent ae) {	
		if(ListController.items.contains(listName.getText())) {
			// List with the given name already exists
			warning.setVisible(true);
			warning.setText("That name is already in use.");
		}
		else {
			// List creation process initiates
			newGame.setName(listName.getText());
			
			// Show and hide the relevant elements
			warning.setVisible(false);
			listName.setVisible(false);
			submitQuestion.setVisible(true);
			submitListName.setVisible(false);
			lhsNumber.setVisible(true);
			rhsNumber.setVisible(true);
			plus.setVisible(true);
			qNum.setVisible(true);
			
			title.setText("Enter numbers which add to between 1 and 99:");
			qNum.setText("Question: "+(newGame.size()+1)+"/10");
			
			// Submit button can be activated with enter key
			submitQuestion.setDefaultButton(true);
		}		
	}
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Instantiate a new game object
		newGame = new Game();
		
		// Show the relevant elements
		title.setText("Enter a name for this list:");
		submitQuestion.setVisible(false);
		
		// Submit button can be activated with enter key
		submitListName.setDefaultButton(true);
	}
}
