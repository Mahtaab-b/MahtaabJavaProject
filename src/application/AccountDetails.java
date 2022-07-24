package application;

public class AccountDetails {
	
	private String loginNumber;
	private String loginName;
	private double balance;
	
	public AccountDetails(String accountNumber, String accountName, double balanceAmount) {
		setLoginNumber(accountNumber);
		setLoginName(accountName);
		setBalance(balanceAmount);
	}
	
	public void deposit (double depositAmount) {
		setBalance(getBalance() + depositAmount);
	}
	
	public void transfer (AccountDetails otherAccount, double transferAmount) {
		setBalance(getBalance() - transferAmount);
		otherAccount.deposit(transferAmount);
		
		}
	
	public void withdraw (double withdrawAmount) {
		setBalance(getBalance() - withdrawAmount);
		}

	public String getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(String loginNumber) {
		this.loginNumber = loginNumber;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
