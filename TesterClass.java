package edu.cuny.csi.csc330.groupproject;

import java.io.IOException;

public class TesterClass {
	public static void main(String[] args) throws IOException {
		Database.start();
		
		
		
		new Host("squish", "host", "host");
//		Host h2 = new Host("turtle", "host", "host");
//		Host h3 = new Host("guwop", "host", "host");
//		Host h4 = new Host("nini", "host", "host");
		
		new Attendee("a1", "a", "b");
//		Attendee a2 = new Attendee("a2", "c", "d");
//		Attendee a3 = new Attendee("a3", "e", "f");
		
		
		
		
		
		
		
		
//		Database.schedule("myid", new Appointment(a1));
//		Database.printHostEvents("myid");
		
		
//		System.out.println(Database.availabilityByID.get("guwop"));
//		Boolean[] p = {false, false, false, false, true, false, false};
//		Database.setAvailability("gibble", p);
	}
}