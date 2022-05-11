package edu.cuny.csi.csc330.groupproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Database {
	private static Map<String, Host> hostsByID;
	private static Map<String, Attendee> attendeesByID;
	private static Map<String, ArrayList<Boolean>> availabilityByID;
	private static Map<String, ArrayList<Appointment>> eventsByID;	
	private static String directory;
	
	static {
		directory = Paths.get("").toAbsolutePath().toString() + "\\src\\edu\\cuny\\csi\\csc330\\groupproject";
		
		hostsByID = new HashMap<String, Host>();
		attendeesByID = new HashMap<String, Attendee>();
		availabilityByID = new HashMap<String, ArrayList<Boolean>>();
		eventsByID = new HashMap<String, ArrayList<Appointment>>();
	}
	
	private static String databasePathAvail = directory + "\\availByID.csv";
	private static String databasePathUsers = directory + "\\users.csv";
	private static String databasePathEvents = directory + "\\events.csv";
	
	private Database() {}
	
	/**
	 * Creates a new host user with no events and no availability.
	 * Takes a username string as input.
	 * 
	 * Example: registerHost("creative_username")
	 * 
	 * @param hostID
	 * @throws IOException
	 */
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
	
	/**
	 * Creates a new attendee user.
	 * Takes a username as input.
	 * 
	 * Example: registerHost(""new_attendee_username)
	 * 
	 * @param attendeeID
	 * @throws IOException
	 */
	public static void registerAttendee(String attendeeID) throws IOException {
		attendeesByID.put(attendeeID, new Attendee(attendeeID));
		pushUser("ATTENDEE", attendeeID);
	}
	
	/**
	 * Returns an instance of host. Specify the host using its username.
	 * Returns Host object.
	 * 
	 * Example: getHost("exising_host")
	 * 
	 * @param hostID
	 * @return
	 */
	public static Host getHost(String hostID) {
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
				eventsByID.put(m[1], new ArrayList<Appointment>()); // this is needed so pushEvents() works correctly
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
	
	private static void pushEvents() throws IOException{
		Set<String> keySet = eventsByID.keySet();

		FileWriter file = null;
		try {
			file = new FileWriter (databasePathEvents);
			
			for(String hostID : keySet) {
				for(Appointment app : eventsByID.get(hostID)) {
					file.append(hostID + "," + app.getAttendee().getUserID() + "," + app.getDate().toString() + "\n");
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
	
	/**
	 * Updates the availability of a host.
	 * 
	 * Example: setAvailability("existing_host", list_collection_of_booleans)
	 * 
	 * @param hostID
	 * @param newAv
	 * @throws IOException
	 */
	public static void setAvailability(String hostID, ArrayList<Boolean> newAv) throws IOException {
		// give DatabaseException for arrays with length >7
		availabilityByID.put(hostID, newAv);	
		pushAvailability();
	}
	
	public static ArrayList<Boolean> getAvailability(String hostID) {
		/**
		 * get availability of one host, pass in host userID
		 */
		
		return availabilityByID.get(hostID);
	}
	
	
	public static void addEvent(String hostID, String attendeeID, String date) throws IOException {
		eventsByID.get(hostID).add(new Appointment(attendeesByID.get(attendeeID), date));
		pushEvents();
	}
	
	/**
	 * Read data from CSV files and import to maps. Must call before attempting to add more events, users, or availability
	 * 
	 * @throws IOException
	 */
	public static void start() throws IOException {
		loadUsers();
		loadAvailability();
		loadEvents();
	}
	
	
	/**
	 * Launch the window that host will use. Specify with host username.
	 * 
	 * @param hostID
	 */
	public static void launchHost(String hostID) {
		new HostView(hostsByID.get(hostID));
	}
	
	/**
	 * Launch the window that attendee will use. Specify with attendee username.
	 * 
	 * @param attendeeID
	 */
	public static void launchAttendee(String attendeeID) {
		new AttendeeView(attendeesByID.get(attendeeID));
	}
	
	/**
	 * Returns a map with host usernames mapped to their object.
	 * Returns Map<String, Host>
	 * 
	 * @return
	 */
	public static Map<String, Host> getHostsByID() { // get all hosts
		return hostsByID;
	}

	/**
	 * Returns a map with attendee usernames mapped to their object.
	 * Returns Map<String, Attendee>
	 * 
	 * @return
	 */
	public static Map<String, Attendee> getAttendeesByID() {
		return attendeesByID;
	}
	
	/**
	 * Returns a map with host usernames mapped to a list of their availability.
	 * Returns Map<String, ArrayList<Boolean>>
	 * 
	 * @return
	 */
	public static Map<String, ArrayList<Boolean>> getAvailabilityByID() {
		return availabilityByID;
	}

	/**
	 * Returns a map with host usernames mapped to a list of their scheduled events.
	 * Returns Map<String, ArrayList<Appointment>>
	 * 
	 * @return
	 */
	public static Map<String, ArrayList<Appointment>> getEventsByID() {
		return eventsByID;
	}
}
