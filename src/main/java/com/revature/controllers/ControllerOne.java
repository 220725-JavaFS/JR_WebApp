package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Customers;
import com.revature.services.CustomerServices;

public class ControllerOne extends HttpServlet{ // CATALINA_Home is another name for tomcat
	
	private CustomerServices cs = new CustomerServices();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	//protected is what the HttpServlets already defines for the methods. You can make it public, but not private (or more restricted)
	//this is a common interview question -> the method signature, make sure you know everything and the throws as well.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ // this exception would be caught by and managed by Tomcat
		
		String URI = request.getRequestURI();
		System.out.println(URI);
		// /projectOneWAR/getToWork/{id}
		
		String[] urlSections = URI.split("/");
		
		if(urlSections.length == 3) {
			
		
		
		
		List<Customers> list = cs.getAllCustomers();
		
		String json = objectMapper.writeValueAsString(list);
		System.out.println(json);
		
		PrintWriter printWriter = response.getWriter(); // this message goes into the body
		
		printWriter.print(json);
		
		response.setStatus(200);
		
		response.setContentType("application/json");
		
		} else if(urlSections.length==4) {
			try {
			int id = Integer.valueOf(urlSections[3]);
			
			Customers customer = cs.getSingleCustomer(); // notice this is not going to work because we call our customers by username and password
			
			PrintWriter printWriter = response.getWriter();
			
			String json = objectMapper.writeValueAsString(customer);
			
			printWriter.print(json);
			response.setStatus(200);
			response.setContentType("application/json");
			
			} catch(NumberFormatException e) {
				response.setStatus(404);
				return;
			}
		}else {
			response.setStatus(404);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		StringBuilder sb = new StringBuilder(); //WEEK4 DAY 2 VIDEO LECTURES
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
		while(line!=null) {
			sb.append(line);
			line=reader.readLine();
		}
		
		String json = new String(sb);
		System.out.println(json);
		
		Customers customer = objectMapper.readValue(json, Customers.class);
		
		cs.createCustomer(customer);
		
		response.setStatus(201);
		
		
		
	}
		
	
	
		
	/*	
		String URI = request.getRequestURI();
		System.out.println(URI);
		
		PrintWriter print = response.getWriter(); // throws an IOException
		print.print("<h1>Hello from your doGet method!</h1>"); //printing to the body of the response in this line
		response.setStatus(218);
		response.setHeader("content-type", "text/html");
		*/
		

}
