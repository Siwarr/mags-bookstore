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
import javafx.scene.control.TextArea;
import metier.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AfficherCategorieController implements Initializable {
    @FXML
    private TextArea txtaria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getAll(ActionEvent event) {
        
        ServiceCategorie SC= new ServiceCategorie();
        txtaria.setText(SC.getAll().toString());
        
    }
    
}
