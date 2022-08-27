package com.revature.daos;

import java.util.List;

import com.revature.models.Connectivity;
import com.revature.models.Customers;

	public class WebDAOImpl {
	
	private CustomerDAOImpl cDao = new CustomerDAOImpl(); 
	private Connectivity connect;

	public void insertObject(Object o) {
		connectToDatabase();
		cDao.insertObject(o);
	}
	
	public void updateObject(Object o) {
		connectToDatabase();
		cDao.updateObject(o);
	}
	
	public <T> List<T> getAllObjects(Class<T> clazz){
		connectToDatabase();
		return cDao.getAllObjects(clazz);
	}
	
	public <T> T getObjectById(Class<T> clazz, int id) {
		connectToDatabase();
		return cDao.getObjectById(clazz, id);
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
