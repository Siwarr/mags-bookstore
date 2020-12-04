/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiReclamation;

import entities.Reclamation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
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
import service.ServiceReclamation;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Jendli
 */
public class SendMail_ReclamationController implements Initializable {

    @FXML
    private Button bt_send;
    @FXML
    private TextField txt_to;
    @FXML
    private TextField txt_object;
    @FXML
    private TextField txt_message;
    @FXML
    private AnchorPane content;
    @FXML
    private Button bt_back;
    @FXML
    private PasswordField txt_pwd;
    @FXML
    private Button bt_joint;
    private Label lb;
   private String path=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendMail(MouseEvent event) throws IOException {
              if(txt_message.getText().isEmpty()||txt_pwd.getText().isEmpty())
        {
            TrayNotification tray=new TrayNotification();
            AnimationType type=AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle("Failed");
            tray.setMessage("Please Verify the fields");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        }
              else
              {
                String fromEmail = "magsbookstore@gmail.com";
		String password = txt_pwd.getText();		
		String toEmail = txt_to.getText();
		
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
			msg.setSubject("Reclamation n°"+txt_object.getText());
			
			Multipart emailContent = new MimeMultipart();
			
			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(txt_message.getText());
			
                        //Attach body parts
			emailContent.addBodyPart(textBodyPart);
			//Attachment body part.
                        
                        if(path!=null)
                        {
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile(path);
			emailContent.addBodyPart(pdfAttachment);
                        }
			
			//Attach multipart to message
			msg.setContent(emailContent);
			
			Transport.send(msg);
                        ServiceReclamation rec=new ServiceReclamation();
                        rec.modifierReclamation(txt_object.getText());
			TrayNotification tray=new TrayNotification();
                        AnimationType type=AnimationType.FADE;
                        tray.setAnimationType(type);
                        tray.setTitle("Mail has been sent");
                        tray.setMessage("Mail to "+txt_to.getText()+" has been sent");
                        tray.setNotificationType(NotificationType.SUCCESS);
                        tray.showAndDismiss(Duration.millis(3000));
                        Parent adminReclamation=FXMLLoader.load(getClass().getResource("/guiReclamation/Admin_Reclamation.fxml"));
                        content.getChildren().removeAll();
                        content.getChildren().setAll(adminReclamation);
                        
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
              }
    }
    
    public void setFields(Reclamation r){
        txt_to.setText(r.getMailclient());
        txt_object.setText(r.getCode());  
        txt_to.setEditable(false);
        txt_object.setEditable(false);
    }

    @FXML
    private void backReclamation(MouseEvent event) throws IOException {
        Parent ongletReclamation=FXMLLoader.load(getClass().getResource("/guiReclamation/Admin_Reclamation.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(ongletReclamation);
    }

    @FXML
    private void jointFile(MouseEvent event) {
        FileChooser filechooser=new FileChooser();
        filechooser.setTitle("Joint File to Mail");
        filechooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files","*.txt"),
                new ExtensionFilter("Pdf Files","*.pdf"),
                new ExtensionFilter("Image Files","*.png","*.jpg")
        );
        Stage stage=(Stage)content.getScene().getWindow();
        File file=filechooser.showOpenDialog(stage);
        if(file!=null)
        { String path=file.getAbsolutePath();
           this.path=path;
        }
           

    }

    
}
