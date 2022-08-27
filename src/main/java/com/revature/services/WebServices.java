package com.revature.services;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.WebDAOImpl;
import com.revature.models.Customers;



public class WebServices {

	private WebDAOImpl wDao = new WebDAOImpl();
	private ObjectMapper objectMapper = new ObjectMapper();
	

	
	
	@SuppressWarnings("unchecked")
	public <T> String getAllObjectsInJson(String className){
		
		Class<T> clazz;
		try {
			clazz = (Class<T>) Class.forName(className).getDeclaredConstructor().newInstance();
			List<T> list = wDao.getAllObjects(clazz);
			
			try {
				String json = objectMapper.writeValueAsString(list);
				return json;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

	public String getCustomerByIdInJson(int id) throws NumberFormatException{
		
		Customers customer = wDao.getCustomerById(id);
		
		String json = objectMapper.writeValueAsString(customer);
		
		return json;
	}
	
	public String getCustomerField(int id, String fieldName) throws NumberFormatException, NoSuchMethodException, SecurityException, Exception{
	
			Customers customerObject = wDao.getCustomerById(id); // values after creating with ID
		
			Class<?> customerClass = customerObject.getClass(); // gets class properties
						
			String getterName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
		       
	        Method getterMethod = customerClass.getMethod(getterName);
	        
	        String jsonField =  (String) getterMethod.invoke(customerObject);
			
	        return jsonField;
	}
	
	//---------------------------------------------doPost, doPut and doPatch methods ------------------------

	public void sendJsonString(String json) {
		OrmyS.deSerialize(json, null)
	}
}
