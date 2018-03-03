package com.revature.project1;

public class Customer implements User{
	
	public String name; 
	public String userID;
	public String passWord;
	
	public Customer() {
		
	}
	
	public Customer(String uID, String uPassW) {
		this.userID = uID;
		this.passWord = uPassW;
	}
	public Customer(String cName, String uID, String uPassW) {
		this.userID = uID;
		this.passWord = uPassW;
		this.name = cName;
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
