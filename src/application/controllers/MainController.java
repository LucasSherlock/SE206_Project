package application.controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class MainController {

	@FXML
	public Button back;
	public Button showInstruc;
	public Label instructions;
	public Label title;
	public Label instrucTitle;
	public SplitPane buttons;
	

	/*
	 * Transitions to the user select view
	 */
	public void start(ActionEvent event) throws Exception {
		Parent pane = FXMLLoader.load(getClass().getResource("../UserSelect.fxml"));
		Scene scene = new Scene(pane);

		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		stage.setHeight(400);
		stage.setWidth(300);
		stage.setTitle("Select a user");

		stage.setScene(scene);
	}


	public void instructions(ActionEvent ae) {
		if(ae.getSource() == showInstruc) {
			title.setVisible(false);
			buttons.setVisible(false);
			instrucTitle.setVisible(true);
			instructions.setVisible(true);
			back.setVisible(true);
		} else if(ae.getSource() == back) {
			title.setVisible(true);
			buttons.setVisible(true);
			instrucTitle.setVisible(false);
			instructions.setVisible(false);
			back.setVisible(false);
		}
	}

}
