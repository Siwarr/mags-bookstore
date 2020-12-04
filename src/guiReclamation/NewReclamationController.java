/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiReclamation;

import entities.Client;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ClientAuthentifie;
import service.ServiceReclamation;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Jendli
 */
public class NewReclamationController implements Initializable {

    @FXML
    private Button addreclam;
    @FXML
    private TextField lb_titre;
    @FXML
    private TextField lb_des;
    @FXML
    private AnchorPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddReclam(MouseEvent event) throws IOException {
        ServiceReclamation sr=new ServiceReclamation();
        Reclamation r=new Reclamation();
        if(lb_titre.getText().isEmpty()||lb_des.getText().isEmpty())
        {TrayNotification tray=new TrayNotification();
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Failed");
            tray.setMessage("Please Verify the fields");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));}
        else{
        r.setTitre(lb_titre.getText());
        r.setDescription(lb_des.getText());
        ClientAuthentifie cl=ClientAuthentifie.getInstance();
        Client c=cl.getClient();
        sr.ajouterReclamation(r, c);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Ajouter la réclamation?");
            Optional<ButtonType> answer= alert.showAndWait();
            if(answer.get()==ButtonType.OK)
             {
             TrayNotification tray=new TrayNotification();
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Reclamation sent");
            tray.setMessage("Our Team will look about you Reclamation ''"+lb_titre.getText()+"'' ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            Parent ongletReclamation=FXMLLoader.load(getClass().getResource("/guiReclamation/Reclamation.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(ongletReclamation);
             
            }
            
            //methode1
            /*Stage stage=(Stage)content.getScene().getWindow();
            stage.close();*/
           
            
        }
    }
    
    void cls(){
        lb_titre.setText("");
        lb_des.setText("");
    }
    
}
