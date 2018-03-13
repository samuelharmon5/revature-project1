package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.Scanner;

import com.revature.dao.DataBaseDaoImpl;
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
	DataBaseDaoImpl newDBdao = new DataBaseDaoImpl();
	
		boolean nameLoop = false;
		while (nameLoop == false) {
		System.out.println("Genie Registration Start: Please Enter your Genie name");
		String checkNameAvail = userI.next();
		//ourBase.getAdmin(checkNameAvail)
			Admin tempAdmin = newDBdao.readAdmin(ourBase, checkNameAvail);
			if (tempAdmin.getAdminName() != null) {
				System.out.println("Your Genie name is already registered");
				nameLoop = false;
			}else {
				this.adminName = checkNameAvail;
				System.out.println("Hello " + this.adminName);
				System.out.println("Please enter a Genie password:");
				this.adminPassWord = userI.next();
				LoggingUtil.logInfo("Genie Created: " + this.adminName);
				nameLoop = true;
			}
		}
	//userI.close();
	}
	
	public void adminOptions(Scanner currScan, UserDataBase ourBase, Serializer newSerializer) {

		boolean loopCheck = false;
		while (loopCheck == false){
		DataBaseDaoImpl newDBdao = new DataBaseDaoImpl();
		
		System.out.println("Please input the number of the wish you want to grant:");
		System.out.println("1) View DiamondInTheRough information");
		System.out.println("2) Approve/deny open applications");
		System.out.println("3) Withdraw, Deposit, or transfer from a treasure pile");
		System.out.println("4) cancel a treasure pile");
		System.out.println("(Deprecated) Re-Animate the Dead");
		System.out.println("5) exit ");
		
		String cAns = currScan.next().toLowerCase();
		switch(cAns) {
		case "1":
			boolean conLoop = false;
			while (conLoop == false) {
				System.out.println("Please input the DiamondID of the Customer you wish to view:");
				//cAns = currScan.nextLine().toLowerCase();
				String currUsID = currScan.next().toLowerCase();
				ourBase.setCustomer(newDBdao.readCustomer(ourBase, currUsID));
				
				if (ourBase.hasKey(currUsID) == true) {
				//return customer information
					Customer tempCurr = ourBase.getCustomer(currUsID);
					System.out.println("User Name: " + tempCurr.getName());
					System.out.println("User ID: " + tempCurr.getUserID());
					System.out.println("User Password: " + tempCurr.getPassWord());
					if (tempCurr.accountsOwned.size() > 0) {
	
						for (int i = 0;i<tempCurr.accountsOwned.size();i++) {
							System.out.println("Treasure piles Owned: " + tempCurr.accountsOwned.get(i));
							Accounts tempAccount = ourBase.getAccounts(tempCurr.accountsOwned.get(i));
							System.out.println("Treasure pile balance (US Dollars): " + tempAccount.getBalance());
							System.out.println("");
						}
						
					}
					System.out.println("Would you like to view another DiamondInTheRough's information?");
					System.out.println("1) Yes");
					System.out.println("2) No");
					cAns = currScan.next().toLowerCase();
					switch(cAns) {
					case "1": conLoop=false;
					break;
					case "2": conLoop=true;
					break;
					}
					continue;
				}
			}//end While Loop
			break;
		case "2":
			
			boolean loopChck = false;
			while(loopChck == false) {
			System.out.println("Please input the DiamondID of the DiamondInTheRough you would like to approve/deny");
			String currUsID = currScan.next();//.toLowerCase();
			
			ourBase.setCustomer(newDBdao.readCustomer(ourBase, currUsID));
			
			if (ourBase.hasKey(currUsID) == true) {
				Customer tempCurr = ourBase.getCustomer(currUsID);
				System.out.println("Would you like to approve TreasurePile: " + tempCurr.getName());// + "y or n");
				System.out.println("1) Yes");
				System.out.println("2) No");
				String currUsID2 = currScan.next().toLowerCase();
				switch(currUsID2) {
				case "1":
					System.out.println("Treasure piles for " + tempCurr.getName() + " have been approved");
					loopChck = true;
					break;
				default: break;	
				/*
				for (int i =0;i<tempCurr.accountsOwned.size();i++) {
					//Accounts tempAcc = ourBase.getAccounts(tempCurr.accountsOwned.get(i));
					Accounts tempAcc = newDBdao.readAccounts(ourBase, tempCurr.accountsOwned.get(i));
					if (tempAcc.isAccountApproved() == false) {
					System.out.println("Would you like to approve TreasurePile: " + tempCurr.accountsOwned.get(i));// + "y or n");
					System.out.println("1) Yes");
					System.out.println("2) No");
					String currUsID2 = currScan.next().toLowerCase();
					switch(currUsID2) {
					case "1": tempAcc.switchApproval();
					//Logger Utility
					LoggingUtil.logInfo("account Approved: " + tempCurr.accountsOwned.get(i));
					System.out.println("Account Approved");
					loopChck = true;
					break;
					case "2": tempCurr.accountsOwned.remove(i);
					System.out.println("Account Denied");
					loopChck = true;
					default:System.out.println("Invalid input ");
					break;
					}}else {
						System.out.println("All Accounts are approved");
					}
					*/
					//ourBase.setAccounts(tempAcc.getAccountNumber(),tempAcc);
					//newDBdao.updateAccounts(ourBase, tempAcc.getAccountNumber());
				}
					ourBase.setCustomer(tempCurr);
				}else {
				System.out.println("please input a valid Diamond ID ");
				loopChck = false;
			
			}
			//newSerializer.writeOut(ourBase);
			
			}
			break;
			
		case "3":
			System.out.println("Please input the Treasure Pile Number: ");
			String doNext = currScan.next();
			int acctNum = Integer.parseInt(doNext);
			
			Accounts currAccount;
			//currAccount = ourBase.getAccounts(acctNum);
			currAccount = newDBdao.readAccounts(ourBase, acctNum);
			
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
				System.out.println("How Much would you like to withdraw? (US Dollars)");
				doNext = currScan.next().toLowerCase();
				withDrawAmmount = Double.parseDouble(doNext);
				currAccount.withdraw(withDrawAmmount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newDBdao.updateAccounts(ourBase, currAccount.getAccountNumber());
				//newSerializer.writeOut(ourBase);
				//accountsOwned[accountNum].withdraw();
			break;
			case "2": //Deposit into an account
				double depositAmmount = 0;
				System.out.println("How Much would you like to deposit? (US Dollars)");
				doNext = currScan.next().toLowerCase();
				depositAmmount = Double.parseDouble(doNext);
				currAccount.deposit(depositAmmount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newDBdao.updateAccounts(ourBase, currAccount.getAccountNumber());
				//newSerializer.writeOut(ourBase);
				
				System.out.println("Account: " + currAccount.getAccountNumber() 
				+ " New Balance: " + currAccount.getBalance());
			break; 
			case"3": //transfer between account
				double transferAmmount = 0;
				System.out.println("How Much would you like to transfer? (US Dollars)");
				doNext = currScan.next().toLowerCase();
				transferAmmount = Double.parseDouble(doNext);
				System.out.println("What account would you like to transfer to?");
				doNext = currScan.next().toLowerCase();
				
				int transferAccount = Integer.parseInt(doNext);
				//Accounts tempAccount = ourBase.getAccounts(transferAccount);
				Accounts tempAccount = newDBdao.readAccounts(ourBase, transferAccount);
				ourBase.setAccounts(transferAccount, tempAccount);
				if (tempAccount == null) {
					System.out.println("Im sorry, this treasure pile does not exist in our system");
					break;
				}
				currAccount.transfer(transferAmmount, tempAccount);
				
				//currAccount.transfer(transferAmmount, currAccount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newDBdao.updateAccounts(ourBase, currAccount.getAccountNumber());
				newDBdao.updateAccounts(ourBase, tempAccount.getAccountNumber());
				//newSerializer.writeOut(ourBase);
			break;	
			default: System.out.println("Invalid Input");
			
			break;
			
			}
			}
			
		case "4":
			System.out.println("Please enter the treasure pile Number that you would like to cancel: ");
			doNext = currScan.next().toLowerCase();
			int deltAcct = Integer.parseInt(doNext);
			ourBase.setAccounts(deltAcct, newDBdao.readAccounts(ourBase, deltAcct));
			
			if (ourBase.hasKey(doNext) != null) {
			ourBase.deleteAccount(deltAcct);
			System.out.println("Treasure Pile " + deltAcct + " Removed from the CaveOfWonder...");
			//newSerializer.writeOut(ourBase);
			newDBdao.deleteAccounts(ourBase, deltAcct);
			}else {
				System.out.println("Error: Account not found.");
			}
			break;
		
		case "5":
			break;
		}//end Switch Statement
		System.out.println("");
		System.out.println("Would you like to grant any more wishes?");// Y or N");
		System.out.println("1) Yes");
		System.out.println("2) No");
		
		String chkForTran = currScan.next().toLowerCase();
		if (chkForTran.equals("1")) {
			loopCheck = false; 
		}
		else if(chkForTran.equals("2")) {
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
