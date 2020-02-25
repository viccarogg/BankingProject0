package com.george.banking.db;

import java.util.Collection;

import com.george.banking.model.Customer;
import com.george.banking.model.Transfer;

public interface TransferDAO {
	// methods interacting with the Transfers table
	public Collection<Transfer> getPendingTransfers(Customer c);
	public Transfer getTransfer(int transfer_id);
	public void postTransfer(Transfer t);
	public void reviewTransfer(boolean confirm, int transfer_id);
}
