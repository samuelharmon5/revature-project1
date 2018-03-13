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
		
		//System.out.println("Welcome to the HarmonBank Application!");
		//System.out.println("Please input if you are a Customer, Employee or Admin.");
		//userType = userInp.nextLine().toLowerCase();
		System.out.println("Welcome to the Cave of Wonders! Where your treasure is safe and your wishes are granted!");

System.out.println("                                              ..                              ");                      
System.out.println("                                             dP/$.                            ");                     
System.out.println("                                            $4$$%                            ");                     
System.out.println("                                           .ee$$ee.                           ");
System.out.println("                                       .eF3??????$C$r.        .d$$$$$$$$$$$e.");
System.out.println("     .zeez$$$$$be..                    JP3F$5'$5K$?K?Je$.     d$$$FCLze.CC?$$$e    ");
System.out.println("	      \"$$$$$$$$ee..         .e$$$e$CC$???$$CC3e$$$$.  $$$/$$$$$$$$$.$$$$  ");
System.out.println("	            `\"?$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$b $$\"$$$$P?CCe$$$$$F  ");
System.out.println("	                 \"?$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$b$$J?bd$$$$$$$$$F\"   ");
System.out.println("	                     \"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$d$$F\"              ");
System.out.println("	                        \"?$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\"...              ");
System.out.println("	                            \"?$$$$$$$$$$$$$$$$$$$$$$$$$F \"$$\"$$$$b             ");
System.out.println("	                                \"?$$$$$$$$$$$$$$$$$$F\"     ?$$$$$F             ");
System.out.println("	                                    \"\"????????C\"                              ");
System.out.println("                                            e$$$$$$$$$$$$.                            ");								
System.out.println("                                          .$b CC$????$$F3eF                                 ");
System.out.println("                                         4$bC/%$bdd$b@$Pd??Jbbr                         ");
System.out.println("                                         \"\"?$$$$eeee$$$$F?\"                          ");
	 
		
		//System.out.println("Welcome to the HarmonBank Application!");
		System.out.println("Please input the number for your account type:");
		System.out.println("1) Diamonds in the Rough (Customer)");
		System.out.println("2) Sultan (Employee)");
		System.out.println("3) Genie (Admin)");
		userType = userInp.next().toLowerCase();
		
		
		switch (userType) {
		
		case "1":
			boolean checkLoop = false;
			while(checkLoop == false) {
			
			System.out.println("~~~~Touch Nothing but the Lamp~~~~");	
			System.out.println("");
			System.out.println("Have entered our layer before?");
			System.out.println("1) yes");
			System.out.println("2) no");
			
			String ans = userInp.next().toLowerCase();
			
			if(ans.equals("1")) {
				//send to current Customer
				//boolean inputLoop = false;
				//while (inputLoop == false) {
				System.out.println("Please Enter your Diamond ID:");
				String currID = userInp.next();
				
				//instCust = ((Customer)newDataBase.getCustomer(currID));
				instCust = newDBdao.readCustomer(newDataBase, currID);
				
				if (instCust.getName() == null) {
					System.out.println("I'm Sorry, this DiamondInTheRough does not appear to be in our system, please start over" );
					checkLoop = false;
				    continue;
				}
				
				System.out.println("Hello, " + " " + instCust.getName());// + " UserID: " + instCust.getUserID() +" ");
				System.out.println("Please Enter your password:");
				String currPassword = userInp.next();
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
					System.out.println("would you like to make another DiamondInTheRough transation? y or n");
					System.out.println("1)yes");
					System.out.println("2)No");
					String askTemp = userInp.nextLine().toLowerCase();
					if(askTemp.equals("1")) {
						checkLoop = false;
						continue;
					}else if(askTemp.equals("2")){
						//System.out.println("LINE 81");
						checkLoop = true;
						break;
					}else {
						System.out.println("Invalid input, please start over");
						checkLoop = false;
						break;
					}
					
				}else {
					System.out.println("Invalid PassWord");
				}
				//}//end while loop
				}else if(ans.equals("2")) {	
				
				instCust = new Customer();
				instCust.customerRegistration(newDataBase);
				
				newDataBase.setCustomer(instCust);
				newDBdao.createCustomer(newDataBase, instCust.getUserID());
				
				System.out.println("Thank you for registering with the ~CaveOfWonders~ " + instCust.getName() + "!");
				instCust.customerOptions(userInp, newDataBase, newSerializer);
				
				newDataBase.setCustomer(instCust);
				newDBdao.updateCustomer(newDataBase, instCust.getUserID());
				//bankUser.put(instCust.userID, instCust);
				//newSerializer.writeOut(newDataBase); 
				
				System.out.println("Would you like to make make another DianmondInTheRough transaction?");// Y or N");
				System.out.println("1) Yes");
				System.out.println("2) No");
				String currID2 = userInp.next().toLowerCase();
				
				if (currID2.equals("1")) {
					checkLoop = false;
					continue;
				}else if (currID2.equals("2")) {
					//System.out.println("n selected");
					checkLoop = true;
					continue;
				}else {
					System.out.println("Invalid Input");
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
				
				case "2":
					//loop until 
					boolean cLoop = false;
					while(cLoop == false) {
						
					System.out.println("Royalty is Among Us, are you currently registered in our system?");
					System.out.println("1) Yes");
					System.out.println("2) No");
					String ans2 = userInp.next().toLowerCase();
					switch(ans2) {
					case "1":
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
					case "2":
						boolean cLoop2 = false;
						while(cLoop2 == false) {
							
						instEmp = new Employee();
						instEmp.employeeRegistration(newDataBase);
						System.out.println("");
						System.out.println("Thank you for registering with the ~CaveOfWonders~ " + instEmp.getEmployeeName() + "!");
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
					System.out.println("Another Royal Transaction?");// Y or N ");
					System.out.println("1) Yes");
					System.out.println("2) No");
					String ans4 = userInp.nextLine().toLowerCase();
					if (ans4.equals("1")) {
						cLoop = true;
					}else {
						cLoop = false;
					}
					
					}
					checkInput = true;
				break;
				case "3":
					boolean interfaceLoop = false;
					while(interfaceLoop == false) {
					
					instAdmin = new Admin();
					System.out.println("All Powerful Genie! have you been released from your lamp before?");
					System.out.println("1) Yes");
					System.out.println("2) No");
					String ans3 = userInp.next().toLowerCase();
					switch(ans3) {
					case "1":
						
						System.out.println("Thank you, please enter your Genie Name: ");
						String ans4 = userInp.next();
						instAdmin = newDBdao.readAdmin(newDataBase, ans4);
						//instAdmin = newDataBase.getAdmin(ans4);
						
						if (instAdmin != null) {
						System.out.println("Password: ");
						ans4 = userInp.next();
						boolean ckkPassword = instAdmin.validatePassword(ans4);
						if (ckkPassword == true) {
							
							instAdmin.adminOptions(userInp, newDataBase, newSerializer);
							newDataBase.setAdmin(instAdmin);
							newDBdao.updateAdmin(newDataBase, instAdmin.getAdminName());
							//newSerializer.writeOut(newDataBase); 
							
							}else if(ckkPassword == false){
							System.out.println("Genie does not exist :(");
							interfaceLoop = false;
							break;
							}
						}else {
							System.out.println("No Genie Registered with that name!");

						}
						interfaceLoop = true;

						break;
						
					case "2":
	
						instAdmin.adminRegistration(newDataBase);
						
						newDataBase.setAdmin(instAdmin);
						//newSerializer.writeOut(newDataBase);
						newDBdao.createAdmin(newDataBase, instAdmin.getAdminName());
						instAdmin.adminOptions(userInp, newDataBase, newSerializer);
						
						newDataBase.setAdmin(instAdmin);
						newDBdao.updateAdmin(newDataBase, instAdmin.getAdminName());
						//newSerializer.writeOut(newDataBase); 
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
		System.out.println("Shall we begin the ~CaveOfWOnders~ from the beginning?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		 String finalIteration = userInp.next().toLowerCase();
		 if (finalIteration.equals("1")) {
			 checkInput = false;
		 }else if(finalIteration.equals("2")) {
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
