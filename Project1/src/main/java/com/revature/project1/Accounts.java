package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;

import com.revature.project1.util.LoggingUtil;

public class Accounts implements Serializable{
	
	private int accountNumber;
	private String[] customersWithAccess; 
	private boolean accountApproved;
	
	static int accountsNumberSerial = 333; 
	
	
	private static final long serialVersionUID = 12345L;
	
	private double balance;

    Accounts() {
		
		balance = 0;
		accountApproved = false;
		this.accountNumber = accountsNumberSerial;
		accountsNumberSerial++;
		
	}
    Accounts(String newAccountRequest){
		
		balance = 0;
		accountApproved = false;
		this.accountNumber = accountsNumberSerial;
		accountsNumberSerial++;
		
	}
    Accounts(double bal){
    	
    	balance = bal;
    	accountApproved = false;
    	this.accountNumber = accountsNumberSerial;
    	accountsNumberSerial++;
    	
    }
	public void withdraw(double wDraw) {
		
		LoggingUtil.logInfo("Withdrawn: " + wDraw);
		balance -= wDraw;
		
	}
	public void deposit(double dDraw) {
		//throw new IllegalArgumentException();
		LoggingUtil.logInfo("deposit: " + dDraw);
		balance += dDraw;
	}
	public void transfer(double transF, Accounts otherAcc) {
		LoggingUtil.logInfo("transfer: Frist Account:" + transF + " Second Account: " + otherAcc);
		balance -= transF;
		otherAcc.deposit(transF);;
		
	}
	public double getBalance() {

		return this.balance;
		
	}
	public boolean isAccountApproved() {
		if (this.accountApproved == false) {
			return false;
		}else {
			return true;
		}
		
	}
	public void switchApproval() {
		
		accountApproved = !accountApproved;
		
	}
	public int getAccountNumber() {
		//return this.accountNumber;
		return accountNumber;
	}
	public String[] getCustomersWithAccess() {
		return this.customersWithAccess;
	}

}