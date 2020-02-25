package com.george.banking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class MyConnection {
	// create an object of  SingletonObject class
	private Connection conn = null;
	private static MyConnection myConnect = new MyConnection();
	
	// make the constructor private so that this class cannot be instantiated
	private MyConnection() {
		try {
			OracleDataSource ods = new OracleDataSource();
			ods.setServerName("localhost");
			ods.setServiceName("orcl");
			ods.setDriverType("thin");
			ods.setPortNumber(1521);
			ods.setUser("bank_admin");
			ods.setPassword("admin");
			
			conn = ods.getConnection();
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "bank_admin", "admin");
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
