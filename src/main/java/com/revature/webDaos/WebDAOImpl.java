package com.revature.webDaos;

import java.util.List;

import com.revature.daos.CustomerDAOImpl;
import com.revature.models.Customers;
import com.revature.modelsORM.Connectivity;
import com.revature.services.WebServices;
import com.revature.webDaos.*;

	public class WebDAOImpl {
	
	private CustomerDAOImpl<Customers> cDao = new CustomerDAOImpl<Customers>(); 
	private Connectivity connect;
	
	@SuppressWarnings("unchecked")
	public List<Customers> getAllObjects(Customers cust){ 
		connectToDatabase();
		System.out.println("testing inside webDAOImpl");
		return (List<Customers>) cDao.getAllObjects(cust.getClass());
 	}
		
	public void insertObject(Customers cust) {
		connectToDatabase();
		cDao.insertObject(cust);
	}
	
	public void updateObject(Customers cust) {
		connectToDatabase();
		cDao.updateObject(cust);
	}
	
	public Customers getObjectById(Customers cust, int id) {
		connectToDatabase();
		return cDao.getObjectById(cust.getClass(), id);
	}
	
	public Customers getObjectByFirstName(Customers cust, String firstName) {
		connectToDatabase();
		return cDao.getObjectByFirstName(cust.getClass(), firstName);
	}
	
	public void deleteObjectById(Customers cust, int id) {
		connectToDatabase();
		cDao.deleteObjectById(cust, id);
	}
	
	//this allows us to connect to our Database 
	
	public void connectToDatabase() {
		connect = new Connectivity("javafs220725.clmfaswsjivh.us-west-1.rds.amazonaws.com", "project1", "postgres", "Lucky123!");
		cDao.initializeConnection(connect);
		}
	
	
	
	
	/*
	public static void main(String[] args) {
		Customers customerTest = new Customers("02/22/22", "myfreind", "SHARM", "Rivas","888-888-8888","bossjarib@yahoo.com","engineer", "8", "04/44/44","hard trainer");
		Customers emptyCustomer = new Customers();
		WebDAOImpl wdi = new WebDAOImpl();
		System.out.println(customerTest);
		wdi.insertObject(customerTest);
	}
	*/
}
	
	
