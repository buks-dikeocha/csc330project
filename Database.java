package edu.cuny.csi.csc330.groupproject;

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
	
	public static void deschedule(String hostID, Appointment event) {
		ArrayList<Appointment> hostEvents = eventsByHostID.get(hostID);
		hostEvents.remove(event);
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
