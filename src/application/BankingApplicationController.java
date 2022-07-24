package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingApplicationController {
	
	Stage applicationStage;

    
    @FXML
    
    void createAccountDetails (ActionEvent event) {
    	Scene createDetailScene = new Scene(new Label("placeholder label"));
    	applicationStage.setScene(createDetailScene);
    	
    	 
    	
    }
    
    @FXML
    
    void openAccountDetails (ActionEvent event) {
    	
    }
  
    

}
