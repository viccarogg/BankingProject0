package com.george.banking.model;

import java.io.Serializable;

public class Account implements Serializable {
	private String name;
	private double balance;
	private int id;
	private boolean is_application;
	private int owner_id;
	
	public Account(int id, String name, double balance, boolean is_app) {
		super();
		this.name = name;
		this.balance = balance;
		this.id = id;
		this.is_application = is_app;
	}
	public Account(int id, String name, double balance, boolean is_app, int owner_id) {
		super();
		this.name = name;
		this.balance = balance;
		this.id = id;
		this.is_application = is_app;
		this.owner_id = owner_id;
	}
	
	public Account(String name, double balance, boolean is_app) {
		this.name=name;
		this.balance=balance;
		this.is_application=is_app;
	}
	
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public boolean getIs_application() {
		return is_application;
	}

	public void setIs_application(boolean is_application) {
		this.is_application = is_application;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\t- Account Name: " + name + "           \t- Balance: " + balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
