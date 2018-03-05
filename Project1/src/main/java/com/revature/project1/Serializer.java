package com.revature.project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
	
	List<UserDataBase> myList = new ArrayList<UserDataBase>();
	
	
	public void Serializer() {
		
	}

	public void writeOut(UserDataBase baseToSerialize){
		myList.clear();
		myList.add(baseToSerialize);
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userDataBase.dat"))){
			
			oos.writeObject(myList);
			System.out.println("Database Updated");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public UserDataBase writeIn() {
		
		UserDataBase loadDataBase = null;
		myList.clear();
		
         try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userDataBase.dat"))){
        	
        	myList = (List) ois.readObject();
        	loadDataBase = myList.get(0);
			System.out.println("User Database loaded");
			
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
