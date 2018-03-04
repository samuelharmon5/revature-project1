package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.Scanner;

public class Customer implements User, Serializable{
	
	public String name; 
	public String userID;
	public String passWord;
	private String[] accountsOwned; 
	
	private static final long serialVersionUID = 12L;
	
	public Customer() {
		
	}
	
	public Customer(String uID, String uPassW) {
		this.userID = uID;
		this.passWord = uPassW;
	}
	public Customer(String cName, String uID, String uPassW) {
		this.userID = uID;
		this.passWord = uPassW;
		this.name = cName;
	}


	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassWord() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void customerRegistration() {
		//Customer currCustomer = new Customer();
		//String cName;
		//String cUserID;
		//String cPassWord;
		
		Scanner userI = new Scanner(System.in);
		
		System.out.println("Registration Start: Please Enter your name");
		this.name = userI.nextLine();
		System.out.println("Hello " + this.name);
		System.out.println("Please enter a user ID:");
		this.userID = userI.nextLine();
		System.out.println("Please enter a user password:");
		this.passWord = userI.nextLine();
		
		//Customer instCust = new Customer(cName, cUserID, cPassWord);
		//return instCust;
	}
	
	
	//public float 
	public boolean validatePassword(String pWord) {
		if (pWord.equals(this.passWord)) {
			return true;
		}else {
			return false;
		}
		
	}
	public void customerOptions(Scanner currScan) {
		
		System.out.print("Input 'Check' to check current accounts or 'apply' to apply for a new account");
		String cAns = currScan.nextLine().toLowerCase();
		
		if (cAns.equals("check")) {
			//customer accounts
			System.out.println("Input the account you want to check: " + accountsOwned);
			//String account 
			System.out.println("Please select what you want to do: Withdraw, deposit, or transfer?");
			String doNext = currScan.next().toLowerCase();
			
			switch(doNext) {
			case "withdraw"://Withdraw from an account
				
			break;
			case "deposit": //Deposit into an account
			break; 
			case"transfer": //transfer between account
			default: System.out.println("Invalid Input");
			break; 
			}
			
		}else if (cAns.equals("apply")) {
			//apply to open a new account
			Accounts newAccount = new Accounts();
			
		}else {
			System.out.println("Invalid Input.");
		}
	
		
		
	
		
	}

}
