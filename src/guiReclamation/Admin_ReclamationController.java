/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiReclamation;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.Pair;
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
public class Admin_ReclamationController implements Initializable {

    @FXML
    private TableColumn<Reclamation, String> codeCol;
    @FXML
    private TableColumn<Reclamation, String> TitreCol;
    @FXML
    private TableColumn<Reclamation, String> DescCol;
    @FXML
    private TableColumn<Reclamation, Date> DateCol;
    @FXML
    private TableColumn<Reclamation, String> NameCol;
    @FXML
    private TableColumn<Reclamation, String> MailClientCol;
    @FXML
    private TableColumn<Reclamation, String> RepCol;
    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private AnchorPane content;
    @FXML
    private TextField txt_search;
   
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        load();
        search();
        answered();
    }    
    private void initCol(){ 
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        TitreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
        MailClientCol.setCellValueFactory(new PropertyValueFactory<>("mailclient"));
        RepCol.setCellValueFactory(new PropertyValueFactory<>("answer"));
        
        codeCol.setStyle("-fx-alignment: CENTER");
        TitreCol.setStyle("-fx-alignment: CENTER"); 
        DescCol.setStyle("-fx-alignment: CENTER");
        DateCol.setStyle("-fx-alignment: CENTER");
        NameCol.setStyle("-fx-alignment: CENTER");
        MailClientCol.setStyle("-fx-alignment: CENTER");
        RepCol.setStyle("-fx-alignment: CENTER");
        
        
    }
    private void load(){
        ServiceReclamation rec=new ServiceReclamation(); 
        tableview.getItems().setAll(rec.afficherListReclamations());
        
    }
                
    private void search(){
        ServiceReclamation rec=new ServiceReclamation();
        ObservableList<Reclamation>list=FXCollections.observableArrayList(rec.afficherListReclamations());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Reclamation> filteredData = new FilteredList<>(list, x -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(r -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (r.getCode().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (r.getTitre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (r.getDescription().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (r.getNomclient().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (r.getMailclient().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (r.getDateReclamation().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                
				return false; // Does not match.
                                
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
	}
    
    public void answered(){
    tableview.setRowFactory(tv -> new TableRow<Reclamation>() {
    @Override
    protected void updateItem(Reclamation item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null)
            setStyle("");
        else if (item.getAnswer().equals("YES"))
            setStyle("-fx-background-color: #baffba;");
        else
            setStyle("");
           }
       });
    }
    @FXML
    private void deleteReclamation(ActionEvent event) throws IOException {
        ServiceReclamation rec=new ServiceReclamation(); 
        Reclamation r=tableview.getSelectionModel().getSelectedItem();
        if(r==null)
        {
            TrayNotification tray=new TrayNotification();
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Failed");
            tray.setMessage("Please Select a Reclamation");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        }
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Delete Reclamation");
            Optional<ButtonType> answer= alert.showAndWait();
            if(answer.get()==ButtonType.OK)
             {
               rec.supprimerReclamation(r.getCode());
             TrayNotification tray=new TrayNotification();
             AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Reclamation deleted");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            Parent ongletReclamation=FXMLLoader.load(getClass().getResource("/guiReclamation/Admin_Reclamation.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(ongletReclamation);
        }
    }

    @FXML
    private void replyReclamation(ActionEvent event) throws IOException {
       Reclamation r=tableview.getSelectionModel().getSelectedItem();
        if(r==null)
        {
            TrayNotification tray=new TrayNotification();
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Failed");
            tray.setMessage("Please Select a Reclamation");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        }
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/guiReclamation/SendMail_Reclamation.fxml"));
        Parent mailReclamation=loader.load();
            
            SendMail_ReclamationController controller=(SendMail_ReclamationController)loader.getController();
            controller.setFields(r);
            content.getChildren().removeAll();
            content.getChildren().setAll(mailReclamation);
    }
    
        
    
    
}
