package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Accounts;
import com.revature.models.Customers;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	
	@Override
	public Accounts getAccountByNum(int number) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM accounts WHERE account_number = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, number); // this is where SQL injection is checked for. Tim used the example of String though.
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				Accounts account = new Accounts();
				account.setAccountNumbers(result.getInt("account_number"));
				account.setBalance(result.getDouble("account_balance"));
				account.setActive(result.getBoolean("account_active"));
				return account;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getAccountIDByUsername(String username) { // this statement must be executed after the customer is inserted into the database
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM customers WHERE customers.username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username); // this is where SQL injection is checked for. Tim used the example of String though.
			
			ResultSet result = statement.executeQuery();
			int id = 10; 
			if (result.next()) {
				id = result.getInt("account_Number");
			}
			return id;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}
	
	public void createAccount(int accountNumber, double accountBalance) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO accounts (account_number, account_balance)\r\n"
					+ "VALUES (?, ?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountNumber); 
			statement.setDouble(2, accountBalance);
			
			statement.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateAccount(int accountNumber, double accountBalance) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE accounts SET account_balance = ?\r\n"
					+ "WHERE accounts.account_number = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(1, accountBalance); 
			statement.setInt(2, accountNumber);
			
			statement.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

}
