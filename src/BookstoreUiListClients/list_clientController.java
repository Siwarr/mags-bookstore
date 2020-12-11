package BookstoreUiListClients;

import java.net.URL;
import java.util.ResourceBundle;

import bookstore.service.ServiceClient;
import bookstore.service.ServiceOuvrage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class list_clientController implements Initializable {
	@FXML
	private AnchorPane rootPane;
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn nameCol;
	@FXML
	private TableColumn idCol;
	@FXML
	private TableColumn telCol;
	@FXML
	private TableColumn emailCol;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initCol();
		loadData();
	}
	private void initCol() {
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
	}

	private void loadData() {
		ServiceClient so = new ServiceClient();
		tableView.getItems().setAll(so.afficherClients());
	}

}
