package com.revature.models;

import java.util.Objects;

public class Accounts {
	

	//private List <Double> customerAccounts = new ArrayList<>();
	private int accountNumbers;
	private double balance;
	private boolean active;
	
	public Accounts(int accountNumber, double balance, boolean active) {
		super();
		this.accountNumbers = accountNumber;
		this.balance = balance;
		this.active = active;
	}

	public Accounts() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumbers, active, balance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accounts other = (Accounts) obj;
		return accountNumbers == other.accountNumbers && active == other.active
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance);
	}

	@Override
	public String toString() {
		return "Accounts [accountNumbers=" + accountNumbers + ", balance=" + balance + ", active=" + active + "]";
	}

	public int getAccountNumbers() {
		return accountNumbers;
	}

	public void setAccountNumbers(int accountNumber) {
		this.accountNumbers = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
	
	