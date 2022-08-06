package application;

//create account class with instance variables 

public class Account {
	
	private String accountNumber;
	private String accountHolderName;
	private double balance;
	private boolean isSavings;
	//Create an account type that consists of a users banking ID, their name and their balance amount.
	
	public Account() {
		
	}
	
	public Account (Account bankAccount) {
		setAccountNumber(bankAccount.getAccountNumber());
		setAccountHolderName(bankAccount.getLoginName());
		setBalance(bankAccount.getBalance());
		
	}
	
	public Account (String bankingNumber, String accountName, double balanceAmount, String savingsOrChequing) {
		setAccountNumber(bankingNumber);
		setAccountHolderName(accountName);
		setBalance(balanceAmount);
		setIsSavings(savingsOrChequing);
	}
	

	public String toString () {
		return "An account exists" + accountNumber+ isSavings;
	}
	
	//A deposit method to add money to an account.
	public void deposit (double depositAmount) {
		
		if(depositAmount>0) {
			setBalance(getBalance() + depositAmount);
		}
	}
	
	//A transfer method to transfer money between accounts.
	public void transfer (Account otherAccount, double transferAmount) {
		if(checkFunds(getBalance(), transferAmount)) {
			setBalance(getBalance() - transferAmount);
			otherAccount.deposit(transferAmount);
			}
		}
	
	//A withdraw method to withdraw money from an account.
	public void withdraw (double withdrawAmount) {
		if(checkFunds(getBalance(), withdrawAmount)){
			setBalance(getBalance() - withdrawAmount);
		}

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
		if(accountBalance>0) {
			balance = accountBalance;
		}
	}
	
	public boolean checkFunds(double balanceAmount, double amount) {
		boolean checker=false;
		
		if(balanceAmount>0 && balanceAmount-amount>0) {
			checker=true;
		}
		
		return checker;
	}

	boolean getIsSavings() {
		return isSavings;
	}

	public void setIsSavings(String type) {
		
		if (type=="Savings") {
			isSavings=true;
		}
		
		else {
			isSavings=false;
		}
		
	}
}
	
