package com.george.banking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.george.banking.model.Audit;

public class AuditDB implements AuditDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public AuditDB() {
		conn = MyConnection.getInstance().getConnection();
	}
	
	@Override
	public Collection<Audit> getAudits() {
		Collection<Audit> result = new ArrayList<Audit>();
		String sql = "SELECT * FROM audits";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("type").equals("TRANSFER"))
					result.add(new Audit(rs.getInt("transaction_id"), rs.getString("type"), 
							rs.getInt("account_from"), rs.getInt("account_to"), rs.getDouble("amount")));
				else
					result.add(new Audit(rs.getInt("transaction_id"), rs.getString("type"), 
							rs.getInt("account_from"), rs.getDouble("amount")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
