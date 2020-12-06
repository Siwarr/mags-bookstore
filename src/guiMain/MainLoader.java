package guiMain;
import BookstoreUiListOuvrages.list_ouvragesController;
import bookstore.service.ServiceOuvrage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class MainLoader extends Application {
		Parent root;
	    Stage stage;
		
		@Override
		public void start(Stage primaryStage) {
			
			/*try {
				Parent root=FXMLLoader.load(getClass().getResource("main.fxml"));
		        Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();            
		    } catch (Exception e) {
		        e.printStackTrace();
		    }*/
			try {
		        FXMLLoader loader = new FXMLLoader();
		        loader.setController(new mainController());
		        loader.setLocation(getClass().getResource("main.fxml"));
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
