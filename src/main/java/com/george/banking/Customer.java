package com.george.banking;

import java.util.Collection;
import java.util.HashSet;

public class Customer extends User {
	private Collection<Account> accounts;
	private boolean application = false;
	
	// might want to edit functionality to keep id the same as User's id (since Customers must first be Users)
	// would add id field to constructor and manually assign instance variables - would not use super constructor
	public Customer(int id, String username, String password) {
		super(id, username, password);

		this.accounts = new HashSet<Account>();
		
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accountList) {
		this.accounts = accounts;
	}
	
	
}
