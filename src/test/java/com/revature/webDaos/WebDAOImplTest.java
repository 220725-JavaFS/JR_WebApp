package com.revature.webDaos;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.webDaos.WebDAOImpl;
import com.revature.models.Customers;

public class WebDAOImplTest {

	private Customers testCustomer;
	private WebDAOImpl wDao = new WebDAOImpl();
	private Customers emptyCustomer = new Customers();
	
	@BeforeEach
	public void setUp() {
		testCustomer = new Customers("02/02/22", "Smith referal", 
			"Jarib", "Rivas", "955-285-5543", "bossjarib@hotmail.com",
			"engineer", "57", "09/1/2022","excellent job on workouts");
	}
	
	@Test
	public void insertNewObjectTest() {
		wDao.insertObject(testCustomer);
	}
	/*
	@Test
	public void retriveRowContentByColumnTest() {
		
		assertNotNull(cDao.retriveRowContentByColumn("admin", 3, testCustomer));
		assertNull(cDao.retriveRowContentByColumn("RandomUser", 3, testCustomer));
	}
	
	@Test
	public void retriveAllTest() {
		assertNotNull(cDao.retriveAll(testCustomer));
	}
	
	@Test
	public void updateRowContentByColumnTest() {
		cDao.updateRowContentByColumn("Updated", 1,"Testuser", 3, testCustomer);
	}
	
	@AfterEach
	public void endOfTests() {
		cDao.deleteRowContentByColumn("TestUser", 3, testCustomer);
	}
	*/
}
