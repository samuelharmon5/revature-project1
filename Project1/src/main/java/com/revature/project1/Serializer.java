package com.revature.project1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.project1.database.dataBase;

//import com.revature.project1.database.UserDataBase;

public class Serializer {
	
	//List<UserDataBase> myList = new ArrayList<UserDataBase>();
	UserDataBase[] myList;// = new UserDataBase[1];
	
	public Serializer() {
		myList = new UserDataBase[1];
	}

	public void writeOut(UserDataBase baseToSerialize){
	
		myList[0] = baseToSerialize;
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userDataBase6.dat"))){
			
			oos.writeObject(myList);
			//System.out.println("Database Updated");
			//LoggingUtil.logInfo("Database Has Been Updated!");
			oos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public UserDataBase writeIn() {
		
		UserDataBase loadDataBase = new UserDataBase();
		
         try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userDataBase6.dat"))){
        	
        	 try {
        		 myList = (UserDataBase[]) ois.readObject();
        	 }catch(ClassCastException e){
        		 System.out.println("Error Will Robinson, Input is not expected Object");
        	 }
        	 
        	loadDataBase = myList[0];
        	//LoggingUtil.logInfo("Database Has been Loaded into the instance!");
			//System.out.println("User Database loaded");
			
         } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
        	 System.out.println("No File Found");
			//e.printStackTrace();
         } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
         }
         
         return loadDataBase; 
		
	}
}
