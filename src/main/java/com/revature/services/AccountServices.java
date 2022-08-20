package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Accounts;
import com.revature.models.Customers;

public class AccountServices {
	
	private AccountDAO aDao = new AccountDAOImpl();
	
	
	public Accounts getAccountByNum(int number) {
		return aDao.getAccountByNum(number);
	}
	
	public int getAccountIDByUsername(String username) {
		return aDao.getAccountIDByUsername(username);
	}
	
	public void createAccount(int accountNumber, double accountBalance) {
		aDao.createAccount(accountNumber, accountBalance);
	}
	
	public void updateAccount(int accountNumber, double accountBalance) {
		aDao.updateAccount(accountNumber,accountBalance);
	}
	
	// ---------------------------------------------------BELOW are regular account transactions
	
	


}