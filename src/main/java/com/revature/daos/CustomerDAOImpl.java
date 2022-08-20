package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Accounts;
import com.revature.models.Customers;
import com.revature.utils.ConnectionUtil;


public class CustomerDAOImpl implements CustomerDAO {


	public List<Customers> getAllCustomers() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM customers LEFT JOIN accounts ON customers.account_number = accounts.account_number;"; //returns all info from both tables
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			//resultSets are cursor based, each time .next is called the cursor moves to the next group of values.
			//it starts one before so you always need to call next.
			
			List<Customers> customerList = new ArrayList<>();
			
			while(result.next()) { // instead of if statement, the while statement will keep returning the next result of the queue.
				Customers customer = new Customers(
						result.getString("username"),
						result.getString("pass_word"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("home_number"),
						result.getString("street_name"),
						result.getString("city"),
						result.getString("state"),
						result.getInt("zipcode"),
						null //result.getInt("account_number") see below code
						);
				
				int accountNumber = result.getInt("account_number");
				if(accountNumber!=0) { // checks if there's an accountNumber in the database
					Accounts account = new Accounts();
					account.setAccountNumbers(accountNumber);
					account.setBalance(result.getDouble("account_balance"));
					account.setActive(result.getBoolean("account_active"));
					customer.setAccountNumber(account); // set the account into customer
				}
				customerList.add(customer); // this will add a customer to arrayList after every creation
			}
			return customerList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public Customers getCustomerByCredential(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM customers WHERE username = ? AND pass_word = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username); // this is where SQL injection is checked for. Tim used the example of String though.
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				Customers customer = new Customers(
						result.getString("username"),
						result.getString("pass_word"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("home_number"),
						result.getString("street_name"),
						result.getString("city"),
						result.getString("state"),
						result.getInt("zipcode"),
						null
						);
				
				int accountNumber = result.getInt("account_number");
				if(accountNumber!=0) { // checks if there's an accountNumber in the database
					AccountDAO accountDao = new AccountDAOImpl(); // if there is, accountDao gets that information
					
					Accounts account = accountDao.getAccountByNum(accountNumber); // make another call to the home??
					
					customer.setAccountNumber(account); // set the account into customer
				}
				
				
				return customer;
						
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	
	
	@Override
	public void registerCustomer(Customers customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO customers (username, pass_word, first_name, last_name, home_number, street_name, "
					+ "city, state, zipcode)"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?,?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, customer.getUsername());
			statement.setString(++count, customer.getPassword()); // must get encryption
			statement.setString(++count, customer.getFirstName());
			statement.setString(++count, customer.getLastName());
			statement.setString(++count, customer.getHomeNumber());
			statement.setString(++count, customer.getStreetName());
			statement.setString(++count, customer.getCity());
			statement.setString(++count, customer.getState());
			statement.setInt(++count, customer.getZipcode());		
			
			statement.execute();	
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//return customer;
		
	}
	
	@Override
	public double getBalance(String username) { // gets balance of user
		try(Connection conn = ConnectionUtil.getConnection()){
							
			String sql = "SELECT * FROM customers LEFT JOIN accounts ON customers.account_number = accounts.account_number;";		
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			double balance = 0;
			if(result.next()) {
			System.out.println(result.getDouble("account_balance"));
			balance = result.getDouble("account_balance");
			}
			return balance;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// copy and paste this one on dbeaver for updating balance
	//UPDATE accounts SET customers.account_number = accounts.account_number, accounts.account_balance = 55 FROM customers LEFT JOIN accounts ON customers.account_number WHERE customer.account_number = accounts.account_number 

		
	/*
		public static void main(String[] args) {
		CustomerDAO cDao = new CustomerDAOImpl();
		cDao.getBalance("user2");
		//System.out.println(cDao.getCustomerByCredential("user2", "yes123"));
	}
	}

	
	public void updateBalance(String username, double balance);
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "UPDATE account_balance FROM customers LEFT JOIN accounts ON customers.account_number = accounts.account_number;";		
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			double balance = 0;
			if(result.next()) {
				System.out.println(result.getDouble("account_balance"));
				balance = result.getDouble("account_balance");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
	}
		*/
	
}