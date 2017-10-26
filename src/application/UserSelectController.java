package application;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/*
 * Controller class for user menu
 */
public class UserSelectController implements Initializable {	
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
			// when selecting user
			if(userList.getSelectionModel().isEmpty()) {
				// nothing is selected
				errorPopup("Select a user.");
			} else {
				// set current user to selected user
				DataFile.user = findUser(userList.getSelectionModel().getSelectedItem());
		
				// go to select screen
				try {
					Parent pane;
					pane = FXMLLoader.load(getClass().getResource("SelectScreen.fxml"));
					Scene scene = new Scene(pane);
					Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow(); 
					stage.setScene(scene);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
	
		} else {
			// When confirming new username	
			if(User.userExists(input.getText())) {
				// Name already exists
				errorPopup("Name exists, enter new name.");
				input.clear();
			} else {
				User newUser = User.getUser(input.getText());

				users.add(newUser);
				usernames.add(newUser.getUsername());
				
				// Hide user list and show text field to input name
				userList.setVisible(true);
				instructions.setVisible(false);
				input.setVisible(false);
				confirm.setText("Confirm Selection");
			}
		}
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		Store store = new Store("users");
		users = store.deserializeAllUsers();
		
		//take list of users and add names to observable list so they are displayed in the GUI
		for(User user : users) {
			usernames.add(user.getUsername());
		}
		
		userList.setItems(usernames); //make list display the users
	}
	
	/*
	 * multi-use error popup
	 */
	public void errorPopup(String message) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Error");
		error.setContentText(message);
		error.showAndWait();
	}
	
	
	/*
	 * given the name of a user (selected from list), get the user object. 
	 */
	public User findUser(String username) {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null; //should be unreachable	
	}
}
