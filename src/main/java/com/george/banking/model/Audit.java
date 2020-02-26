package com.george.banking.model;

public class Audit {
	private int transaction_id;
	private String type;
	private int account_from_id;
	private int account_to_id;
	private double amount;
	
	@Override
	public String toString() {
		String result = "Transaction ID: " + transaction_id + "\t- Type: " + type + "  \t- From ID: ";
		if(type.equals("WITHDRAW"))
			result += account_from_id + "\t- To ID: .\t- Amount: " + -amount;
		else if(type.equals("DEPOSIT"))
			result += ". \t- To ID: " + account_from_id + "\t- Amount: " + amount;
		else	
			return "Transaction ID: " + transaction_id + "\t- Type: " + type + "\t- From ID: " + account_from_id
				+ "\t- To ID: " + account_to_id + "\t- Amount: " + amount;
		return result;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAccount_from_id() {
		return account_from_id;
	}

	public void setAccount_from_id(int account_from_id) {
		this.account_from_id = account_from_id;
	}

	public int getAccount_to_id() {
		return account_to_id;
	}

	public void setAccount_to_id(int account_to_id) {
		this.account_to_id = account_to_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Audit(int transaction_id, String type, int account_from_id, int account_to_id, double amount) {
		super();
		this.transaction_id = transaction_id;
		this.type = type;
		this.account_from_id = account_from_id;
		this.account_to_id = account_to_id;
		this.amount = amount;
	}
	public Audit(int transaction_id, String type, int account_from_id, double amount) {
		super();
		this.transaction_id = transaction_id;
		this.type = type;
		this.account_from_id = account_from_id;
		this.amount = amount;
	}
}
