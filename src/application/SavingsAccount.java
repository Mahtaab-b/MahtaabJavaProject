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

	// Constructors featured below.

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

	// Display the arguments of a savings account object in a string representation.

	public String toString() {
		return "Account Number:" + getAccountNumber() + " Account Holder Name: " + getLoginName() + " Balance: "
				+ +getBalance() + " Savings: " + getIsSavings() + " Period: " + period + " Interest Rate: "
				+ interestRate + " Compound Frequency: " + compoundFrequency;
	}

	/**
	 * Checks if a savings account has allowed 4 or less withdrawals (the withdrawal
	 * limit) and then subtracts the inputed amount and a 20 dollar fee from the
	 * account balance.
	 */

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

	/**
	 * Utilizes account balance, interest rate, and compound frequency to return the
	 * future value of the balance over an inputed time period.
	 * 
	 * @param savings an object of type SavingsAccount upon which a future value
	 *                calculation may be processed.
	 * @return future value a double representation of the future value of an
	 *         investment.
	 */

	public double futureValueCalculator(SavingsAccount savings) {
		double futureValue = savings.getBalance()
				* Math.pow((1 + getInterestRate() / 100), getPeriod() * getNumberOfCompounds());
		return futureValue;
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

	/**
	 * Checks if an account balance will still be greater than the minimum balance
	 * of $1000 in order to process the inputed withdrawal amount and then subtracts
	 * said withdrawal amount from the account balance.
	 * 
	 * @param amount a double value referencing the amount a user would like to
	 *               withdraw/transfer.
	 */
	public boolean checkSufficientFunds(double amount) {
		boolean checker = false;

		if (getBalance() > 0 && getBalance() - amount > minimumBalance) {
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