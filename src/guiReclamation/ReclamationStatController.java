/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiReclamation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Jendli
 */
public class ReclamationStatController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private Button bt_back;
    @FXML
    private AnchorPane graphicContent;
    
    private PieChart recChart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniGraphics();
    }    
     
    private ObservableList<PieChart.Data> reclaimStatisData(){
            
        ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
        ServiceReclamation sr=new ServiceReclamation();
        data.add(new PieChart.Data("Total Reclamations: "+sr.rowCount(),sr.rowCount()));
        data.add(new PieChart.Data("Answered Reclamations: "+sr.answeredCount(),sr.answeredCount()));
        return data;
        }
    
    private void iniGraphics(){
            recChart=new PieChart(reclaimStatisData());
            graphicContent.getChildren().add(recChart);
        }
    @FXML
    private void backToReclaim(MouseEvent event) throws IOException {
        
        Parent ongletReclamation=FXMLLoader.load(getClass().getResource("/guiReclamation/Admin_Reclamation.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(ongletReclamation);
    }
    
}
