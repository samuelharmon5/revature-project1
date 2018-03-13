package com.revature.dao;

import com.revature.project1.Accounts;
import com.revature.project1.Admin;
import com.revature.project1.Customer;
import com.revature.project1.Employee;
import com.revature.project1.UserDataBase;
import com.revature.project1.util.ConnectionFactory;
import com.revature.project1.util.LoggingUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.List;

public class DataBaseDaoImpl implements DataBaseDao {

	public static void displaySql() {

        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            System.out.println("Connected");
            String sql = "SELECT  * FROM BEAR";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
           
            //System.out.println(rs.next());
            
            while (rs.next()) {
            	 System.out.println(rs.getString("BEAR_NAME"));
               // System.out.println(rs.getRow());
                //System.out.println(rs.getString(0));
                //System.out.println("here");

            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

	@Override
	public int createCustomer(UserDataBase ourBase, String userID) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO CUSTOMER (NAME, PASSWORD, USERID) " 
		+ " VALUES (?,?,?)"; 
		
		try{
			//Customer instCust = ourBase.getCustomer(userID);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ourBase.getCustomer(userID).getName());
			ps.setString(2, ourBase.getCustomer(userID).getPassWord());
			ps.setString(3, ourBase.getCustomer(userID).getUserID());
			return ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			LoggingUtil.logInfo("DataBase error ");
			return 0;
		}
		
	}

	@Override
	public Customer readCustomer(UserDataBase ourBase, String userID) {
		// TODO Auto-generated method stub
			Customer read_Cust = new Customer();
		    Connection conn = ConnectionFactory.getInstance().getConnection();
		    String sql = "SELECT * FROM CUSTOMER WHERE USERID = ?";
		    try {
		      PreparedStatement ps = conn.prepareStatement(sql);
		      ps.setString(1, userID);
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()) {
		        read_Cust.setName(rs.getString("NAME"));
		        read_Cust.setUserID(rs.getString("USERID"));
		        read_Cust.setPassWord(rs.getString("PASSWORD"));
		      } else {
		    	  LoggingUtil.logInfo("DataBase error: No user with that name ");
		    	  //System.out.println("No Customer with that name");
		      }
		    } catch (SQLException e) {
		    	 LoggingUtil.logInfo("DataBase error");
		    }
		    
		    return read_Cust;
		  }

	@Override
	public int updateCustomer(UserDataBase ourBase, String userID) {
		// TODO Auto-generated method stub
		   Connection conn = ConnectionFactory.getInstance().getConnection();
		   String sql = "UPDATE CUSTOMER"
		        + " SET USERID = ?, NAME= ?, PASSWORD = ?"
		        + " WHERE USERID = ?";
		    try {
		     PreparedStatement ps = conn.prepareStatement(sql);
		      ps.setString(1, ourBase.getCustomer(userID).getUserID());
		      ps.setString(2, ourBase.getCustomer(userID).getName());
		      ps.setString(3, ourBase.getCustomer(userID).getPassWord());
		      ps.setString(4, userID);
		      return ps.executeUpdate();
		    } catch (SQLException e) {
		      LoggingUtil.logInfo("DataBase error");
		      return 0;
		    }
		  }

