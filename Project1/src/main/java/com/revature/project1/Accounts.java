package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.revature.project1.util.LoggingUtil;

public class Accounts implements Serializable{
	
	private int accountNumber;
	//private String[] customersWithAccess; 
	public List<String> customersWithAccess = new ArrayList<String>();
	private boolean accountApproved;
	
	public int accountsNumberSerial; 
	
	
	private static final long serialVersionUID = 12345L;
	
	private double balance;

    public Accounts() {
		
		this.balance = 0;
		this.accountApproved = false;
		//this.accountNumber = accountsNumberSerial;
		//accountsNumberSerial++;
		
		Random randomNum = new Random();
		this.accountsNumberSerial = randomNum.nextInt(500);
		
	}
    public Accounts(String newAccountRequest){
		
		this.balance = 0;
		accountApproved = false;
		//this.accountNumber = accountsNumberSerial;
		Random randomNum = new Random();
		this.accountsNumberSerial = randomNum.nextInt(500);
		
	}
    public Accounts(double bal){
    	
    	this.balance = bal;
    	accountApproved = false;
    	this.accountNumber = accountsNumberSerial;
    	accountsNumberSerial++;
    	
    }
	public void withdraw(double wDraw) {
		
		LoggingUtil.logInfo("Withdrawn: " + wDraw);
		this.balance -= wDraw;
		
		if (this.balance < 0) {
			System.out.println("NO OVERDRAFT PROTECTION, CANNOT GO NEGATIVE!");
			this.balance += wDraw;
		}
		
	}
	public void deposit(double dDraw) {
		//throw new IllegalArgumentException();
		LoggingUtil.logInfo("deposit: " + dDraw);
		this.balance += dDraw;
	}
	public void transfer(double transF, Accounts otherAcc) {
		LoggingUtil.logInfo("transfer: Frist Account:" + transF + " Second Account: " + otherAcc);
		this.balance -= transF;
		otherAcc.deposit(transF);;
		
	}
	public double getBalance() {

		return this.balance;
	}
	public void setBalance(int bal) {
		
		this.balance = bal;
	}
	public boolean isAccountApproved() {
		if (this.accountApproved == false) {
			return false;
		}else {
			return true;
		}
		
	}
	public void setApproval(String thisChar) {
		if (thisChar.equals("n")) {
			accountApproved = false; 
		}else if (thisChar.equals("y")) {
			accountApproved = true;
		}
		
	}
	public void switchApproval() {
		
		LoggingUtil.logInfo("Approval Given for account: " + this.accountNumber);
		accountApproved = !accountApproved;
		
	}
	public int getAccountNumber() {
		//return this.accountNumber;
		return this.accountsNumberSerial;
	}
	public void setAccountNumber(int acctNum) {
		this.accountsNumberSerial = acctNum;
		
	}
	public List<String> getCustomersWithAccess() {
		return this.customersWithAccess;
	}
	public void setCustomersWithAccess(String customerID) {
		
		this.customersWithAccess.add(customerID);
		LoggingUtil.logInfo("Customer access added to: " + customerID);
	}

}