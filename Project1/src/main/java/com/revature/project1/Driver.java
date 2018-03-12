package com.revature.project1;

import java.io.FileNotFoundException;
import java.util.*;

import com.revature.dao.DataBaseDaoImpl;

public class Driver {

	static UserDataBase newDataBase = new UserDataBase(); 
	static Serializer newSerializer = new Serializer();
	DataBaseDaoImpl newDBdao = new DataBaseDaoImpl();
	
	public void doWork() {
		// TODO Auto-generated method stuff
	
		Customer instCust;
		Employee instEmp;
		Admin instAdmin;
		
		Scanner userInp = new Scanner(System.in);
		
		//newDataBase = newSerializer.writeIn();
		//if (newDataBase == null) {
			//newDataBase = new UserDataBase();
			//System.out.println("NO DATABASE WAS LOADED");
		//}
	
		String userType;
		boolean checkInput = false;
		while (checkInput == false) {
		
		System.out.println("Welcome to the HarmonBank Application!");
		System.out.println("Please input if you are a Customer, Employee or Admin.");
		userType = userInp.nextLine().toLowerCase();
		
		
		
		switch (userType) {
		
		case "customer":
			boolean checkLoop = false;
			while(checkLoop == false) {
			
			System.out.println("Welcome, are you currently registered as one of our customers: Y or N");
			String ans = userInp.nextLine().toLowerCase();
			
			if(ans.equals("y")) {
				//send to current Customer
				//boolean inputLoop = false;
				//while (inputLoop == false) {
				System.out.println("Please Enter your User ID:");
				String currID = userInp.nextLine();
				
				instCust = ((Customer)newDataBase.getCustomer(currID));
				if (instCust == null) {
					System.out.println("I'm Sorry, this customer does not appear to be in our system, please start over" );
					checkLoop = false;
				    continue;
				}
				
				System.out.println("Hello, " + " " + instCust.getName());// + " UserID: " + instCust.getUserID() +" ");
				System.out.println("Please Enter your password:");
				String currPassword = userInp.nextLine();
				boolean chckPassword = false;
		
				try {
				chckPassword = instCust.validatePassword(currPassword);
				}catch(NullPointerException e) {
					System.out.println("invalid password");
					//e.printStackTrace();
				}
				
				if (chckPassword == true) {
					System.out.println("password approved");
					instCust.customerOptions(userInp, newDataBase, newSerializer);
					System.out.println("");
					System.out.println("would you like to make another customer transation? y or n");
					String askTemp = userInp.nextLine().toLowerCase();
					if(askTemp.equals("y")) {
						checkLoop = false;
						continue;
					}else if(askTemp.equals("n")){
						System.out.println("LINE 81");
						checkLoop = true;
						break;
					}else {
						System.out.println("Invalid input, please start over");
						checkLoop = false;
						break;
					}
					
				}else {
					System.out.println("Probably dont need this line here");
				}
				//}//end while loop
				}else if(ans.equals("n")) {	
				
				instCust = new Customer();
				instCust.customerRegistration(newDataBase);
				System.out.println("Thank you for registering with HarmonBank " + instCust.getName() + "!");
				instCust.customerOptions(userInp, newDataBase, newSerializer);
				
				newDataBase.setCustomer(instCust);
				//bankUser.put(instCust.userID, instCust);
				newSerializer.writeOut(newDataBase); 
				
				System.out.println("Would you like to make make another transaction? Y or N");
				String currID2 = userInp.nextLine().toLowerCase();
				if (currID2.equals("y")) {
					checkLoop = false;
					continue;
				}else if (currID2.equals("n")) {
					System.out.println("n selected");
					checkLoop = true;
					continue;
				}else {
					System.out.println("Invalid Input: please start over");
					checkLoop = false;
					continue;
				}
				
				//checkLoop = true;
				
				//currentCustomer();
				}else {
				System.out.println("invalid input, please start over");
				}
			}//end while
			checkInput = true;
				break;
				
				case "employee":
					//loop until 
					boolean cLoop = false;
					while(cLoop == false) {
						
					System.out.println("Valued Employee, are you currently registered in our system? Y or N");
					String ans2 = userInp.nextLine().toLowerCase();
					switch(ans2) {
					case "y":
						System.out.println("Please enter your Employee name");
						ans2 = userInp.nextLine().toLowerCase();
						
						instEmp = newDataBase.getEmployee(ans2); 
						System.out.println("PassWord: ");
						ans2 = userInp.nextLine().toLowerCase();
						System.out.println("+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
						if(instEmp != null) {
						
						boolean chckPassword = instEmp.validatePassword(ans2);
						if(chckPassword == true) {
						instEmp.employeeOptions(userInp, newDataBase, newSerializer);
						cLoop = true;
						}
						
						}else {
							System.out.println("Employee does not exist, Please start over");
							cLoop = false;
						}
				
						break;
					case "n":
						boolean cLoop2 = false;
						while(cLoop2 == false) {
							
						instEmp = new Employee();
						instEmp.employeeRegistration(newDataBase);
						System.out.println("");
						System.out.println("Thank you for registering with HarmonBank " + instEmp.getEmployeeName() + "!");
						System.out.print("");
						newDataBase.setEmployee(instEmp);
						newSerializer.writeOut(newDataBase);
						
						instEmp.employeeOptions(userInp, newDataBase, newSerializer);
						
						newDataBase.setEmployee(instEmp);
						newSerializer.writeOut(newDataBase); 
						cLoop2 = true;
						
						}//cLoop = true;
						break;
					default: System.out.println("Invalid Input");
					break;
					}
					//Check for addition transactions
					System.out.println("Another Transaction? Y or N ");
					String ans4 = userInp.nextLine().toLowerCase();
					if (ans4.equals("n")) {
						cLoop = true;
					}else {
						cLoop = false;
					}
					
					}
					checkInput = true;
				break;
				case "admin":
					boolean interfaceLoop = false;
					while(interfaceLoop == false) {
					
					instAdmin = new Admin();
					System.out.println("Are you currently Registered in our system? Y or N");
					String ans3 = userInp.nextLine().toLowerCase();
					switch(ans3) {
					case "y":
						
						System.out.println("Thank you, please enter your Admin Name: ");
						String ans4 = userInp.nextLine();
						instAdmin = newDataBase.getAdmin(ans4);
						
						if (instAdmin != null) {
						System.out.println("Password: ");
						ans4 = userInp.nextLine();
						boolean ckkPassword = instAdmin.validatePassword(ans4);
						if (ckkPassword == true) {
							
							instAdmin.adminOptions(userInp, newDataBase, newSerializer);
							newDataBase.setAdmin(instAdmin);
							newSerializer.writeOut(newDataBase); 
							
							}else if(ckkPassword == false){
							System.out.println("Admin does not exist :(");
							interfaceLoop = false;
							break;
							}
						}else {
							System.out.println("No Admin Registered with that name!");

						}
						interfaceLoop = true;

						break;
						
					case "n":
	
						instAdmin.adminRegistration(newDataBase);
						
						newDataBase.setAdmin(instAdmin);
						newSerializer.writeOut(newDataBase);
						
						instAdmin.adminOptions(userInp, newDataBase, newSerializer);
						
						newDataBase.setAdmin(instAdmin);
						
						newSerializer.writeOut(newDataBase); 
						interfaceLoop = true;
						break;
					}
					}
					checkInput = true;
					
				break;
					
				default:System.out.println("Invalid input");
				checkInput = false;
				break;
				
		}//end Switch statement
		System.out.println("");
		System.out.println("----------------------------------------------------");
		System.out.println("");
		System.out.println("Begin the HarmonBank Application from the beginning? Y or N");
		 String finalIteration = userInp.nextLine().toLowerCase();
		 if (finalIteration.equals("y")) {
			 checkInput = false;
		 }else if(finalIteration.equals("n")) {
			 checkInput = true;
		 }else {
			 System.out.println("invalid input");
			 checkInput = true;
		 }
		 
		 }//end while loop.
	} 
	
	public static void main(String[] args) {
		
		Driver ourDriver = new Driver();
		ourDriver.doWork();
		
		
		//DataBaseDaoImpl newDBdao = new DataBaseDaoImpl();
		//Customer newCustie = new Customer ("Sam", "Sam","Sam");
		//newDataBase.setCustomer(newCustie);
		
		//newDBdao.createCustomer(newDataBase, newCustie.getUserID());
		//newDBdao.deleteCustomer(newDataBase, newCustie.getUserID());
		//newDBdao.displaySql();
		
		//newSerializer = new Serializer();
		//UserDataBase testDB;//= new UserDataBase();
		//UserDataBase testDB = new UserDataBase();
		
		//UserDataBase testDB = new UserDataBase();
		
		
		//UserDataBase testDB2 = newSerializer.writeIn();
		
		//Admin bob2 = testDB2.getAdmin("bob");
		//String name = bob2.getAdminName();
		//String name2 = bob2.getAdminPassWord();
		//System.out.println(name + " passWord " + name2);
		//System.out.println(testDB.getAdmin("bob").getAdminName());
		
		
		
	}

}
