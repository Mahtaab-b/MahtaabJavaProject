package application;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingApplicationController {

	private static int testCounter=0;	
	
	@FXML

	private Label accountNumberDisplay;

	@FXML

	private Label accountHolderNameDisplay;

	@FXML

	private Label balanceDisplay;
	
	@FXML
	
	private Label typeDisplay;

	@FXML

	private TextField searchAccountTextField;

	@FXML

	private Label searchErrorLabel;
	

	private Stage applicationStage;

	private Label accountNumberErrorLabel = new Label();
	private Label accountNameErrorLabel = new Label();
	private Label accountBalanceErrorLabel = new Label();
	private Label accountTypeErrorLabel = new Label();
	private Label interestSelectionErrorLabel = new Label();
	private Label periodInputErrorLabel = new Label();

	
	// Create the list of bank accounts opened in the application.
	Bank accountsRegistered = new Bank();

	Account testOne = new Account("11111111", "Mahtaab", 50, "Checking");
	Account testTwo = new Account("22222222", "Kevin", 50, "Checking");

	// Setter and getter for application stage.
	public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}

	public void addTests() {
		accountsRegistered.addBankAccounts(testOne);
		accountsRegistered.addBankAccounts(testTwo);

	}

	public String isValidAccountNumber(String userNumber) {

		String errorMessage = "";

		if (userNumber == "") {
			errorMessage = "Please enter an account number.";
		}

		else if (userNumber.length() != 8) {
			errorMessage = "Your account number must be 8 characters in length.";
		}

		for (char c : userNumber.toCharArray()) {
			if ((!Character.isDigit(c))) {
				errorMessage = ("Account number should only contain numbers. " + "Don't include the character: " + c);
			}
		}

		return errorMessage;
	}

	public String isValidAccountName(String userName) {

		String errorMessage = "";

		if (userName == "") {
			errorMessage = "Please enter a name.";
		}

		for (char c : userName.toCharArray()) {
			if ((!Character.isAlphabetic(c)) && c != ' ') {
				errorMessage = ("Account name should only contain letters. " + "Don't include the character: " + c);
			}
		}

		return errorMessage;
	}
	
	public String isValidPeriod(String userNumber) {

		String errorMessage = "";

		if (userNumber == "") {
			errorMessage = "Please enter a number of years.";
		}

		for (char c : userNumber.toCharArray()) {
			if ((!Character.isDigit(c))) {
				errorMessage = ("Period amount should only contain numbers. " + "Don't include the character: " + c);
			}
		}

		return errorMessage;
	}

	public String isValidBalance(String inputBalance) {

		String errorMessage = "";

		int count = 0;

		if (inputBalance == "") {
			errorMessage = "Please enter a dollar amount.";
		}
		

		// check that the user entered only a numeric value
		for (char c : inputBalance.toCharArray()) {

			// count the number of decimals in the user input
			if (c == '.') {

				count = count + 1;

			}

			// if the user input has more than one decimal, set the flag to false
			if (count > 1) {
				errorMessage = ("Balance should not have more than one decimal input.");

			}
			// if any character is not a digit or decimal, set flag to false as it is not a
			// number
			if ((!Character.isDigit(c)) && c != '.') {

				errorMessage = ("Balance should only contain numeric values. " + "Don't include the character: " + c);
			}

		}

		return errorMessage;
	}
	

	@FXML

	void createAccountsScene(ActionEvent event) {
		
		if (testCounter==0) {
			addTests();
			testCounter++;
		}
		// Set the default scene to the main landing scene
		Scene mainScene = getApplicationStage().getScene();


		// Create a new container to serve as form with the following fields.
		VBox createDetailsContainer = new VBox();

		// Create fields for the user to input their account number, name and starting
		// balance when creating a new account.
		HBox addAccountNumberHBox = new HBox();
		Label addAccountNumberLabel = new Label("Add an 8 digit account number: ");
		TextField addAccountNumberTextField = new TextField();
		addAccountNumberHBox.getChildren().addAll(addAccountNumberLabel, addAccountNumberTextField,
				getAccountNumberErrorLabel());

		HBox addBalanceHBox = new HBox();
		Label addBalanceLabel = new Label("Add your starting balance: ");
		TextField addBalanceTextField = new TextField();
		addBalanceHBox.getChildren().addAll(addBalanceLabel, addBalanceTextField, getAccountBalanceErrorLabel());

		HBox addAccountHolderNameHBox = new HBox();
		Label addAccountHolderNameLabel = new Label("Add an account holder name: ");
		TextField addAccountHolderNameTextField = new TextField();
		addAccountHolderNameHBox.getChildren().addAll(addAccountHolderNameLabel, addAccountHolderNameTextField,
				getAccountNameErrorLabel());
		
		HBox addTypeHBox = new HBox();
		Label addTypeLabel = new Label("Select a type of account: ");
		ChoiceBox<String> addTypeChoiceBox = new ChoiceBox();
		addTypeChoiceBox.getItems().addAll("Savings","Checking");
		addTypeHBox.getChildren().addAll(addTypeLabel, addTypeChoiceBox,
				getAccountTypeErrorLabel());
		

		// Add all the fields to the container to create the form.
		createDetailsContainer.getChildren().addAll(addAccountNumberHBox, addAccountHolderNameHBox, addBalanceHBox, addTypeHBox);

		// Create a button for user to return to the main scene when they've completed
		// an account and add an error label for the scene.
		HBox doneMenuBox = new HBox();
		Label accountCreationErrorLabel = new Label("");
		Button doneCreatingAccountButton = new Button("Done Creating Account");
		doneCreatingAccountButton
				.setOnAction(doneCreatingAccountEvent -> addAnAccount(mainScene, addAccountNumberTextField,
						addAccountHolderNameTextField, addBalanceTextField, accountCreationErrorLabel, addTypeChoiceBox));
		doneMenuBox.getChildren().addAll(doneCreatingAccountButton, accountCreationErrorLabel);
		createDetailsContainer.getChildren().add(doneMenuBox);

		// Load the account creation scene from the main scene.
		Scene createDetailsScene = new Scene(createDetailsContainer, 400, 100);
		getApplicationStage().setScene(createDetailsScene);

	}

	// The following method is called upon to add a new account to the list of
	// accounts opened and return the user to the main scene.

	void addAnAccount(Scene mainScene, TextField addAccountNumberTextField, TextField addAccountHolderNameTextField,
			TextField addBalanceTextField, Label accountCreationErrorLabel, ChoiceBox addTypeChoiceBox) {

		getAccountNumberErrorLabel().setText("");
		getAccountNameErrorLabel().setText("");
		getAccountBalanceErrorLabel().setText("");
		getAccountTypeErrorLabel().setText("");

		// Take the users current account number entry from the
		// addAccountNumberTextField TextField.
		String searchNumber = addAccountNumberTextField.getText();
		String searchName = addAccountHolderNameTextField.getText();
		String searchBalance = addBalanceTextField.getText();
		Object searchType= addTypeChoiceBox.getValue();

		if (isValidAccountNumber(searchNumber) != "") {
			getAccountNumberErrorLabel().setText(isValidAccountNumber(searchNumber));
		}

		if (isValidAccountName(searchName) != "") {
			getAccountNameErrorLabel().setText(isValidAccountName(searchName));
		}

		if (isValidBalance(searchBalance) != "") {
			getAccountBalanceErrorLabel().setText(isValidBalance(searchBalance));
		}
		
		if (searchType==null) {
			getAccountTypeErrorLabel().setText("Please enter a type");
		}

		if (isValidAccountNumber(searchNumber) == "" && isValidAccountName(searchName) == ""
				&& isValidBalance(searchBalance) == "" && searchType!=null) {
			// Convert the users balance entry from a string to double.
			double balanceValue = Double.parseDouble(addBalanceTextField.getText());

			// Create an account using the users inputed fields.
			Account bankAccount = new Account(addAccountNumberTextField.getText(),
					addAccountHolderNameTextField.getText(), balanceValue, (String) searchType);
			
			// Stores the number of duplicates of the requested account, if any.
			int existsSum = accountsRegistered.searchIfAccountExists(searchNumber);

			// If the list of registered accounts is empty add the new account directly.
			if (accountsRegistered.getBank().size() == 0) {
				getApplicationStage().setScene(mainScene);
				searchErrorLabel.setText("");

				accountsRegistered.addBankAccounts(bankAccount);

				System.out.println(accountsRegistered.getBank());

				accountNumberDisplay.setText("");
				accountHolderNameDisplay.setText("");
				balanceDisplay.setText("");
				typeDisplay.setText("");
			}

			else {

				// If there are no duplicates add the account.
				if (existsSum == 0) {
					
					
					
					searchErrorLabel.setText("");

					accountsRegistered.addBankAccounts(bankAccount);

					System.out.println(accountsRegistered.getBank());

					accountNumberDisplay.setText("");
					accountHolderNameDisplay.setText("");
					balanceDisplay.setText("");
					typeDisplay.setText("");
					
					if (bankAccount.getIsSavings()==false) {
						getApplicationStage().setScene(mainScene);
					}
					
					else {
						createSavingsScene(mainScene,bankAccount);
					}
				}

				// Else display an error message that an account with that number exists.
				else {
					accountCreationErrorLabel.setText("An account with this number exists.");
				}
			}
		}

	}

	@FXML

	void openAnAccountScene(ActionEvent event) {

		searchErrorLabel.setText("");

		// Stores the account the user enters in a variable.

		String search = searchAccountTextField.getText();

		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		}

		else {
			if (accountsRegistered.searchIfAccountExists(search) == 0) {
				searchErrorLabel.setText("No account found");
			}

			else {
				accountNumberDisplay.setText(accountsRegistered.accountSaver(search).getAccountNumber());
				accountHolderNameDisplay.setText(accountsRegistered.accountSaver(search).getLoginName());
				balanceDisplay.setText("$" + String.valueOf(accountsRegistered.accountSaver(search).getBalance()));
				
				if ((accountsRegistered.accountSaver(search).getIsSavings()==false)) {
					typeDisplay.setText("Checking");
				}
				
				else {
					typeDisplay.setText("Savings");
				}
			}
		}
	}

	@FXML

	void startWithdrawal() {
		String search = searchAccountTextField.getText();

		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		}
		
		else if (accountsRegistered.searchIfAccountExists(search)==0) {
			searchErrorLabel.setText("Account does not exist.");
		}

		else {
			// Set the default scene to the main landing scene
			Scene mainScene = getApplicationStage().getScene();

			// Create the containers for fields.
			VBox withdrawalContainer = new VBox();
			HBox withdrawalEntryBox = new HBox();
			withdrawalContainer.getChildren().add(withdrawalEntryBox);

			// Create the user input section and instructions.
			Label withdrawalInstructionLabel = new Label("Enter the amount you'd like to withdraw.");
			TextField withdrawalAmountTextField = new TextField();
			withdrawalEntryBox.getChildren().addAll(withdrawalInstructionLabel, withdrawalAmountTextField);

			// Create the done button and its functionality.
			HBox withdrawalDoneBox = new HBox();
			Label withdrawalErrorLabel = new Label("");
			Button doneWithdrawalButton = new Button("Withdraw");
			withdrawalDoneBox.getChildren().addAll(withdrawalErrorLabel, doneWithdrawalButton);
			withdrawalContainer.getChildren().add(withdrawalDoneBox);
			doneWithdrawalButton.setOnAction(doneWithdrawalEvent -> processWithdrawal(mainScene,
					withdrawalAmountTextField, withdrawalErrorLabel));

			// Load the account creation scene from the main scene.
			Scene withdrawalScene = new Scene(withdrawalContainer, 400, 100);
			getApplicationStage().setScene(withdrawalScene);
		}
	}

	void processWithdrawal(Scene mainScene, TextField withdrawalAmountTextField, Label withdrawalErrorLabel) {
		// Gather the inputed user account number and store it in the search variable.

		String search = withdrawalAmountTextField.getText();

		if (isValidBalance(search) == "") {
			// Create a new account to reference in the withdrawal method later.
			Account withdrawalAccount = accountsRegistered.accountSaver(searchAccountTextField.getText());

			if (withdrawalAccount.checkFunds(withdrawalAccount.getBalance(),
					Double.parseDouble(withdrawalAmountTextField.getText()))) {
				// Return the user to the main home screen
				getApplicationStage().setScene(mainScene);

				// Update the balance of the requested account and display the updated value.
				withdrawalAccount.withdraw(Double.parseDouble(withdrawalAmountTextField.getText()));
				balanceDisplay.setText("Updated: $" + String.valueOf(withdrawalAccount.getBalance()));
			}

			else {
				withdrawalErrorLabel.setText("Insufficient funds.");
			}
		}

		else {
			withdrawalErrorLabel.setText(isValidBalance(search));
		}
	}

	@FXML

	void startDeposit() {
		String search = searchAccountTextField.getText();
		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		} 
		
		else if (accountsRegistered.searchIfAccountExists(search)==0) {
			searchErrorLabel.setText("Account does not exist.");
		}
		
		else {
			// Set the default scene to the main landing scene
			Scene mainScene = getApplicationStage().getScene();

			// Create the containers for fields.
			VBox depositContainer = new VBox();
			HBox depositEntryBox = new HBox();
			depositContainer.getChildren().add(depositEntryBox);

			// Create the user input section and instructions.
			Label depositInstructionLabel = new Label("Enter the amount you'd like to deposit.");
			TextField depositAmountTextField = new TextField();
			depositEntryBox.getChildren().addAll(depositInstructionLabel, depositAmountTextField);

			// Create the done button and its functionality.
			HBox depositDoneBox = new HBox();
			Label depositErrorLabel = new Label("");
			Button doneDepositButton = new Button("Deposit");
			depositDoneBox.getChildren().addAll(doneDepositButton,depositErrorLabel);
			depositContainer.getChildren().add(depositDoneBox);
			doneDepositButton.setOnAction(
					doneWithdrawalEvent -> processDeposit(mainScene, depositAmountTextField, depositErrorLabel));
			// Load the account creation scene from the main scene.
			Scene depositScene = new Scene(depositContainer, 400, 100);
			getApplicationStage().setScene(depositScene);
		}
	}

	void processDeposit(Scene mainScene, TextField depositAmountTextField, Label depositErrorLabel) {

		if (isValidBalance(depositAmountTextField.getText()) == "") {
			// Return the user to the main home screen
			getApplicationStage().setScene(mainScene);

			// Gather the inputed user account number and store it in the search variable.
			String search = searchAccountTextField.getText();

			// Create a new account to reference in the deposit method later.
			Account depositAccount = accountsRegistered.accountSaver(search);

			// Update the balance of the requested account and display the updated value.
			depositAccount.deposit(Double.parseDouble(depositAmountTextField.getText()));
			balanceDisplay.setText("Updated: $" + String.valueOf(depositAccount.getBalance()));
		}

		else {
			depositErrorLabel.setText(isValidBalance(depositAmountTextField.getText()));

		}
	}

	@FXML

	void startTransfer() {
		String search = searchAccountTextField.getText();

		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		}
		
		else if (accountsRegistered.searchIfAccountExists(search)==0) {
			searchErrorLabel.setText("Account does not exist.");
		}

		else {
			// Set the default scene to the main landing scene
			Scene mainScene = getApplicationStage().getScene();

			// Create the containers for fields.
			VBox transferContainer = new VBox();
			HBox transferEntryBox = new HBox();
			HBox transferAccountBox = new HBox();
			transferContainer.getChildren().addAll(transferAccountBox, transferEntryBox);

			// Create the user account input section and instructions.
			Label transferAccountInstructionLabel = new Label("Enter the account number you'd like to transfer to.");
			TextField transferAccountTextField = new TextField();
			Label transferAccountErrorLabel = new Label("");
			transferAccountBox.getChildren().addAll(transferAccountInstructionLabel, transferAccountTextField,
					transferAccountErrorLabel);

			// Create the user transfer input section and instructions.
			Label transferInstructionLabel = new Label("Enter the amount you'd like to transfer.");
			TextField transferAmountTextField = new TextField();
			transferEntryBox.getChildren().addAll(transferInstructionLabel, transferAmountTextField);

			// Create the done button and its functionality.
			HBox transferDoneBox = new HBox();
			Label transferErrorLabel = new Label("");
			Button doneTransferButton = new Button("Transfer");
			transferDoneBox.getChildren().addAll(doneTransferButton, transferErrorLabel);
			transferContainer.getChildren().add(transferDoneBox);
			doneTransferButton.setOnAction(donetransferEvent -> processTransfer(mainScene, transferAccountTextField,
					transferAmountTextField, transferAccountErrorLabel, transferErrorLabel));

			// Load the account creation scene from the main scene.
			Scene transferScene = new Scene(transferContainer, 400, 100);
			getApplicationStage().setScene(transferScene);
		}
	}

	void processTransfer(Scene mainScene, TextField transferAccountTextField, TextField transferAmountTextField,
			Label transferAccountErrorLabel, Label transferErrorLabel) {

		// Check if the account the user would like to transfer to exists.
		if (isValidAccountNumber(transferAccountTextField.getText()) != "") {
			transferAccountErrorLabel.setText(isValidAccountNumber(transferAccountTextField.getText()));
		}

		else {

			if (accountsRegistered.searchIfAccountExists(transferAccountTextField.getText()) == 0) {
				transferAccountErrorLabel.setText("Account not found.");
			}

			else {

				Account transferAccount = accountsRegistered.accountSaver(transferAccountTextField.getText());

				if (transferAccount.checkFunds(transferAccount.getBalance(),
						Double.parseDouble(transferAmountTextField.getText())) == false) {

					// Return the user to the main home screen
					getApplicationStage().setScene(mainScene);

					// Store the accounts involved in the transfer in their respective variables.
					Account accountToTrasnferTo = accountsRegistered.accountSaver(transferAccountTextField.getText());
					Account currentAccount = accountsRegistered.accountSaver(searchAccountTextField.getText());

					// Update the balance of the requested account and display the updated value.
					currentAccount.transfer(accountToTrasnferTo,
							(Double.parseDouble(transferAmountTextField.getText())));
					balanceDisplay.setText("Updated: $" + String.valueOf(currentAccount.getBalance()));
				}

				else {
					transferErrorLabel.setText("Insufficient funds.");
				}
			}
		}

	}
	
	
	void createSavingsScene (Scene mainScene, Account bankAccount) {
		
		
		// Create the containers for fields.
		VBox savingsContainer = new VBox();
		HBox savingsInterestBox = new HBox();
		HBox savingsPeriodBox = new HBox();
		savingsContainer.getChildren().addAll(savingsInterestBox, savingsPeriodBox);

		// Create the user input section and instructions.
		Label savingsInstructionLabel = new Label("Select an interest rate percentage.");
		ChoiceBox savingsAmountChoiceBox = new ChoiceBox();
		savingsAmountChoiceBox.getItems().addAll("1","2","5","8");
		savingsInterestBox.getChildren().addAll(savingsInstructionLabel, savingsAmountChoiceBox, interestSelectionErrorLabel);
		
		Label savingsInstructionLabelTwo = new Label("Enter the number of years to calculate your investment.");
		TextField savingsAmountTextField = new TextField();
		savingsPeriodBox.getChildren().addAll(savingsInstructionLabelTwo, savingsAmountTextField,periodInputErrorLabel);
		
		HBox savingsDoneBox = new HBox();
		Label savingsErrorLabel = new Label("");
		Button savingsDoneButton = new Button("Done");
		savingsDoneBox.getChildren().addAll(savingsDoneButton, savingsErrorLabel);
		savingsContainer.getChildren().add(savingsDoneBox);
		
		savingsDoneButton.setOnAction(donetransferEvent -> processSavingsScene(mainScene, savingsAmountChoiceBox,
				savingsAmountTextField, savingsErrorLabel, bankAccount));
		
		Scene savingsMenuScene= new Scene(savingsContainer);
		getApplicationStage().setScene(savingsMenuScene);
		
		
		
	}
	
	void processSavingsScene (Scene mainScene, ChoiceBox savingsAmountChoiceBox, TextField savingsAmountTextField, Label savingsErrorLabel, Account bankAccount) {
			
			interestSelectionErrorLabel.setText("");
			periodInputErrorLabel.setText("");
			
			
			Object interestRateSelection= savingsAmountChoiceBox.getValue();
			
			if (interestRateSelection==null) {
				interestSelectionErrorLabel.setText("Please select an interest rate.");
			}
			
			if(isValidPeriod(savingsAmountTextField.getText())!="") {
				periodInputErrorLabel.setText(isValidPeriod(savingsAmountTextField.getText()));
			}
		
			
			if(interestRateSelection!=null && isValidPeriod(savingsAmountTextField.getText())=="") {
				// Return the user to the main home screen
				getApplicationStage().setScene(mainScene);
	
				// Gather the inputed user account number and store it in the search variable.
				int interestRateSelected =Integer.valueOf((((String) savingsAmountChoiceBox.getValue())));
				int periodSpecified= Integer.valueOf(savingsAmountTextField.getText());
				
				// Create a new account to reference in the deposit method later.
				SavingsAccount savingAccount= new SavingsAccount (bankAccount, periodSpecified,interestRateSelected);
				
				accountsRegistered.removeBankAccounts(bankAccount);
				
				accountsRegistered.addBankAccounts(savingAccount);
				
				System.out.println(accountsRegistered.getBank());
			}
			
		
		
	}
	

	public Label getAccountNumberErrorLabel() {
		return accountNumberErrorLabel;
	}

	public void setAccountNumberErrorLabel(Label accountNumberErrorLabel) {
		this.accountNumberErrorLabel = accountNumberErrorLabel;
	}

	public Label getAccountNameErrorLabel() {
		return accountNameErrorLabel;
	}

	public void setAccountNameErrorLabel(Label accountNameErrorLabel) {
		this.accountNameErrorLabel = accountNameErrorLabel;
	}

	Label getAccountBalanceErrorLabel() {
		return accountBalanceErrorLabel;
	}

	void setAccountBalanceErrorLabel(Label accountBalanceErrorLabel) {
		this.accountBalanceErrorLabel = accountBalanceErrorLabel;
	}

	public Label getAccountTypeErrorLabel() {
		return accountTypeErrorLabel;
	}

	public void setAccountTypeErrorLabel(Label accountTypeErrorLabel) {
		this.accountTypeErrorLabel = accountTypeErrorLabel;
	}

	public Label getInterestSelectionErrorLabel() {
		return interestSelectionErrorLabel;
	}

	public void setInterestSelectionErrorLabel(Label interestSelectionErrorLabel) {
		this.interestSelectionErrorLabel = interestSelectionErrorLabel;
	}

	public Label getPeriodInputErrorLabel() {
		return periodInputErrorLabel;
	}

	public void setPeriodInputErrorLabel(Label periodInputErrorLabel) {
		this.periodInputErrorLabel = periodInputErrorLabel;
	}


}
