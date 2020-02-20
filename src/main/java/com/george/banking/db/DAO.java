package com.george.banking.db;

import java.util.Collection;

import com.george.banking.Account;
import com.george.banking.Application;
import com.george.banking.Customer;
import com.george.banking.Transfer;

public interface DAO {
	// methods interacting with Users table
	public boolean loginUser(String username, String password);
	public void registerUser(String username, String password, int type);
	public Collection<Customer> getAllCustomers();
	public Customer getCustomer(int customer_id);
//	public void deleteCustomer(int customer_id);
//	public void updateCustomer(Customer c);
	
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
	public void applyForAccount(Account app);
	public void reviewApplication(boolean confirm, Account app);
	
	// methods interacting with the Transfers table
	public Collection<Transfer> getAllTransfers();
	public Transfer getTransfer(int transfer_id);
	public void postTransfer(Transfer t);
	public void reviewTransfer(boolean confirm, Transfer t);
	
	
	
	
}
