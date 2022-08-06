package application;

public class SavingsAccount extends Account {

	private int period;
	private int interestRate;

	public SavingsAccount(Account bankAccount, int period, int interestrate) {

		super(bankAccount);
		this.setPeriod(period);
		this.setInterestRate(interestrate);
		
	}
	
	public String toString () {
		return "A savings account exists" + getAccountNumber()+ getLoginName()+getBalance()+period+interestRate;
	}


	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

}