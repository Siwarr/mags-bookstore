/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voiceControl;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jendli
 */
public class VoiceControlController implements Initializable {

    @FXML
    private Button bt_start;
    @FXML
    private Button bt_stop;
    @FXML
    private Button bt_resume;
    
    private SpeechRecognizer speechRecognition = new SpeechRecognizer();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void startVoiceControl(MouseEvent event) {
      speechRecognition.startSpeechRecognition();
    }

    @FXML
    private void stopVoiceControl(MouseEvent event) {
      speechRecognition.ignoreSpeechRecognitionResults();
    }

    @FXML
    private void resumeVoiceControl(MouseEvent event) {
      speechRecognition.stopIgnoreSpeechRecognitionResults();
    }
    
}
