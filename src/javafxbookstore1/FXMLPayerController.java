/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxbookstore1;

import bookstore.stripe.payement.ServicePayement;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class FXMLPayerController implements Initializable {

    ServicePayement sp=new ServicePayement();
    @FXML
    private TextField tfGetTotal1;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void effectuePayement(ActionEvent e) throws StripeException {
        Customer a = Customer.retrieve("cus_IXizF2m8Lj3Wwi");//current customerId after integration
        sp.Payement(a,"5000");//total PAnier Prices(sommme)
        
        //close current stage
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    
}
