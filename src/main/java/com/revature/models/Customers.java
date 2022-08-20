package com.revature.models;

import java.util.Objects;

public class Customers {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String homeNumber;
	private String streetName;
	private String city;
	private String state;
	private int zipcode;
	private Accounts accountNumber;
	
	public Customers(String username, String password, String firstName, String lastName, String homeNumber,
			String streetName, String city, String state, int zipcode, Accounts accountNumber) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeNumber = homeNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.accountNumber = accountNumber;
	}

	public Customers() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public Accounts getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Accounts accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, city, firstName, homeNumber, lastName, password, state, streetName, username,
				zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customers other = (Customers) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(city, other.city)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(homeNumber, other.homeNumber)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(state, other.state) && Objects.equals(streetName, other.streetName)
				&& Objects.equals(username, other.username) && zipcode == other.zipcode;
	}

	@Override
	public String toString() {
		return "Customers [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", homeNumber=" + homeNumber + ", streetName=" + streetName + ", city=" + city + ", state="
				+ state + ", zipcode=" + zipcode + ", accountNumber=" + accountNumber + "]";
	}
	
	
	
	
}
