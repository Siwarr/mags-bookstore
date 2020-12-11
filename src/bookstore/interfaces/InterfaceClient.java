package bookstore.interfaces;

import java.util.List;

import bookstore.entites.Client;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public interface InterfaceClient {
	public void ajouterClient(Client c);
	public List<Client> afficherClients();
	public ObservableList<PieChart.Data> getMembreGraphStat();
}
