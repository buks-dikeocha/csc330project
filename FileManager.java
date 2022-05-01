/*
 * project must have the following files to hold user and host info:
 * eventsByHostID.txt
 * hostsByID.txt
 * availabilityByHostID.txt
 * 
 * files above are specifically holds info for the host(s)
 */

package edu.cuny.csi.csc330.groupproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
	ArrayList<String> host = new ArrayList<String>();
	ArrayList<String> attendee = new ArrayList<String>();
	
	//private boolean fileFound;
	
	public FileManager() {
	}
	
	private void createFile(String fileName) {
		try {
		      File myObj = new File(fileName + ".txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private void storeFiles(Map<String, Host> hostsByID, 
			Map<String, ArrayList<Appointment>> eventsByHostID) {
		
		try {
		      FileWriter myWriter = new FileWriter("filename.txt");
		      myWriter.write("Pooh\n");
		      myWriter.write("Reee\n");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private void storeInfo(String fileName, Map<String, Host> hostsByID) {
		try {
			FileWriter myWriter = new FileWriter(fileName+".txt");
		    for (Map.Entry<String, Host> set : hostsByID.entrySet()) {
		    	System.out.println(set.getKey() + " = " + set.getValue());
		    }
			
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	
	
	private void readFile() {
		try {
		      File myObj = new File("filename.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void main(String[] args) {
		System.out.println("Yes");
		FileManager tester = new FileManager();
		tester.createFile("eventsByHostID");
		tester.createFile("hostsByID");
		tester.createFile("availabilityByHostID");

	}

}
