/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore_v1;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Admin;
import entities.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;
import javafx.util.Duration;
import service.AdminAuthentifie;


import service.ClientAuthentifie;
import service.ServiceLogin_Register;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Jendli
 */
public class FXMLDocumentController implements Initializable {
    
    private double xOffset=0;
    private double yOffset=0;
    
    private Label label;
    @FXML
    private Button login;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pwd;
    @FXML
    private Pane content;
    @FXML
    private BorderPane parent;
    @FXML
    private Hyperlink register;
    @FXML
    private AnchorPane loginParent;
    @FXML
    private Button bt_close;
    @FXML
    private Button bt_minimize;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        makeStageDragable();
    }    

   
    private void makeStageDragable(){
        parent.setOnMousePressed((event) -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        parent.setOnMouseDragged((event) -> {
             BookStore_V1.stage.setX(event.getScreenX()-xOffset);
             BookStore_V1.stage.setY(event.getScreenY()-yOffset);
             BookStore_V1.stage.setOpacity(0.8f);
        });
        parent.setOnDragDone((event) -> {
             BookStore_V1.stage.setOpacity(1.0f);
             
        });
        parent.setOnMouseReleased((event) -> {
             BookStore_V1.stage.setOpacity(1.0f);
             
        });
      }
    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(MouseEvent event) {
        BookStore_V1.stage.setIconified(true);
    }
    

    @FXML
    private void connecter(MouseEvent event) throws IOException{
        TrayNotification tray= new TrayNotification();
        ServiceLogin_Register log=new ServiceLogin_Register();
        Client client=log.seConnecter(username.getText(),pwd.getText());
        Admin admin=log.seConnecterAdmin(username.getText(),pwd.getText());
        if(client!=null){
            /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Successful login");
            alert.show();
            ClientAuthentifie cl=ClientAuthentifie.getInstance();
            cl.setClient(client);
            */
            ClientAuthentifie cl=ClientAuthentifie.getInstance();
            cl.setClient(client);
 
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Successful Login");
            tray.setMessage("Welcome to Mags BookStore "+username.getText().toUpperCase());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
           Parent mainMenu=FXMLLoader.load(getClass().getResource("/guiMain/MainMenu.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(mainMenu);
           /*Scene reclamation=new Scene(rec);
           Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
           window.setScene(reclamation);
           window.show();*/
        }
        else if(admin!=null){
            AdminAuthentifie al=AdminAuthentifie.getInstance();
            al.setAdmin(admin);
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Successful Login");
            tray.setMessage("Welcome to Mags BookStore "+username.getText().toUpperCase());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
           Parent mainMenu=FXMLLoader.load(getClass().getResource("/guiMain/MainMenu.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(mainMenu);
        }
        else{
            AnimationType type=AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Failed to Login");
            tray.setMessage("Please Verify your UserName or Password");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

    @FXML
    private void openRegister(MouseEvent event) throws IOException {
        Parent register=FXMLLoader.load(getClass().getResource("/guiRegister/Register.fxml"));
           loginParent.getChildren().removeAll();
           loginParent.getChildren().setAll(register);
    }

    
         
    
    
}
