/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compte;

import java.sql.Date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import magsbookstore.Service.ServiceCompte;
import magsbookstore.Service.ServiceCompteClient;
import magsbookstore.entite.Client;
import magsbookstore.technique.myconnexion;

/**
 *
 * @author ghaith khiari
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TableColumn<Client, String> nom;
    @FXML
    private TableColumn<Client, String> pre;
    @FXML
    private TableColumn<Client, String> mail;
    @FXML
    private TableColumn<Client, Long> num;
    @FXML
    private TableColumn<Client, String> ville;
   
    @FXML
    private TableColumn<Client, Date> datee;
    @FXML
    private TableView<Client> table;
   

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date(System.currentTimeMillis());
String dt = formatter.format(date);

      ObservableList<Client> list = FXCollections.observableArrayList(new Client("ghaith","khiari", "ville", 8100 , 99676342 , dt, "07992678", "gkhiari6@gmail.com","555")
             
    );
      Client c1 =new Client("ghaith","khiari", "ville", 8100 , 99676342 , dt, "07992678", "gkhiari6@gmail.com","555");
    @FXML
    private TableColumn<Client, String> cin;
    
  
 @FXML
    private void supprimer (ActionEvent event) {
        ServiceCompteClient sc =new ServiceCompteClient();
               sc.supprimercompte(c1);
                
       exit();
        
    }
     
        
    
       
                
       
        
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
           mail.setCellValueFactory(new PropertyValueFactory<>("email"));
            num.setCellValueFactory(new PropertyValueFactory<>("num_de_telf"));
             ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
             datee.setCellValueFactory(new PropertyValueFactory<>("date_inscri"));
             cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
             table.setItems(list);
            
             
    }    

    @FXML
    private void modifier(Event event) throws IOException {
        Client c =table.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/compte/modifCompte.fxml"));
        Parent modifcompte=loader.load();
            
            ModifCompteController controller=(ModifCompteController)loader.getController();
            controller.setf(c);
           Stage stage = new Stage();
           stage.setScene(new Scene(modifcompte));
           stage.show();
    }
    
    }
        
    

