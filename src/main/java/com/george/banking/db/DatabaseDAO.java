package com.george.banking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.george.banking.Account;
import com.george.banking.Application;
import com.george.banking.Customer;
import com.george.banking.Transfer;

public class DatabaseDAO implements DAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public DatabaseDAO() {
		conn = MyConnection.getInstance().getConnection();
	}

	@Override
	public boolean loginUser(String username, String password) {
		String sql = "SELECT * FROM tablename WHERE username=? AND password=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void registerUser(String username, String password, int type) {
		String sql = "INSERT INTO tablename (username, password, type) VALUES (?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setInt(3, type);
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Collection<Customer> getAllCustomers() {
		Collection<Customer> result = new ArrayList<Customer>();
		String sql = "SELECT * FROM tablename WHERE type=1";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Customer cust = new Customer(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
				cust.setAccounts(getCustomerAccounts(cust));
				result.add(cust);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Customer getCustomer(int customer_id) {
		Customer result = null;
		String sql = "SELECT * FROM tablename WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = new Customer(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
				result.setAccounts(getCustomerAccounts(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Account> getCustomerAccounts(Customer c) {
		Collection<Account> result = new ArrayList<Account>();
		String sql = "SELECT * FROM accttable WHERE customer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getId());
			rs = pstmt.executeQuery();
			
			while(rs.next())
				result.add(new Account(rs.getInt("account_id"), rs.getString("account_name"), rs.getDouble("balance"), false));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Account getAccount(int account_id) {
		Account result = null;
		String sql = "SELECT * FROM accttable WHERE account_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
				result = new Account(account_id, rs.getString("account_name"), rs.getDouble("balance"), false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//	@Override
//	public double getAccountBalance(Account a) {
//		
//		return 0;
//	}

	@Override
	public void updateBalance(Account a, double amount) {
		String sql = "UPDATE accttable SET balance=? WHERE account_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, a.getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

//	@Override
//	public void deleteAccount(int account_id) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void createAccount(Customer c, Account a) {
		String sql = "INSERT INTO accttable (account_name, balance, customer_id) VALUES (?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getName());
			pstmt.setDouble(2, a.getBalance());
			pstmt.setInt(3, c.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Collection<Account> getAllApplications() {
		Collection<Account> result = new ArrayList<Account>();
		String sql = "SELECT * FROM accttable WHERE is_app=1";
		Account acct = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				acct = new Account(rs.getInt("account_id"), rs.getString("account_name"), rs.getDouble("balance"), true);
				result.add(acct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Account getApplication(int application_id) {
		Account result = null;
		String sql = "SELECT * FROM apptable WHERE account_id=? AND is_application=1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, application_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
				result = new Account(application_id, rs.getString("account_name"), rs.getDouble("balance"), true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void applyForAccount(Application app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reviewApplication(boolean confirm, Application app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Transfer> getAllTransfers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transfer getTransfer(int transfer_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postTransfer(Transfer t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reviewTransfer(boolean confirm, Transfer t) {
		// TODO Auto-generated method stub
		
	}

}
