package com.revature.project1;

import java.io.FileNotFoundException;
import java.util.*;

public class Driver {

	static UserDataBase newDataBase = new UserDataBase(); 
	static Serializer newSerializer = new Serializer();
	
	public void doWork() {
		// TODO Auto-generated method stuff
	
		Customer instCust;
		Employee instEmp;
		Admin instAdmin;
		
		Scanner userInp = new Scanner(System.in);
		
		newDataBase = newSerializer.writeIn();
		if (newDataBase == null) {
			newDataBase = new UserDataBase();
		}
			//newSerializer.writeOut(newDataBase);	
		System.out.println("Welcome to the HarmonBank Application!");
		System.out.println("Please input if you are a Customer, Employee or Admin.");
		String userType = userInp.nextLine().toLowerCase();
		 
		switch (userType) {
		
		case "customer":
			System.out.println("Welcome, are you currently one of our customers: Y or N");
			String ans = userInp.nextLine().toLowerCase();
			
			if(ans.equals("y")) {
				//send to current Customer
				System.out.println("Please Enter your User ID:");
				String currID = userInp.nextLine();
				
				instCust = ((Customer)newDataBase.getCustomer(currID));
				System.out.println(instCust.getName() + " " + instCust.getUserID() +" ");
				System.out.println("Please Enter your password:");
				String currPassword = userInp.nextLine();
				boolean chckPassword = false;
				try {
				chckPassword = instCust.validatePassword(currPassword);
				}catch(NullPointerException e) {
					System.out.println("invalid password");
					e.printStackTrace();
				}
				
				if (chckPassword == true) {
					System.out.println("password approved");
					instCust.customerOptions(userInp, newDataBase, newSerializer);
					
				}else {
					System.out.println("invalid Password");
				}
				
				}else if(ans.equals("n")) {	
				
				instCust = new Customer();
				instCust.customerRegistration();
				instCust.customerOptions(userInp, newDataBase, newSerializer);
				
				newDataBase.setCustomer(instCust);
				//bankUser.put(instCust.userID, instCust);
				newSerializer.writeOut(newDataBase); 
				
				
				//currentCustomer();
				}else {
				System.out.println("invalid input, please start over");
				}
			
		
				break;
				case "employee":
					System.out.println("Valued Employee, are you currently registered in our system? Y or N");
					String ans2 = userInp.nextLine().toLowerCase();
					switch(ans2) {
					case "y":
						System.out.println("Please enter your Employee name");
						ans2 = userInp.nextLine().toLowerCase();
						instEmp = newDataBase.getEmployee(ans2); 
						instEmp.employeeOptions(userInp, newDataBase, newSerializer);
						break;
					case "n":
						instEmp = new Employee();
						instEmp.employeeRegistration();
						instEmp.employeeOptions(userInp, newDataBase, newSerializer);
						
						newDataBase.setEmployee(instEmp);
						//bankUser.put(instCust.userID, instCust);
						newSerializer.writeOut(newDataBase); 
						break;
					default: System.out.println("Invalid Input");
					break;
					
					}
			
				break;
				case "admin":
					instAdmin = new Admin();
					System.out.println("Are you currently Registered in our system? Y or N");
					String ans3 = userInp.nextLine().toLowerCase();
					switch(ans3) {
					case "y":
						System.out.println("Thank you, please enter your Admin ID");
						ans3 = userInp.nextLine().toLowerCase();
						instAdmin = newDataBase.getAdmin(ans3);
						instAdmin.adminOptions(userInp, newDataBase, newSerializer);
						
						newDataBase.setAdmin(instAdmin);
						//bankUser.put(instCust.userID, instCust);
						newSerializer.writeOut(newDataBase); 
						
						break;
					
					case "n":
	
						instAdmin.adminRegistration();
						instAdmin.adminOptions(userInp, newDataBase, newSerializer);
						
						newDataBase.setAdmin(instAdmin);
						
						newSerializer.writeOut(newDataBase); 
						
						break;
					
					
					}
				break;
				default:System.out.println("Invalid input");
				break;
		}//end Switch statement
		
	} 
	
	public static void main(String[] args) {
		
		Driver ourDriver = new Driver();
		ourDriver.doWork();
		
	}

}
