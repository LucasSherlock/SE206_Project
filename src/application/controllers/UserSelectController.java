package application.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.DataFile;
import application.models.LocalRepo;
import application.models.User;
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		LocalRepo repo = new LocalRepo();
		users = repo.getAllUsers();
		
		// Take list of users and add names to observable list so they are displayed in the GUI
		for(User user : users) {
			usernames.add(user.getUsername());
		}
		
		// Adds the list of names to the list component
		userList.setItems(usernames);
	}
	
	/*
	 * Returns the selected user or null
	 */
	public User getSelectedUser() {
		if(userList.getSelectionModel().isEmpty()) {
			return null;
		}
		else {
			String username = userList.getSelectionModel().getSelectedItem();
			return findUser(username);
		}
	}
	
	
	/*
	 * Called when the create user button is activated
	 */
	public void createUser() {
		userList.setVisible(false);
		instructions.setVisible(true);	
		input.setVisible(true);
		
		confirm.setText("Confirm Name");	
	}
	
	/*
	 * Called when the delete user button is activated
	 */
	public void deleteUser() {
		User user;
		
		if ((user = getSelectedUser()) != null) {	
			if (user.deleteUser()) {
				// User was successfully deleted from the Repo
				users.remove(user);
				usernames.remove(user.getUsername());
			}
			else {
				// User could not be deleted from the Repo
				errorPopup("Unable to delete user.");
			}
		}
	}
	
	/*
	 * Called when the confirm user button is activated. Navigates to the play options menu.
	 */
	public void select(ActionEvent ae) {
		if(userList.isVisible()) {
			if(userList.getSelectionModel().isEmpty()) {
				// No user is selected
				errorPopup("Select a user.");
			} else {
				// Set current user to selected user
				DataFile.user = getSelectedUser();
		
				// go to select screen
				try {
					Parent pane = FXMLLoader.load(getClass().getResource("../Select.fxml"));
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
