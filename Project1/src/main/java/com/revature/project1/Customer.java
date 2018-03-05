package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer implements User, Serializable{
	
	private String name; 
	private String userID = "0";
	private String passWord;
	public List<Integer> accountsOwned = new ArrayList<Integer>();
	
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
	public String getName() {
		return this.name;
	}
	public void setName(String usName) {
		this.name = usName;
	}
	public String getUserID() {
		return this.userID;
	}
	public void setUserID(String usID) {
		this.userID = usID;
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
	}
	
	//public float 
	public boolean validatePassword(String pWord) {
		if (pWord.equals(this.passWord)) {
			return true;
		}else {
			return false;
		}
		
	}

	public void customerOptions(Scanner currScan, UserDataBase ourBase, Serializer newSerializer) {
		
		System.out.println("Input 'Check' to check current accounts or 'apply' to apply for a new account");
		String cAns = currScan.nextLine().toLowerCase();
		
		if (cAns.equals("check")) {
			//customer accounts
			System.out.println("Input the account you want to check: " + accountsOwned.toString());
			String accountNum = currScan.nextLine();
			int currVal = Integer.valueOf(accountNum);
			
			Accounts currAccount = ((UserDataBase) ourBase).getAccounts(currVal);
			
			if (currAccount == null) {
				System.out.println("I'm sorry that account does not exit");
			}
			
			System.out.println("Please select what you want to do: Withdraw, deposit, or transfer?");
			
			//TODO: check to make sure account has been approved
			
			//while(currScan.hasNext()) {
			String doNext = currScan.next().toLowerCase();
			
			switch(doNext) {
			case "withdraw"://Withdraw from an account
				double withDrawAmmount = 0;
				System.out.println("How Much would you like to withdraw?");
				doNext = currScan.next().toLowerCase();
				withDrawAmmount = Double.parseDouble(doNext);
				currAccount.withdraw(withDrawAmmount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
			break;
			case "deposit": //Deposit into an account
				double depositAmmount = 0;
				System.out.println("How Much would you like to deposit?");
				doNext = currScan.next().toLowerCase();
				depositAmmount = Double.parseDouble(doNext);
				currAccount.deposit(depositAmmount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
			break; 
			case"transfer": //transfer between account
				double transferAmmount = 0;
				System.out.println("How Much would you like to transfer?");
				doNext = currScan.next().toLowerCase();
				transferAmmount = Double.parseDouble(doNext);
				currAccount.transfer(transferAmmount, currAccount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
				
			default: System.out.println("Invalid Input");
			break; 
			}
			
			newSerializer.writeOut(ourBase);
			
		}else if (cAns.equals("apply")) {
			//apply to open a new account
			Accounts newAccount = new Accounts();
			System.out.println("Accounts Number: " + newAccount.getAccountNumber());
			accountsOwned.add(newAccount.getAccountNumber());
			ourBase.setAccounts(newAccount.getAccountNumber(), newAccount);
			System.out.println("Thank you for applying. Your account: " + newAccount.getAccountNumber() 
			   + " is waiting to be approved");
		}else {
			System.out.println("Invalid Input.");
		}
	
		newSerializer.writeOut(ourBase);
		
	}

}
