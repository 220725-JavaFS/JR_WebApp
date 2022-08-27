package com.revature.webDaos;

import java.util.List;

import com.revature.daos.CustomerDAOImpl;
import com.revature.models.Customers;
import com.revature.modelsORM.Connectivity;
import com.revature.webDaos.*;

	public class WebDAOImpl {
	
	private CustomerDAOImpl<Customers> cDao = new CustomerDAOImpl<Customers>(); 
	private Connectivity connect;
	
	@SuppressWarnings("unchecked")
	public List<Customers> getAllObjects(Customers cust){ // 1st in webServices
		
		return (List<Customers>) cDao.getAllObjects(cust.getClass());
 	}
	
	//connectToDatabase();
	
	public void insertObject(Object o) {
		connectToDatabase();
		cDao.insertObject(o);
	}
	
	public void updateObject(Object o) {
		connectToDatabase();
		cDao.updateObject(o);
	}
	
	public Customers getObjectById(Customers cust, int id) { //2nd in webservices
		connectToDatabase();
		return cDao.getObjectById(cust.getClass(), id);
	}
	
	public <T> T getObjectByFirstName(Class<T> clazz, String firstName) {
		connectToDatabase();
		return cDao.getObjectByFirstName(clazz, firstName);
	}
	
	public void deleteObjectById(Object o, int id) {
		connectToDatabase();
		cDao.deleteObjectById(o, id);
	}
	
	//this allows us to connect to our Database through any ORM
	public void connectToDatabase() {
		connect = new Connectivity("javafs220725.clmfaswsjivh.us-west-1.rds.amazonaws.com", "project1", "postgres", "Lucky123!");
		cDao.initializeConnection(connect);
		}
}
