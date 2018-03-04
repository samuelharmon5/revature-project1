package com.revature.project1;

import java.io.Serializable;
import java.io.Serializable.*;

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
	
	
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassWord() {
		// TODO Auto-generated method stub
		return null;
	}

}
