package empruntStat;

import bookstoreuiregister.loginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class statemprunts extends Application {
	Parent root;
    Stage stage;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setController(new empruntstatController());
	        loader.setLocation(getClass().getResource("empruntstat.fxml"));
	        root = loader.load();
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();            
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
