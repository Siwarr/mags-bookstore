package empruntStat;

import java.net.URL;
import java.util.ResourceBundle;

import bookstore.service.ServiceClient;
import bookstore.service.ServiceOuvrage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;

public class empruntstatController implements Initializable{
	@FXML
	private StackPane bookInfoContainer;
	@FXML
	private StackPane clientInfoContainer;
	
	PieChart bookChart;
	PieChart clientChart;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initGraphs();
	}

	private void initGraphs() {
		// TODO Auto-generated method stub
		ServiceOuvrage s=new ServiceOuvrage();
		ServiceClient so=new ServiceClient();
		bookChart=new PieChart(s.getBookGraphStat());
		clientChart=new PieChart(so.getMembreGraphStat());
		bookInfoContainer.getChildren().add(bookChart);
		clientInfoContainer.getChildren().add(clientChart);
		
	}
	
	
	//

}
