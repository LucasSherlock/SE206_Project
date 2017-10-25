package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/*
 * Controller class for user menu
 */
public class UserController implements Initializable {

	
	public ArrayList<User> users; 
	public ObservableList<String> usernames = FXCollections.observableArrayList();
	
	@FXML
	public Button create;
	public Button delete;
	public Button confirm;
	public ListView<String> userList;
	public Label instructions;
	public TextField input;
	
	
	/*
	 * called when create user button is pressed
	 */
	public void createUser() {
		//hide user list and show text field to input name
		userList.setVisible(false);
		instructions.setVisible(true);
		input.setVisible(true);
		confirm.setText("Confirm Name");
		
	}
	
	/*
	 * called when confirm button is pressed.
	 */
	public void select(ActionEvent ae) {
		if(userList.isVisible()) {
			//when selecting user
			//TODO go to next screen and set user
		} else {
			//when confirming new username
			
			if(usernames.contains(input.getText())) {
				//if name already exists
				errorPopup("Name exists, enter new name.");
				input.clear();
			} else {
				User newUser = new User(input.getText());
				users.add(newUser);
				usernames.add(newUser.getUsername());
				//hide user list and show text field to input name
				userList.setVisible(true);
				instructions.setVisible(false);
				input.setVisible(false);
				confirm.setText("Confirm Selection");
			}
		}
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//TODO add serialization of users and get here
		
		users = new ArrayList<User>(); // placeholder before serialization
		
		//take list of users and add names to observable list so they are displayed in the GUI
		for(User user : users) {
			usernames.add(user.getUsername());
		}
		
		
		userList.setItems(usernames); //make list display the users
	}
	
	
	public void errorPopup(String message) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Error");
		error.setContentText(message);
		error.showAndWait();
	}
	
	
}
