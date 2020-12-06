package guiMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import bookstore.entites.Admin;
import bookstore.entites.Client;
import bookstore.service.AuthAdmin;
import bookstore.service.AuthClt;
import bookstore_v1.bookstore_v1;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.logging.Logger;

public class MainMenuController implements Initializable {
	@FXML
	private AnchorPane panelSide;
	@FXML
	private AnchorPane content;
    @FXML
    private Button bt_ouvrage;
    @FXML
    private Button bt_minimize;
    @FXML
    private Button bt_close;
    @FXML
    private Button bars;
    @FXML
    private Button chevronleft;
    @FXML
    private Button bt_ouvrageAdmin;
    //@FXML
    //private FontAwesomeIconView bt_signout;
    @FXML
    private Button bt_home;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 try {
	            // TODO
	            Parent home=FXMLLoader.load(getClass().getResource("/guiHome/Home.fxml"));
	            content.getChildren().removeAll();
	            content.getChildren().setAll(home);
	            bars.setVisible(false);
	            chevronleft.setVisible(true);
	            AuthClt c1= AuthClt.getInstance();
	            Client client=c1.getClient();    
	            AuthAdmin a1= AuthAdmin.getInstance();
	            Admin admin=a1.getAdmin();
	            if(client==null)
	            {
	                bt_ouvrage.setVisible(false);
	            }
	            if(admin==null)
	            {
	                bt_ouvrageAdmin.setVisible(false);
	            }
	        } catch (IOException ex) {
	           Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

    @FXML
    private void ongletOuvrage(MouseEvent event)throws IOException {
           Parent ongletOuvrage=FXMLLoader.load(getClass().getResource("/Bookstore.ui.listOuvrages/list_ouvrages.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(ongletOuvrage);
    }

    @FXML
    private void minimize(MouseEvent event) {
        bookstore_v1.stage.setIconified(true);
    }

    @FXML
    private void close(MouseEvent event) {
         System.exit(0);
    }

    @FXML
    private void openPanel(MouseEvent event) {
        
        TranslateTransition slide=new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(panelSide);
        
        slide.setToX(170);
        slide.play();
        panelSide.setVisible(false);
        //panelSide.setTranslateX(-230);
        slide.setOnFinished((ActionEvent e) -> {
            bars.setVisible(false);
            chevronleft.setVisible(true);
        });
    }

    @FXML
    private void closePanel(MouseEvent event) {
        
        TranslateTransition slide=new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(panelSide);
        
        slide.setToX(0);
        panelSide.setVisible(true);
        slide.play();
        //panelSide.setTranslateX(-230);
        slide.setOnFinished((ActionEvent e) -> {
            bars.setVisible(true);
            chevronleft.setVisible(false );
        });
    }

    private void minimize(ActionEvent event) {
    	bookstore_v1.stage.setIconified(true);
    }

    @FXML
    private void ongletOuvrageAdmin(MouseEvent event) throws IOException {
        Parent ongletOuvrageAdmin=FXMLLoader.load(getClass().getResource("/Bookstore.ui.listOuvrages/list_ouvrages.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(ongletOuvrageAdmin);

    }
    

    @FXML
    private void signOut(MouseEvent event) throws IOException {
    	AuthClt clientAuthentifie= AuthClt.getInstance();
         Client client=clientAuthentifie.getClient();
         AuthAdmin ad= AuthAdmin.getInstance();
         Admin admin=ad.getAdmin();
         if(client!=null)
         {   
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
  			alert.setHeaderText(null);
  			alert.setContentText("Successful Logout");
  			alert.showAndWait();
  			return;
             
         }
         if(admin!=null)
         {
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
 			alert.setHeaderText(null);
 			alert.setContentText("Successful Logout");
 			alert.showAndWait();
 			return;
         }
        clientAuthentifie.setClient(null);
        ad.setAdmin(null);
        Parent root = FXMLLoader.load(getClass().getResource("/bookstore_v1/Bookstorelogin.fxml"));
        bookstore_v1.stage.getScene().setRoot(root);
    }

    @FXML
    private void goToHome(MouseEvent event) throws IOException {
        /*Parent mainMenu=FXMLLoader.load(getClass().getResource("/guiMain/MainMenu.fxml"));
        BookStore_V1.stage.getScene().setRoot(mainMenu);*/
         Parent home=FXMLLoader.load(getClass().getResource("/guiHome/Home.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(home);
    }

}
