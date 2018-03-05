package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.Scanner;

import com.revature.project1.util.LoggingUtil;

public class Employee implements User, Serializable {

	public String employeeName;
	public String employeePassWord;
	
	private static final long serialVersionUID = 123L;
	public Employee() {
		
	}
	
	public Employee(String uID, String uPassW) {
		
		this.employeeName = uID;
		this.employeePassWord = uPassW;
		
	}
	
	public void employeeRegistration() {
		
		Scanner userI = new Scanner(System.in);
		
		System.out.println("Registration Start: Please Enter your name");
		this.employeeName = userI.nextLine();
		System.out.println("Hello " + this.employeeName);
		System.out.println("Please enter a user password:");
		this.employeeName = userI.nextLine();
	
	}
	
	public void employeeOptions(Scanner currScan, UserDataBase ourBase, Serializer newSerializer) {
		
		System.out.println("Please input the number for your desired action:");
		System.out.println("1) View Customer information");
		System.out.println("2) Approve/deny open applications");
		
		String cAns = currScan.nextLine().toLowerCase();
		switch(cAns) {
		case "1":
			//view customer information
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
				
			}else {
				System.out.println("please input a valid CustomerID ");
				conLoop = false;
			}
			}//end while loop
			break;
		case "2":
			//approve/deny application
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
			break;
				}		
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
