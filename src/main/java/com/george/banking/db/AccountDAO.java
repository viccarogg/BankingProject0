package com.george.banking.db;

import java.util.Collection;

import com.george.banking.model.Account;
import com.george.banking.model.Customer;

public interface AccountDAO {
	// methods interacting with Accounts table
	public Collection<Account> getCustomerAccounts(Customer c);
	public Account getAccount(int account_id);
//	public double getAccountBalance(Account a);
	public void updateBalance(Account a, double amount);
//	public void deleteAccount(int account_id);
	public void createAccount(Customer c, Account a);
	
	// methods interacting with Accounts table but is_application is TRUE
	public Collection<Account> getAllApplications();
	public Account getApplication(int application_id);
//	public void applyForAccount(Account app);
	public void reviewApplication(boolean confirm, Account app);

}
