package com.george.banking;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import com.george.banking.db.UserDAO;
import com.george.banking.db.UserDB;
import com.george.banking.model.Customer;
import com.george.banking.model.Employee;
import com.george.banking.model.User;
import com.george.banking.model.Session;

public class App {
	
	public static void main(String[] args) {
//		User currentUser = new User(1, "george", "password");
		// scanner to obtain username and password login
		UserDAO userDB = new UserDB();
		Scanner sc = new Scanner(System.in);
		String input = null;
		Session current = new Session(null);
//		System.out.println("------------------------------------------------------------");
//		System.out.println("|       WECLOME TO THE FIRST NATIONAL BANK OF GEORGE       |");
//		System.out.println("------------------------------------------------------------");
		SystemOperations.displayLogo();
		do {

			System.out.println("------------------------------------------------------------");
			System.out.println("Menu");
			System.out.println("------------------------------------------------------------");
			System.out.println("1 - Login");
			System.out.println("2 - Register as a Customer");
			System.out.println("3 - Exit");
			
			input = sc.nextLine();
			if(input.equals("1")) {
				do {
					System.out.println("Please enter your username.");
					String username = sc.nextLine();
//					System.out.println(username);
					System.out.println("Please enter your password.");
					String password = sc.nextLine();
//					System.out.println(password);
					
					current.setCurrentUser(userDB.loginUser(username, password));
					
					if(current.getCurrentUser() != null) {
						System.out.println("Welcome " + username);
						// break out of login loop.
						break;
					}
					else
						System.out.println("Username/password did not match one in our system.");
				} while(true);
			}
			else if(input.equals("2")) {
				System.out.println("Please choose a username.");
				String username = sc.nextLine();
				System.out.println("Please choose a password.");
				String password = sc.nextLine();
				
				userDB.registerUser(username, password, 1);
				current.setCurrentUser(userDB.loginUser(username, password));
				System.out.println("Welcome " + username);
			}
			else if(input.equals("3")) {
				System.out.println("Thank you for banking with us.");
				SystemOperations.exitBank();
				break;
			}
			else {
				System.out.println("Please select a valid option \n");
				continue;
			}
			if(current.getCurrentUser()!= null) {
				SystemOperations.loginUser(current.getCurrentUser());
			}
			do {
				// user has been logged in. loop until they log out
				SystemOperations.displayOptions(current.getCurrentUser());
				input = sc.nextLine();
			} while(SystemOperations.processInput(current.getCurrentUser(), input, sc));
			
		} while(true);
		
	}
}
