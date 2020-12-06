package bookstoreUiAddMembre;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import bookstore.entites.Ouvrage;
import bookstore.service.ServiceOuvrage;
import javafx.event.ActionEvent;

public class membre_addController {
	@FXML
	private TextField name;
	@FXML
	private TextField id;
	@FXML
	private TextField tel;
	@FXML
	private TextField email;
	@FXML
	private Button save;
	@FXML
	private Button cancel;

	// Event Listener on Button[#save].onAction
	@FXML
	public void addMember(ActionEvent event) {
		// TODO Autogenerated
		String mName=name.getText();
		String mid=id.getText();
		String mtelephone=tel.getText();
		String memail=email.getText();
		Boolean mflag=mName.isEmpty()||mid.isEmpty()||mtelephone.isEmpty()||memail.isEmpty();
		if(mflag) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("entrer tous les champs");
			alert.showAndWait();
			return;
		}
		String req="Insert into client values ("
				+ "'"+mName+"',"
				+ "'"+mtelephone+"',"
				+ "'"+memail+"',";
		System.out.println(req);
		/*
		try {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req= "insert into ouvrage (author,name,price,edition,isAvail,quantite,description) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, o.getAuthor());
            ps.setString(2, o.getName());
            ps.setFloat(3, o.getPrice());
            ps.setString(4, o.getEdition());
            ps.setBoolean(5, o.getIsAvail());
            ps.setInt(6, o.getQuantite());
            ps.setString(7, o.getDescription());
            ps.executeUpdate();
            */
	}
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void cancel(ActionEvent event) {
		// TODO Autogenerated
	}
}
