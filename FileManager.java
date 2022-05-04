package edu.cuny.csi.csc330.groupproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileManager{
	
	private void createFile(String fileName) {
		try {
		      File myObj = new File(fileName + ".txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error has occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void pushDatabase(File file) throws IOException{
        try {
        		FileOutputStream f = new FileOutputStream(new File("C:\\Users\\Jhon\\eclipse-workspace\\CSC330-SrcTree\\Tester.txt"));
    			ObjectOutputStream o = new ObjectOutputStream(f);
    			
    			// Write objects to file
    			o.writeObject(Database.get());
    			o.close();
    			f.close();
    			System.out.println("All is well!");
    			
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
        }
    }
	
	public static void loadDatabase(File file) throws IOException, ClassNotFoundException {
		 try {
	        	FileInputStream fi = new FileInputStream(new File("C:\\Users\\Jhon\\eclipse-workspace\\CSC330-SrcTree\\Tester.txt"));
				ObjectInputStream oi = new ObjectInputStream(fi);
				Database.set((Database) oi.readObject());
				System.out.println("You did it you bastard!");
				
				oi.close();
				fi.close();
				
		 } catch(FileNotFoundException e) {
	        	
	        }
	}
	
	//

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		// fileName = "C:\\Users\\Jhon\\eclipse-workspace\\CSC330-SrcTree";
		
		Host h1 = new Host("hippie", "f", "l");
		Host h2 = new Host("squish", "host", "host");
		Attendee a1 = new Attendee("a1", "firstN", "lastN");
		Attendee a2 = new Attendee("a2", "a", "b");
		Appointment day = new Appointment(a1);
		Appointment day2 = new Appointment(a2);
		
		ArrayList<Host> listTest = new ArrayList<Host>();
		listTest.add(h1);
		listTest.add(h2);
		
//		pushDatabase(null);
//		loadDatabase(null);
//		System.out.println(Database.hostsByID);
	}
}
