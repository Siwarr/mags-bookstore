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
import javafx.scene.control.TextField;
import metier.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ChercherCategorieController implements Initializable {
    @FXML
    private TextField idlabel;
    @FXML
    private TextArea areaaffiche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void findCategorieById(ActionEvent event)
    {
        ServiceCategorie SC= new ServiceCategorie();
        Categorie p =new Categorie();
        
        areaaffiche.setText(SC.findCategorieById(Integer.parseInt(idlabel.getText())).toString());
        
    }
    
}
