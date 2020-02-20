package com.george.banking;

public class Transfer {
	private Account to;
	private Account from;
	private double amount;
	private int id;
	
	public Transfer(int id, Account from, Account to, double amount) {
		this.to = to;
		this.from = from;
		this.amount = amount;
		this.id = id;
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
		return "Transfer [id=" + id + ", to=" + to + ", from=" + from + ", amount=" + amount + "]";
	}
	
}
