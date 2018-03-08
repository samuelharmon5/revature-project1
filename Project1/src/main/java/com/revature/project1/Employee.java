package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;
import java.util.Scanner;

import com.revature.project1.util.LoggingUtil;

public class Employee implements User, Serializable {

	private String employeeName;
	private String employeePassWord;
	
	private static final long serialVersionUID = 123L;
	
	public Employee() {
		
	}
	
	public Employee(String uID, String uPassW) {
		
		this.employeeName = uID;
		this.employeePassWord = uPassW;
		
	}
	public String getEmployeeName() {
		return this.employeeName;
	}
	public void setEmployeeName(String eName) {
		this.employeeName = eName;
	}
	public String getEmployeePassWord() {
		return employeePassWord;
	}
	public void setEmployeePassWord(String ePassword) {
		this.employeePassWord = ePassword;
	}
	public void employeeRegistration(UserDataBase ourBase) {
		
		Scanner userI = new Scanner(System.in);
		boolean nameLoop = false;
		while (nameLoop == false) {
		System.out.println("             <><><><><><><><>            ");
		System.out.println("Registration Start: Please Enter your name");
		String checkNameAvail = userI.next();
		
			if (ourBase.getEmployee(checkNameAvail) != null) {
				System.out.println("You already have an account registered");
				nameLoop = false;
			}else {
				this.employeeName = checkNameAvail;
				System.out.println("Hello " + this.employeeName);
				System.out.println("Please enter a user password:");
				String emplyPass = userI.next();
				this.employeePassWord = emplyPass;
				LoggingUtil.logInfo("Employee Created: " + this.employeeName);
				nameLoop = true;
			}
			
		}//end while loop
	//userI.close();
	}
	
	public void employeeOptions(Scanner currScan, UserDataBase ourBase, Serializer newSerializer) {
		
		boolean conLoop = false;
		while (conLoop == false) {
		
		System.out.println("Please input the number for your desired action:");
		System.out.println("1) View Customer information");
		System.out.println("2) Approve/deny open applications");
		
		String cAns = currScan.nextLine().toLowerCase();
		switch(cAns) {
		case "1":
			//view customer information
			//boolean conLoop = false;
			//while (conLoop == false) {
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
					for (int i = 0;i<tempCurr.accountsOwned.size();i++) {
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
			//end while loop
			break;
		case "2":
			//approve/deny application
			boolean loopChck = false;
			while(loopChck == false) {
			System.out.println("Please input the UserID of the customer you would like to approve/deny");
			String currUsID4 = currScan.nextLine().toLowerCase();
			
			if (ourBase.hasKey(currUsID4) == true) {
				Customer tempCurr = ourBase.getCustomer(currUsID4);
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
			}
			newSerializer.writeOut(ourBase);
			break;
				}		
	}
	}
	public boolean validatePassword(String pWord) {
		if (pWord.equals(this.employeePassWord)) {
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
