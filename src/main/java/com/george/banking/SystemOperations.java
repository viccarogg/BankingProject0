package com.george.banking;

import java.util.Collection;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.george.banking.db.AccountDAO;
import com.george.banking.db.AccountDB;
import com.george.banking.db.AuditDAO;
import com.george.banking.db.AuditDB;
import com.george.banking.db.TransferDAO;
import com.george.banking.db.TransferDB;
import com.george.banking.db.UserDAO;
import com.george.banking.db.UserDB;
import com.george.banking.exceptions.NegativeTransactionException;
import com.george.banking.exceptions.OverdrawException;
import com.george.banking.model.Account;
import com.george.banking.model.Audit;
import com.george.banking.model.Customer;
import com.george.banking.model.Transfer;
import com.george.banking.model.User;

public class SystemOperations {
	static final Logger logger = (Logger) LogManager.getLogger(SystemOperations.class.getName());
	
	public static void loginUser(User u) {
		int account_type = u.getType();
		if(account_type == 1)
			logger.trace("Logged in Customer " + u.getUsername() + " with ID " + u.getId());
		else if(account_type == 2)
			logger.trace("Logged in Employee " + u.getUsername() + " with ID " + u.getId());
	}
	
	public static void displayOptions(User u) {
		int account_type = u.getType();
		if(account_type == 1) {
			// user is a customer. display customer options
			System.out.println("------------------------------------------------------------");
			System.out.println("Customer Menu");
			System.out.println("------------------------------------------------------------");
			System.out.println("1 - Apply for an account");
			System.out.println("2 - View Accounts");
			System.out.println("3 - Deposit");
			System.out.println("4 - Withdraw");
			System.out.println("5 - Post Transfer");
			System.out.println("6 - View Pending Transfers");
			System.out.println("7 - Review Transfers");
			System.out.println("8 - Logout");
		}
		else if (account_type == 2) {
			// user is an employee. display employee options
			System.out.println("------------------------------------------------------------");
			System.out.println("Employee Menu");
			System.out.println("------------------------------------------------------------");
			System.out.println("1 - View Customers");
			System.out.println("2 - View Customer Accounts");
			System.out.println("3 - View Account Applications");
			System.out.println("4 - Review Application");
			System.out.println("5 - View Transaction Log");
			System.out.println("6 - Logout");
			
		}
	}
	
	public static boolean processEmployeeInput(String input, Scanner sc) {
		UserDAO userDB = new UserDB();
		AccountDAO accountDB = new AccountDB();
		AuditDAO auditDB = new AuditDB();
		
		String logMessage = "";
		boolean cont = false;
		
		if(input.equals("1")) {
			// view all customers
			Collection<Customer> customers = userDB.getAllCustomers();
			customers.forEach( (cust) -> {System.out.println(cust);});
			cont = true;
			logMessage = "Employee viewed all Customers";
		}
		else if(input.equals("2")) {
			// view a customer's accounts
			System.out.println("Please specify the ID of the customer whose accounts you'd like to view.");
			int id = Integer.parseInt(sc.nextLine());
			Collection<Account> accounts = accountDB.getCustomerAccounts((Customer)userDB.getUser(id));
			accounts.forEach( (acct) -> {System.out.println(acct);});
			cont = true;
			logMessage = "Employee viewed Customer " + id + "'s accounts.";
		}
		else if(input.equals("3")) {
			// view account applications
			Collection<Account> apps = accountDB.getAllApplications();
			apps.forEach( (app) -> {System.out.println(app + " - Owner: " + app.getOwner_id());});
			cont = true;
			logMessage = "Employee viewed all Applications";
			return true;
		}
		else if(input.equals("4")) {
			// review an application
			System.out.println("Please specify the application to review.");
			int id = Integer.parseInt(sc.nextLine());
			Account toReview = accountDB.getAccount(id);
			System.out.println("Press 1 to accept the application. Any other number will deny the application.");
			boolean confirm = Integer.parseInt(sc.nextLine()) == 1;
			accountDB.reviewApplication(confirm, toReview);
			cont = true;
			logMessage = "Employee gave review of " + confirm + " to application " + id;
		}
		else if(input.equals("5")) {
			// view log of transactions
			Collection<Audit> audits = auditDB.getAudits();
			audits.forEach( (audit) -> {System.out.println(audit);});
			cont = true;
			logMessage = "Employee viewed a log of all transactions.";
		}
		else if(input.equals("6")) {
			// logout
			System.out.println("Goodbye.");
			logMessage = "Employee logged out.";
			cont = false;
		}
		else {
			System.out.println("Please select a valid option.");
			logger.error("Employee input an invalid option");
			return true;
		}
		logger.trace(logMessage);
		return cont;
	}
	
