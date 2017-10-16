package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ListController implements Initializable {
	
	public ObservableList<String> items = FXCollections.observableArrayList("Test 1","Test 2", "Test 3");
	@FXML
	public ListView<String> QuestionList = new ListView<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		QuestionList.setItems(items);
	}
	
	
}
