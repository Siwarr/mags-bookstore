package bookstoreuiregister;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bookstore.entites.Client;
import bookstore.service.ServiceLogin_Register;
import bookstore_v1.bookstore_v1;
import javafx.event.ActionEvent;

public class RegisterController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button register;
    @FXML
    private Button arrow_back;
    @FXML
    private AnchorPane registerParent;
    @FXML
    private TextField mail;
    @FXML
    private TextField txt_lastname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void register(MouseEvent event) throws IOException {
        ServiceLogin_Register rg=new ServiceLogin_Register();
        Client client=new Client();
        if(username.getText().isEmpty()||txt_lastname.getText().isEmpty()||mail.getText().isEmpty()||pwd.getText().isEmpty())
        {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
  			alert.setHeaderText(null);
  			alert.setContentText("Please Verify the fields");
  			alert.showAndWait();
  			return;
        }
        else{
        client.setLogin(username.getText());
        client.setNom(txt_lastname.getText());
        client.setMail(mail.getText());
        client.setMdp(pwd.getText());
        rg.registerClient(client);
        Client c2=rg.seConnecter(username.getText(),pwd.getText());
        if(c2!=null){
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
  			alert.setHeaderText(null);
  			alert.setContentText("Successful Registration");
  			alert.showAndWait();
  			
            Parent root = FXMLLoader.load(getClass().getResource("/bookstore_v1/Bookstorelogin.fxml"));
            bookstore_v1.stage.getScene().setRoot(root);
            return;
           }
        }
    }

    @FXML
    private void backLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bookstore_v1/Bookstorelogin.fxml"));
        bookstore_v1.stage.getScene().setRoot(root);
    }
}
