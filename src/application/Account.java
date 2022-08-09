package application;

public class Account {

	private String accountNumber;
	private String accountHolderName;
	private double balance;
	private boolean isSavings;
	private static double overDraftFee = 35.0;

	// Below are three constructors pertaining to the account class.

	public Account() {

	}

	public Account(Account bankAccount) {
		setAccountNumber(bankAccount.getAccountNumber());
		setAccountHolderName(bankAccount.getLoginName());
		setBalance(bankAccount.getBalance());

	}

	public Account(String bankingNumber, String accountName, double balanceAmount, String savingsOrChequing) {
		setAccountNumber(bankingNumber);
		setAccountHolderName(accountName);
		setBalance(balanceAmount);
		setIsSavings(savingsOrChequing);
	}

	// Returns the string representation of an account object with its relevant
	// parameters.
	public String toString() {
		return "Account Number: " + accountNumber + " Account Holder Name: " + accountHolderName + " Savings: "
				+ isSavings;
	}

	/**
	 * Checks if the inputed deposit amount is a positive, non-zero value and then
	 * adds it to the balance of an account.
	 * 
	 * @param depositAmount a double value referencing the amount a user would like
	 *                      to deposit.
	 */

	public void deposit(double depositAmount) {

		if (depositAmount > 0) {
			setBalance(getBalance() + depositAmount);
		}
	}

	/**
	 * Checks if an account has sufficient funds to transfer the inputed amount to
	 * another account. If so the method subtracts the amount from the account's
	 * balance and deposits it to the balance of the other account passed as an
	 * argument.
	 * 
	 * @param otherAccount   an account object to transfer money to.
	 * @param transferAmount a double value representing the amount of money a user
	 *                       would like to transfer from one account to another.
	 */
	public void transfer(Account otherAccount, double transferAmount) {
		if (checkSufficientFunds(transferAmount)) {
			setBalance(getBalance() - transferAmount);
			otherAccount.deposit(transferAmount);
		}
	}

	/**
	 * Checks if an account balance will still be greater than 0 in order to process
	 * the inputed withdrawal amount and then subtracts given withdrawal amount from
	 * the account balance.
	 * 
	 * @param withdrawAmount a double value referencing the amount a user would like
	 *                       to withdraw.
	 */
	public void withdraw(double withdrawAmount) {
		if (checkSufficientFunds(withdrawAmount)) {
			setBalance(getBalance() - withdrawAmount);
		}

	}

	/**
	 * Subtracts the overdraft fee from an accounts balance value.
	 */
	public void overdraftFee() {
		setBalance(getBalance() - overDraftFee);
	}

	// Setter and getters for the instance variables accountNumber,
	// accountHolderName and balance.
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String bankingNumber) {
		accountNumber = bankingNumber;
	}

	public String getLoginName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountName) {
		accountHolderName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double accountBalance) {
		balance = accountBalance;
	}

	/**
	 * Determine if an account's balance falls below 0 following a transfer or
	 * withdrawal of the inputed amount.
	 * 
	 * @param amount an amount a user would like to transfer or withdraw from an
	 *               account as a balance value.
	 * @return checker a boolean that is set to false if an account lacks sufficient
	 *                 funds to transfer/withdraw an amount or true otherwise.
	 */
	public boolean checkSufficientFunds(double amount) {
		boolean checker = false;

		if (balance > 0 && balance - amount > 0) {
			checker = true;
		}

		return checker;
	}

	boolean getIsSavings() {
		return isSavings;
	}

	// Set the boolean instance variable to true or false based on string inputs of
	// "Checking" or "Savings"
	public void setIsSavings(String type) {

		if (type == "Savings") {
			isSavings = true;
		}

		else {
			isSavings = false;
		}

	}
}
