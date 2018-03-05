package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.Scanner;

import com.revature.project1.util.LoggingUtil;

public class Admin implements User, Serializable {
	
	public String adminName; 
	public String adminPassWord; 
	private static final long serialVersionUID = 1234L;

	public Admin () {
		
	}
	
	public Admin (String uID, String passW) {
		
		this.adminName = uID;
		this.adminPassWord = passW;  
		
	}
	
	public void adminRegistration(){
	Scanner userI = new Scanner(System.in);
		
		System.out.println("Registration Start: Please Enter your name");
		this.adminName = userI.nextLine();
		System.out.println("Hello " + this.adminName);
		System.out.println("Please enter a password:");
		this.adminPassWord = userI.nextLine();
		LoggingUtil.logInfo("New Admin " + this.adminName);
	
	}
	
	public void adminOptions(Scanner currScan, UserDataBase ourBase, Serializer newSerializer) {
		
		System.out.println("Please input the number for your desired action:");
		System.out.println("1) View Customer information");
		System.out.println("2) Approve/deny open applications");
		System.out.println("3) Withdraw, Deposit, or transfer from an Account");
		System.out.println("4) cancel an Account");
		
		String cAns = currScan.nextLine().toLowerCase();
		switch(cAns) {
		case "1":
			boolean conLoop = false;
			while (conLoop == false) {
				System.out.println("Please input the UserID of the Customer you wish to view:");
				//cAns = currScan.nextLine().toLowerCase();
				String currUsID = currScan.nextLine().toLowerCase();
				if (ourBase.hasKey(currUsID) == true) {
				//return customer information
					Customer tempCurr = ourBase.getCustomer(currUsID);
					System.out.println("User Name: " + tempCurr.getName());
					System.out.println("User ID: " + tempCurr.getUserID());
					System.out.println("User Password: " + tempCurr.getPassWord());
					if (tempCurr.accountsOwned.size() > 0) {
						for (int i = 0;i<tempCurr.accountsOwned.get(i);i++) {
							System.out.println(tempCurr.accountsOwned.get(i));
							Accounts tempAccount = ourBase.getAccounts(tempCurr.accountsOwned.get(i));
							System.out.println("Account balance: " + tempAccount.getBalance());
						}
					}
					System.out.println("Would you like to view another Customers information? Y or N ");
					cAns = currScan.nextLine().toLowerCase();
					switch(cAns) {
					case "y": conLoop=false;
					case "n": conLoop=true;
					}
				}
			}//end While Loop
			break;
		case "2":
			
			boolean loopChck = false;
			while(loopChck == false) {
			System.out.println("Please input the UserID of the customer you would like to approve/deny");
			String currUsID = currScan.nextLine().toLowerCase();
			
			if (ourBase.hasKey(currUsID) == true) {
				Customer tempCurr = ourBase.getCustomer(currUsID);
				for (int i =0;i<tempCurr.accountsOwned.size();i++) {
					Accounts tempAcc = ourBase.getAccounts(tempCurr.accountsOwned.get(i));
					if (tempAcc.isAccountApproved() == false) {
					System.out.println("Would you like to approve account: " + tempCurr.accountsOwned.get(i) + "y or n");
					String currUsID2 = currScan.nextLine().toLowerCase();
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
			String doNext = currScan.next().toLowerCase();
			int acctNum = Integer.getInteger(doNext);
			Accounts currAccount;
			currAccount = ourBase.getAccounts(acctNum);
			
			System.out.println("Please select what you want to do: Withdraw, deposit, or transfer?");
			doNext = currScan.next().toLowerCase();

			switch(doNext) {
			case "withdraw"://Withdraw from an account
				double withDrawAmmount = 0;
				System.out.println("How Much would you like to withdraw?");
				doNext = currScan.next().toLowerCase();
				withDrawAmmount = Double.parseDouble(doNext);
				currAccount.withdraw(withDrawAmmount);
				ourBase.setAccounts(currAccount.getAccountNumber(), currAccount);
				newSerializer.writeOut(ourBase);
				//accountsOwned[accountNum].withdraw();
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
			break;	
			default: System.out.println("Invalid Input");
			
			break;
		
			}
		case "4":
			System.out.println("Please enter the Account Number that you would like to cancel: ");
			doNext = currScan.next().toLowerCase();
			int deltAcct = Integer.getInteger(doNext);
			ourBase.deleteAccount(deltAcct);
			newSerializer.writeOut(ourBase);
			break;
		
		
		}//end Switch Statement
		
		
		
		
		
	}//end adminOptions Method
	
	 
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassWord() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
