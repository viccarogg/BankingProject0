package com.george.banking;

import java.util.Collection;
import java.util.Scanner;

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
		if(input.equals("1")) {
			// view all customers
			Collection<Customer> customers = userDB.getAllCustomers();
			customers.forEach( (cust) -> {System.out.println(cust);});
			return true;
		}
		else if(input.equals("2")) {
			// view a customer's accounts
			System.out.println("Please specify the ID of the customer whose accounts you'd like to view.");
			int id = Integer.parseInt(sc.nextLine());
			Collection<Account> accounts = accountDB.getCustomerAccounts((Customer)userDB.getUser(id));
			accounts.forEach( (acct) -> {System.out.println(acct);});
			return true;	
		}
		else if(input.equals("3")) {
			// view account applications
			Collection<Account> apps = accountDB.getAllApplications();
			apps.forEach( (app) -> {System.out.println(app + " - Owner: " + app.getOwner_id());});
			return true;
		}
		else if(input.equals("4")) {
			// review an application
			System.out.println("Please specify the application to review.");
			int id = Integer.parseInt(sc.nextLine());
			Account toReview = accountDB.getAccount(id);
			System.out.println("Press 1 to accept the application. Any other input will deny the application.");
			boolean confirm = Integer.parseInt(sc.nextLine()) == 1;
			accountDB.reviewApplication(confirm, toReview);
			return true;
		}
		else if(input.equals("5")) {
			// view log of transactions
			Collection<Audit> audits = auditDB.getAudits();
			audits.forEach( (audit) -> {System.out.println(audit);});
			return true;
		}
		else if(input.equals("6")) {
			// logout
			System.out.println("Goodbye.");
			return false;
		}
		else {
			System.out.println("Please select a valid option.");
			return true;
		}
	}
	
	public static boolean processCustomerInput(Customer c, String input, Scanner sc) {
		AccountDAO accountDB = new AccountDB();
		TransferDAO transferDB = new TransferDB();
		if(input.equals("1")) {
			// apply for an account
			System.out.println("What would you like to name the account?");
			String name = sc.nextLine();
			System.out.println("Please specify a starting balance.");
			double startingBal = Double.parseDouble(sc.nextLine());
			Account toCreate = new Account(name, startingBal, true);
			accountDB.createAccount(c, toCreate);
			return true;
		}
		else if(input.equals("2")) {
			// view my accounts
			Collection<Account> accounts = accountDB.getCustomerAccounts(c);
			accounts.forEach( (acct) -> {System.out.println(acct);});
			return true;
		}
		else if(input.equals("3")) {
			// make a deposit
			System.out.println("Please specify the ID of the account you'd like to deposit into.");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Please specify the amount you'd like to deposit.");
			double amount = Double.parseDouble(sc.nextLine());
			try {
				BankOperations.deposit(accountDB.getAccount(id), amount);				
				System.out.println(amount + " deposited into account " + id);
			} catch (NegativeTransactionException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		else if(input.equals("4")) {
			// make a withdrawal
			System.out.println("Please specify the ID of the account you'd like to withdraw from.");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Please specify the amount you'd like to withdraw.");
			double amount = Double.parseDouble(sc.nextLine());
			try {
				BankOperations.withdraw(accountDB.getAccount(id), amount);
				System.out.println(amount + " withdrawn from account " + id);			
				return true;
			} catch (NegativeTransactionException e) {
				System.out.println(e.getMessage());
			} catch (OverdrawException e) {
				System.out.println(e.getMessage()	);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
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
			return true;
		}
		else if(input.equals("6")) {
			// view pending transfers
			Collection<Transfer> transfers = transferDB.getPendingTransfers(c);
			transfers.forEach( (trans) -> {System.out.println(trans);});
			return true;
		}
		else if(input.equals("7")) {
			// review a transfer
			System.out.println("Please specify the ID of the transfer you'd like to review.");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Press 1 to accept the transfer. Any other input will deny the transfer.");
			boolean confirm = Integer.parseInt(sc.nextLine()) == 1;
			transferDB.reviewTransfer(confirm, id);
			System.out.println("Transaction "+id+" has been processed.");
			return true;
		}
		else if(input.equals("8")) {
			// logout
			System.out.println("Goodbye.");
			return false;
		}
		else {
			System.out.println("Please select a valid option.");
			return true;
		}
	}
	
	public static boolean processInput(User u, String input, Scanner sc) {
		int account_type = u.getType();
		if(account_type == 1)
			// user is a customer
			return processCustomerInput(new Customer(u.getId(), u.getUsername(), u.getPassword()), input, sc);
		else if(account_type == 2)
			// user is an employee
			return processEmployeeInput(input, sc);
		else
			return false;
	}
}
