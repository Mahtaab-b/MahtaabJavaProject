package application;

import java.util.ArrayList;

public class Bank {

	// Declare the instance variable of a bank, which is a list of accounts.
	private ArrayList<Account> bank = new ArrayList<Account>();

	// The constructor for the bank class.
	public Bank() {
		setBank(new ArrayList<Account>());
	}

	// Featured below are the setter and getter methods of the instance variable
	// bank, used to initialize and access the array list of accounts.
	public ArrayList<Account> getBank() {
		return bank;
	}

	public void setBank(ArrayList<Account> bank) {
		this.bank = bank;
	}

	/**
	 * Adds a specified account, bankAccount, to the array list of accounts within
	 * an initialized bank.
	 * 
	 * @param bankAccount or an object of type Account passed by the user to be
	 *                    added to the bank.
	 */
	public void addBankAccounts(Account bankAccount) {
		bank.add(bankAccount);
	}

	/**
	 * Removes a specified account, bankAccount, from the array list of accounts
	 * within an initialized bank.
	 * 
	 * @param bankAccount or an object of type Account passed by the user to be
	 *                    removed from the bank.
	 */
	public void removeBankAccounts(Account bankAccount) {
		bank.remove(bankAccount);
	}

	/**
	 * Determines whether or not an account exists in an array list of accounts, or
	 * bank object. Returns 0 if it does not exist and increments exists variable
	 * for when it is found (or multiple times for duplicates).
	 * 
	 * @param accountNumber, an account number of strings associated with an account
	 *                       object.
	 * @return exists, an integer indicating the number of time an account exists in
	 *         an array list of accounts. Returns 0 if the account does not appear
	 *         in the list and 1 if it does.
	 */
	public int searchIfAccountExists(String accountNumber) {

		int exists = 0;

		int index = 0;

		while (index < getBank().size()) {

			if (accountNumber.equals(getBank().get(index).getAccountNumber())) {
				exists += 1;
				break;
			}

			index++;
		}

		return exists;

	}

	/**
	 * Stores an account object from an array list of accounts, the bank, as a new
	 * account object to be used in future methods and operations.
	 * 
	 * @param accountNumber, an account number of strings associated with an account
	 *                       object.
	 * @return takeThisAccount, or an account retrieved from an array list of
	 *         accounts, the bank, stored as a new account variable.
	 */
	public Account accountSaver(String accountNumber) {

		Account takeThisAccount = new Account();

		int index = 0;

		while (index < getBank().size()) {

			if (accountNumber.equals(getBank().get(index).getAccountNumber())) {
				takeThisAccount = getBank().get(index);
				break;
			}

			index++;
		}

		return takeThisAccount;
	}

}
