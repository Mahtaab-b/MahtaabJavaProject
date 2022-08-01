package application;

//create account class with instance variables 

public class Account {
	
	private String accountNumber;
	private String accountHolderName;
	private double balance;
	
	//Create an account type that consists of a users banking ID, their name and their balance amount.
	
	public Account () {

	}
	
	public Account (String bankingNumber, String accountName, double balanceAmount) {
		setAccountNumber(bankingNumber);
		setAccountHolderName(accountName);
		setBalance(balanceAmount);
	}
	
	public String toString () {
		return "An account exists" + accountNumber;
	}
	
	//A deposit method to add money to an account.
	public void deposit (double depositAmount) {
		setBalance(getBalance() + depositAmount);
	}
	
	//A transfer method to transfer money between accounts.
	public void transfer (Account otherAccount, double transferAmount) {
		setBalance(getBalance() - transferAmount);
		otherAccount.deposit(transferAmount);
		
		}
	
	//A withdraw method to withdraw money from an account.
	public void withdraw (double withdrawAmount) {
		setBalance(getBalance() - withdrawAmount);
		}

	//Setter and getters for the instance variables accountNumber, accountHolderName and balance.
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
	
}
