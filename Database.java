package edu.cuny.csi.csc330.groupproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
	public static Map<String, ArrayList<Appointment>> eventsByHostID
		= new HashMap<String, ArrayList<Appointment>>();
	public static Map<String, Host> hostsByID = new HashMap<String, Host>();
	// map of map for host availability: outer map is each host, inner map is each day length 24 keys "00" to "23"
	public static Map<String, ArrayList<ArrayList<Boolean>>> availabilityByHostID
		= new HashMap<String, ArrayList<ArrayList<Boolean>>>();
	
	
	// new working stuff
	public static Map<String, ArrayList<Boolean>> availabilityByID
		= new HashMap<String, ArrayList<Boolean>>();
	
	private static String databasePathAvail = "C:\\Users\\chewb\\eclipse-workspace\\CSC330\\edu\\cuny\\csi\\csc330\\groupproject\\availByID.csv";
	
	private Database() {}
	
	public static void addHost(String hostID, Host host) {
		// if hostID is not in use
		hostsByID.put(hostID, host);
		eventsByHostID.put(hostID, new ArrayList<Appointment>());
		availabilityByHostID.put(hostID, new ArrayList<ArrayList<Boolean>>());
		for(int i = 0; i < 24; i++) { // set all to false
			availabilityByHostID.get(hostID).add(new ArrayList<Boolean>());
			for(int j = 0; j < 7; j++) {
				availabilityByHostID.get(hostID).get(i).add(false);
			}
		}
	}
	
	public static void schedule(String hostID, Appointment event) {
		ArrayList<Appointment> hostEvents = eventsByHostID.get(hostID);
		hostEvents.add(event);
		eventsByHostID.put(hostID, hostEvents);
	}
	
	public static void printHostEvents(String hostID) {
		ArrayList<Appointment> hostEvents = eventsByHostID.get(hostID);
		
		System.out.printf("%s ", hostID);
		for(Appointment event : hostEvents) {
			System.out.println(event);
		}
	}
	
	public static Host getHostByID(String hostID) {
		return hostsByID.get(hostID);
	}
	
	private static void loadAvailability() throws IOException {
		
		/**
		 * read content of file of host availability and load it into the database
		 */
		
		BufferedReader file = new BufferedReader(new FileReader(databasePathAvail));
		
		String a = "";
		while((a = file.readLine()) != null) {
			String[] m = a.split(", ");

			ArrayList<Boolean> b = new ArrayList<Boolean>();
			
			for(int i = 1; i < 8; i++) {
				b.add(Boolean.parseBoolean(m[i]));
			}
			
			availabilityByID.put(m[0], b);
		}
		
		file.close();
	}

	private static void pushAvailability() throws IOException {
		
		/**
		 * put content of map into file of host availability
		 */
		
		FileWriter file = null;
		try {
			file = new FileWriter(databasePathAvail);
			
			for(String key : availabilityByID.keySet()) {
				String newLine = key;
				for(int i = 0; i < 7; i++) {
					newLine += ", " + availabilityByID.get(key).get(i).toString();
				}
				newLine += "\n";
				System.out.println(newLine);
				
				file.append(newLine);
			}
		}
		catch(Exception e){ e.printStackTrace(); }
		finally {
			try {
				file.flush();
				file.close();
			}
			catch(Exception e) { e.printStackTrace(); }
		}
		
	}
	
	public static void setAvailability(String hostID, ArrayList<Boolean> newAv) throws IOException {
		availabilityByID.put(hostID, newAv);	
		pushAvailability();
	}
	
	public static ArrayList<Boolean> getAvailability(String hostID) {
		/**
		 * get availability of one host, pass in host userID
		 */
		
		// called when we search
		return availabilityByID.get(hostID);
	}
	
	
	
	public static void start() throws IOException {
		loadAvailability();
		
		// loading events called here
	}
}

/*
host scheduler() and descheduler() will access this map

Map:

{

	"host_id" : [Appointment],
	"host_id" : [Appointment],
	...

}

*/
