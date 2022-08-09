package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingApplicationController {

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

	@FXML

	private Label futureValue;

	// Variables and FXML elements used throughout the program, hence declared
	// outside of methods for accessibility.
	private Stage applicationStage;
	private Label accountNumberErrorLabel = new Label();
	private Label accountNameErrorLabel = new Label();
	private Label accountBalanceErrorLabel = new Label();
	private Label accountTypeErrorLabel = new Label();
	private Label interestSelectionErrorLabel = new Label();
	private Label periodInputErrorLabel = new Label();
	private Label compoundInputErrorLabel = new Label();
	private double thisIsFutureValue;

	// The list of bank accounts opened in the application.
	private Bank accountsRegistered = new Bank();

	// Setter and getter for application stage variable.
	public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}

	/**
	 * Validates if a string input demonstrates the required formatting criteria for
	 * an all-numeric, 8 digit banking account number. Returns relevant error
	 * messaging if criteria is not met.
	 * 
	 * @param userNumber a string input from the user representing a given account
	 *                   number.
	 * @return error message, a string that is either empty, indicating there are no
	 *         errors in the input or an error message specific to the formatting
	 *         violation displayed by inputed account number.
	 */
	public String isValidAccountNumber(String accountNumber) {

		// Initialize the error message and default it to empty.
		String errorMessage = "";

		// Check if the user has provided an input.
		if (accountNumber == "") {
			errorMessage = "Please enter an account number.";
		}

		// Ensure the input is at least 8 digits in length.
		else if (accountNumber.length() != 8) {
			errorMessage = "Your account number must be 8 characters in length.";
		}

		// Ensure the input is all numeric or alert the user of characters in the input
		// causing the error.
		for (char c : accountNumber.toCharArray()) {
			if ((!Character.isDigit(c))) {
				errorMessage = ("Account number should only contain numbers. " + '\n' + "Don't include the character: "
						+ c);
			}
		}

		return errorMessage;
	}

	/**
	 * Validates if a string input demonstrates the required formatting criteria for
	 * an all-alphabetical account holder name. Returns relevant error messaging if
	 * criteria is not met.
	 * 
	 * @param accountHolder a string input from the user representing their name.
	 * @return error message, a string that is either empty, indicating there are no
	 *         errors in the input or an error message specific to the formatting
	 *         violation displayed by inputed account holder name.
	 */

	public String isValidAccountName(String accountHolderName) {

		// Initialize the error message and default it to empty.
		String errorMessage = "";

		// Check if the user has provided an input.
		if (accountHolderName == "") {
			errorMessage = "Please enter a name.";
		}

		// Ensure the input is all alphabetical or alert the user of characters in the
		// input
		// causing an error.
		for (char c : accountHolderName.toCharArray()) {
			if ((!Character.isAlphabetic(c)) && c != ' ') {
				errorMessage = ("Account name should only contain letters. " + '\n' + "Don't include the character: "
						+ c);
			}
		}

		return errorMessage;
	}

	/**
	 * Validates if a string input demonstrates the required formatting criteria for
	 * a set number of years. Returns relevant error messaging if criteria is not
	 * met.
	 * 
	 * @param accountPeriod a string input from the user representing the time
	 *                      period of an investment.
	 * @return error message, a string that is either empty, indicating there are no
	 *         errors in the input or an error message specific to the formatting
	 *         violation displayed by the inputed account period.
	 */

	public String isValidPeriod(String accountPeriod) {

		// Default error message to an empty string.
		String errorMessage = "";

		// Check if the user is actually providing an input.
		if (accountPeriod == "") {
			errorMessage = "Please enter a number of years.";
		}

		// Ensure that the string represents an all numeric input signifying a valid
		// number of years or return the characters in the input causing an error.
		for (char c : accountPeriod.toCharArray()) {
			if ((!Character.isDigit(c))) {
				errorMessage = ("Period amount should only contain numbers. " + '\n' + "Don't include the character: "
						+ c);
			}
		}

		return errorMessage;
	}

	/**
	 * Validates if a string input demonstrates the required formatting criteria for
	 * an input balance. Returns relevant error messaging if criteria is not met.
	 * 
	 * @param inputBalance a string input from the user representing a dollar value.
	 * @return error message, a string that is either empty, indicating there are no
	 *         errors in the input or an error message specific to the formatting
	 *         violation displayed by the inputed balance/dollar value.
	 */

	public String isValidBalance(String inputBalance) {

		// Default the error message to an empty string.
		String errorMessage = "";

		// A count variable indicating the number of decimal occurrences.
		int count = 0;

		// Ensure whether the user actually has passed an input to the method.
		if (inputBalance == "") {
			errorMessage = "Please enter a dollar amount.";
		}

		// check that the user entered only a numeric value with at most one decimal or
		// identify the characters in the input throwing an error.
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
			// number.
			if ((!Character.isDigit(c)) && c != '.') {

				errorMessage = ("Balance should only contain numeric values. " + '\n' + "Don't include the character: "
						+ c);
			}

		}

		return errorMessage;
	}

	/**
	 * Generate an account creation scene for a user to create a new savings or
	 * checking account with an associated account number, name and starting
	 * balance. The method is called when the user clicks the createAccount button
	 * in the GUI.
	 * 
	 */

	@FXML

	void createAccountsScene(ActionEvent creationButtonClicked) {

		// Set the default scene to the main landing scene
		Scene mainScene = getApplicationStage().getScene();

		// Create a container to house user input fields.
		VBox createDetailsContainer = new VBox();

		// Create fields for the user to input their account number, name, account type
		// and starting
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
		addTypeChoiceBox.getItems().addAll("Savings", "Checking");
		addTypeHBox.getChildren().addAll(addTypeLabel, addTypeChoiceBox, getAccountTypeErrorLabel());

		// Add all the fields to the container to create the form.
		createDetailsContainer.getChildren().addAll(addAccountNumberHBox, addAccountHolderNameHBox, addBalanceHBox,
				addTypeHBox);

		// Create a button for user to return to the main scene when they've completed
		// an account and add an error label for the scene for input validation
		// messages.
		HBox doneMenuBox = new HBox();
		Label accountCreationErrorLabel = new Label("");
		Button doneCreatingAccountButton = new Button("Done Creating Account");
		doneCreatingAccountButton.setOnAction(doneCreatingAccountEvent -> addAnAccount(mainScene,
				addAccountNumberTextField, addAccountHolderNameTextField, addBalanceTextField,
				accountCreationErrorLabel, addTypeChoiceBox));
		doneMenuBox.getChildren().addAll(doneCreatingAccountButton, accountCreationErrorLabel);
		createDetailsContainer.getChildren().add(doneMenuBox);

		// Load the account creation scene from the main scene.
		Scene createDetailsScene = new Scene(createDetailsContainer, 620, 150);
		getApplicationStage().setScene(createDetailsScene);

	}

	/**
	 * Validates each user input. If all inputs are valid the method creates an
	 * account with the needed parameters and checks if it already exists in a list
	 * of registered accounts. If the account is not a duplicate it is added to the
	 * list and the user is returned to the main scene. Otherwise a relevant
	 * duplicate account error message is provided.
	 * 
	 * @param mainScene                      the main landing scene the user
	 *                                       accesses when starting the program to
	 *                                       create, open or perform operations on
	 *                                       an account.
	 * @param addAccountNumberTextField      a text field containing an account
	 *                                       number string.
	 * @param addAccountHolderNameTextFielda a text field containing an account
	 *                                       holder name string.
	 * @param addBalanceTextField            a text field containing a starting
	 *                                       balance string.
	 * @param accountCreationErrorLabel      a label to display error messages
	 *                                       pertaining to input validation on the
	 *                                       various fields processed when creating
	 *                                       an account.
	 * @param addTypeChoiceBox               a choice box containing values for the
	 *                                       type of account a user wishes to open
	 *                                       (checking or savings).
	 */

	void addAnAccount(Scene mainScene, TextField addAccountNumberTextField, TextField addAccountHolderNameTextField,
			TextField addBalanceTextField, Label accountCreationErrorLabel, ChoiceBox addTypeChoiceBox) {

		// Set all error labels to default to an empty string.
		getAccountNumberErrorLabel().setText("");
		getAccountNameErrorLabel().setText("");
		getAccountBalanceErrorLabel().setText("");
		getAccountTypeErrorLabel().setText("");

		// Store the requested inputs needed to create an account in their own
		// respective variables signifying an account number, holder name, account type
		// and starting balance.
		String searchNumber = addAccountNumberTextField.getText();
		String searchName = addAccountHolderNameTextField.getText();
		String searchBalance = addBalanceTextField.getText();
		Object searchType = addTypeChoiceBox.getValue();

		// Validate all user inputs in each respective field and provide error messages
		// if required.
		if (isValidAccountNumber(searchNumber) != "") {
			getAccountNumberErrorLabel().setText(isValidAccountNumber(searchNumber));
		}

		if (isValidAccountName(searchName) != "") {
			getAccountNameErrorLabel().setText(isValidAccountName(searchName));
		}

		if (isValidBalance(searchBalance) != "") {
			getAccountBalanceErrorLabel().setText(isValidBalance(searchBalance));
		}

		if (searchType == null) {
			getAccountTypeErrorLabel().setText("Please enter a type");
		}

		// Process the user inputs further if there are no formatting issues in each
		// input.
		if (isValidAccountNumber(searchNumber) == "" && isValidAccountName(searchName) == ""
				&& isValidBalance(searchBalance) == "" && searchType != null) {

			// Check if the starting balance inputed is greater than $0.00 with a relevant
			// error prompt.
			if (Double.parseDouble(searchBalance) == 0.0) {
				getAccountBalanceErrorLabel().setText("Enter a non-zero amount.");
			}

			// Process all user inputs to form a new account object to validated and added
			// to the list of accounts registered.
			else {

				// Convert the users balance entry from a string to double.
				double balanceValue = Double.parseDouble(addBalanceTextField.getText());

				// Ensure an account of types savings account has validated input not falling
				// short of the minimum required balance or throw an error message.
				if (searchType == "Savings" && balanceValue < 1000.0) {
					getAccountBalanceErrorLabel()
							.setText("Savings accounts must have a balance of"+"\n"+"at least $1000 to initialize.");
				}

				else {

					// Create an account using the users inputed fields.
					Account bankAccount = new Account(addAccountNumberTextField.getText(),
							addAccountHolderNameTextField.getText(), balanceValue, (String) searchType);

					// Stores the number of duplicates of the requested account, if any.
					int existsSum = getAccountsRegistered().searchIfAccountExists(searchNumber);

					// If the list of registered accounts is empty add the new account directly and
					// return the user to the main scene.
					if (getAccountsRegistered().getBank().size() == 0) {
						getAccountsRegistered().addBankAccounts(bankAccount);

						// Print the list of accounts to the console to demonstrate that an account was
						// added.
						System.out.println(getAccountsRegistered().getBank());

						// Reset error labels.
						searchErrorLabel.setText("");
						accountNumberDisplay.setText("");
						accountHolderNameDisplay.setText("");
						balanceDisplay.setText("");
						typeDisplay.setText("");
						futureValue.setText("");

						if (bankAccount.getIsSavings() == true) {
							createSavingsScene(mainScene, bankAccount);
						}

						else {
							getApplicationStage().setScene(mainScene);
						}

					}

					// Validate the account before adding to the list of accounts (as it is not
					// empty).
					else {

						// If there are no duplicates add the account.
						if (existsSum == 0) {

							getAccountsRegistered().addBankAccounts(bankAccount);

							// Print the list of accounts to the console to demonstrate that an account was
							// added.
							System.out.println(getAccountsRegistered().getBank());

							// Reset error labels.
							searchErrorLabel.setText("");
							accountNumberDisplay.setText("");
							accountHolderNameDisplay.setText("");
							balanceDisplay.setText("");
							typeDisplay.setText("");
							futureValue.setText("");

							// Return the user to the main scene if they choose a checking account or to the
							// investment calculator scene for a savings account.
							if (bankAccount.getIsSavings() == false) {
								getApplicationStage().setScene(mainScene);
							}

							else {
								createSavingsScene(mainScene, bankAccount);
							}
						}

						// Display an error message that an account with that number exists if a user
						// tries to duplicate an account.
						else {
							accountCreationErrorLabel.setText("An account with this number exists.");
						}

					}
				}
			}
		}

	}

	/**
	 * Display all relevant parameters of an account upon a user searching for the
	 * account number. Validate the input of the account number and ensure it
	 * exists, otherwise display relevant error message.
	 * 
	 * @param openAnAccountButtonClicked
	 */
	@FXML

	void openAnAccountScene(ActionEvent openAnAccountButtonClicked) {

		// Reset error label.
		searchErrorLabel.setText("");

		// Stores the account the user enters in a variable.
		String search = searchAccountTextField.getText();

		// Validates the account number input providing relevant error message if
		// validation fails.
		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		}

		// Proceed to display the account provided a valid user input of account number
		// in the search field.

		else {

			// Check if the account exists.
			if (getAccountsRegistered().searchIfAccountExists(search) == 0) {
				searchErrorLabel.setText("No account found");
			}

			// Display the relevant account parameters.
			else {
				accountNumberDisplay.setText(getAccountsRegistered().accountSaver(search).getAccountNumber());
				accountHolderNameDisplay.setText(getAccountsRegistered().accountSaver(search).getLoginName());
				balanceDisplay
						.setText("$" + String.format("%.2f",(getAccountsRegistered().accountSaver(search).getBalance())));

				// Display the future value parameter for savings accounts only.
				if ((getAccountsRegistered().accountSaver(search).getIsSavings() == false)) {
					typeDisplay.setText("Checking");
					futureValue.setText("Not available for checking accounts.");
				}

				else {
					SavingsAccount temporary = (SavingsAccount) getAccountsRegistered().accountSaver(search);
					typeDisplay.setText("Savings");
					futureValue.setText("$" + String.format("%.2f", temporary.getFutureValue()));
				}
			}
		}
	}

	/**
	 * Validate user input of an account number and create a withdrawal scene with
	 * fields for user input of an amount to withdraw.
	 */
	@FXML

	void startWithdrawal() {

		// Store the user account number search as a string.
		String search = searchAccountTextField.getText();

		// Validate the user account number input.
		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		}

		// Search if the account exists in the list of registered accounts.
		else if (getAccountsRegistered().searchIfAccountExists(search) == 0) {
			searchErrorLabel.setText("Account does not exist.");
		}

		// If the account exists and the input is valid create and display the
		// withdrawal scene.
		else {

			// Set the default scene to the main landing scene
			Scene mainScene = getApplicationStage().getScene();

			// Create the containers for fields.
			VBox withdrawalContainer = new VBox();
			HBox withdrawalEntryBox = new HBox();
			withdrawalContainer.getChildren().add(withdrawalEntryBox);

			// Create the user input section and instructions.
			Label withdrawalInstructionLabel = new Label("Enter the amount you'd like to withdraw.");
			Label withdrawNotice = new Label("Withdrawls on savings account incur $20 fee.");
			TextField withdrawalAmountTextField = new TextField();
			withdrawalEntryBox.getChildren().addAll(withdrawalInstructionLabel, withdrawalAmountTextField);

			withdrawalContainer.getChildren().add(withdrawNotice);

			// Create the done button and its functionality.
			HBox withdrawalDoneBox = new HBox();
			Label withdrawalErrorLabel = new Label("");
			Button doneWithdrawalButton = new Button("Withdraw");
			withdrawalDoneBox.getChildren().addAll(doneWithdrawalButton,withdrawalErrorLabel);
			withdrawalContainer.getChildren().add(withdrawalDoneBox);
			doneWithdrawalButton.setOnAction(doneWithdrawalEvent -> processWithdrawal(mainScene,
					withdrawalAmountTextField, withdrawalErrorLabel));

			// Load the withdrawal scene from the main scene.
			Scene withdrawalScene = new Scene(withdrawalContainer, 715, 150);
			getApplicationStage().setScene(withdrawalScene);
		}
	}

	/**
	 * Validate the user inputed withdrawal amount and process the withdrawal after
	 * ensuring an account has sufficient funds. If a savings account has
	 * insufficient funds return the user to the main scene and remind the user of a
	 * necessary minimum balance. Alternatively for checking accounts charge an
	 * overdraft fee. For successful withdrawals on savings accounts updates future
	 * value calculation. Display relevant error messages for withdrawal amount
	 * inputs and in insufficient funds instances.
	 * 
	 * @param mainScene                 the default scene the user interacts with
	 *                                  when they open the application
	 * @param withdrawalAmountTextField a text field containing a dollar amount as a
	 *                                  string.
	 * @param withdrawalErrorLabel      a label to display error messages, if any.
	 */
	void processWithdrawal(Scene mainScene, TextField withdrawalAmountTextField, Label withdrawalErrorLabel) {

		// Reset error labels.
		searchErrorLabel.setText("");

		// Gather the inputed withdrawal amount and store it in a separate variable.
		String searchAmount = withdrawalAmountTextField.getText();

		// Validate user inputs.
		if (isValidBalance(searchAmount) == "") {

			if (Double.parseDouble(searchAmount) == 0.0) {
				getApplicationStage().setScene(mainScene);
				searchErrorLabel.setText("Enter a non-zero amount.");
			}

			else {

				// Create a new account to reference in the withdrawal method later.
				Account withdrawalAccount = getAccountsRegistered().accountSaver(searchAccountTextField.getText());

				// Check for sufficient funds.
				if (withdrawalAccount.checkSufficientFunds(Double.parseDouble(withdrawalAmountTextField.getText()))) {

					if (withdrawalAccount.getIsSavings() == false) {
						// Return the user to the main scene.
						getApplicationStage().setScene(mainScene);

						// Update the balance of the requested account and display the updated value.
						withdrawalAccount.withdraw(Double.parseDouble(withdrawalAmountTextField.getText()));
						balanceDisplay.setText("Updated: $" + String.format("%.2f",withdrawalAccount.getBalance()));

					}

					// Check for withdrawal limits on savings accounts.
					else {

						// Display error message if the user exceeds the max number of withdrawals.
						if (SavingsAccount.getNumberOfWithdraw() > 3) {
							getApplicationStage().setScene(mainScene);
							searchErrorLabel.setText("Cannot withdraw from a savings account over 4 times.");
						}

						else {

							// Return the user to the main home screen
							getApplicationStage().setScene(mainScene);

							// Update the balance of the requested account and display the updated value.
							withdrawalAccount.withdraw(Double.parseDouble(withdrawalAmountTextField.getText()));
							balanceDisplay.setText("Updated: $" + String.format("%.2f",withdrawalAccount.getBalance()));

							// Update and display the future value of the investment account with the
							// updated balance.
							SavingsAccount tester = (SavingsAccount) getAccountsRegistered()
									.accountSaver(searchAccountTextField.getText());

							tester.setFutureValue(tester.futureValueCalculator(tester));
							System.out.println("Future Value: " + tester.getFutureValue());

							futureValue.setText("Updated: $" + String.format("%.2f", tester.getFutureValue()));
						}
					}

				}

				// If an account lacks the sufficient funds to withdraw initiate error
				// processing.
				else {

					// Return the user to the main scene.
					applicationStage.setScene(mainScene);

					if (withdrawalAccount.getIsSavings() == true) {
						searchErrorLabel.setText("Insufficient funds. Savings account cannot fall below $1000.");
					}

					else {
						searchErrorLabel.setText("Insufficient funds. Overdraft fee charged");
						withdrawalAccount.overdraftFee();
						balanceDisplay.setText("Updated: $" + String.format("%.2f",withdrawalAccount.getBalance()));
					}
				}
			}
		}

		// Display a relevant error message for an invalid withdrawal amount input.
		else {
			withdrawalErrorLabel.setText(isValidBalance(searchAmount));
		}

	}

	/**
	 * Validate user input of an account number and create a deposit scene with
	 * fields for user input of an amount to deposit.
	 * 
	 */
	@FXML

	void startDeposit() {

		// Store the user account number search in a string variable
		String search = searchAccountTextField.getText();

		// Ensure the account number inputed is a valid input
		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		}

		// Ensure the account searched exists in the list of registered accounts.
		else if (getAccountsRegistered().searchIfAccountExists(search) == 0) {
			searchErrorLabel.setText("Account does not exist.");
		}

		// If the user input is valid and the account exists populate the deposit scene.
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
			depositDoneBox.getChildren().addAll(doneDepositButton, depositErrorLabel);
			depositContainer.getChildren().add(depositDoneBox);
			doneDepositButton.setOnAction(
					doneWithdrawalEvent -> processDeposit(mainScene, depositAmountTextField, depositErrorLabel));
			// Load the account creation scene from the main scene.
			Scene depositScene = new Scene(depositContainer, 715, 150);
			getApplicationStage().setScene(depositScene);
		}
	}

	/**
	 * Validate the user inputed deposit amount and process the deposit to return
	 * the user to the main scene with an updated balance value. If the account is
	 * of type savings update the future value calculation. Display relevant error
	 * messages for deposit amount inputs.
	 * 
	 * @param mainScene              the default scene the user interacts with when
	 *                               they open the application
	 * @param depositAmountTextField a text field containing a dollar amount as a
	 *                               string.
	 * @param depositErrorLabel      a label to display error messages, if any.
	 */
	void processDeposit(Scene mainScene, TextField depositAmountTextField, Label depositErrorLabel) {

		// Check if deposit amount inputed is a valid input.
		if (isValidBalance(depositAmountTextField.getText()) == "") {

			// Ensure the user inputs a non-zero deposit value.
			if (Double.parseDouble(depositAmountTextField.getText()) == 0.0) {
				getApplicationStage().setScene(mainScene);
				searchErrorLabel.setText("Enter a non-zero amount.");
			}

			// If the inputed deposit amount is a valid input process the deposit to update
			// account balance and return the user to main scene.
			else {

				// Return the user to the main home screen
				getApplicationStage().setScene(mainScene);

				// Gather the inputed user account number and store it in the search variable.
				String search = searchAccountTextField.getText();

				// Create a new account to reference in the deposit method later.
				Account depositAccount = getAccountsRegistered().accountSaver(search);

				// Update the balance of the requested account and display the updated value.
				depositAccount.deposit(Double.parseDouble(depositAmountTextField.getText()));
				balanceDisplay.setText("Updated: $" + String.format("%.2f",depositAccount.getBalance()));

				// Update future value calculation for saving account deposits
				if (depositAccount.getIsSavings() == true) {

					// Cast the given account to a savings account type to process future value.
					SavingsAccount tester = (SavingsAccount) getAccountsRegistered().accountSaver(search);

					// Update and display the new future value calculated with changes in the
					// account balance.
					tester.setFutureValue(tester.futureValueCalculator(tester));
					System.out.println("Future Value: " + tester.getFutureValue());

					futureValue.setText("Updated: $" + String.format("%.2f", tester.getFutureValue()));
				}
			}
		}

		// Display relevant error messages for invalid deposit amount inputs.
		else {
			depositErrorLabel.setText(isValidBalance(depositAmountTextField.getText()));

		}
	}

	/**
	 * Validate user input of an account number and create a transfer scene with
	 * fields for user input of an amount to transfer and account to transfer to.
	 * 
	 */

	@FXML

	void startTransfer() {

		// Store the account number the user would like to transfer from as a String
		// variable.
		String search = searchAccountTextField.getText();

		// Validate the user input of an account number and display the relevant error
		// message.
		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		}

		// Ensure the account the user would like to search from exists.
		else if (getAccountsRegistered().searchIfAccountExists(search) == 0) {
			searchErrorLabel.setText("Account does not exist.");
		}

		// If an account input is valid and the account exists create and open the
		// transfer scene.
		else {
			// Set the default scene to the main landing scene
			Scene mainScene = getApplicationStage().getScene();

			// Create the containers, labels and elements for input fields.
			VBox transferContainer = new VBox();
			HBox transferEntryBox = new HBox();
			HBox transferAccountBox = new HBox();
			transferContainer.getChildren().addAll(transferAccountBox, transferEntryBox);

			Label transferAccountInstructionLabel = new Label("Enter the account number you'd like to transfer to.");
			TextField transferAccountTextField = new TextField();
			Label transferAccountErrorLabel = new Label("");
			transferAccountBox.getChildren().addAll(transferAccountInstructionLabel, transferAccountTextField,
					transferAccountErrorLabel);

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
			Scene transferScene = new Scene(transferContainer, 715, 150);
			getApplicationStage().setScene(transferScene);
		}
	}

	/**
	 * Validates the inputed account number a user would like to transfer funds to
	 * and the inputed balance amount. Displays relevant error messages on invalid
	 * inputs. Ensures the account the funds are being transfered from has
	 * sufficient funds (displaying error messages and charging an overdraft fee on
	 * checking account type if funds are insufficient). Given valid user input and
	 * sufficient funds in the account being transfered from, updates balance value
	 * in both accounts involved and updates future value on savings accounts type.
	 * Following a transfer the method returns the user to the main scene with
	 * updated balance amount displays (and future value display for savings
	 * accounts).
	 * 
	 * 
	 * 
	 * @param mainScene                 the default scene the user interacts with
	 *                                  when they open the application.
	 * @param transferAccountTextField  a text field containing an account number to
	 *                                  transfer funds to.
	 * @param transferAmountTextField   a text field containing a dollar value to
	 *                                  transfer.
	 * @param transferAccountErrorLabel a label to display errors pertaining to an
	 *                                  account number input, if any.
	 * @param transferErrorLabel        a label to display general errors that occur
	 *                                  when transferring funds.
	 */

	void processTransfer(Scene mainScene, TextField transferAccountTextField, TextField transferAmountTextField,
			Label transferAccountErrorLabel, Label transferErrorLabel) {

		// Reset error label.
		searchErrorLabel.setText("");

		// Valid user inputs.
		if (isValidAccountNumber(transferAccountTextField.getText()) != "") {
			transferAccountErrorLabel.setText(isValidAccountNumber(transferAccountTextField.getText()));
		}

		if (isValidBalance(transferAmountTextField.getText()) != "") {
			transferErrorLabel.setText(isValidBalance(transferAmountTextField.getText()));
		}

		else if (isValidAccountNumber(transferAccountTextField.getText()) == ""
				&& isValidBalance(transferAmountTextField.getText()) == "") {

			// Ensure the user inputs a non zero amount to transfer.
			if (Double.parseDouble(transferAmountTextField.getText()) == 0.0) {
				getApplicationStage().setScene(mainScene);
				searchErrorLabel.setText("Enter a non-zero amount.");
			}

			else {

				// Search if the account already exists.
				if (getAccountsRegistered().searchIfAccountExists(transferAccountTextField.getText()) == 0) {
					getApplicationStage().setScene(mainScene);
					searchErrorLabel.setText("Account not found.");
				}

				else {

					// Store the searched account.
					Account currentAccount = getAccountsRegistered().accountSaver(searchAccountTextField.getText());

					// Check if the account has sufficient funds.
					if (currentAccount
							.checkSufficientFunds(Double.parseDouble(transferAmountTextField.getText())) == true) {

						// Return the user to the main home screen
						getApplicationStage().setScene(mainScene);

						// Store the account to transfer to.
						Account accountToTrasnferTo = getAccountsRegistered()
								.accountSaver(transferAccountTextField.getText());

						// Update the balance of the requested account and display the updated value.
						currentAccount.transfer(accountToTrasnferTo,
								(Double.parseDouble(transferAmountTextField.getText())));
						balanceDisplay.setText("Updated: $" + String.format("%.2f",currentAccount.getBalance()));

						// Update the future value calculation(s).

						if (currentAccount.getIsSavings() == true) {
							SavingsAccount tester = (SavingsAccount) getAccountsRegistered()
									.accountSaver(searchAccountTextField.getText());

							tester.setFutureValue(tester.futureValueCalculator(tester));
							System.out.println("Future Value: " + tester.getFutureValue());

							futureValue.setText("Updated: $" + String.format("%.2f", tester.getFutureValue()));
						}

						if (accountToTrasnferTo.getIsSavings() == true) {
							SavingsAccount testerTwo = (SavingsAccount) getAccountsRegistered()
									.accountSaver(transferAccountTextField.getText());

							testerTwo.setFutureValue(testerTwo.futureValueCalculator(testerTwo));
							System.out.println("Future Value: " + testerTwo.getFutureValue());
						}

					}

					// If the account does not have sufficient funds to transfer return the user to
					// the main scene and display error messages.
					else {

						applicationStage.setScene(mainScene);

						// Display respective error messages for checking and savings account. Charge an
						// overdraft fee on checking accounts.
						if (currentAccount.getIsSavings() == true) {
							searchErrorLabel.setText("Insufficient funds. Savings account cannot fall below $1000.");
						}

						else {
							searchErrorLabel.setText("Insufficient funds. Overdraft fee charged");
							currentAccount.overdraftFee();
							balanceDisplay.setText("Updated: $" + String.format("%.2f",currentAccount.getBalance()));
						}
					}
				}
			}
		}

	}

	/**
	 * Creates the savings account configuration scene with input fields for an
	 * interest rate, time period and compound frequency to calculate the future
	 * value of an investment account.
	 * 
	 * @param mainScene   the default scene the user interacts with when they open
	 *                    the application
	 * @param bankAccount an object of account type created and added to the list of
	 *                    registered accounts.
	 */

	void createSavingsScene(Scene mainScene, Account bankAccount) {

		// Create the containers for fields.
		VBox savingsContainer = new VBox();
		HBox savingsInterestBox = new HBox();
		HBox savingsPeriodBox = new HBox();
		savingsContainer.getChildren().addAll(savingsInterestBox, savingsPeriodBox);

		// Create the user input section and instructions.
		Label savingsInstructionLabel = new Label("Select an interest rate percentage.");
		ChoiceBox savingsInterestChoiceBox = new ChoiceBox();
		savingsInterestChoiceBox.getItems().addAll("1", "2", "5", "8");
		savingsInterestBox.getChildren().addAll(savingsInstructionLabel, savingsInterestChoiceBox,
				interestSelectionErrorLabel);

		Label savingsInstructionLabelTwo = new Label("Enter the number of years to calculate your investment.");
		TextField savingsPeriodTextField = new TextField();
		savingsPeriodBox.getChildren().addAll(savingsInstructionLabelTwo, savingsPeriodTextField,
				periodInputErrorLabel);

		HBox savingsCompoundBox = new HBox();
		Label compoundInstructionLabel = new Label("Select the compound frequency of your investment.");
		ChoiceBox savingsCompoundChoiceBox = new ChoiceBox();
		savingsCompoundChoiceBox.getItems().addAll("Monthly", "Semi-Annually", "Annually");
		savingsCompoundBox.getChildren().addAll(compoundInstructionLabel, savingsCompoundChoiceBox,
				compoundInputErrorLabel);
		savingsContainer.getChildren().add(savingsCompoundBox);

		// Create the done button to return the user to the main scene and further
		// process inputs.
		HBox savingsDoneBox = new HBox();
		Label savingsErrorLabel = new Label("");
		Button savingsDoneButton = new Button("Done");
		savingsDoneBox.getChildren().addAll(savingsDoneButton, savingsErrorLabel);
		savingsContainer.getChildren().add(savingsDoneBox);

		savingsDoneButton.setOnAction(donetransferEvent -> processSavingsScene(mainScene, savingsInterestChoiceBox,
				savingsPeriodTextField, savingsCompoundChoiceBox, savingsErrorLabel, bankAccount));

		// Creates and sets the scene to this savings scene with the elements created
		// above.
		Scene savingsMenuScene = new Scene(savingsContainer, 715, 150);
		getApplicationStage().setScene(savingsMenuScene);

	}

	/**
	 * Validates all user inputs of interest rate, period and compound frequency and
	 * displays relevant error messages on invalid inputs. Then updates the account
	 * in the list of accounts to feature references to the new parameters (interest
	 * rate, period , compound frequency). Finally, computes future value
	 * calculation for the savings account and returns the user to the main scene.
	 * 
	 * @param mainScene                the default scene the user interacts with
	 *                                 when they open the application
	 * @param savingsInterestChoiceBox A ChoiceBox containing various interest rates
	 *                                 values a user can select from.
	 * @param savingsPeriodTextField   A string input of the period (number of
	 *                                 years) a user would like to configure their
	 *                                 investment.
	 * @param savingsCompoundChoiceBox A ChoiceBox containing various values of
	 *                                 interest rate compound frequency.
	 * @param savingsErrorLabel        An error label to display errors in
	 *                                 processing a future value calculation, if
	 *                                 any.
	 * @param bankAccount              an object of account type created and added
	 *                                 to the list of registered accounts.
	 */
	void processSavingsScene(Scene mainScene, ChoiceBox savingsInterestChoiceBox, TextField savingsPeriodTextField,
			ChoiceBox savingsCompoundChoiceBox, Label savingsErrorLabel, Account bankAccount) {

		// Reset error labels.
		interestSelectionErrorLabel.setText("");
		periodInputErrorLabel.setText("");
		compoundInputErrorLabel.setText("");

		Object interestRateSelection = savingsInterestChoiceBox.getValue();
		Object compoundRateSelection = savingsCompoundChoiceBox.getValue();

		// Validate user inputs.

		if (interestRateSelection == null) {
			interestSelectionErrorLabel.setText("Please select an interest rate.");
		}

		if (isValidPeriod(savingsPeriodTextField.getText()) != "") {
			periodInputErrorLabel.setText(isValidPeriod(savingsPeriodTextField.getText()));
		}

		if (compoundRateSelection == null) {
			compoundInputErrorLabel.setText("Please select a compound frequency.");
		}

		if (interestRateSelection != null && isValidPeriod(savingsPeriodTextField.getText()) == ""
				&& compoundRateSelection != null) {

			// Return the user to the main home screen
			getApplicationStage().setScene(mainScene);

			// Gather user inputs and store them in correctly formatted variables.
			int interestRateSelected = Integer.valueOf((((String) savingsInterestChoiceBox.getValue())));
			int periodSpecified = Integer.valueOf(savingsPeriodTextField.getText());
			String compoundSelected = savingsCompoundChoiceBox.getValue().toString();

			// Create a new savings account with the inputed parameters.
			SavingsAccount savingAccount = new SavingsAccount(bankAccount, periodSpecified, interestRateSelected,
					compoundSelected, 0);
			savingAccount.setIsSavings("Savings");

			// Remove the old account reference and replace with the new reference that
			// contains them.
			getAccountsRegistered().removeBankAccounts(bankAccount);
			getAccountsRegistered().addBankAccounts(savingAccount);

			System.out.println(getAccountsRegistered().getBank());

			// Calculate and print the future value of the account.
			savingAccount.setFutureValue(savingAccount.futureValueCalculator(savingAccount));
			System.out.println("Future Value: " + savingAccount.getFutureValue());
		}

	}

	/**
	 * Creates the savings account update scene with input fields for an updated
	 * interest rate, time period and compound frequency to calculate an updated
	 * future value of an investment account.
	 * 
	 */

	@FXML

	void startSavingsFromButton() {

		// Store the user account number search as a string.
		String search = searchAccountTextField.getText();

		// Validate the user account number input.
		if (isValidAccountNumber(search) != "") {
			searchErrorLabel.setText(isValidAccountNumber(search));

		}
		// Search if the account exists in the list of registered accounts.
		else if (getAccountsRegistered().searchIfAccountExists(search) == 0) {
			searchErrorLabel.setText("Account does not exist.");
		}

		// Ensure the account being accesses is a savings account.
		else if (getAccountsRegistered().accountSaver(search).getIsSavings() == false) {
			searchErrorLabel.setText("Error: Not Savings Account");
		}

		// If the savings account exists and the input is valid create and display the
		// savings menu scene.

		else {

			// Set the default scene to the main scene.
			Scene mainScene = getApplicationStage().getScene();

			// Create the containers and fields for interest rate, period and compound
			// frequency.
			VBox savingsContainer = new VBox();
			HBox savingsInterestBox = new HBox();
			HBox savingsPeriodBox = new HBox();
			savingsContainer.getChildren().addAll(savingsInterestBox, savingsPeriodBox);

			Label savingsInterestInstructionLabel = new Label("Select an interest rate percentage.");
			ChoiceBox savingsInterestChoiceBox = new ChoiceBox();
			savingsInterestChoiceBox.getItems().addAll("1", "2", "5", "8");
			savingsInterestBox.getChildren().addAll(savingsInterestInstructionLabel, savingsInterestChoiceBox,
					interestSelectionErrorLabel);

			Label savingsPeriodInstructionLabel = new Label("Enter the number of years to calculate your investment.");
			TextField savingsPeriodTextField = new TextField();
			savingsPeriodBox.getChildren().addAll(savingsPeriodInstructionLabel, savingsPeriodTextField,
					periodInputErrorLabel);

			HBox savingsCompoundBox = new HBox();
			Label compoundInstructionLabel = new Label("Select the compound frequency of your investment.");
			ChoiceBox savingsCompoundChoiceBox = new ChoiceBox();
			savingsCompoundChoiceBox.getItems().addAll("Monthly", "Semi-Annually", "Annually");
			savingsCompoundBox.getChildren().addAll(compoundInstructionLabel, savingsCompoundChoiceBox,
					compoundInputErrorLabel);
			savingsContainer.getChildren().add(savingsCompoundBox);

			// Create the done button and its functionality.
			HBox savingsDoneBox = new HBox();
			Label savingsErrorLabel = new Label("");
			Button savingsDoneButton = new Button("Done");
			savingsDoneBox.getChildren().addAll(savingsDoneButton, savingsErrorLabel);
			savingsContainer.getChildren().add(savingsDoneBox);

			savingsDoneButton.setOnAction(donetransferEvent -> processSavingsButton(mainScene, savingsInterestChoiceBox,
					savingsPeriodTextField, savingsCompoundChoiceBox, savingsErrorLabel, search));

			// Create and set the scene to the savings menu scene.
			Scene savingsMenuScene = new Scene(savingsContainer, 715, 150);
			getApplicationStage().setScene(savingsMenuScene);
		}
	}

	/**
	 * Validates all user inputs of interest rate, period and compound frequency and
	 * displays relevant error messages on invalid inputs. Then updates the account
	 * in the list of accounts to feature references to the new parameters (interest
	 * rate, period , compound frequency). Finally, computes future value
	 * calculation for the savings account and returns the user to the main scene.
	 * 
	 * @param mainScene                the default scene the user interacts with
	 *                                 when they open the application
	 * @param savingsInterestChoiceBox A ChoiceBox containing various interest rates
	 *                                 values a user can select from.
	 * @param savingsPeriodTextField   A string input of the period (number of
	 *                                 years) a user would like to configure their
	 *                                 investment.
	 * @param savingsCompoundChoiceBox A ChoiceBox containing various values of
	 *                                 interest rate compound frequency.
	 * @param savingsErrorLabel        An error label to display errors in
	 *                                 processing a future value calculation, if
	 *                                 any.
	 * @param search                   a string variable referencing the account
	 *                                 number searched by the user in the main
	 *                                 scene.
	 */

	void processSavingsButton(Scene mainScene, ChoiceBox savingsInterestChoiceBox, TextField savingsPeriodTextField,
			ChoiceBox savingsCompoundChoiceBox, Label savingsErrorLabel, String search) {

		// Reset Error Labels.
		interestSelectionErrorLabel.setText("");
		periodInputErrorLabel.setText("");
		compoundInputErrorLabel.setText("");

		Object interestRateSelection = savingsInterestChoiceBox.getValue();
		Object compoundRateSelection = savingsCompoundChoiceBox.getValue();

		// Validate user inputs.
		if (interestRateSelection == null) {
			interestSelectionErrorLabel.setText("Please select an interest rate.");
		}

		if (isValidPeriod(savingsPeriodTextField.getText()) != "") {
			periodInputErrorLabel.setText(isValidPeriod(savingsPeriodTextField.getText()));
		}

		if (compoundRateSelection == null) {
			compoundInputErrorLabel.setText("Please select a compound frequency.");
		}

		if (interestRateSelection != null && isValidPeriod(savingsPeriodTextField.getText()) == ""
				&& compoundRateSelection != null) {

			// Return the user to the main scene
			getApplicationStage().setScene(mainScene);

			SavingsAccount tester = (SavingsAccount) getAccountsRegistered().accountSaver(search);

			// Update the paramters of the account and update the future value calculation.
			tester.setInterestRate(Integer.valueOf((((String) savingsInterestChoiceBox.getValue()))));
			tester.setPeriod(Integer.valueOf(savingsPeriodTextField.getText()));
			tester.setCompoundFrequency((String) savingsCompoundChoiceBox.getValue());

			tester.setFutureValue(tester.futureValueCalculator(tester));
			System.out.println("Future Value: " + tester.getFutureValue());

			futureValue.setText("Updated: $" + String.format("%.2f", tester.getFutureValue()));
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

	public double getThisIsFutureValue() {
		return thisIsFutureValue;
	}

	public void setThisIsFutureValue(double thisIsFutureValue) {
		this.thisIsFutureValue = thisIsFutureValue;
	}

	public Bank getAccountsRegistered() {
		return accountsRegistered;
	}

	public void setAccountsRegistered(Bank accountsRegistered) {
		this.accountsRegistered = accountsRegistered;
	}

}
