package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.Scanner;

import com.revature.project1.util.LoggingUtil;

public class Admin implements User, Serializable {
	
	private String adminName; 
	private String adminPassWord; 
	private static final long serialVersionUID = 1234L;

	public Admin () {
		
	}
	
	public Admin (String uID, String passW) {
		
		this.adminName = uID;
		this.adminPassWord = passW;  
		
	}
	public void adminRegistration(UserDataBase ourBase){
	Scanner userI = new Scanner(System.in);
	
		boolean nameLoop = false;
		while (nameLoop == false) {
		System.out.println("Registration Start: Please Enter your name");
		String checkNameAvail = userI.next();
		
			if (ourBase.getAdmin(checkNameAvail) != null) {
				System.out.println("You already have an account registered");
				nameLoop = false;
			}else {
				this.adminName = checkNameAvail;
				System.out.println("Hello " + this.adminName);
				System.out.println("Please enter a user password:");
				this.adminPassWord = userI.next();
				LoggingUtil.logInfo("Admin Created: " + this.adminName);
				nameLoop = true;
			}
		}
	//userI.close();
	}
	
	public void adminOptions(Scanner currScan, UserDataBase ourBase, Serializer newSerializer) {

		boolean loopCheck = false;
		while (loopCheck == false){
		
		System.out.println("Please input the number for your desired action:");
		System.out.println("1) View Customer information");
		System.out.println("2) Approve/deny open applications");
		System.out.println("3) Withdraw, Deposit, or transfer from an Account");
		System.out.println("4) cancel an Account");
		System.out.println("5) exit ");
		
		String cAns = currScan.next().toLowerCase();
		switch(cAns) {
		case "1":
			boolean conLoop = false;
			while (conLoop == false) {
				System.out.println("Please input the UserID of the Customer you wish to view:");
				//cAns = currScan.nextLine().toLowerCase();
				String currUsID = currScan.next().toLowerCase();
				if (ourBase.hasKey(currUsID) == true) {
				//return customer information
					Customer tempCurr = ourBase.getCustomer(currUsID);
					System.out.println("User Name: " + tempCurr.getName());
					System.out.println("User ID: " + tempCurr.getUserID());
					System.out.println("User Password: " + tempCurr.getPassWord());
					if (tempCurr.accountsOwned.size() > 0) {
	
						for (int i = 0;i<tempCurr.accountsOwned.size();i++) {
							System.out.println("Accounts Owned: " + tempCurr.accountsOwned.get(i));
							Accounts tempAccount = ourBase.getAccounts(tempCurr.accountsOwned.get(i));
							System.out.println("Account balance: " + tempAccount.getBalance());
							System.out.println("");
						}
					}
					System.out.println("Would you like to view another Customers information? Y or N ");
					cAns = currScan.next().toLowerCase();
					switch(cAns) {
					case "y": conLoop=false;
					break;
					case "n": conLoop=true;
					break;
					}
					continue;
				}
			}//end While Loop
			break;
		case "2":
			
			boolean loopChck = false;
			while(loopChck == false) {
			System.out.println("Please input the UserID of the customer you would like to approve/deny");
			String currUsID = currScan.next().toLowerCase();
			
			if (ourBase.hasKey(currUsID) == true) {
				Customer tempCurr = ourBase.getCustomer(currUsID);
				for (int i =0;i<tempCurr.accountsOwned.size();i++) {
					Accounts tempAcc = ourBase.getAccounts(tempCurr.accountsOwned.get(i));
					if (tempAcc.isAccountApproved() == false) {
					System.out.println("Would you like to approve account: " + tempCurr.accountsOwned.get(i) + "y or n");
					String currUsID2 = currScan.next().toLowerCase();
					switch(currUsID2) {
					case "y": tempAcc.switchApproval();
					//Logger Utility
					LoggingUtil.logInfo("account Approved: " + tempCurr.accountsOwned.get(i));
					System.out.println("Account Approved");
					loopChck = true;
					break;
					case "n": tempCurr.accountsOwned.remove(i);
					System.out.println("Account Denied");
					loopChck = true;
					default:System.out.println("Invalid input ");
					break;
					}}else {
						System.out.println("All Accounts are approved");
					}
					
					ourBase.setAccounts(tempAcc.getAccountNumber(),tempAcc);
					
				}
					ourBase.setCustomer(tempCurr);
				}else {
				System.out.println("please input a valid CustomerID ");
				loopChck = false;
			
			}
			newSerializer.writeOut(ourBase);
			}
			break;
			
		case "3":
			System.out.println("Please input the account Number: ");
			String doNext = currScan.next();
			int acctNum = Integer.parseInt(doNext);
			
			Accounts currAccount;
			currAccount = ourBase.getAccounts(acctNum);
			if(currAccount == null) {
				System.out.println("Account Does Not Exist");
				loopCheck = false;
				continue; 
			}else {
			
			System.out.println("Please input the number for what you would like to do:");
			System.out.println("1) Withdraw ");//, deposit, or transfer?
			System.out.println("2) Deposit" );	
			System.out.println("3) Transfer ");
			doNext = currScan.nextLine().toLowerCase();
			
			 
			switch(doNext) {
			case "1"://Withdraw from an account
				double withDrawAmmount = 0;
				System.out.println("How Much would you like to withdraw?");
				doNext = currScan.next().toLowerCase();
				withDrawAmmount = Double.parseDouble(doNext);
				currAccount.withdraw(withDrawAmmount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
				//accountsOwned[accountNum].withdraw();
			break;
			case "2": //Deposit into an account
				double depositAmmount = 0;
				System.out.println("How Much would you like to deposit?");
				doNext = currScan.next().toLowerCase();
				depositAmmount = Double.parseDouble(doNext);
				currAccount.deposit(depositAmmount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
				
				System.out.println("Account: " + currAccount.getAccountNumber() 
				+ " New Balance: " + currAccount.getBalance());
			break; 
			case"3": //transfer between account
				double transferAmmount = 0;
				System.out.println("How Much would you like to transfer?");
				doNext = currScan.next().toLowerCase();
				transferAmmount = Double.parseDouble(doNext);
				System.out.println("What account would you like to transfer to?");
				doNext = currScan.next().toLowerCase();
				
				int transferAccount = Integer.parseInt(doNext);
				Accounts tempAccount = ourBase.getAccounts(transferAccount);
				if (tempAccount == null) {
					System.out.println("Im sorry, this account does not exist in our system");
					break;
				}
				currAccount.transfer(transferAmmount, tempAccount);
				
				currAccount.transfer(transferAmmount, currAccount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
			break;	
			default: System.out.println("Invalid Input");
			
			break;
			
			}
			}
			
		case "4":
			System.out.println("Please enter the Account Number that you would like to cancel: ");
			doNext = currScan.next().toLowerCase();
			int deltAcct = Integer.parseInt(doNext);
			
			if (ourBase.hasKey(doNext) != null) {
			ourBase.deleteAccount(deltAcct);
			System.out.println("Account " + deltAcct + " Deleted...");
			newSerializer.writeOut(ourBase);
			}else {
				System.out.println("Error: Account not found.");
			}
			break;
		
		case "5":
			break;
		}//end Switch Statement
		
		System.out.println("Would you like to make another transaction? Y or N");
		String chkForTran = currScan.nextLine().toLowerCase();
		if (chkForTran.equals("y")) {
			loopCheck = false; 
		}
		else if(chkForTran.equals("n")) {
			loopCheck = true; 
		}else {
			System.out.println("Invalid Input");
			loopCheck = false;
		}
		
		}//end while loop
		
	}//end adminOptions Method
	
	public String getAdminName() {
		
		return this.adminName;
		
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName; 
	}
	public String getAdminPassWord() {
		return this.adminPassWord;
	}
	public void setAdminPassWord(String adminPassword) {
		this.adminPassWord = adminPassword;
	}
	public boolean validatePassword(String pWord) {
		if (pWord.equals(this.adminPassWord)) {
			return true;
		}else {
			return false;
		}
		
	}
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassWord() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
