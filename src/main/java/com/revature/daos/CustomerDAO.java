package com.revature.daos;

import java.util.List;

import com.revature.models.Customers;

// DAO data access object - this is where we define what type of interactions we can have with our database
public interface CustomerDAO {

	//undefined at the moment
	//public Customers getCustomersByID(int ID);
	
	List<Customers> getAllCustomers();
	
	public Customers getCustomerByCredential(String username, String password);
	
	public void registerCustomer(Customers customer);

	public double getBalance(String username);

}
