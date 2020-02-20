package com.george.banking;

import com.george.banking.db.DAO;
import com.george.banking.db.DatabaseDAO;
import com.george.banking.exceptions.*;

public class BankOperations {
	static DAO dbDAO = new DatabaseDAO();

	public static void withdraw(Account a, double amount) {
		double bal = a.getBalance();
		if(amount > bal) 
			throw new OverdrawException("Insufficient funds.");
		else if(amount < 0) 
			throw new NegativeTransactionException("Please enter a nonnegative value");
		else 
			dbDAO.updateBalance(a, bal - amount);
	}
	public static void deposit(Account a, double amount) {
		double bal = a.getBalance();
		if(amount < 0) 
			throw new NegativeTransactionException("Please enter a nonnegative value");
		else
			dbDAO.updateBalance(a, bal + amount);
	}
	public static double viewBalance(Account a) {
		return a.getBalance();
	}
	

}
