package com.revature.daos;

import com.revature.models.Accounts;

public interface AccountDAO {

	public Accounts getAccountByNum(int number);
	
	public int getAccountIDByUsername(String username);

	public void createAccount(int accountNumber, double accountBalance);

	public void updateAccount(int accountNumber, double accountBalance);
	
}

