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
import com.george.banking.model.Transfer;

public class TransferDB implements TransferDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private ResultSet rs = null;

	public TransferDB() {
		conn = MyConnection.getInstance().getConnection();
	}
	
	@Override
	public Collection<Transfer> getPendingTransfers(Customer c) {
		Collection<Transfer> result = new ArrayList<Transfer>();
		String sql = "SELECT * FROM transfers WHERE owner_id=? AND status='PENDING'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result.add(new Transfer(rs.getInt("transfer_id"), 
						rs.getInt("account_from_id"), rs.getInt("account_to_id"), rs.getDouble("amount")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Transfer getTransfer(int transfer_id) {
		Transfer result = null;
		String sql = "SELECT * FROM transfers WHERE transfer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, transfer_id);
			rs = pstmt.executeQuery();
			
			while(rs.next())
				result = new Transfer(rs.getInt("transfer_id"), rs.getInt("account_from_id"), 
						rs.getInt("account_to_id"), rs.getDouble("amount"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void postTransfer(Transfer t) {
		// TODO Auto-generated method stub
		String sql = "{call transfer_insert(?,?,?,?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, t.getAccount_from_id());
			cstmt.setInt(2, t.getAccount_to_id());
			cstmt.setDouble(3, t.getAmount());
			cstmt.setInt(4, t.getOwner_id());
			
			cstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void reviewTransfer(boolean confirm, int transfer_id) {
		String sql = "{call process_transfer(?,?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, transfer_id);
			if(confirm)
				cstmt.setInt(2, 1);
			else
				cstmt.setInt(2, 0);
			cstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
