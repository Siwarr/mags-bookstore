/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxbookstore1;

import bookstore.stripe.payement.ServicePayement;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class FXMLCarteController implements Initializable {

    @FXML
    private TextField tfNumCarte;
    @FXML
    private TextField tfMonth;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfCvc;
    
    ServicePayement sp=new ServicePayement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutCarte(ActionEvent event) throws StripeException, IOException {
        Customer a = Customer.retrieve("cus_IXizF2m8Lj3Wwi");//current customerId after integration
        sp.AjoutCard(a, tfNumCarte.getText(), tfMonth.getText(), tfYear.getText(), tfCvc.getText());

        
        //Switch to FXMLPayement
        Parent homePage=FXMLLoader.load(getClass().getResource("FXMLPayer.fxml"));
        Scene homePageScene =new Scene(homePage);
        Stage appStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
        

    }
    
}
