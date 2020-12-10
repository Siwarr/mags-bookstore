/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slidenavigation;

import entity.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import metier.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SupprimerCategorieController implements Initializable {
    @FXML
    private TextField idSupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deleteCategorie(ActionEvent event) {
         
         ServiceCategorie SC= new ServiceCategorie();
        Categorie p =new Categorie();
        p.setDescription(idSupp.getText());
        SC.deleteCategorie(Integer.parseInt(idSupp.getText()));
               
    }
    
}
