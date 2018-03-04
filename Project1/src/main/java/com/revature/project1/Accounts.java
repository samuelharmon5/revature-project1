package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;

public class Accounts implements Serializable{
	
	private String accountNumber;
	private String[] customersWithAccess; 
	private boolean accountApproved;
	
	private static final long serialVersionUID = 12345L;
	
	private double balance;

	public void Accounts() {
		
		balance = 0;
		accountApproved = false;
		
	}
	public void Accounts(String newAccountRequest){
		
		balance = 0;
		accountApproved = false;
	}
	public void withdraw(double wDraw) {
		balance -= wDraw;
		
	}
	public void deposit(double dDraw) {
		//throw new IllegalArgumentException();
		
		balance += dDraw;
	}
	public void transfer(double transF, Accounts otherAcc) {
		balance -= transF;
		otherAcc.deposit(transF);;
		
	}
	public double getBalance() {
		
		return this.balance;
		
	}
	public String getAccountNumber() {
		return this.accountNumber;
	}
	public String[] getCustomersWithAccess() {
		return this.customersWithAccess;
	}

}