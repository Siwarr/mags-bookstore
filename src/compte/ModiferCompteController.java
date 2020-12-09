package compte;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import magsbookstore.Service.ServiceCompteClient;
import magsbookstore.entite.Client
        ;

public class ModiferCompteController {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField ville;

    @FXML
    private TextField email;

    @FXML
    private TextField mdp;

    @FXML
    private Button save;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'modiferCompte.fxml'.";
        assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'modiferCompte.fxml'.";
        assert ville != null : "fx:id=\"ville\" was not injected: check your FXML file 'modiferCompte.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'modiferCompte.fxml'.";
        assert mdp != null : "fx:id=\"mdp\" was not injected: check your FXML file 'modiferCompte.fxml'.";
        assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'modiferCompte.fxml'.";

    }

    @FXML
    void savebd(MouseEvent event) {
        ServiceCompteClient sc = new ServiceCompteClient();
        String x="00";
        sc.modifiercompte(nom.getText(), prenom.getText(), ville.getText(),mdp.getText(), x );

    }
    
    public void setf (Client c){
        nom.setText(c.getNom());
        prenom.setText(c.getPrenom());
        mdp.setText(c.getPwd());
        ville.setText(c.getVille());
    }
    


}
