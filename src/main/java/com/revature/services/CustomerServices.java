package com.revature.services;

import java.util.List;

import com.revature.daos.CustomerDAO;
import com.revature.daos.CustomerDAOImpl;
import com.revature.models.Customers;

public class CustomerServices {
	
	private CustomerDAO customerDao = new CustomerDAOImpl();
	
	public Customers getSingleCustomer(String username, String password) { //instead of ID, it's by username and password
		return customerDao.getCustomerByCredential(username, password);
	}
	
	public List<Customers> getAllCustomers(){
		return customerDao.getAllCustomers();
	}

	public void createCustomer(Customers customer) {
		customerDao.registerCustomer(customer);
	}
	
	public double getBalances(String username) {
		return customerDao.getBalance(username);
	}
	
	
	/*public static String encryption(String password) {
		
		String tempPassword = password;
		
		char[] charay = tempPassword.toCharArray();
		for (int i = 0; i < tempPassword.length(); i++) {
			charay[i] = (char) (charay[i] + 20);
		}
		
		String encryptedPassword = new String(charay);
		return encryptedPassword;
	}*/
	
}