	public static boolean processCustomerInput(Customer c, String input, Scanner sc) {
		AccountDAO accountDB = new AccountDB();
		TransferDAO transferDB = new TransferDB();
		
		String logMessage = "";
		boolean cont = false;
		
		if(input.equals("1")) {
			// apply for an account
			System.out.println("What would you like to name the account?");
			String name = sc.nextLine();
			System.out.println("Please specify a starting balance.");
			double startingBal = Double.parseDouble(sc.nextLine());
			Account toCreate = new Account(name, startingBal, true);
			accountDB.createAccount(c, toCreate);
			
			logMessage = "Customer " + c.getId() + " applied for account titled " + name + " with starting balance " + startingBal;
			cont = true;
		}
		else if(input.equals("2")) {
			// view my accounts
			Collection<Account> accounts = accountDB.getCustomerAccounts(c);
			accounts.forEach( (acct) -> {System.out.println(acct);});
			
			logMessage = "Customer " + c.getId() + " viewed all their accounts.";
			cont = true;
		}
		else if(input.equals("3")) {
			// make a deposit
			System.out.println("Please specify the ID of the account you'd like to deposit into.");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Please specify the amount you'd like to deposit.");
			double amount = Double.parseDouble(sc.nextLine());
			Account acct = null;
			try {
				acct = accountDB.getAccount(id);
				if(acct.getIs_application()) {
					System.out.println("This account is in the application stage. Please provide a valid account ID.");
					return true;
				}
				BankOperations.deposit(acct, amount);				
				System.out.println(amount + " deposited into account " + id);
				
				logMessage = "Customer " + c.getId() + " deposited " + amount + " into account " + id;
				cont = true;
			} catch (NegativeTransactionException e) {
				System.out.println(e.getMessage());
				logger.error("Customer " + c.getId() + " attempted to make a negative deposit to account " + id);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				cont = false;
			}
		}
		else if(input.equals("4")) {
			// make a withdrawal
			System.out.println("Please specify the ID of the account you'd like to withdraw from.");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Please specify the amount you'd like to withdraw.");
			double amount = Double.parseDouble(sc.nextLine());
			Account acct = null;
			try {
				acct = accountDB.getAccount(id);
				if(acct.getIs_application()) {
					System.out.println("This account is in the application stage. Please provide a valid account ID.");
					return true;
				}
				BankOperations.withdraw(acct, amount);
				System.out.println(amount + " withdrawn from account " + id);			

				logMessage = "Customer " + c.getId() + " withdrew " + amount + " from account " + id;
				cont = true;
			} catch (NegativeTransactionException e) {
				System.out.println(e.getMessage());
				logger.error("Customer " + c.getId() + " attempted to make a negative withdrawal from account " + id);
				return true;
			} catch (OverdrawException e) {
				System.out.println(e.getMessage());
				logger.error("Customer " + c.getId() + " attempted to overdraw from account " + id);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(input.equals("5")) {
			// post a transfer 
			System.out.println("Please specify the ID of the account you'd like to transfer from.");
			int from_id = Integer.parseInt(sc.nextLine());
			System.out.println("Please specify the ID of the accound you'd like to transfer into.");
			int to_id = Integer.parseInt(sc.nextLine());
			System.out.println("Please specify the amount that you'd like to transfer.");
			double amount = Double.parseDouble(sc.nextLine());
			Transfer t = new Transfer(from_id, to_id, amount, c.getId());
			transferDB.postTransfer(t);
			System.out.println("Transfer request posted.");
			
			logMessage = "Customer " + c.getId() + " posted a transfer from account " + from_id + " to account " + to_id + " for amount of " + amount;
			cont = true;
		}
		else if(input.equals("6")) {
			// view pending transfers
			Collection<Transfer> transfers = transferDB.getPendingTransfers(c);
			transfers.forEach( (trans) -> {System.out.println(trans);});
			
			logMessage = "Customer " + c.getId() + " viewed all their pending transactions.";
			cont = true;
		}
		else if(input.equals("7")) {
			// review a transfer
			System.out.println("Please specify the ID of the transfer you'd like to review.");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Press 1 to accept the transfer. Any other number will deny the transfer.");
			boolean confirm = Integer.parseInt(sc.nextLine()) == 1;
			transferDB.reviewTransfer(confirm, id);
			System.out.println("Transaction "+id+" has been processed.");
			
			logMessage = "Customer " + c.getId() + " gave review of " + confirm + " to transfer " + id;
			cont = true;
		}
		else if(input.equals("8")) {
			// logout
			System.out.println("Goodbye.");
			
			logMessage = "Customer " + c.getId() + " logged out.";
			cont = false;
		}
		else {
			System.out.println("Please select a valid option.");
			logger.error("Customer " + c.getId() + " input an invalid option");
			return true;
		}
		logger.trace(logMessage);
		return cont;
	}
	
	public static boolean processInput(User u, String input, Scanner sc) {
		int account_type = u.getType();
		boolean result = false;
		if(account_type == 1) 
			// user is a customer
			try {
				result = processCustomerInput(new Customer(u.getId(), u.getUsername(), u.getPassword()), input, sc);
			} catch (NumberFormatException e) {
				result = true;
				System.out.println("Please enter a valid number.");
				logger.error("Customer " + u.getId() + " did not input a valid number.");
			} catch (NullPointerException e) {
				result = true;
				System.out.println("Please specify a valid ID.");
				logger.error("Customer " + u.getId() + " did not input a valid ID.");
			} catch (ClassCastException e) {
				result = true;
				System.out.println("User is not a customer.");
				logger.error("Customer " + u.getId() + " did not input a customer's ID when expected.");
			} catch (Exception e) {
				result = true;
				e.printStackTrace();
			}
		else if(account_type == 2)
			// user is an employee
			try {
				result = processEmployeeInput(input, sc);				
			} catch (NumberFormatException e) {
				result = true;
				System.out.println("Please enter a valid number.");
				logger.error("Employee " + u.getId() + " did not input a valid number.");
			} catch (NullPointerException e) {
				result = true;
				System.out.println("Please specify a valid ID.");
				logger.error("Employee " + u.getId() + " did not input a valid ID.");
			} catch (ClassCastException e) {
				result = true;
				System.out.println("User is not a customer.");
				logger.error("Employee " + u.getId() + " did not input a customer's ID when expected.");
			}
			
		return result;
	}
	
	public static void displayLogo() {
		System.out.println("oooooooooo.        .o.       ooooo      ooo oooo    oooo     .oooooo.   oooooooooooo      .oooooo.    oooooooooooo   .oooooo.   ooooooooo.     .oooooo.    oooooooooooo ");
		System.out.println("`888'   `Y8b      .888.      `888b.     `8' `888   .8P'     d8P'  `Y8b  `888'     `8    d8P'  `Y8b   `888'     `8  d8P'  `Y8b  `888   `Y88.  d8P'  `Y8b   `888'     `8 ");
		System.out.println(" 888     888     .8\"888.      8 `88b.    8   888  d8'      888      888  888           888            888         888      888  888   .d88' 888            888         ");
		System.out.println(" 888oooo888'    .8' `888.     8   `88b.  8   88888[        888      888  888oooo8      888            888oooo8    888      888  888ooo88P'  888            888oooo8");
		System.out.println(" 888    `88b   .88ooo8888.    8     `88b.8   888`88b.      888      888  888    \"      888     ooooo  888    \"    888      888  888`88b.    888     ooooo  888    \"  ");
		System.out.println(" 888    .88P  .8'     `888.   8       `888   888  `88b.    `88b    d88'  888           `88.    .88'   888       o `88b    d88'  888  `88b.  `88.    .88'   888       o ");
		System.out.println("o888bood8P'  o88o     o8888o o8o        `8  o888o  o888o    `Y8bood8P'  o888o           `Y8bood8P'   o888ooooood8  `Y8bood8P'  o888o  o888o  `Y8bood8P'   o888ooooood8 ");
		
		logger.trace("System startup.");
	}
	
	public static void exitBank() {
		logger.trace("System shutdown.");
	}
}
