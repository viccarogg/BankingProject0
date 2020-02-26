package com.george.banking;

import com.george.banking.db.UserDAO;
import com.george.banking.db.UserDB;
import com.george.banking.db.TransferDAO;
import com.george.banking.db.TransferDB;
import com.george.banking.db.AccountDAO;
import com.george.banking.db.AccountDB;
import com.george.banking.exceptions.*;
import com.george.banking.model.Account;
import com.george.banking.model.Transfer;

public class BankOperations {
	static UserDAO userDB = new UserDB();
	static AccountDAO accountDB = new AccountDB();
	static TransferDAO transferDB = new TransferDB();

	public static void withdraw(Account a, double amount) {
		if(a.getId() < 0) {
			throw new InvalidIDException("ID cannot be negative.");
		}
		double bal = a.getBalance();
		if(amount > bal) 
			throw new OverdrawException("Insufficient funds.");
		else if(amount < 0) 
			throw new NegativeTransactionException("Please enter a nonnegative value");
		else 
			accountDB.updateBalance(a, bal - amount);
	}
	public static void deposit(Account a, double amount) {
		if(a.getId() < 0) {
			throw new InvalidIDException("ID cannot be negative.");
		}
		double bal = a.getBalance();
		if(amount < 0) 
			throw new NegativeTransactionException("Please enter a nonnegative value");
		else
			accountDB.updateBalance(a, bal + amount);
	}
	public static double viewBalance(Account a) {
		return a.getBalance();
	}
	
	public static void postTransfer(Account from, Account to, double amount) {
		Transfer t = new Transfer(0, from, to, amount);
		transferDB.postTransfer(t);
	}
	

}
