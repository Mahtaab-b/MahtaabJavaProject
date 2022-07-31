package application;


import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingApplicationController {

	
	@ FXML
	
	private Label accountNumberDisplay;
	
	@ FXML
	
	private Label accountHolderNameDisplay;
	
	@ FXML
	
	private Label balanceDisplay;
	
	@FXML
	
	private TextField searchAccountTextField;
	
	Stage applicationStage;

	//Create the list of bank accounts opened in the application.
	public ArrayList<Account> bankAccountsRegistered = new ArrayList<Account>();
	
    @FXML
    
    void createAccountsScene (ActionEvent event) {
    	
    
    	//Set the default scene to the main landing scene
		Scene mainScene = applicationStage.getScene();
    	
		//Add an array list to store the values of the text fields
		ArrayList<TextField> accountTextFields = new ArrayList<TextField>();
		
		//Create a new container to serve as form with the following fields.
    	VBox createDetailsContainer = new VBox();
    	
    	//Create fields for the user to input their account number, name and starting balance when creating a new account.
    	HBox addAccountNumberHBox = new HBox();
    	Label addAccountNumberLabel= new Label ("Add an account holder ID: ");
    	TextField addAccountNumberTextField= new TextField();
    	addAccountNumberHBox.getChildren().addAll(addAccountNumberLabel, addAccountNumberTextField);
    	
    	HBox addBalanceHBox = new HBox();
    	Label addBalanceLabel= new Label ("Add your starting balance: ");
    	TextField addBalanceTextField= new TextField();
    	addBalanceHBox.getChildren().addAll(addBalanceLabel, addBalanceTextField);
    	
    	HBox addAccountHolderNameHBox= new HBox();
    	Label addAccountHolderNameLabel= new Label ("Add an account holder name: ");
    	TextField addAccountHolderNameTextField= new TextField();
    	addAccountHolderNameHBox.getChildren().addAll(addAccountHolderNameLabel, addAccountHolderNameTextField);
    	
    	//Add all the fields to the container to create the form.
    	createDetailsContainer.getChildren().addAll(addAccountNumberHBox,addAccountHolderNameHBox,addBalanceHBox);
    	
    	//Add fields to array list
    	
    	accountTextFields.add(addAccountNumberTextField);
    	accountTextFields.add(addAccountHolderNameTextField);
    	accountTextFields.add(addBalanceTextField);
    	
    	
    	
    	//Create a button for user to return to the main scene when they've completed an account.
    	Button doneCreatingAccountButton = new Button("Done Creating Account");
    	doneCreatingAccountButton.setOnAction(doneCreatingAccountEvent -> addAnAccount(mainScene, addAccountNumberTextField, addAccountHolderNameTextField,addBalanceTextField));
		createDetailsContainer.getChildren().add(doneCreatingAccountButton);
		
		//Load the account creation scene from the main scene.
		Scene createDetailsScene = new Scene(createDetailsContainer,400,100);
		applicationStage.setScene(createDetailsScene);

    
    }
    
  //The following method is called upon to add a new account to the list of accounts opened and return the user to the main scene.
    
  void addAnAccount (Scene mainScene, TextField addAccountNumberTextField, TextField addAccountHolderNameTextField, TextField addBalanceTextField ) {
    	
    	applicationStage.setScene(mainScene);
    	
    	double balanceValue= Double.parseDouble(addBalanceTextField.getText());
    	
    	Account bankAccount= new Account(addAccountNumberTextField.getText(),addAccountHolderNameTextField.getText(), balanceValue);
    	
    	bankAccountsRegistered.add(bankAccount);
    	
    	System.out.println(bankAccountsRegistered);
    	
    	accountNumberDisplay.setText("");
		accountHolderNameDisplay.setText("");
		balanceDisplay.setText("");
    }
    
  @FXML
  
  void openAnAccountScene (ActionEvent event) {
	  
	  String search= searchAccountTextField.getText();
	  
	  System.out.println(search);
	  
	  int index=0;
	  
	  while (index < bankAccountsRegistered.size()) {
		  
		  if (search.equals(bankAccountsRegistered.get(index).getAccountNumber())) {
			  
			  accountNumberDisplay.setText(bankAccountsRegistered.get(index).getAccountNumber());
			  accountHolderNameDisplay.setText(bankAccountsRegistered.get(index).getLoginName());
			  balanceDisplay.setText("$"+String.valueOf(bankAccountsRegistered.get(index).getBalance()));
	  }
	
	  index++;
  }

    

}
}
