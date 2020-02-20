package com.george.banking;

import java.util.Collection;
import com.george.banking.exceptions.*;

public class CustomerOperations {	
	public static void applyForAccount(Customer cust, String name, double balance) {
		Account newAccount = new Account(name, balance);
		
		// write to accountApplications database
		// employee will be able to approve or reject account from there
	}
	
	
	
	public static void withdraw(Account a, double amount) {
		if(amount < 0) {
			// cannot withdraw negative amount - throw custom Exception
			throw new NegativeTransactionException("Please enter a nonzero value.");
		}
		// log withdrawal
		
		AccountOperations.modifyBalance(a, -amount);
	}
	public static void withdraw(Customer cust, String acctName, double amount) {
		if(amount < 0) {
			// cannot withdraw a negative amount - throw custom Exception here
			throw new NegativeTransactionException("Please enter a nonzero value.");
		}
		
		// log withdrawal here
		
		AccountOperations.modifyBalance(cust, acctName, -amount);
	}
	public static void deposit(Customer cust, String acctName, double amount) {
		if(amount < 0) {
			// cannot deposit a negative amount - throw custom Exception here
			throw new NegativeTransactionException("Please enter a nonzero value.");
		}
		
		// log deposit here
		
		AccountOperations.modifyBalance(cust, acctName, amount);
	}
	public static void deposit(Account a, double amount) {
		if(amount < 0) {
			// cannot deposit a negative amount
			throw new NegativeTransactionException("Please enter a nonzero value.");
		}
		
		AccountOperations.modifyBalance(a, amount);
	}
	public static void postTransfer(Customer cust, String acctNameFrom, String acctNameTo, double amount) {
		if(amount < 0) {
			throw new NegativeTransactionException("Please enter a nonzero value.");
			// cannot withdraw a negative amount - throw custom Exception here
		}
		Collection<Account> accts = cust.getAccounts();
		Account acctFrom = AccountOperations.getAccountByName(accts, acctNameFrom);
		Account acctTo = AccountOperations.getAccountByName(accts, acctNameTo);
		Transfer t = new Transfer(acctFrom, acctTo, amount);
		
		// log transfer maybe, alternatively when accepting the transfer
	}
	
	public static void reviewTransfer(Customer cust, Transfer t, boolean confirm) {
		Account from = t.getFrom();
		Account to = t.getTo();
		double amount = t.getAmount();
		
		if(confirm) {
			// customer is accepting the transfer.
			withdraw(from, amount);
			deposit(to, amount);
			
			// this will also log each withdraw and deposit - don't have to log transfer?
		} 
		else {
			// customer is rejecting the transfer
			// don't manipulate balances, just remove transfer from database 
		}
	}
}
