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
		Scene mainScene = applicationStage.getScene();
    	
    	VBox createDetailsContainer = new VBox();
    	HBox idHbox = new HBox();
    	HBox balanceHbox = new HBox();
    	Label addAccountHolderIdLabel= new Label ("Add an account holder ID: ");
    	Label addBalanceLabel= new Label ("Add your starting balance: ");
    	HBox accountHolderNameHBox= new HBox();
    	Label addAccountHolderNameLabel= new Label ("Add an account holder name: ");
    	TextField idNumber= new TextField();
    	TextField accountHolderName= new TextField();
    	TextField balanceField= new TextField();
    	balanceHbox.getChildren().addAll(addBalanceLabel, balanceField);
    	
    	idHbox.getChildren().addAll(addAccountHolderIdLabel, idNumber);
    	accountHolderNameHBox.getChildren().addAll(addAccountHolderNameLabel, accountHolderName);
    	
    	createDetailsContainer.getChildren().addAll(idHbox,accountHolderNameHBox,balanceHbox);
    	
    	Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> applicationStage.setScene(mainScene));
		createDetailsContainer.getChildren().add(doneButton);

		Scene createDetailsScene = new Scene(createDetailsContainer,400,100);
		applicationStage.setScene(createDetailsScene);

    	
    	 
    	
    }
    
    @FXML
    
    void openAccountDetails (ActionEvent event) {
    	
    }
  
    

}
