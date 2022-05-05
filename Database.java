package edu.cuny.csi.csc330.groupproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Database implements Serializable {
	public static ArrayList<Appointment> events = new ArrayList<Appointment>();
	public static Map<String, ArrayList<Appointment>> eventsByID
		= new HashMap<String, ArrayList<Appointment>>();	
	
	// new working stuff
	public static Map<String, ArrayList<Boolean>> availabilityByID
		= new HashMap<String, ArrayList<Boolean>>();
	public static Map<String, Host> hostsByID = new HashMap<String, Host>();
	public static Map<String, Attendee> attendeesByID = new HashMap<String, Attendee>();
	
	// use properties for this
	private static String databasePathAvail = "availByID.csv";
	private static String databasePathUsers = "users.csv";
	private static String databasePathEvents = "events.csv";
	
	//
	private static Attendee currentAtt = null;
	private static Host currentHost = null;
	
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
		
		
		
		// availability is set to unavailable by default
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
		 * read content of file of host events and load it into the database
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
	
	private static void pushEvents() throws IOException{
		Set<String> keySet = eventsByID.keySet();
		ArrayList<String> listKeys = new ArrayList<String>(keySet);
		Collection<ArrayList<Appointment>> values = eventsByID.values();
		ArrayList<ArrayList<Appointment>> listVal = new ArrayList<ArrayList<Appointment>>(values);
		System.out.println(listKeys);
		System.out.println(listVal);

		FileWriter file = null;
		try {
			file = new FileWriter (databasePathEvents);
			for(ArrayList<Appointment> day : listVal) {
				for(Appointment write : day) {
					file.append(write.getHostID() + "," + write.getAttendee() + "," + write.getDate() + "\n");
				}
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
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
	
	
	public static void addEvent(LocalDate date, String hostID) throws IOException {
		Appointment addApp = new Appointment(currentAtt, date);
		//System.out.println(addApp);
		events.add(addApp);
		addApp.setHostID(hostID);
		eventsByID.put(hostID, events);
	}
	
	public static void start() throws IOException {
		loadUsers();
		loadAvailability();
		loadEvents();
		
		// loading events called here
		
		/*
		 * System.out.println(hostsByID); System.out.println(attendeesByID);
		 * System.out.println(availabilityByID); System.out.println(eventsByID);
		 */
	}
	
	public static void launchHost(String hostID) {
		currentHost = hostsByID.get(hostID);
		new HostView(hostsByID.get(hostID));
	}
	
	public static void launchAttendee(String attendeeID) {
		currentAtt = attendeesByID.get(attendeeID);
		new AttendeeView(attendeesByID.get(attendeeID));
	}
}