# BankingProject0

This project is the first in Revature's Java bootcamp. The associated technologies are Java, Oracle Database, packaged and built using Maven.

## Usage

To clone the database with existing data, run the `bankexport.sql` file in an Oracle Database.
To run the project, open the project0 folder in SpringToolsSuite4 or similar Java IDE. Then select the class `App.java` and click Run.
Next you will be prompted with instructions. Improper input will be rejected, notifying the user of the error.

## Implementation
This project makes use of the JDBC classes `Connection`, `PreparedStatement`, `CallableStatement`, and `ResultSet`. It uses the JUnit5 library to test the code and the Log4j library to create application level logging files. 

## Design
The project makes use of the `Singleton` and `DataAccessObject` design patterns. `Singleton` is used in the `MyConnection` class to ensure that only one connection to the related database is opened. The `DataAccessObject` technique defines four interfaces, one for each entity of data related to the program. Each is implemented with a class that makes use of the `MyConnection` class to interact with each of the tables in the database.

The project is structured around the `model` package. This stores classes for Java objects that hold data from the database.

## Additional Information
Entity-Relationship Diagram can be found in the presentation PDF. Below is a list of the project requirements.

#### Description
The Bank app is a console-based application that simulates banking operations. A customer can apply for an account, view their balance, and make withdrawals and deposits. An employee can approve or deny accounts and view account balances for their customers.
	
#### Purpose
We want to see that you can meet deadlines and that you can code. You are expected to complete the following requirements and give a 5 minute presentation of your project to our QC team.
​
#### Requirements
1. Functionality should reflect the below user stories.
2. Data is stored in a database.
3. Data Access is performed through the use of JDBC in a data layer consisting of Data Access Objects.
4. All input is received using the java.util.Scanner class.
5. Log4j is implemented to log events to a file.
6. A minimum of one (1) JUnit test is written to test some functionality.
​
#### User Stories
* As a user, I can login.
* As a customer, I can apply for a new bank account with a starting balance.
* As a customer, I can view the balance of a specific account.
* As a customer, I can make a withdrawal or deposit to a specific account.
* As the system, I reject invalid transactions.
	* Ex:
		* A withdrawal that would result in a negative balance.
		* A deposit or withdrawal of negative money.t
* As an employee, I can approve or reject an account.
* As an employee, I can view a customer's bank accounts.
* As a user, I can register for a customer account.
* As a customer, I can post a money transfer to another account.
* As a customer, I can accept a money transfer from another account.
* As an employee, I can view a log of all transactions.
​
