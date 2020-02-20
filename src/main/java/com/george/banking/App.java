package com.george.banking;

public class App {
	private static class Session {
		User u;
		
		public Session(User u) {
			this.u = u;
		}
	}
	
	public static void main(String[] args) {
		User currentUser = new User(1, "george", "password");
		// scanner to obtain username and password login
		Session currentSession = new Session(currentUser);
		
		if(currentSession.u instanceof Employee) {
			
		}
		else if(currentSession.u instanceof Customer) {
			
		}
		else {
			// current user is neither Employee nor Customer => regular User
		}
	}
}
