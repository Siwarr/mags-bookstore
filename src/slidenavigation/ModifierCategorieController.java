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
public class ModifierCategorieController implements Initializable {
    @FXML
    private TextField libModif;
    @FXML
    private TextField descriptionmodif;
    @FXML
    private TextField idmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) {
        ServiceCategorie SC= new ServiceCategorie();
        Categorie p =new Categorie();
        p.setDescription(descriptionmodif.getText());
        p.setLib(libModif.getText());
        SC.modifier(Integer.parseInt(idmodif.getText()),p);
        
        
    }
    
}
