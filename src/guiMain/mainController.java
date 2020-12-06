package guiMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class mainController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void loadAddMember(ActionEvent event) {
		loadWindow("/bookstoreUiAddMembre/membre_add.fxml","Add new Member");
	}
	@FXML
	private void loadAddOuvrage (ActionEvent event) {
		loadWindow("/bookstoreUiAddOuvrage/BookstoreAddOuv.fxml","Add new Ouvrage");
	}
	@FXML
	private void loadOuvrageTable(ActionEvent event) {
		loadWindow("/BookstoreUiListOuvrages/list_ouvrages.fxml","Liste Ouvrages");
	}
	@FXML
	private void loadMemberTable(ActionEvent event) {
		loadWindow("/bookstore/ui/addMember/member_add.fxml","Add new Member");
	}
	void loadWindow(String loc,String title) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
