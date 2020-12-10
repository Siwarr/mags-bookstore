/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxbookstore1;

import bookstore.entity.Basket;
import bookstore.entity.Ouvrage;
import bookstore.service.ServicesBasket;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class FXMLBasketController implements Initializable {

    @FXML
    private TableView<Basket> tvBasket;
    @FXML
    private TableColumn<Basket, Ouvrage> tcNom;
    @FXML
    private TableColumn<Basket, Ouvrage> tcPrix;
    @FXML
    private TableColumn<Basket, Integer> tcQanti;
    @FXML
    private TableColumn<Basket, Float> tcPrixTotal;
    
    ServicesBasket sp=new ServicesBasket();
    
    ObservableList<Basket> listp=FXCollections.observableArrayList();
    
    @FXML
    private TextField filterfield;
    @FXML
    private TextField tfGetTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        showBasket();

        FilteredList<Basket> filteredData = new FilteredList<>(listp, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(basket -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (basket.getBookTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Basket> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvBasket.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvBasket.setItems(sortedData);
               
        
    }    

    public void showBasket(){
        //1. Affichage total 
        listp=sp.getBasketOfUser(1);
        tcNom.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        tcQanti.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tcPrix.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        tcPrixTotal.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
        tvBasket.setItems(listp);
        System.out.println(listp);
        prixTotal();
    }
    
    public String prixTotal(){
        float m=0f;
        for(int i = 0;i<listp.size();i++){
            m=(float) (m+(listp.get(i).getTotalPrice()));
        }
        tfGetTotal.setText(String.valueOf(m));
        return String.valueOf(m);
    }
    
    
    @FXML
    private void updateOuvrage(ActionEvent event) {
        try{
            int index=tvBasket.getSelectionModel().getSelectedIndex();
            int quantite;
            do {
                quantite = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter Quantity"));
                if(quantite>0) {
                       int res = JOptionPane.showConfirmDialog(null,"Would you like to update?","ConfirmationDelete",JOptionPane.YES_NO_OPTION);
                            if(res==JOptionPane.YES_OPTION) {
                    }
                             tvBasket.getItems().get(index).setQuantity(quantite);
                             System.out.println(tvBasket.getItems().get(index));
                             sp.modifier(tvBasket.getItems().get(index));
                             listp.get(index).setQuantity(quantite);
                             
                             tvBasket.refresh();
                    }
                }
                while(quantite<=0);
            }
            catch(Exception e){
                    System.out.println(e.getMessage());
                    }    
    }

    @FXML
    private void SupprimOuvrage(ActionEvent event) {
        int index = tvBasket.getSelectionModel().getSelectedIndex();
        sp.supprimer(tvBasket.getItems().get(index));
        showBasket();
    }

    @FXML
    private void confirmBasket(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLCarte.fxml"));
        Parent root=loader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("FXMLCarte.fxml");
        stage.show();
        
    }
    
}
