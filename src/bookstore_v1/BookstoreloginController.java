package bookstore_v1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bookstore.entites.Admin;
import bookstore.entites.Client;
import bookstore.service.AuthAdmin;
import bookstore.service.AuthClt;
import bookstore.service.ServiceLogin_Register;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class BookstoreloginController implements Initializable{
	private double xOffset=0;
    private double yOffset=0;
	private Label label;
	@FXML
	private BorderPane parent;
	@FXML
	private Pane content;
	@FXML
	private AnchorPane loginParent;
	@FXML
	private TextField username;
	@FXML
	private PasswordField pwd;
	@FXML
	private Button login;
	@FXML
	private Hyperlink register;
	@FXML
	private Button bt_close;
	@FXML
	private Button bt_minimize;

	private void makeStageDragable(){
        parent.setOnMousePressed((event) -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        parent.setOnMouseDragged((event) -> {
             bookstore_v1.stage.setX(event.getScreenX()-xOffset);
             bookstore_v1.stage.setY(event.getScreenY()-yOffset);
             bookstore_v1.stage.setOpacity(0.8f);
        });
        parent.setOnDragDone((event) -> {
        	bookstore_v1.stage.setOpacity(1.0f);
             
        });
        parent.setOnMouseReleased((event) -> {
        	bookstore_v1.stage.setOpacity(1.0f);
             
        });
      }
    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(MouseEvent event) {
        bookstore_v1.stage.setIconified(true);
    }
    

    @FXML
    private void connecter(MouseEvent event) throws IOException{
        ServiceLogin_Register log=new ServiceLogin_Register();
        Client client=log.seConnecter(username.getText(),pwd.getText());
        Admin admin=log.seConnecterAdmin(username.getText(),pwd.getText());
        if(client!=null){
            AuthClt cl=AuthClt.getInstance();
            cl.setClient(client);
 
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Successful login");
            alert.show();
            cl.setClient(client);
           Parent mainMenu=FXMLLoader.load(getClass().getResource("/guiMain/MainMenu.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(mainMenu);
           /*Scene reclamation=new Scene(rec);
           Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
           window.setScene(reclamation);
           window.show();*/
        }
        else if(admin!=null){
            AuthAdmin al=AuthAdmin.getInstance();
            al.setAdmin(admin);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Successful login");
            alert.show();
           Parent mainMenu=FXMLLoader.load(getClass().getResource("/guiMain/MainMenu.fxml"));
           content.getChildren().removeAll();
           content.getChildren().setAll(mainMenu);
        }
        else{
        	Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Successful login");
            alert.show();
        }
    }
    @FXML
    private void openRegister(MouseEvent event) throws IOException {
           Parent register=FXMLLoader.load(getClass().getResource("/bookstoreuiregister/register.fxml"));
           loginParent.getChildren().removeAll();
           loginParent.getChildren().setAll(register);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		makeStageDragable();
		
	}
}
