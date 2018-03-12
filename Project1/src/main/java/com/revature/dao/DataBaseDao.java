package com.revature.dao;

import java.util.List;

import com.revature.project1.Accounts;
import com.revature.project1.Admin;
import com.revature.project1.Customer;
import com.revature.project1.Employee;
import com.revature.project1.UserDataBase;

public interface DataBaseDao {
	
	/*
public void createFlashCard(FlashCard flashCard);
	
	public FlashCard retrieveFlashCardById(int id);
	
	public List<FlashCard> retrieveAllFlashCards();
	
	public void updateFlashCard(FlashCard flashCard);
	
	public void deleteFlashCard(int id);
	
	public void createFlashCardPreparedStmt(FlashCard flashCard);
*/
	public int createCustomer(UserDataBase ourBase, String userID);
	
	public Customer readCustomer(UserDataBase ourBase, String userID);
	
	public int updateCustomer (UserDataBase ourBase, String userID);
	
	public void deleteCustomer (UserDataBase ourBase, String userID);
	
	public int createEmployee(UserDataBase ourBase, String employeeName);
	
	public Employee readEmployee(UserDataBase ourBase, String employeeName);
	
	public int updateEmployee (UserDataBase ourBase, String employeeName);
	
	public void deleteEmployee (UserDataBase ourBase, String employeeName);
	
	public int createAdmin(UserDataBase ourBase, String adminName);
	
	public Admin readAdmin(UserDataBase ourBase, String adminName);
	
	public int updateAdmin (UserDataBase ourBase, String adminName);
	
	public void deleteAdmin (UserDataBase ourBase, String adminName);
	
	public int createAccounts(UserDataBase ourBase,  int accountsNumberSerial);
	
	public Accounts readAccounts(UserDataBase ourBase,  int accountsNumberSerial);
	
	public int updateAccounts (UserDataBase ourBase,  int accountsNumberSerial);
	
	public void deleteAccounts (UserDataBase ourBase,  int accountsNumberSerial);
	

	
	//public void createDataEntry(UserDataBase ourBase);
	
	//public List<UserDataBase> retieveDataBase();
	
	//public void updateDataBase(UserDataBase thisBase);
	
	//public void deleteUserDataBase();
	
	//public void createDataBase(UserDataBase ourbase);
	
	
	
}
