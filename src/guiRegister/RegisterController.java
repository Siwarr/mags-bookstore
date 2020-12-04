/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiRegister;

import bookstore_v1.BookStore_V1;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
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
import service.ClientAuthentifie;
import service.ServiceLogin_Register;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Jendli
 */
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
            TrayNotification tray=new TrayNotification();
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Failed");
            tray.setMessage("Please Verify the fields");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        }
        else{
        client.setLogin(username.getText());
        client.setNom(txt_lastname.getText());
        client.setMail(mail.getText());
        client.setMdp(pwd.getText());
        rg.registerClient(client);
        Client c2=rg.seConnecter(username.getText(),pwd.getText());
        if(c2!=null){
            TrayNotification tray=new TrayNotification();
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Successful Registration");
            tray.setMessage("Welcome to Mags BookStore "+username.getText().toUpperCase()+",You May now Login");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            Parent root = FXMLLoader.load(getClass().getResource("/bookstore_v1/Login.fxml"));
            BookStore_V1.stage.getScene().setRoot(root);
           }
        }
    }

    @FXML
    private void backLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bookstore_v1/Login.fxml"));
        BookStore_V1.stage.getScene().setRoot(root);
    }
    
} 
