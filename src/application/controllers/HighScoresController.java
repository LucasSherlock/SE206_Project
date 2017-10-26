package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.DataFile;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class HighScoresController implements Initializable {

	@FXML
	public TableView<String> easyTable;
	public TableView<String> hardTable;
	public TableColumn<?, ?> easyColumn;

	public void backToMenu(ActionEvent event) throws Exception{

		Parent pane = FXMLLoader.load( getClass().getResource("../Select.fxml"));
		Scene scene = new Scene( pane);

		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		stage.setScene(scene);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		setEasyScores();
		setHardScores();

	}
	private void setEasyScores(){

		final ObservableList<String> data = FXCollections.observableArrayList(DataFile.easyScores);
		easyTable.setPlaceholder(new Label("No Scores"));
		easyTable.setEditable(true);
		easyTable.setItems(data);
		TableColumn<String, String> tc = new TableColumn<>("Scores - Easy ");
		tc.setPrefWidth(120);
		tc.setCellValueFactory((p) -> {
			return new ReadOnlyStringWrapper(p.getValue());
		});
		easyTable.getColumns().add(tc);


	}
	private void setHardScores(){

		
		final ObservableList<String> data = FXCollections.observableArrayList(DataFile.hardScores);
		hardTable.setPlaceholder(new Label("No Scores"));
		hardTable.setEditable(true);
		hardTable.setItems(data);
		TableColumn<String, String> tc = new TableColumn<>("Scores - Hard ");
		
		tc.setPrefWidth(120);
		tc.setCellValueFactory((p) -> {
			return new ReadOnlyStringWrapper(p.getValue());
		});
		hardTable.getColumns().add(tc);

	}


}





