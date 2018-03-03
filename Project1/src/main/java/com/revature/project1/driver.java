package com.revature.project1;

import java.util.*;

public class driver {
	
	static Map bankUser = new HashMap<String, Customer>();
	static Map employeeUser = new HashMap<String, Customer>();
	static Map adminUser = new HashMap<String, Customer>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stuf
		
		//Map bankUser = new HashMap<String, Customer>();
		
		//Employee instEmp;
		//Admin instAdmin;
		
		Scanner userInp = new Scanner(System.in);
		
		System.out.println("Welcome to HarmonBank Application!");
		System.out.println("Please input if you are a Customer, Employee or Admin.");
		String userType = userInp.nextLine().toLowerCase();
		
		
		if (userType.equals("customer")) {
			
			System.out.println("Welcome, are you currently one of our customers: Y or N");
			String ans = userInp.nextLine().toLowerCase();
			
			if(ans.equals("y")) {
				//send to current Customer
				System.out.println("Please Enter your User Name");
				
				
			}else if(ans.equals("n")) {	
				
				Customer instCust = customerRegistration();
				bankUser.put(instCust.userID, instCust);
				
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
	
	public static void currentCustomer() {
		
	}

}
