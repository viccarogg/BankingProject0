package com.george.banking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	// create an object of  SingletonObject class
	private Connection conn = null;
	private static MyConnection myConnect = new MyConnection();
	
	// make the constructor private so that this class cannot be instantiated
	private MyConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "hr", "hr");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// get the only object available
	public static MyConnection getInstance() {
		return myConnect;
	}
	
	public Connection getConnection() {
		return conn;
	}
	

}
