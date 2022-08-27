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
	
		List<Customers> list = wDao.getAllObjects(cust);
			
		String json;
		try {
			json = objectMapper.writeValueAsString(list);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getObjectByIdInJson(int id) throws NumberFormatException{
		
		Customers customer = wDao.getObjectById(cust, id);
	
		try {
			String json = objectMapper.writeValueAsString(customer);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
	}
		
	// this method calls getObjectById Method and selects the fieldName 
	public String getFieldByIdInJson(int id, String fieldName) throws NumberFormatException, NoSuchMethodException, SecurityException, Exception{
	
		
		Customers customer = wDao.getObjectById(cust, id);
					
		String getterName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
		       
	    Class<?> getterString = customer.getClass().getDeclaredField(fieldName).getType();
		
	    Method getterMethod = customer.getClass().getMethod(getterName, getterString);
	    
	    String jsonField =  (String) getterMethod.invoke(customer);
				
		String json = objectMapper.writeValueAsString(jsonField);
		
		return json;
		}

	
	//---------------------------------------------doPost, doPut and doPatch methods ------------------------

	
}
