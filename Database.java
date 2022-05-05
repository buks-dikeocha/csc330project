package edu.cuny.csi.csc330.groupproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
	public static Map<String, Host> hostsByID;
	public static Map<String, Attendee> attendeesByID;
	public static Map<String, ArrayList<Boolean>> availabilityByID;
	public static Map<String, ArrayList<Appointment>> eventsByID;	
	private static String directory;
	
	static {
		directory = Paths.get("").toAbsolutePath().toString() + "\\edu\\cuny\\csi\\csc330\\groupproject";
		
		hostsByID = new HashMap<String, Host>();
		attendeesByID = new HashMap<String, Attendee>();
		availabilityByID = new HashMap<String, ArrayList<Boolean>>();
		eventsByID = new HashMap<String, ArrayList<Appointment>>();
	}
	
	
	// use properties for this
	private static String databasePathAvail = directory + "\\availByID.csv";
	private static String databasePathUsers = directory + "\\users.csv";
	private static String databasePathEvents = directory + "\\events.csv";
	
	//
	
	private Database() {}
	
	private static Database self = new Database();
	
	public static Database get() { return self; }
	
	public static Database set(Database temp) { return temp; }
	
	//
	
	public static void registerHost(String hostID) throws IOException {
		// if hostID in use, throw DatabaseExeption error
		hostsByID.put(hostID, new Host(hostID));
		pushUser("HOST", hostID);
		
		
		
		eventsByID.put(hostID, new ArrayList<Appointment>());
		pushEvents();
		
		
		
		// availability is never by default
		ArrayList<Boolean> hostAv = new ArrayList<Boolean>();
		for(int i = 0; i < 7; i++) {
			hostAv.add(false);
		}
		Database.setAvailability(hostID, hostAv);
	}
	
	public static void registerAttendee(String attendeeID) throws IOException {
		attendeesByID.put(attendeeID, new Attendee(attendeeID));
		pushUser("ATTENDEE", attendeeID);
	}
	
	public static Host getHostByID(String hostID) {
		return hostsByID.get(hostID);
	}
	
	private static void loadUsers() throws IOException {
		
		/**
		 * read content of file of users and load it into the database
		 */
		
		BufferedReader file = new BufferedReader(new FileReader(databasePathUsers));
		
		String a = "";
		while((a = file.readLine()) != null) {
			String[] m = a.split(",");
				
			if("HOST".equalsIgnoreCase(m[0])) {
				hostsByID.put(m[1], new Host(m[1]));
			}
			else if ("ATTENDEE".equalsIgnoreCase(m[0])) {
				attendeesByID.put(m[1], new Attendee(m[1]));
			}
			else {
				// make DatabaseException
				System.err.println("Invalid user type in file");
				System.exit(0);
			}
		}
		
		file.close();
	}
	
	private static void loadAvailability() throws IOException {
		
		/**
		 * read content of file of host availability and load it into the database
		 */
		
		BufferedReader file = new BufferedReader(new FileReader(databasePathAvail));
		
		String a = "";
		while((a = file.readLine()) != null) {
			String[] m = a.split(",");

			ArrayList<Boolean> b = new ArrayList<Boolean>();
			
			for(int i = 1; i < 8; i++) {
				b.add(Boolean.parseBoolean(m[i]));
			}
			
			availabilityByID.put(m[0], b);
		}
		
		file.close();
	}
	
	private static void loadEvents() throws IOException {
		
		/**
		 * read content of file of host availability and load it into the database
		 */
		
		BufferedReader file = new BufferedReader(new FileReader(databasePathEvents));
		
		String a = "";
		while((a = file.readLine()) != null) {
			String[] m = a.split(",");
			
			ArrayList<Appointment> currentApps = eventsByID.get(m[0]);
			if(currentApps != null) {
				eventsByID.get(m[0]).add(new Appointment(attendeesByID.get(m[1]), m[2]));
			}
			else {
				ArrayList<Appointment> newApp = new ArrayList<Appointment>();
				newApp.add(new Appointment(attendeesByID.get(m[1]), m[2]));
				eventsByID.put(m[0], newApp);
			}
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
					newLine += "," + availabilityByID.get(key).get(i).toString();
				}
				newLine += "\n";
				
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
	
	private static void pushUser(String accType, String userID) throws IOException{
		FileWriter file = null;
		try {
			file = new FileWriter(databasePathUsers);
			
			for(String id : hostsByID.keySet()) {
				file.append("HOST," + id + "\n");
			}
			
			for(String id : attendeesByID.keySet()) {
				file.append("ATTENDEE," + id + "\n");
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
	
	private static void pushEvents() throws IOException{}
	
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
	
	
	public static void addEvent(LocalDate date) {
		System.out.println(date);
		// use jhons event file code to do
	}
	
	public static void start() throws IOException {
		loadUsers();
		loadAvailability();
		loadEvents();
		
		// loading events called here
	}
	
	public static void launchHost(String hostID) {
		new HostView(hostsByID.get(hostID));
	}
	
	public static void launchAttendee(String attendeeID) {
		new AttendeeView(attendeesByID.get(attendeeID));
	}
}
