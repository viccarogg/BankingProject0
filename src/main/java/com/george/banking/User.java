package com.george.banking;

import java.io.Serializable;

public class User implements Serializable {
	// instance variables of a User
	private String username;
	private String password;
	private int id;
	
	public User(int id, String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
	}
	
	// getters and setters, don't want to be able to change id
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	// not sure whether these should be protected, or even methods at all. 
	// TODO some form of password protection, being able to get a plain text password is not safe
	protected String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	
	// only want to display username and id of a user account
	@Override
	public String toString() {
		return "User [username=" + username + ", id=" + id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
