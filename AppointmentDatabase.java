package edu.cuny.csi.csc330.groupproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppointmentDatabase {
	public static Map<String, ArrayList<Appointment>> eventsByHostID
		= new HashMap<String, ArrayList<Appointment>>();
	public static Map<String, Host> hostsByID = new HashMap<String, Host>();
	
	private AppointmentDatabase() {}
	
	public static void addHost(String hostID, Host host) {
		// if hostID is not in use
		hostsByID.put(hostID, host);
	}
	
	public static void schedule(String hostID, Appointment event) {
		//ArrayList<Appointment> hostEvents = 
				System.out.println(eventsByHostID.get(hostID));
//		hostEvents.add(event);
//		eventsByHostID.put(hostID, hostEvents);
	}
	
	public static void printHostEvents(String hostID) {
		ArrayList<Appointment> hostEvents = eventsByHostID.get(hostID);
		
		System.out.printf("%s", hostID);
		for(Appointment a : hostEvents) {
			System.out.println(a);
		}
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