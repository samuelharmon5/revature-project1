package com.revature.project1;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.io.Serializable.*;

public class UserDataBase implements Serializable {
	
	static Map<String, Customer> bankUser = new HashMap<String, Customer>();
	static Map<String, Employee> employeeUser = new HashMap<String, Employee>();
	static Map<String, Admin> adminUser = new HashMap<String, Admin>();
	static Map<Integer, Accounts> accountsActive = new HashMap<Integer, Accounts>();
	
	private static final long serialVersionUID = 123456L;
	
	public UserDataBase() {
		
	}
	
	public void setCustomer(Customer nCust) {
		bankUser.put(nCust.getUserID(), nCust);
		
	}
	public Customer getCustomer(String userID) {
		
	return (Customer) bankUser.get(userID);
	}
	public Boolean hasKey(String userID) {
		
		if (bankUser.containsKey(userID) == true) {
			return true;
		}else{
			return false; 
		}
		
	}
	public void setEmployee(Employee nEmp) {
		
		employeeUser.put(nEmp.employeeName, nEmp);
	}
	public Employee getEmployee(String empNam) {
		
		return employeeUser.get(empNam);
	}
	public void setAdmin(Admin nAdm) {
		
		adminUser.put(nAdm.adminName, nAdm);
		
	}
	public Admin getAdmin(String aID) {
		
		return adminUser.get(aID);
	}
	public void setAccounts(int aNum, Accounts nAcct) {
		
		accountsActive.put(aNum, nAcct);
		
	}
	public Accounts getAccounts(int accountNum) {
		
		return accountsActive.get(accountNum);
	}
	public void deleteAccount(int accountNum) {
	
		/*
		 * Delete Customer's access to these accounts
		 */
		
		accountsActive.remove(accountNum);
		
	}

}
