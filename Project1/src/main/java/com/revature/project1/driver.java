package com.revature.project1;

import java.util.*;

public class driver {
	
	//static Map bankUser = new HashMap<String, Customer>();
	//static Map employeeUser = new HashMap<String, Customer>();
	//static Map adminUser = new HashMap<String, Customer>();

	public void doWork() {
		// TODO Auto-generated method stuff
		
		//Map bankUser = new HashMap<String, Customer>();
		Customer instCust;
		Employee instEmp;
		Admin instAdmin;
		
		UserDataBase newDataBase = new UserDataBase();
		Scanner userInp = new Scanner(System.in);
		
		System.out.println("Welcome to HarmonBank Application!");
		System.out.println("Please input if you are a Customer, Employee or Admin.");
		String userType = userInp.nextLine().toLowerCase();
		
		
		if (userType.equals("customer")) {
			
			System.out.println("Welcome, are you currently one of our customers: Y or N");
			String ans = userInp.nextLine().toLowerCase();
			
			if(ans.equals("y")) {
				//send to current Customer
				System.out.println("Please Enter your User ID:");
				String currID = userInp.nextLine();
				//Customer thisCust = (Customer) bankUser.get(currID);
				
				instCust = ((Customer)newDataBase.getCustomer(currID));
				
				System.out.println("Please Enter your password:");
				String currPassword = userInp.nextLine();
				boolean chckPassword = instCust.validatePassword(currPassword);
				if (chckPassword == true) {
					System.out.println("password approved");
					instCust.customerOptions(userInp);
					
				}else {
					System.out.println("invalid Password");
				}
				
			}else if(ans.equals("n")) {	
				
				instCust = new Customer();
				instCust.customerRegistration();
				instCust.customerOptions(userInp);
				
				newDataBase.setNewCustomer(instCust);
				//bankUser.put(instCust.userID, instCust);
				
				
				//currentCustomer();
			}else {
				System.out.println("invalid input, please start over");
			}
			
		}else if (userType.equals("employee")) {
			//System.out.println("Are you );
			
		}else if (userType.equals("admin")) {
			
		}else {
			
			System.out.println("Invalid input");
		}
		

	} 
	
	public static void main(String[] args) {
		driver ourDriver = new driver();
		ourDriver.doWork();
	}
	/*
	Customer getCustomerObj(String uID) {
		
		return UserDataBase.getCustomer(uID);
	}
	/*
	public static Customer customerRegistration() {
		//Customer currCustomer = new Customer();
		String cName;
		String cUserID;
		String cPassWord;
		
		Scanner userI = new Scanner(System.in);
		
		System.out.println("Registration Start: Please Enter your name");
		cName = userI.nextLine();
		System.out.println("Hello " + cName);
		System.out.println("Please enter a user ID:");
		cUserID = userI.nextLine();
		System.out.println("Please enter a user password:");
		cPassWord = userI.nextLine();
		
		Customer instCust = new Customer(cName, cUserID, cPassWord);
		return instCust;
	}
	*/

}
