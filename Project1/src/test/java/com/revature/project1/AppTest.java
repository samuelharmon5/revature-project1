package com.revature.project1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/*
 * testing for banking application
 * using the junit framework
 */
public class AppTest 
    extends TestCase
{
	UserDataBase ourBase = new UserDataBase();
	
    //@test
    public void testAccount() {
    	Accounts newA = new Accounts();
    	newA.deposit(50.00);
    	assertEquals(50.00, newA.getBalance());
    }
    
    public void testAccount2() {
    	Accounts newA = new Accounts();
    	newA.deposit(50.00);
    	newA.withdraw(50.00);
    	assertEquals(0.0, newA.getBalance());
    }
    
    public void testAccount3() {
    	Accounts newA = new Accounts(50);
    	newA.getBalance();
    	assertEquals(50.00, newA.getBalance());
    }
    public void testAccount4() {
    	
    	Accounts newA = new Accounts();
    	newA.setCustomersWithAccess("samuel");
    	
    	Customer samuel = new Customer();
    	samuel.setName("samuel");
    	
    	assertEquals(newA.customersWithAccess.get(0), samuel.getName());
    	
    }
   // @test
    public void testAccount5() {
    	
    	Accounts newA = new Accounts();
    	newA.switchApproval();
    	assertEquals(true, newA.isAccountApproved());
    }
    public void testAccount6() {
    	
    	Accounts newA = new Accounts(356.00);
    	double thisNumb = newA.getBalance();
    	assertEquals(356.00, thisNumb);
    }
    public void testAccount7() {
    	
    	
    	
    	
    }
    public void testAdmin() {
    	
    	Admin newA = new Admin();
    	newA.setAdminName("mother");
    	assertEquals("mother", newA.getAdminName());
    	
    }
  public void testCustomer() {
    	
    	Customer newC = new Customer();
    	newC.setName("mother");
    	assertEquals("mother", newC.getName());
    	
    }
  public void testCustomer2() {
  	
  	Customer newC = new Customer();
  	newC.setName("mother");
  	assertEquals("mother", newC.getName());
  	
  }
    
    }

    
