package voiceControl;

import bookstore_v1.BookStore_V1;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SpeechRecognizer {
	
	// Necessary
	private LiveSpeechRecognizer recognizer;
	
	// Logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * This String contains the Result that is coming back from SpeechRecognizer
	 */
	private String speechRecognitionResult;
	
	//-----------------Lock Variables-----------------------------
	
	/**
	 * This variable is used to ignore the results of speech recognition cause actually it can't be stopped...	 
	 */
	private boolean ignoreSpeechRecognitionResults = false;
	
	/**
	 * Checks if the speech recognise is already running
	 */
	private boolean speechRecognizerThreadRunning = false;
	
	/**
	 * Checks if the resources Thread is already running
	 */
	private boolean resourcesThreadRunning;
	
	//---
	
	/**
	 * This executor service is used in order the playerState events to be executed in an order
	 */
	private ExecutorService eventsExecutorService = Executors.newFixedThreadPool(2);
	
	//------------------------------------------------------------------------------------
        
        
	
	/**
	 * Constructor
	 */
	public SpeechRecognizer() {
		
		// Loading Message
		logger.log(Level.INFO, "Loading Speech Recognizer...\n");
		
		// Configuration
		Configuration configuration = new Configuration();
		
		// Load model from the jar
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		
		//====================================================================================
		//=====================READ THIS!!!===============================================
		//Uncomment this line of code if you want the recognizer to recognize every word of the language 
		//you are using , here it is English for example	
		//====================================================================================
		//configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		
		//====================================================================================
		//=====================READ THIS!!!===============================================
		//If you don't want to use a grammar file comment below 3 lines and uncomment the above line for language model	
		//====================================================================================
		
		// Grammar
		configuration.setGrammarPath("resource:/grammars");
		configuration.setGrammarName("grammar");
		configuration.setUseGrammar(true);
		
		try {
			recognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		
		// Start recognition process pruning previously cached data.
		// recognizer.startRecognition(true);
		
		//Check if needed resources are available
		startResourcesThread();
		//Start speech recognition thread
		startSpeechRecognition();
	}
	
	//-----------------------------------------------------------------------------------------------
	
	/**
	 * Starts the Speech Recognition Thread
	 */
	public synchronized void startSpeechRecognition() {
		
		//Check lock
		if (speechRecognizerThreadRunning)
			logger.log(Level.INFO, "Speech Recognition Thread already running...\n");
		else
			//Submit to ExecutorService
			eventsExecutorService.submit(() -> {
				
				//locks
				speechRecognizerThreadRunning = true;
				ignoreSpeechRecognitionResults = false;
				
				//Start Recognition
				recognizer.startRecognition(true);
				
				//Information			
				logger.log(Level.INFO, "You can start to speak...\n");
				
				try {
					while (speechRecognizerThreadRunning) {
						
						SpeechResult speechResult = recognizer.getResult();
						
						//Check if we ignore the speech recognition results
						if (!ignoreSpeechRecognitionResults) {
							
							//Check the result
							if (speechResult == null)
								logger.log(Level.INFO, "I can't understand what you said.\n");
							else {
								
								//Get the hypothesis
								speechRecognitionResult = speechResult.getHypothesis();
								
								//You said?
								System.out.println("You said: [" + speechRecognitionResult + "]\n");
                                                                cmd(speechRecognitionResult);
								
							}
						} else
							logger.log(Level.INFO, "Ingoring Speech Recognition Results...");
						
					}
				} catch (Exception ex) {
					logger.log(Level.WARNING, null, ex);
					speechRecognizerThreadRunning = false;
				}
				
				logger.log(Level.INFO, "SpeechThread has exited...");
				
			});
	}
	
	/**
	 * Stops ignoring the results of SpeechRecognition
	 */
	public synchronized void stopIgnoreSpeechRecognitionResults() {
		
		//Stop ignoring speech recognition results
		ignoreSpeechRecognitionResults = false;
	}
	
	/**
	 * Ignores the results of SpeechRecognition
	 */
	public synchronized void ignoreSpeechRecognitionResults() {
		
		//Instead of stopping the speech recognition we are ignoring it's results
		ignoreSpeechRecognitionResults = true;
		
	}
	
	//-----------------------------------------------------------------------------------------------
	
	/**
	 * Starting a Thread that checks if the resources needed to the SpeechRecognition library are available
	 */
	public void startResourcesThread() {
		
		//Check lock
		if (resourcesThreadRunning)
			logger.log(Level.INFO, "Resources Thread already running...\n");
		else
			//Submit to ExecutorService
			eventsExecutorService.submit(() -> {
				try {
					
					//Lock
					resourcesThreadRunning = true;
					
					// Detect if the microphone is available
					while (true) {
						
						//Is the Microphone Available
						if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE))
							logger.log(Level.INFO, "Microphone is not available.\n");
						
						// Sleep some period
						Thread.sleep(350);
					}
					
				} catch (InterruptedException ex) {
					logger.log(Level.WARNING, null, ex);
					resourcesThreadRunning = false;
				}
			});
	}
	
	/**
	 * Takes a decision based on the given result
	 * 
	 * @param speechWords
	 */
	public void makeDecision(String speech , List<WordResult> speechWords) {
		
		System.out.println(speech);
		
	}
	public void cmd(String speech) throws IOException {
		
		switch (speech){
                    case "reclaim":
                    Parent rec=FXMLLoader.load(getClass().getResource("/guiReclamation/Reclamation.fxml"));
                    BookStore_V1.stage.getScene().setRoot(rec);
                    break;
                    case "add reclaim":
                    Parent newrec=FXMLLoader.load(getClass().getResource("/guiReclamation/NewReclamation.fxml"));
                    BookStore_V1.stage.getScene().setRoot(newrec);
                    break;
                    case "home":
                    Parent mainMenu=FXMLLoader.load(getClass().getResource("/guiMain/MainMenu.fxml"));
                    BookStore_V1.stage.getScene().setRoot(mainMenu);
                    break;
                    
                   
                }
		
	}
	public boolean getIgnoreSpeechRecognitionResults() {
		return ignoreSpeechRecognitionResults;
	}
	
	public boolean getSpeechRecognizerThreadRunning() {
		return speechRecognizerThreadRunning;
	}
	
	
}