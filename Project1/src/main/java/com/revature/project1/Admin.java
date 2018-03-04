package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;

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
	
	
	
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassWord() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
