package application;

import java.lang.Math;

public class SavingsAccount extends Account {

	private double period;
	private double interestRate;
	private double futureValue;
	
	public SavingsAccount() {
		
	}
	
	public SavingsAccount(Account bankAccount, double period, double interestrate, double futureValue) {

		super(bankAccount);
		
		this.setPeriod(period);
		this.setInterestRate(interestrate);
		this.futureValue=futureValue;
		
	}
	
	public String toString () {
		return "A savings account exists" + getAccountNumber()+ getLoginName()+getBalance()+ getIsSavings()+period+interestRate;
	}


	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public double futureValueCalculator (SavingsAccount savings) {
		double fiv=savings.getBalance()*Math.pow((1+getInterestRate()/100),getPeriod()*2);
		return fiv;
	}

	double getFutureValue() {
		return futureValue;
	}

	void setFutureValue(double futureValue) {
		this.futureValue = futureValue;
	}

}