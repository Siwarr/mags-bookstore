package bookstoreuiregister;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bookstore.database.Myconnection;
import bookstore.entites.Admin;
import bookstore.entites.Client;
import bookstore.entites.Ouvrage;
import bookstore.service.AuthAdmin;
import bookstore.service.AuthClt;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

public class loginController {
	@FXML
	private TextField emailIdField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button submitButton;

	// Event Listener on Button[#submitButton].onAction
	public Client seConnecter(String email,String pwd) {
		Myconnection cnx=new Myconnection();
		 String emailId = emailIdField.getText();
	     String password = passwordField.getText();
        Client c;
        try {
            String query= "select * from client where email=? and pwd=?";
            Statement s= cnx.getConnection().createStatement();
            PreparedStatement ps = cnx.getConnection().prepareStatement(query);
            ps.setString(1, emailId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {  c=new Client();
               c.setId(rs.getString("id"));
               c.setTel(rs.getString("tel"));
               c.setEmail(rs.getString("email"));
               c.setName(rs.getString("name"));
               c.setPwd(rs.getString("pwd"));
               return c;
            }
            } catch (SQLException ex) {
             System.err.println("Erreur de selection");
             System.out.println(ex.getMessage());
             }
        return null;
    }

    public Admin seConnecterAdmin(String email,String pwd) {
    	Myconnection cnx=new Myconnection();
    	 String emailId = emailIdField.getText();
         String password = passwordField.getText();
        Admin admin;
        try {
            String query= "select * from admin where email=? and pwd=?";
            Statement s= cnx.getConnection().createStatement();
            PreparedStatement ps = cnx.getConnection().prepareStatement(query);
            ps.setString(1, emailId);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {  admin=new Admin();
            	admin.setId(rs.getString("id"));
            	admin.setTel(rs.getString("tel"));
            	admin.setEmail(rs.getString("email"));
            	admin.setName(rs.getString("name"));
            	admin.setPwd(rs.getString("pwd"));
               return admin;
            }
            } catch (SQLException ex) {
             System.err.println("Erreur de selection");
             System.out.println(ex.getMessage());
             }
        return null;  
    }
	
	
	@FXML
	public void login(ActionEvent event) {
		Myconnection cnx=new Myconnection();
		System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());

        if (emailIdField.getText().isEmpty()) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please enter your email id");
            alert.show();
            return;
            
        }
        if (passwordField.getText().isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
  			alert.setHeaderText(null);
  			alert.setContentText("Please enter a password");
  			alert.showAndWait();
  			return;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();
        
		Client clt=seConnecter(emailId,password);
		Admin admin=seConnecterAdmin(emailId,password);
		if(clt!=null){
            loadWindow("/guiMain/mainClient.fxml","Menu");
		}
	    else if(admin!=null){
	        loadWindow("/guiMain/main.fxml","Menu");
	    }
	    else{
	    	Alert alert=new Alert(Alert.AlertType.INFORMATION);
	        alert.setHeaderText(null);
	        alert.setContentText("Please enter correct Email and Password");
	        alert.show();
	    }
		Window owner = submitButton.getScene().getWindow();
		Stage stage=(Stage) owner.getScene().getWindow();
		stage.close();
	}
	void loadWindow(String loc,String title) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
