package com.george.banking.model;

public class Session {
	private User currentUser;
	
	
	public User getCurrentUser() {
		return currentUser;
	}


	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}


	public Session(User u) {
		this.currentUser = u;
	}
}
