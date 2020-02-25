package com.george.banking.db;

import java.util.Collection;

import com.george.banking.model.Customer;
import com.george.banking.model.User;

public interface UserDAO {
	// methods interacting with Users table
	public User loginUser(String username, String password);
	public void registerUser(String username, String password, int type);
	public Collection<Customer> getAllCustomers();
	public User getUser(int user_id);
	
//	public void deleteCustomer(int customer_id);
//	public void updateCustomer(Customer c);

}
