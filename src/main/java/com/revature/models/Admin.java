package com.revature.models;

import java.util.Objects;

public class Admin {
	
	private String username;
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private boolean canApprove;
	private boolean hasAuthority;
	
	
	public Admin(String username, String password, String role, String firstName, String lastName, boolean canApprove,
			boolean hasAuthority) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.canApprove = canApprove;
		this.hasAuthority = hasAuthority;
	}

	public Admin() {
		super();
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", role=" + role + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", canApprove=" + canApprove + ", hasAuthority=" + hasAuthority + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(canApprove, firstName, hasAuthority, lastName, password, role, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return canApprove == other.canApprove && Objects.equals(firstName, other.firstName)
				&& hasAuthority == other.hasAuthority && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
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


	public boolean isCanApprove() {
		return canApprove;
	}


	public void setCanApprove(boolean canApprove) {
		this.canApprove = canApprove;
	}


	public boolean isHasAuthority() {
		return hasAuthority;
	}


	public void setHasAuthority(boolean hasAuthority) {
		this.hasAuthority = hasAuthority;
	}
	
}
