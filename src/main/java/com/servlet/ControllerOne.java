package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Customers;
import com.revature.services.WebServices;

public class ControllerOne extends HttpServlet{ // CATALINA_Home is another name for tomcat
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final long serialVersionUID = 55L;
	public WebServices ws = new WebServices();
	
	//protected is what the HttpServlets already defines for the methods. You can make it public, but not private (or more restricted)
	//this is a common interview question -> the method signature, make sure you know everything and the throws as well.
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ // this exception would be caught by and managed by Tomcat
		
		String URI = request.getRequestURI(); // WEEK4 DAY 3 VIdeo lectures last one
		System.out.println(URI); //prints outwell
		// /projectOneWAR/getToWork/{id}
		String[] urlSections = URI.split("/");
		
		if(urlSections.length == 3) { // section {3} is where it returns all avenger, if there's another / it will return specifics
		// gets className from URL		
		// gets all Objects to jSonList via ServiceLayer	
			try {
		
			String jsonList = ws.getAllObjectsInJson();
			
			PrintWriter printWriter = response.getWriter(); // this message goes into the body
			
			printWriter.print(jsonList);
			
			response.setStatus(200);
			
			response.setContentType("application/json"); 
			} catch (Exception e){
				e.printStackTrace();
			}
		//-----------------------------------------------------------------------------------------------------------------------------
			// this example below will return a single customer using the {id} !
		} else if(urlSections.length==4) {
			
			if (Integer.valueOf(urlSections[3])>0) { // added this to check if user inputs id or string
				try {	
				int id = Integer.valueOf(urlSections[3]);
				
				String jsonId = ws.getObjectByIdInJson(id);
				
				PrintWriter printWriter = response.getWriter();
				
				printWriter.print(jsonId);
				response.setStatus(200);
				response.setContentType("application/json");
				
				} catch(NumberFormatException e) {
					response.setStatus(404);
					return;
				}
				
			} else {
				try {	
					String firstName = String.valueOf(urlSections[3]);
					
					String jsonFirstName = ws.getObjectByFirstNameInJson(firstName);
					
					PrintWriter printWriter = response.getWriter();
					
					printWriter.print(jsonFirstName);
					response.setStatus(200);
					response.setContentType("application/json");
					
					} catch(NumberFormatException e) {
						response.setStatus(404);
						return;
					}
			}
			
		}else if (urlSections.length==5) { // this will return a specific field from the customer!
			try {
				int id = Integer.valueOf(urlSections[3]);
				// /blank/customers/3/modelField/
				String fieldName = String.valueOf(urlSections[4]); // field retrieving from the 5th '/' 
				
				String jsonField = ws.getFieldByIdInJson(id, fieldName);
				
				PrintWriter printWriter = response.getWriter();
				
				printWriter.print(jsonField);
				response.setStatus(200);
				response.setContentType("application/json");
			}catch (NumberFormatException nfe) {
				response.setStatus(404);
			}catch (NoSuchMethodException  nsme) {
	             response.setStatus(404);
			}catch (SecurityException se) {
				response.setStatus(404);
			}catch (Exception e) {
				response.setStatus(404);
			}
		}else {
			response.setStatus(404);
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 
		StringBuilder sb = new StringBuilder(); //WEEK4 DAY 3 VIDEO LECTURES
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
		while(line!=null) {
			sb.append(line);
			line=reader.readLine();
		}
		
		String json = new String(sb);
		System.out.println(json);
				
		try {
			
			Customers customer = objectMapper.readValue(json, Customers.class);

			ws.insertObject(customer);
			
			response.setStatus(202);
			response.setContentType("application/json");
			
		}catch (NumberFormatException nfe) {
			response.setStatus(404);
		}catch (SecurityException se) {
			response.setStatus(404);
		}catch (Exception e) {
			response.setStatus(404);
		}
	}
	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		StringBuilder sb = new StringBuilder(); //WEEK4 DAY 3 VIDEO LECTURES
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
		while(line!=null) {
			sb.append(line);
			line=reader.readLine();
		}
		
		String json = new String(sb);
		System.out.println("this is the json string: "+json);
		
		
		//client send complete customer info
		Customers customer = objectMapper.readValue(json, Customers.class);
		//updates entire entity
		System.out.println("this is customer: "+customer);
		try {
			System.out.println("inside the try method");
			ws.updateObject(customer);
			System.out.println("inside the try method after running update object");
			response.setStatus(202);
		} catch (Exception e) {
			response.setStatus(404);
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String URI = request.getRequestURI();
		System.out.println(URI);
		String[] urlSections = URI.split("/");
		
		//DELETE ITEM BY ITEM NUMBER
		if (urlSections.length==4) {
			System.out.println("inside the if but outside the try/catch");
			try {
				int id = Integer.valueOf(urlSections[3]);

				System.out.println("inside the tryblock");
				ws.deleteObjectByIdInJson(id);

				response.setStatus(202); //STATUS ACCEPTED
			}catch(NumberFormatException e) {
				response.setStatus(404);//ERROR: NOT FOUND
				return;
			}
		}else {
			System.out.println("else block controllerOne");
			response.setStatus(404);//ERROR: NOT FOUND
		}
	}
		
		//if (not good) {
		//	response.setStatus(400); // bad request
		
	
	
	/*
	protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		StringBuilder sb = new StringBuilder(); //WEEK4 DAY 3 VIDEO LECTURES
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
		while(line!=null) {
			sb.append(line);
			line=reader.readLine();
		}
		
		String json = new String(sb);
		System.out.println(json);
		
		
		//client send complete customer info
		Customers customer = objectMapper.readValue(json, Customers.class);
		//updates entire entity
		try {
			ws.updateObject(customer);
			response.setStatus(202);
		} catch (Exception e) {
			response.setStatus(403);
		}
	}
	
	
	/* ---------------this is how the doDelete method works according to Alehandro Huerta-----------
	String URI = request.getRequestURI();
	String[] urlSections = URI.split("/");
	if(urlSections.length==4) {
		String userName = String.valueOf(urlSections[3]);
		Account deleteAccountByUsername = (Account) or.retriveRowContentByColumn(userName, 3, ac);	
		or.deleteRowContentByColumn(userName, 3, deleteAccountByUsername);
		response.setStatus(200);
	}else {
		response.setStatus(404);
	}
	*/
}
	

	
	
	
