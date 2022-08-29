package com.revature.services;

import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Customers;
import com.revature.webDaos.WebDAOImpl;



public class WebServices {

	private Customers cust = new Customers();
	private WebDAOImpl wDao = new WebDAOImpl();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	public String getAllObjectsInJson(){
		List<Customers> list = wDao.getAllObjects(this.cust);
		try {
			String json = objectMapper.writeValueAsString(list);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	public static void main(String[] args) {
		Customers customerTest = new Customers("02/22/22", "myfreind", "SHARM", "Rivas","888-888-8888","bossjarib@yahoo.com","engineer", 8, "04/44/44","hard trainer");
		Customers emptyCustomer = new Customers();
		WebServices wst = new WebServices();
		System.out.println(customerTest);
		wst.insertObject(customerTest);
	
		
	}
	*/
	
	public void insertObject(Customers customer) {
		wDao.insertObject(customer);
	}
	
	public void updateObject(Customers customer) {
		wDao.updateObject(customer);
	}

	public String getObjectByIdInJson(int id) throws NumberFormatException{
		Customers customer = wDao.getObjectById(this.cust, id);
		try {
			String json = objectMapper.writeValueAsString(customer);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String getObjectByFirstNameInJson(String firstName) throws NumberFormatException{
		Customers customer = wDao.getObjectByFirstName(this.cust, firstName);
		try {
			String json = objectMapper.writeValueAsString(customer);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void deleteObjectByIdInJson(int id) {
		wDao.deleteObjectById(this.cust, id);
	}
		
	// this method calls getObjectById Method and selects the fieldName here 
	public String getFieldByIdInJson(int id, String fieldName) throws NumberFormatException, NoSuchMethodException, SecurityException, Exception{
	
		
		Customers customer = wDao.getObjectById(this.cust, id);
					
		String getterName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
		       
	    Class<?> getterString = customer.getClass().getDeclaredField(fieldName).getType();
		
	    Method getterMethod = customer.getClass().getMethod(getterName, getterString);
	    
	    String jsonField =  (String) getterMethod.invoke(customer);
				
		String json = objectMapper.writeValueAsString(jsonField);
		
		return json;
		}
	
}
