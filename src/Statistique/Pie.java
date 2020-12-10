

package Statistique;

/**
 *
 * @author DELL
 */

/**
 *
 * @author DELL
 */
import entity.Ouvrage;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import metier.ServiceOuvrage;
 
public class Pie extends Application 
{
    @Override public void start(Stage stage) 
    {
        Scene scene = new Scene(new Group());
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);
        
        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
        ServiceOuvrage so = new ServiceOuvrage();
        ArrayList<Ouvrage> list =so.getAll();
        for(int i=0 ; i<list.size() ; i++)
        {
            pieChartData.add(new PieChart.Data(list.get(i).getName(),list.get(i).getQuantite()));
        }
       /* ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));*/
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported "+Ouvrage.class);

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) 
    {

        launch(args);
    }
}
