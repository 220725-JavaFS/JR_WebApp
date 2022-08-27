package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.models.Customers;


import com.revature.services.WebServices;

public class ControllerOne extends HttpServlet{ // CATALINA_Home is another name for tomcat
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WebServices ws = new WebServices();
	
	//protected is what the HttpServlets already defines for the methods. You can make it public, but not private (or more restricted)
	//this is a common interview question -> the method signature, make sure you know everything and the throws as well.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ // this exception would be caught by and managed by Tomcat
		
		String URI = request.getRequestURI(); // WEEK4 DAY 3 VIdeo lectures last one
		System.out.println(URI);
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
	
	/*
	// -------------------------------bottom ones are incomplete and not up-to-date-------------------------	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		StringBuilder sb = new StringBuilder(); //WEEK4 DAY 3 VIDEO LECTURES time 3:14
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
		while(line!=null) {
			sb.append(line);
			line=reader.readLine();
		}
		
		String json = new String(sb);
		
		ws.
		
		/* -------- I comment this old stuff out. Instead of converting straight to json, we'll be sending the json to the deserializer in ORM-----
		 
		StringBuilder sb = new StringBuilder(); //WEEK4 DAY 3 VIDEO LECTURES
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
		while(line!=null) {
			sb.append(line);
			line=reader.readLine();
		}
		
		String json = new String(sb);
		System.out.println(json);
		
		Customers customer = objectMapper.readValue(json, Customers.class);
		
		ws.createCustomer(customer);
		
		response.setStatus(201);
		-------------------------------------------------------------STOP HERE OF COMMENT
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
		System.out.println(json);
		
		
		//client send complete customer info
		Customers customer = objectMapper.readValue(json, Customers.class);
		//updates entire entity
		ws.updateCustomer(customer);
		
		response.setStatus(202);
	}
	
	
	@Override
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
		ws.updateCustomer(customer);
		
		response.setStatus(202);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
		ws.deleteCustomer(customer);
		
		response.setStatus(200);
		
		//if (not good) {
		//	response.setStatus(400); // bad request
		}
	}
	
	*/
	
}	
	