	@Override
	public void deleteCustomer(UserDataBase ourBase, String userID) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
	    String sql = "DELETE FROM Customer WHERE USERID = ?";
	    try {
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setString(1, userID);
	      ps.executeUpdate();
	    } catch (SQLException e) {
	    	LoggingUtil.logInfo("DataBase error");
	    }
	  }

	@Override
	public int createEmployee(UserDataBase ourBase, String employeeName) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO EMPLOYEE (NAME, PASSWORD) " 
		+ " VALUES (?,?)"; 
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ourBase.getEmployee(employeeName).getEmployeeName());
			ps.setString(2, ourBase.getEmployee(employeeName).getEmployeePassWord());
			
			return ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			LoggingUtil.logInfo("DataBase error ");
			return 0;
		}
		
	}

	@Override
	public Employee readEmployee(UserDataBase ourBase, String employeeName) {
		// TODO Auto-generated method stub
		Employee read_Emp = new Employee();
	    Connection conn = ConnectionFactory.getInstance().getConnection();
	    String sql = "SELECT * FROM EMPLOYEE WHERE NAME = ?";
	    try {
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setString(1, employeeName);
	      ResultSet rs = ps.executeQuery();
	      if (rs.next()) {
	        read_Emp.setEmployeeName(rs.getString("NAME"));
	        read_Emp.setEmployeePassWord(rs.getString("PASSWORD"));
	      } else {
	    	  LoggingUtil.logInfo("DataBase error: No user with that name ");
	    	  System.out.println("No Customer with that name");
	      }
	    } catch (SQLException e) {
	    	 LoggingUtil.logInfo("DataBase error");
	    }
	    
	    return read_Emp;
	  }

	@Override
	public int updateEmployee(UserDataBase ourBase, String employeeName) {
		// TODO Auto-generated method stub
		 Connection conn = ConnectionFactory.getInstance().getConnection();
		   String sql = "UPDATE EMPLOYEE"
		        + " NAME= ?, PASSWORD = ?"
		        + " WHERE NAME = ?";
		    try {
		      PreparedStatement ps = conn.prepareStatement(sql);
		      ps.setString(1, ourBase.getEmployee(employeeName).getEmployeeName());
		      ps.setString(2, ourBase.getEmployee(employeeName).getEmployeePassWord());
		      ps.setString(3, employeeName);
		      return ps.executeUpdate();
		    } catch (SQLException e) {
		      LoggingUtil.logInfo("DataBase error");
		      return 0;
		    }
		  }
	
	@Override
	public void deleteEmployee(UserDataBase ourBase, String employeeName) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
	    String sql = "DELETE FROM EMPLOYEE WHERE NAME = ?";
	    try {
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setString(1, employeeName);
	      ps.executeUpdate();
	    } catch (SQLException e) {
	    	LoggingUtil.logInfo("DataBase error");
	    }
	  }

	@Override
	public int createAdmin(UserDataBase ourBase, String adminName) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO ADMIN (NAME, PASSWORD) " 
		+ " VALUES (?,?)"; 
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			
			System.out.println(ourBase.getAdmin(adminName).getAdminPassWord());
			
			ps.setString(1, ourBase.getAdmin(adminName).getAdminName());
			ps.setString(2, ourBase.getAdmin(adminName).getAdminPassWord());
			
			return ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			LoggingUtil.logInfo("DataBase error ");
			return 0;
		}
		
		
		
	}

	@Override
	public Admin readAdmin(UserDataBase ourBase, String adminName) {
		// TODO Auto-generated method stub
		Admin read_Adm = new Admin();
	    Connection conn = ConnectionFactory.getInstance().getConnection();
	    String sql = "SELECT * FROM ADMIN WHERE NAME = ?";
	    try {
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setString(1, adminName);
	      ResultSet rs = ps.executeQuery();
	      if (rs.next()) {
	        read_Adm.setAdminName(rs.getString("NAME"));
	        read_Adm.setAdminPassWord(rs.getString("PASSWORD"));
	      } else {
	    	  LoggingUtil.logInfo("DataBase error: No user with that name ");
	    	  //System.out.println("No Customer with that name");
	      }
	    } catch (SQLException e) {
	    	 LoggingUtil.logInfo("DataBase error");
	    }
	    
	    return read_Adm;
	  }

	@Override
	public int updateAdmin(UserDataBase ourBase, String adminName) {
		// TODO Auto-generated method stub
		 Connection conn = ConnectionFactory.getInstance().getConnection();
		   String sql = "UPDATE ADMIN"
		        + " NAME= ?, PASSWORD = ?"
		        + " WHERE NAME = ?";
		    try {
		      PreparedStatement ps = conn.prepareStatement(sql);
		      ps.setString(1, ourBase.getAdmin(adminName).getAdminName());
		      ps.setString(2, ourBase.getAdmin(adminName).getAdminPassWord());
		      ps.setString(3, adminName);
		      return ps.executeUpdate();
		    } catch (SQLException e) {
		      LoggingUtil.logInfo("DataBase error");
		      return 0;
		    }
		  }
	@Override
	public void deleteAdmin(UserDataBase ourBase, String adminName) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
	    String sql = "DELETE FROM ADMIN WHERE NAME = ?";
	    try {
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setString(1, adminName);
	      ps.executeUpdate();
	    } catch (SQLException e) {
	    	LoggingUtil.logInfo("DataBase error");
	    }
	  }

	@Override
	public int createAccounts(UserDataBase ourBase, int accountsNumberSerial) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO ACCOUNTS (ACCOUNT_NUMBER, APPROVED, BALANCE) " 
		+ " VALUES (?,?,?)"; 
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, ourBase.getAccounts(accountsNumberSerial).getAccountNumber());
			ps.setString(2, "n");
			ps.setDouble(3, ourBase.getAccounts(accountsNumberSerial).getBalance());
			
			return ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			LoggingUtil.logInfo("DataBase error: Account not created");
			return 0;
		}
	}

	@Override
	public Accounts readAccounts(UserDataBase ourBase, int accountsNumberSerial) {
		// TODO Auto-generated method stub

		Accounts read_Acc = new Accounts();
	    Connection conn = ConnectionFactory.getInstance().getConnection();
	    String sql = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_NUMBER = ?";
	    try {
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setInt(1, accountsNumberSerial);
	      ResultSet rs = ps.executeQuery();
	      if (rs.next()) {
	        read_Acc.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
	        read_Acc.setApproval(rs.getString("APPROVED"));
	        read_Acc.setBalance(rs.getInt("BALANCE"));
	      } else {
	    	  LoggingUtil.logInfo("DataBase error: No Account with this number ");
	    	  System.out.println("No Customer with that name");
	      }
	    } catch (SQLException e) {
	    	 LoggingUtil.logInfo("DataBase error");
	    }
	    
	    return read_Acc;
	  }
	@Override
	public int updateAccounts(UserDataBase ourBase, int accountsNumberSerial) {
		// TODO Auto-generated method stub
		 Connection conn = ConnectionFactory.getInstance().getConnection();
		   String sql = "UPDATE ACCOUNTS"
		        + " ACCOUNT_NUMBER= ?, APPROVED = ?, BALANCE= ?"
		        + " WHERE ACCOUNT_NUMBER = ?";
		    try {
		      PreparedStatement ps = conn.prepareStatement(sql);
		      ps.setDouble(1, ourBase.getAccounts(accountsNumberSerial).getAccountNumber());
		      if (ourBase.getAccounts(accountsNumberSerial).isAccountApproved() == false) {
		    	  ps.setString(2, "n");
		      }else {
		    	  ps.setString(2, "y");
		      }
		      ps.setDouble(3, ourBase.getAccounts(accountsNumberSerial).getBalance());
		      ps.setDouble(4, accountsNumberSerial);
		      
		      return ps.executeUpdate();
		    } catch (SQLException e) {
		      LoggingUtil.logInfo("DataBase error");
		      return 0;
		    }
		  }
		
	@Override
	public void deleteAccounts(UserDataBase ourBase, int accountsNumberSerial) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getInstance().getConnection();
	    String sql = "DELETE FROM ACCOUNTS WHERE ACCOUNT_NUMBER = ?";
	    try {
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setDouble(1, accountsNumberSerial);
	      ps.executeUpdate();
	    } catch (SQLException e) {
	    	LoggingUtil.logInfo("DataBase error");
	    }
	  }
	}
