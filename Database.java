package edu.cuny.csi.csc330.groupproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
	public static Map<String, ArrayList<Appointment>> eventsByHostID
		= new HashMap<String, ArrayList<Appointment>>();	
	
	// new working stuff
	public static Map<String, ArrayList<Boolean>> availabilityByID
		= new HashMap<String, ArrayList<Boolean>>();
	public static Map<String, Host> hostsByID = new HashMap<String, Host>();
	public static Map<String, Attendee> attendeesByID = new HashMap<String, Attendee>();
	
	// use properties for this
	private static String databasePathAvail = "C:\\Users\\chukwuebuka.dikeocha\\eclipse-workspace\\workspace\\src\\edu\\cuny\\csi\\csc330\\groupproject\\availByID.csv";
	private static String databasePathUsers = "C:\\Users\\chukwuebuka.dikeocha\\eclipse-workspace\\workspace\\src\\edu\\cuny\\csi\\csc330\\groupproject\\users.csv";
	
	private Database() {}
	
	public static void registerHost(String hostID) {
		// if hostID in use, throw DatabaseExeption error
		hostsByID.put(hostID, new Host(hostID));
		try {
			pushUser("HOST", hostID);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		
		//		eventsByHostID.put(hostID, new ArrayList<Appointment>());
		
		
		
		// availability is never by default
		ArrayList<Boolean> hostAv = new ArrayList<Boolean>();
		for(int i = 0; i < 7; i++) {
			hostAv.add(false);
		}
		
		try {
			Database.setAvailability(hostID, hostAv);
		} catch (IOException e) {
			e.printStackTrace();
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
		
		// loading events called here
	}
	
	public static void launchHost(String hostID) {
		new HostView(hostsByID.get(hostID), "Home", 400, 300);
	}
	
	public static void launchAttendee(String attendeeID) {
		new AttendeeView(attendeesByID.get(attendeeID), "Home", 400, 500);
	}
}
