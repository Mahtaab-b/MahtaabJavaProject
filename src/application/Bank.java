package application;

import java.util.ArrayList;

public class Bank {
	
	private ArrayList <Account> bank = new ArrayList<Account>();

	public Bank() {
		setBank(new ArrayList<Account>());
	}
	
	public ArrayList <Account> getBank() {
		return bank;
	}

	public void setBank(ArrayList <Account> bank) {
		this.bank = bank;
	}
	
	public void addBankAccounts(Account a) {
		bank.add(a);
	}
	
	public void removeBankAccounts (Account a) {
		bank.remove(a);
	}
	
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
	
	public Account savingsAccountSaver(String accountNumber) {

		SavingsAccount takeThisAccount = new SavingsAccount();

		int index = 0;

		while (index < getBank().size()) {

			if (accountNumber.equals(getBank().get(index).getAccountNumber())) {
				takeThisAccount = (SavingsAccount) getBank().get(index);
				break;
			}

			index++;
		}

		return takeThisAccount;
	}
	
	

}
