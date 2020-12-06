package BookstoreUiListOuvrages;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import bookstore.entites.Ouvrage;
import bookstore.service.ServiceOuvrage;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;

public class list_ouvragesController implements Initializable {
	@FXML
	private AnchorPane rootPane;
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn authorCol;
	@FXML
	private TableColumn titleCol;
	@FXML
	private TableColumn priceCol;
	@FXML
	private TableColumn editionCol;
	@FXML
	private TableColumn isAvailCol;
	@FXML
	private TableColumn quantiteCol;
	@FXML
	private TableColumn descriptionCol;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initCol();
		loadData();
	}

	private void initCol() {
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
		isAvailCol.setCellValueFactory(new PropertyValueFactory<>("isAvail"));
		quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
	}

	private void loadData() {
		ServiceOuvrage so = new ServiceOuvrage();
		tableView.getItems().setAll(so.afficher());
	}
	@FXML
	private void handleOuvDeleteOption(ActionEvent event) {
		Ouvrage selectedForDeletion = (Ouvrage) tableView.getSelectionModel().getSelectedItem();
		if(selectedForDeletion == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("aucun ouvrage selectionné");
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppression ouvrage");
		alert.setContentText("Vous voulez supprimer cet ouvrage "+selectedForDeletion.getName()+" ?");
		Optional<ButtonType> answer=alert.showAndWait();
		if(answer.get()==ButtonType.OK) {
			ServiceOuvrage so = new ServiceOuvrage();
			so.supprimerOuvrage(selectedForDeletion);
			so.getOuvrages().remove(selectedForDeletion);
			loadData();
		}else {
			Alert alert2 = new Alert(Alert.AlertType.ERROR);
			alert2.setHeaderText(null);
			alert2.setContentText("suppression annulée");
			alert2.showAndWait();
			return;
		}
	}
}
