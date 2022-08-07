package application;

import java.lang.Math;

public class SavingsAccount extends Account {

	private double period;
	private double interestRate;
	private double futureValue;
	private static int numberOfWithdraw = 0;
	private double minimumBalance = 1000.0;
	private String compoundFrequency = "Annually";
	private int numberOfCompounds;

	public SavingsAccount() {

	}

	public SavingsAccount(Account bankAccount, double period, double interestrate, String compoundFrequency,
			double futureValue) {

		super(bankAccount);

		setPeriod(period);
		setInterestRate(interestrate);
		setCompoundFrequency(compoundFrequency);
		setFutureValue(futureValue);

		if (compoundFrequency == "Annually") {
			setNumberOfCompounds(1);
		}

		else if (compoundFrequency == "Semi-Annually") {
			setNumberOfCompounds(2);
		}

		else {
			setNumberOfCompounds(12);
		}

	}

	public String toString() {
		return "A savings account exists" + getAccountNumber() + getLoginName() + getBalance() + getIsSavings() + period
				+ interestRate;
	}

	public void withdraw(double withdrawAmount) {

		if (getNumberOfWithdraw() <= 4) {
			super.withdraw(withdrawAmount + 20.0);
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

	public double futureValueCalculator(SavingsAccount savings) {
		double fiv = savings.getBalance()
				* Math.pow((1 + getInterestRate() / 100), getPeriod() * getNumberOfCompounds());
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
		boolean checker = false;

		if (balanceAmount > 0 && balanceAmount - amount > minimumBalance) {
			checker = true;
		}

		return checker;
	}

	public int getNumberOfCompounds() {

		if (compoundFrequency == "Annually") {
			setNumberOfCompounds(1);
		}

		else if (compoundFrequency == "Semi-Annually") {
			setNumberOfCompounds(2);
		}

		else {
			setNumberOfCompounds(12);
		}

		return numberOfCompounds;
	}

	public void setNumberOfCompounds(int numberOfCompounds) {
		this.numberOfCompounds = numberOfCompounds;
	}

	public String getCompoundFrequency() {
		return compoundFrequency;
	}

	public void setCompoundFrequency(String compoundFrequency) {
		this.compoundFrequency = compoundFrequency;
	}

}