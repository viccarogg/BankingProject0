package com.george.banking.tests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.george.banking.BankOperations;
import com.george.banking.db.AccountDAO;
import com.george.banking.db.AccountDB;
import com.george.banking.db.MyConnection;
import com.george.banking.db.UserDAO;
import com.george.banking.db.UserDB;
import com.george.banking.model.Account;
import com.george.banking.model.User;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBankOps {
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static CallableStatement cstmt = null;
	static ResultSet rs = null;
	static UserDAO userDB = null;
	static AccountDAO accountDB = null;
	
	@BeforeAll
	public static void setUp() {
		userDB = new UserDB();
		accountDB = new AccountDB();
		conn = MyConnection.getInstance().getConnection();
		String sql = "{call disable_triggers}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@AfterAll
	public static void cleanUp() {
		String sql = "{call enaable_triggers}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreate() {
		userDB.registerUser("tester", "testing", 1);
		User u = userDB.loginUser("tester", "testing");
		assertTrue(u.getUsername().equals("tester") && u.getPassword().equals("testing"));
	}
	
	@Test
	public void testDeposit() {
		Account a = accountDB.getAccount(1);
		double balanceBefore = a.getBalance();
		BankOperations.deposit(a, 50);
		a = accountDB.getAccount(1);
		assertTrue(a.getBalance() == balanceBefore + 50);
	}
	

	@Test
	public void testWithdraw() {
		Account a = accountDB.getAccount(1);
		double balanceBefore = a.getBalance();
		BankOperations.withdraw(a, 50);
		a = accountDB.getAccount(1);
		assertTrue(a.getBalance() == balanceBefore - 50);
	}
	
}
