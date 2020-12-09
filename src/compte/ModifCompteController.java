/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compte;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import magsbookstore.Service.ServiceCompteClient;
import magsbookstore.entite.Client;

/**
 * FXML Controller class
 *
 * @author ghaith khiari
 */
public class ModifCompteController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField pwd;
    @FXML
    private TextField ville;
    @FXML
    private TextField cin;
    @FXML
    private TextField mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savebd(MouseEvent event) {
       
       
ServiceCompteClient sc = new ServiceCompteClient();
        sc.modifiercompte(nom.getText(), prenom.getText(), ville.getText(),pwd.getText(),cin.getText());
        
     /*  
        */
              }
        
        
    
    
     public void setf (Client c){
        nom.setText(c.getNom());
        prenom.setText(c.getPrenom());
        pwd.setText(c.getPwd());
        ville.setText(c.getVille());
        cin.setText(c.getCin());
        cin.setEditable(false);
        mail.setText(c.getEmail());

    }

    @FXML
    private void savemodif(MouseEvent event) {
       
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("modifier compte ");
            Optional<ButtonType> answer= alert.showAndWait();
            if(answer.get()==ButtonType.OK)
             {
                 String fromEmail = "magsbookstore@gmail.com";
		String password = "Mags1234";
		String toEmail =  mail.getText();
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail,password);
			}
		});
		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject(" modification de compte ");
                        msg.setText("Bonjour "+nom.getText()+" " +prenom.getText()+" votre compte est modifier: votre e-mail: "+mail.getText()+"Votre mot de passe"+pwd.getText() );
			Transport.send(msg);
			
                        
                        
                        
		}catch (MessagingException e) {
			e.printStackTrace();
		}
                
        // TODO Auto-generated catch block
        ServiceCompteClient sc = new ServiceCompteClient();
        sc.modifiercompte(nom.getText(), prenom.getText(), ville.getText(),pwd.getText(),cin.getText());
    }
    }
}
