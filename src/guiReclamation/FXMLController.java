/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiReclamation;

import bookstore_v1.FXMLDocumentController;
import entities.Client;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ClientAuthentifie;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Jendli
 */
public class FXMLController implements Initializable {
    
    
    @FXML
    private TableColumn<Reclamation, String> codeCol;
    @FXML
    private TableColumn<Reclamation, String> TitreCol;
    @FXML
    private TableColumn<Reclamation, String> DescCol;
    @FXML
    private TableColumn<Reclamation, Date> DateCol;
    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private AnchorPane content;
    @FXML
    private TextField txt_search;
    @FXML
    private Button addRec;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        load();
        search();
        
    }    
    private void initCol(){ 
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        TitreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));
        TitreCol.setStyle("-fx-alignment: CENTER"); 
        DescCol.setStyle("-fx-alignment: CENTER");
        DateCol.setStyle("-fx-alignment: CENTER");
        codeCol.setStyle("-fx-alignment: CENTER");
        
    }
    private void load(){
        ClientAuthentifie cl=ClientAuthentifie.getInstance();
        Client c=cl.getClient();
        ServiceReclamation rec=new ServiceReclamation();
        tableview.getItems().setAll(rec.afficherMesReclamations(c));
    }
    private void search(){
        ClientAuthentifie cl=ClientAuthentifie.getInstance();
        Client c=cl.getClient();
        ServiceReclamation rec=new ServiceReclamation();
        ObservableList<Reclamation>list=FXCollections.observableArrayList(rec.afficherMesReclamations(c));
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
    
        
    
    @FXML
    private void AddReclamation(MouseEvent event) throws IOException {
        //open in new frame
        /*Parent newrec=FXMLLoader.load(getClass().getResource("/guiReclamation/NewReclamation.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("ajouter reclamation");
        stage.setScene(new Scene(newrec));
        stage.show();*/
        Parent addReclamation=FXMLLoader.load(getClass().getResource("/guiReclamation/NewReclamation.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(addReclamation);
        
        
    }
    
}
