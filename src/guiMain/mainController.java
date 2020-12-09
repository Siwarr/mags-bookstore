package guiMain;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

import bookstore.database.Myconnection;
import bookstore.entites.Ouvrage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class mainController implements Initializable {

	@FXML
	private HBox book_info;
	@FXML
	private HBox member_info;
	@FXML
	private TextField bookNameInput;
	@FXML
	private TextField memberNameInput;
	@FXML
	private Text bookName;
	@FXML
	private Text memberName;
	@FXML
	private Text memberTel;
	@FXML
	private Text bookAuthor;
	@FXML
	private Text bookStatus;
	@FXML
	private TextField Namebook;
	@FXML
	private ListView<String> issueDataList;
	@FXML
	boolean isReadyForSubmission=false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
		//JFXDepthManager.setDepth(book_info,1);
		
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
		loadWindow("/BookstoreUiListClients/list_client.fxml","Liste Clients");
	}
	@FXML 
	private void loadEmpruntTable(ActionEvent event) {
		loadWindow("/listEmprunts/list_emprunt.fxml","Liste Emprunts");
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
	@FXML
	private void loadBookInfo(ActionEvent event) {
		clearBookCache();
		String name = bookNameInput.getText();
		String req="select * from ouvrage where name= "+"'"+name+"'";
		Boolean flag=false;
		try {
        	Myconnection cnx=new Myconnection();
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req);
            while(rs.next())
            {
            	String bAuthor=rs.getString("author");
            	String bName=rs.getString("name");
            	Boolean bStatus=rs.getBoolean("isAvail");
                //System.out.println("Succes de Recherche :\n id: "+rs.getInt("id")+" author: "+rs.getString("author")+" name: "+rs.getString("name")+" price: "+rs.getFloat("price")+" edition: "+rs.getString("edition")+" isAvail: "+rs.getBoolean("isAvail")+" quantite: "+rs.getInt("quantite")+" description: "+rs.getString("description"));
            	bookName.setText(bName);
            	bookAuthor.setText(bAuthor);
            	String status=(bStatus)?"Available": "Not available";
            	bookStatus.setText(status);
            	flag=true;
            }
            if(!flag) {
            	bookName.setText("No such book available");
            }
        } catch (SQLException ex) {
            System.err.println("erreur de recherche");
        }
	}
	void clearBookCache() {
		bookName.setText("");
		bookAuthor.setText("");
		bookStatus.setText("");
	}
	void clearMemberCache() {
		memberName.setText("");
		memberTel.setText("");
	}
	
	@FXML
	private void loadMemberInfo(ActionEvent event) {
		clearMemberCache();
		String name = memberNameInput.getText();
		String req="select * from client where name= "+"'"+name+"'";
		Boolean flag=false;
		try {
        	Myconnection cnx=new Myconnection();
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req);
            while(rs.next())
            {
            	String bName=rs.getString("name");
            	String bTel=rs.getString("tel");
            	memberName.setText(bName);
            	memberTel.setText(bTel);
            	flag=true;
            }
            if(!flag) {
            	memberName.setText("No such member available");
            }
        } catch (SQLException ex) {
            System.err.println("erreur de recherche");
        }
	}
	
	@FXML
	private void loadEmpruntOperation(ActionEvent event) {
		Myconnection cnx=new Myconnection();
		System.out.println("helo");
		String memberName=memberNameInput.getText();
		String bookName=bookNameInput.getText();  
		Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm Emprunt operation");
		alert.setHeaderText(null);
		alert.setContentText("Vous voulez emprunter ouvrage "+ bookName + "\n to "+memberName +" ?");
		
		Optional<ButtonType> response =alert.showAndWait();
		if(response.get()==ButtonType.OK) {
			String req="insert into emprunt(memberName,bookName) VALUES ( + "
					+"'"+ memberName +"',"
					+"'"+ bookName +"')";
			String str2 = "UPDATE ouvrage set isAvail=false where name='"+bookName+"'";
			
			try {
		        PreparedStatement ps = cnx.getConnection().prepareStatement(req);
				ps.executeUpdate(req);
				ps.executeUpdate(str2);
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Success");
				alert1.setHeaderText(null);
				alert1.setContentText("Emprunt Ouvrage terminé");
				alert1.showAndWait();
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Failed");
				alert1.setHeaderText(null);
				alert1.setContentText("Emprunt Ouvrage echoué");
				alert1.showAndWait();
				e.printStackTrace();
			}
		}else {
			Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
			alert1.setTitle("Cancelled");
			alert1.setHeaderText(null);
			alert1.setContentText("Emprunt Ouvrage annulé");
			alert1.showAndWait();
		}
	}
	@FXML
	private void loadBookInfo2(ActionEvent event) {
		ObservableList<String> issueData=FXCollections.observableArrayList();
		isReadyForSubmission=false;
		Myconnection cnx=new Myconnection();
		String name=Namebook.getText();
		String qu="";
		try {
			//qu="select * from emprunt where bookName='"+name+"'";
			Statement s = cnx.getConnection().createStatement();
			Statement s1 = cnx.getConnection().createStatement();
			Statement s2 = cnx.getConnection().createStatement();
			ResultSet rs = s.executeQuery("select * from emprunt where bookName='"+name+"'");
			
			while(rs.next()) {
				String mbookName=name;
				String mMemberName=rs.getString("memberName");
				Timestamp mIssueTime=rs.getTimestamp("issueTime");
				int mRenewCount = rs.getInt("renew_count");
				
				issueData.add("Emprunt Date and Time :"+mIssueTime.toGMTString());
				issueData.add("Renew Count :"+mRenewCount);
				
				issueData.add("Book informations :-");
				
				//qu="select * from ouvrage where name= '"+mbookName+"'";
				ResultSet r2=s1.executeQuery("select * from ouvrage where name= '"+mbookName+"'");
				while(r2.next()) {
					issueData.add("\t Book Name :"+r2.getString("name"));
					issueData.add("\t Book Author :"+r2.getString("author"));
					issueData.add("\t Book Edition :"+r2.getString("edition"));
					issueData.add("\t Book price :"+r2.getString("price"));
				}
				//qu="select * from client where id = '"+mMemberName+"'";
				issueData.add("Member informations :-");
				ResultSet r3=s2.executeQuery("select * from client where name = '"+mMemberName+"'");
				//System.out.println(r3);
				while(r3.next()) {
					issueData.add("\t Name :"+r3.getString("name"));
					issueData.add("\t Tel :"+r3.getString("tel"));
					issueData.add("\t email :"+r3.getString("email"));
				}
				isReadyForSubmission=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		issueDataList.getItems().setAll(issueData);
	}
	
	@FXML
	private void loadSubmissionOperation(ActionEvent event) {
		Myconnection cnx=new Myconnection();
		if(!isReadyForSubmission) {
			Alert alert1 = new Alert(Alert.AlertType.ERROR);
			alert1.setTitle("Echec");
			alert1.setHeaderText(null);
			alert1.setContentText("Selectionnez un ouvrage pour retourner");
			alert1.showAndWait();
			return;
		}
		String name=Namebook.getText();
		String ac1="Delete from emprunt where bookName='"+name+"'";
		String ac2="Update ouvrage set isAvail = true where name = '"+name+"'";
		Alert alert0 = new Alert(Alert.AlertType.CONFIRMATION);
		alert0.setTitle("Confirmez le retour");
		alert0.setHeaderText(null);
		alert0.setContentText("Vous etes sur vous voulez retourner l'ouvrage ?");
		alert0.showAndWait();
		
		Optional<ButtonType> response=alert0.showAndWait();
		if(response.get()==ButtonType.OK) {
		
		
		try {
	        PreparedStatement ps = cnx.getConnection().prepareStatement(ac1);
	        PreparedStatement ps2 = cnx.getConnection().prepareStatement(ac2);
	        ps.executeUpdate();
	        ps2.executeUpdate();
			Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
			alert1.setTitle("Succes");
			alert1.setHeaderText(null);
			alert1.setContentText("Ouvrage retourné");
			alert1.showAndWait();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Echec");
			alert.setHeaderText(null);
			alert.setContentText("Retour echoué");
			alert.showAndWait();
			e.printStackTrace();
		}
		}else {
			Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
			alert4.setTitle("Annuler");
			alert4.setHeaderText(null);
			alert4.setContentText("Operation de retour annulée");
			alert4.showAndWait();
		}
	}
	@FXML
	private void loadRenewOp(ActionEvent event) {
		Myconnection cnx=new Myconnection();
		if(!isReadyForSubmission) {
			Alert alert1 = new Alert(Alert.AlertType.ERROR);
			alert1.setTitle("Echec");
			alert1.setHeaderText(null);
			alert1.setContentText("Selectionnez un ouvrage pour renouveler l'emprunt");
			alert1.showAndWait();
			return;
		}
		Alert alert0 = new Alert(Alert.AlertType.CONFIRMATION);
		alert0.setTitle("Confirmer renouvellement");
		alert0.setHeaderText(null);
		alert0.setContentText("Vous voulez renouveler l'emprunt ouvrage ?");
		alert0.showAndWait();
		Optional<ButtonType> response=alert0.showAndWait();
		if(response.get()==ButtonType.OK) {
			String ac="update emprunt set issueTime =CURRENT_TIMESTAMP,renew_count=renew_count+1  where bookName= '"+ Namebook.getText() +"'";
			System.out.println(ac);
			
			try {
				PreparedStatement ps2 = cnx.getConnection().prepareStatement(ac);
				ps2.executeUpdate();
				Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
				alert4.setTitle("SUCCESS");
				alert4.setHeaderText(null);
				alert4.setContentText("Operation de renouvellement emprunt reussie");
				alert4.showAndWait();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Alert alert4 = new Alert(Alert.AlertType.ERROR);
				alert4.setTitle("Echeck");
				alert4.setHeaderText(null);
				alert4.setContentText("Operation de renouvellement echouée");
				alert4.showAndWait();
				e.printStackTrace();
			}
		}else {
			Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
			alert4.setTitle("Annuler");
			alert4.setHeaderText(null);
			alert4.setContentText("Operation de renouvellement annulée");
			alert4.showAndWait();
		}
		
	}
	
}
