package edu.cuny.csi.csc330.groupproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
	
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
	
	//write Hosts into file
	public static void writeHosts(ArrayList<Host> obj, File file) throws IOException{
        try {
        		FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
    			ObjectOutputStream o = new ObjectOutputStream(f);

    			// Write objects to file
    			for(Host host : obj) {
    				o.writeObject(host);
    			}

    			o.close();
    			f.close();
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
        }
    }
	
	// write Host's events into file
	public static void writeEvents() {
		
	}
	
	public static ArrayList<Host> readObjectFromFile(File file) throws IOException, ClassNotFoundException {
		ArrayList<Host> hostsList = new ArrayList<Host>();
		Host obj = null;
		boolean nextLine = true;
		
        try {
        	FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			while(nextLine){
		        if(fi.available() != 0){
		         obj = (Host) oi.readObject();    
		         hostsList.add(obj);
		        }
		        else{
		        nextLine =false;
		        }
			}
        } catch(FileNotFoundException e) {
        	
        }
		return hostsList;
    }

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		// testing testing...
		
		Host h1 = new Host("hippie", "f", "l");
		Host h2 = new Host("squish", "host", "host");
		Attendee a1 = new Attendee("a1", "firstN", "lastN");
		Attendee a2 = new Attendee("a2", "a", "b");
		Appointment day = new Appointment(a1);
		Appointment day2 = new Appointment(a2);
		
		ArrayList<Host> listTest = new ArrayList<Host>();
		listTest.add(h1);
		listTest.add(h2);
		
		Database.addHost(h1.getUserID(), h1);
		Database.addHost(h2.getUserID(), h2);
		Database.schedule(h1.getUserID(), day);
		Database.schedule(h2.getUserID(), day2);
		Database.schedule(h1.getUserID(), day2);
		
		System.out.println(h1);
		System.out.println(h2);
		System.out.println(Database.getEvents(h1.getUserID()));
		
		for(Map.Entry<String, ArrayList<Appointment>> events : Database.eventsByHostID.entrySet()) {
			System.out.println("Key = " + events.getKey() + ", Value = " + events.getValue());
		}
		System.out.println("__________________________________________________________");
		
		writeHosts(listTest, null);
		ArrayList<Host> test = readObjectFromFile(null);
		
		for(Host host : test) {
			System.out.println(host);
		}
		
	}
}
