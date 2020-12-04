/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMain;

import bookstore_v1.BookStore_V1;
import bookstore_v1.FXMLDocumentController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Admin;
import entities.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import service.AdminAuthentifie;
import service.ClientAuthentifie;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Jendli
 */
public class FXMLController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private Button bt_reclamation;
    @FXML
    private Button bt_minimize;
    @FXML
    private Button bt_close;
    @FXML
    private Button bars;
    @FXML
    private Button chevronleft;
    @FXML
    private AnchorPane panelSide;
    @FXML
    private Button bt_reclamationAdmin;
    @FXML
    private FontAwesomeIconView bt_signout;
    @FXML
    private Button bt_home;
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Parent home=FXMLLoader.load(getClass().getResource("/guiHome/Home.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(home);
            bars.setVisible(false);
            chevronleft.setVisible(true);
            ClientAuthentifie c1= ClientAuthentifie.getInstance();
            Client client=c1.getClient();    
            AdminAuthentifie a1= AdminAuthentifie.getInstance();
            Admin admin=a1.getAdmin();
            if(client==null)
            {
                bt_reclamation.setVisible(false);
            }
            if(admin==null)
            {
                bt_reclamationAdmin.setVisible(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ongletReclamation(MouseEvent event)throws IOException {
           Parent ongletReclamation=FXMLLoader.load(getClass().getResource("/guiReclamation/Reclamation.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(ongletReclamation);
    }

    @FXML
    private void minimize(MouseEvent event) {
        BookStore_V1.stage.setIconified(true);
    }

    @FXML
    private void close(MouseEvent event) {
         System.exit(0);
    }

    @FXML
    private void openPanel(MouseEvent event) {
        
        TranslateTransition slide=new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(panelSide);
        
        slide.setToX(170);
        slide.play();
        panelSide.setVisible(false);
        //panelSide.setTranslateX(-230);
        slide.setOnFinished((ActionEvent e) -> {
            bars.setVisible(false);
            chevronleft.setVisible(true);
        });
    }

    @FXML
    private void closePanel(MouseEvent event) {
        
        TranslateTransition slide=new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(panelSide);
        
        slide.setToX(0);
        panelSide.setVisible(true);
        slide.play();
        //panelSide.setTranslateX(-230);
        slide.setOnFinished((ActionEvent e) -> {
            bars.setVisible(true);
            chevronleft.setVisible(false );
        });
    }

    private void minimize(ActionEvent event) {
         BookStore_V1.stage.setIconified(true);
    }

    @FXML
    private void ongletReclamationAdmin(MouseEvent event) throws IOException {
        Parent ongletReclamationAdmin=FXMLLoader.load(getClass().getResource("/guiReclamation/Admin_Reclamation.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(ongletReclamationAdmin);

    }

    @FXML
    private void signOut(MouseEvent event) throws IOException {
         ClientAuthentifie clientAuthentifie= ClientAuthentifie.getInstance();
         Client client=clientAuthentifie.getClient();
         AdminAuthentifie ad= AdminAuthentifie.getInstance();
         Admin admin=ad.getAdmin();
         TrayNotification tray= new TrayNotification();
         if(client!=null)
         {   
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Successful Logout");
            tray.setMessage("Come Back Soon "+client.getLogin().toUpperCase());
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.showAndDismiss(Duration.millis(3000));
             
         }
         if(admin!=null)
         {
             AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Successful Logout");
            tray.setMessage("Come Back Soon "+admin.getLogin().toUpperCase());
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.showAndDismiss(Duration.millis(3000));
         }
        clientAuthentifie.setClient(null);
        ad.setAdmin(null);
        Parent root = FXMLLoader.load(getClass().getResource("/bookstore_v1/Login.fxml"));
        BookStore_V1.stage.getScene().setRoot(root);
    }

    @FXML
    private void goToHome(MouseEvent event) throws IOException {
        /*Parent mainMenu=FXMLLoader.load(getClass().getResource("/guiMain/MainMenu.fxml"));
        BookStore_V1.stage.getScene().setRoot(mainMenu);*/
         Parent home=FXMLLoader.load(getClass().getResource("/guiHome/Home.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(home);
    }
    
}
