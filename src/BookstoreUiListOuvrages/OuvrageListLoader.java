package BookstoreUiListOuvrages;

import bookstore.service.ServiceOuvrage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class OuvrageListLoader extends Application {
	Parent root;
    Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		/*try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Bookstore.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setController(new list_ouvragesController());
	        loader.setLocation(getClass().getResource("list_ouvrages.fxml"));
	        root = loader.load();
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();            
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public static void main(String[] args) {
		launch(args);
		//ServiceOuvrage sp=new ServiceOuvrage();
	}
}
