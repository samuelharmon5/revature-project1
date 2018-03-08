package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.project1.util.LoggingUtil;

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
		return this.passWord;
	}
	
	public void customerRegistration(UserDataBase ourBase) {
		
		Scanner userI = new Scanner(System.in);
		
		System.out.println("Registration Start: Please Enter your name");

		this.name = userI.nextLine();
		System.out.println("Hello! " + this.name);
		
		boolean nameLoop = false;
		while(nameLoop == false) {
		System.out.println("Please enter a new user ID:");
		String checkNameAvail = userI.nextLine();
		
		if (ourBase.getCustomer(checkNameAvail) != null) {
			System.out.println("Sorry that User ID is already taken, Please try another.");
			nameLoop = false;
		}else {
		this.userID = checkNameAvail;
		nameLoop = true;
		}//end while loop to set User ID
		}
		
		System.out.println("Please enter a user password:");
		this.passWord = userI.nextLine();
		
		LoggingUtil.logInfo("Customer Created: " + this.name);
		
		//userI.close();
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
			if (accountsOwned.size() > 0 ) {
				
			System.out.print("Input the account you want to check: " + " ");
			for (int i = 0;i<accountsOwned.size();i++) {
			 System.out.print(accountsOwned.get(i).toString() + " ");
			}
			
			String accountNum = currScan.nextLine();
			int currVal = Integer.valueOf(accountNum);
			
			Accounts currAccount = ((UserDataBase) ourBase).getAccounts(currVal);
			
			boolean custLoop = false;
			
			if (currAccount == null) {
				System.out.println("I'm sorry that account does not exit");
				custLoop = true;
			}else if(currAccount.isAccountApproved() == false) {
				System.out.println("Im sorry, this account has not been approved yet");
				custLoop = true;
			}
			System.out.println(" -<>-<>-<>-<>--<>-<>-<>-<>-<>-");
			System.out.println("Current Balance in your account: " + currAccount.getBalance());
			System.out.println("");
			
			while (custLoop == false) {
			System.out.println("Please input the number of the transaction you would like to complete:");
			
			System.out.println("1) Withdraw");//Withdraw, deposit, or transfer?");
			System.out.println("2) deposit");
			System.out.println("3) transfer");
			System.out.println("4) Exit");
		
			String doNext = currScan.next().toLowerCase();
			
			switch(doNext) {
			case "1"://Withdraw from an account
				double withDrawAmmount = 0;
				System.out.println("How Much would you like to withdraw?");
				doNext = currScan.next().toLowerCase();
				withDrawAmmount = Double.parseDouble(doNext);
				currAccount.withdraw(withDrawAmmount);
				System.out.println("New Account Balance: " + currAccount.getBalance());
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
				
				System.out.println("Would you like to make another transaction? Y or N");
				String chkchk = currScan.next().toLowerCase();
				if(chkchk == "y") {
					custLoop = false;
				}else if (chkchk == "n") {
					custLoop = true;
				}else {
					custLoop = false;
				}
				
			break;
			case "2": //Deposit into an account
				double depositAmmount = 0;
				System.out.println("How Much would you like to deposit?");
				doNext = currScan.next().toLowerCase();
				depositAmmount = Double.parseDouble(doNext);
				currAccount.deposit(depositAmmount);
				System.out.println("Account Balance: " + currAccount.getBalance());
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
				
				System.out.println("Would you like to make another transaction? Y or N");
				String chkchk2 = currScan.next().toLowerCase();
				if(chkchk2.equals("y")) {
					custLoop = false;
				}else if (chkchk2.equals("n")) {
					custLoop = true;
				}else {
					custLoop = false;
				}
				
			break; 
			case"3": //transfer between account
				double transferAmmount = 0;
				System.out.println("How Much would you like to transfer?");
				doNext = currScan.next().toLowerCase();
				transferAmmount = Double.parseDouble(doNext);
				System.out.println("Please input the account you would like to transfer to:");
				doNext = currScan.next().toLowerCase();
				int transferAccount = Integer.parseInt(doNext);
				Accounts tempAccount = ourBase.getAccounts(transferAccount);
				if (tempAccount == null) {
					System.out.println("Im sorry, this account does not exist in our system");
					break;
				}
				currAccount.transfer(transferAmmount, tempAccount);
				
				System.out.println("Balance left in your account: " + currAccount.getBalance());
				System.out.println("Balance in account: " + tempAccount.getBalance());
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				//newSerializer.writeOut(ourBase);
				
				System.out.println("Would you like to make another transaction? Y or N");
				String chkchk3 = currScan.next().toLowerCase();
				if(chkchk3.equals("y")) {
					custLoop = false;
				}else if (chkchk3.equals("n")) {
					custLoop = true;
				}else {
					custLoop = false;
				}
				
			case "4":
				custLoop = true;
				break;
			default: System.out.println("Invalid Input");
			custLoop = false;
			break; 
			}
			
			newSerializer.writeOut(ourBase);
			}//end custLoop
			}else {
				System.out.println("I'm sorry, you do not have any accounts at the moment");
			}
			
		}else if (cAns.equals("apply")) {
			//apply to open a new account
			Accounts newAccount = new Accounts();
			System.out.println("New Account Number: " + newAccount.getAccountNumber());
			accountsOwned.add(newAccount.getAccountNumber());
			ourBase.setAccounts(newAccount.getAccountNumber(), newAccount);
			System.out.println("Thank you for applying. Your account: " + newAccount.getAccountNumber() 
			   + " is waiting to be approved");
			
			//newSerializer.writeOut(ourBase);
			
		}else {
			System.out.println("Invalid Input.");
		}
	
		//newSerializer.writeOut(ourBase);
		
	}

}
