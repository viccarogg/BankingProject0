package com.george.banking.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.george.banking.model.Account;
import com.george.banking.model.Customer;

public class AccountDB implements AccountDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private ResultSet rs = null;
	
	public AccountDB() {
		conn = MyConnection.getInstance().getConnection();
	}
	
	@Override
	public Collection<Account> getCustomerAccounts(Customer c) {
		Collection<Account> result = new ArrayList<Account>();
		String sql = "SELECT * FROM accounts WHERE user_id=? AND is_application=0";
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
		String sql = "SELECT * FROM accounts WHERE account_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_id);
			rs = pstmt.executeQuery();
			
			while(rs.next())
				result = new Account(account_id, rs.getString("account_name"), rs.getDouble("balance"), rs.getInt("is_application") == 1);
		} catch (SQLException e) {
//			System.out.println("this is the exception spot");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void updateBalance(Account a, double amount) {
		String sql = "{call account_update(?, ?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, a.getId());
			cstmt.setDouble(2, amount);
			cstmt.execute();
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
		String sql = "{call account_insert(?,?,?,?)}";
//		String sql = "INSERT INTO accounts (account_name, balance, customer_id) VALUES (?, ?, ?)";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setDouble(1, a.getBalance());
			cstmt.setString(2, a.getName());
			cstmt.setInt(3, c.getId());
			if(a.getIs_application())
				cstmt.setInt(4, 1);
			else
				cstmt.setInt(4, 0);
			
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Collection<Account> getAllApplications() {
		Collection<Account> result = new ArrayList<Account>();
		String sql = "SELECT * FROM accounts WHERE is_application=1";
		Account acct = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				acct = new Account(rs.getInt("account_id"), rs.getString("account_name"), 
						rs.getDouble("balance"), true, rs.getInt("user_id"));
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
		String sql = "SELECT * FROM accounts WHERE account_id=? AND is_application=1";
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
//
//	@Override
//	public void applyForAccount(Account app) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void reviewApplication(boolean confirm, Account app) {
		// TODO Auto-generated method stub
		String sql;
		if(confirm)
			// if application is approved, reflect that in is_application
			sql = "UPDATE accounts SET is_application=0 WHERE account_id=?";
		else
			// if application is denied, remove it from the table
			sql = "DELELTE FROM accounts WHERE account_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, app.getId());
			
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
