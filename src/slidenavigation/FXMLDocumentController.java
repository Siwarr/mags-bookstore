/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slidenavigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) throws IOException  {
        
        Parent root=FXMLLoader.load(getClass().getResource("/slidenavigation/FXMLDocument.fxml"))  ; 
           SlideNavigation.stage.getScene().setRoot(root);
    }

    @FXML
    private void AjouterCategorie(MouseEvent event) throws IOException {
         LoadPage("/slidenavigation/AjouterCategorie.fxml");
    }

    @FXML
    private void SupprimerCategorie(MouseEvent event) throws IOException {
        LoadPage("/slidenavigation/SupprimerCategorie.fxml");
      
    }
    

    @FXML
    private void ModifierCategorie(MouseEvent event) throws IOException {
         LoadPage("/slidenavigation/ModifierCategorie.fxml");
    }

    @FXML
    private void AfficherCategorie(MouseEvent event) throws IOException {
         LoadPage("/slidenavigation/AfficherCategorie.fxml");
    }

    @FXML
    private void ChercherCategorie(MouseEvent event) throws IOException {
         LoadPage("/slidenavigation/chercherCategorie.fxml");
    }
    
    
     private void LoadPage(String page) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource(page))  ; 
         ap.getChildren().removeAll();
         ap.getChildren().setAll(root);
    }

    @FXML
    private void Statistique(ActionEvent event) {
    }
    
    
}
