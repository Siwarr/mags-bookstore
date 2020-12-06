package bookstoreUiAddOuvrage;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import bookstore.entites.Ouvrage;
import bookstore.service.ServiceOuvrage;
import javafx.event.ActionEvent;

public class BookstoreAddController {
	@FXML
	private TextField title;
	@FXML
	private TextField id;
	@FXML
	private TextField author;
	@FXML
	private TextField edition;
	@FXML
	private TextField price;
	@FXML
	private TextField description;
	@FXML
	private TextField isAvail;
	@FXML
	private TextField quantite;
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private BorderPane rootPane;

	// Event Listener on Button[#save].onAction
	@FXML
	public void addBook(ActionEvent event) {
		// TODO Autogenerated

		ServiceOuvrage so = new ServiceOuvrage();
		Ouvrage o1 = new Ouvrage();
		o1.setAuthor(author.getText());
		o1.setName(title.getText());
		o1.setPrice(Float.parseFloat(price.getText()));
		o1.setEdition(edition.getText());
		o1.setIsAvail(true);
		o1.setQuantite(Integer.parseInt(quantite.getText()));
		o1.setDescription(description.getText());

		if (title.getText().isEmpty() || author.getText().isEmpty() || price.getText().isEmpty()
				|| quantite.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Veuillez entrer tous les parametres");
			alert.showAndWait();
			return;
		}
		so.ajouterOuvrage(o1);
	}

	// Event Listener on Button[#l].onAction
	@FXML
	public void cancel(ActionEvent event) {
		// TODO Autogenerated
		Stage stage=(Stage) rootPane.getScene().getWindow();
		stage.close();
		
	}
}
