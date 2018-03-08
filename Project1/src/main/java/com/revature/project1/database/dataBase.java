package com.revature.project1.database;

import java.util.HashMap;
import java.util.Map;

import com.revature.project1.Accounts;
import com.revature.project1.Admin;
import com.revature.project1.Customer;
import com.revature.project1.Employee;
import com.revature.project1.util.LoggingUtil;

import java.io.Serializable;
import java.io.Serializable.*;

public class dataBase implements Serializable {
	
	Map<String, Admin> adminUser = new HashMap<String, Admin>();
	Map<String, Customer> bankUser = new HashMap<String, Customer>();
	Map<String, Employee> employeeUser = new HashMap<String, Employee>();
	Map<Integer, Accounts> accountsActive = new HashMap<Integer, Accounts>();
	
	private static final long serialVersionUID = 123456L;
	
	public dataBase() {
		
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
		
		employeeUser.put(nEmp.getEmployeeName(), nEmp);
	}
	public Employee getEmployee(String empNam) {
		
		return (Employee) employeeUser.get(empNam);
	}
	public void setAdmin(Admin nAdm) {
		
		adminUser.put(nAdm.getAdminName(), nAdm);
		
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
		accountsActive.remove(accountNum);
		/*
		 * Delete Customer's access to these accounts
		 */
		accountsActive.remove(accountNum);
		LoggingUtil.logInfo("Account has been deleted: " + accountNum);
		
	}

}
