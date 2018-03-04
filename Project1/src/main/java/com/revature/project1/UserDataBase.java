package com.revature.project1;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.io.Serializable.*;

public class UserDataBase implements Serializable {
	
	static Map<String, Customer> bankUser = new HashMap<String, Customer>();
	static Map<String, Employee> employeeUser = new HashMap<String, Employee>();
	static Map<String, Admin> adminUser = new HashMap<String, Admin>();
	static Map<String, Accounts> accountsActive = new HashMap<String, Accounts>();
	
	private static final long serialVersionUID = 123456L;
	
	public UserDataBase() {
		
	}
	
	public void setNewCustomer(Customer nCust) {
		bankUser.put(nCust.userID, nCust);
		
	}
	public Customer getCustomer(String userID) {
		
	return (Customer) bankUser.get(userID);
	}
	public void setNewEmployee(Employee nEmp) {
		
		
	}
	public Employee getEmployee() {
		
		return null;
	}
	public void setAdmin(Admin nAdm) {
		
	}
	public Admin getAdmin() {
		
		return null;
	}
	public void setAccounts(Accounts nAcct) {
		
	}
	public Accounts getAcounts() {
		
		return null;
	}

}
