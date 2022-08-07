package application;

import java.lang.Math;

public class SavingsAccount extends Account {

	private double period;
	private double interestRate;
	private double futureValue;
	private static int numberOfWithdraw=0;
	private double minimumBalance=1000.0;
	
	
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
	
	public void withdraw (double withdrawAmount) {
		
		
		if(getNumberOfWithdraw()<=4) {
			super.withdraw(withdrawAmount+20.0);
			numberOfWithdraw = getNumberOfWithdraw() + 1;
		}
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

	public static int getNumberOfWithdraw() {
		return numberOfWithdraw;
	}
	
	public boolean checkFunds(double balanceAmount, double amount) {
		boolean checker=false;
		
		if(balanceAmount>0 && balanceAmount-amount>minimumBalance) {
			checker=true;
		}
		
		return checker;
	}

}