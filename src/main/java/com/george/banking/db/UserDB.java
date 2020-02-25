package com.george.banking.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.george.banking.model.User;
import com.george.banking.model.Customer;
import com.george.banking.model.Employee;

public class UserDB implements UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private ResultSet rs = null;
	
	public UserDB() {
		conn = MyConnection.getInstance().getConnection();
	}

	@Override
	public User loginUser(String username, String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getInt("user_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void registerUser(String username, String password, int type) {
		String sql = "{call user_insert(?, ?, ?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, username);
			cstmt.setString(2, password);
			cstmt.setInt(3, type);
			cstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Collection<Customer> getAllCustomers() {
		Collection<Customer> result = new ArrayList<Customer>();
		String sql = "SELECT * FROM users WHERE user_type=1";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Customer cust = new Customer(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
//				cust.setAccounts(getCustomerAccounts(cust));
				result.add(cust);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User getUser(int user_id) {
		User result = null;
		String sql = "SELECT * FROM users WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("user_type") == 1) 
					result = new Customer(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
				else if(rs.getInt("user_type") == 2)
					result = new Employee(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
				else
					result = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
