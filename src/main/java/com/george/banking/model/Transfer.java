package com.george.banking.model;

public class Transfer {
	private Account to;
	private Account from;
	private int account_to_id;
	private int account_from_id;
	private double amount;
	private int owner_id;
	private int id;
	
	public Transfer(int id, Account from, Account to, double amount) {
		this.to = to;
		this.from = from;
		this.amount = amount;
		this.id = id;
	}

	public Transfer(int id, int from_id, int to_id, double amount) {
		this.account_to_id = to_id;
		this.account_from_id = from_id;
		this.amount = amount;
		this.id = id;
	}
	public Transfer(int from_id, int to_id, double amount, int owner_id) {
		this.owner_id=owner_id;
		this.account_to_id = to_id;
		this.account_from_id = from_id;
		this.amount = amount;
	}
	
	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public int getAccount_to_id() {
		return account_to_id;
	}

	public void setAccount_to_id(int account_to_id) {
		this.account_to_id = account_to_id;
	}

	public int getAccount_from_id() {
		return account_from_id;
	}

	public void setAccount_from_id(int account_from_id) {
		this.account_from_id = account_from_id;
	}

	public Account getTo() {
		return to;
	}

	public void setTo(Account to) {
		this.to = to;
	}

	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Transfer ID: " + id + " - From ID: " + account_from_id + " - To ID: " + account_to_id + " - Amount: " + amount;
	}
	
}
