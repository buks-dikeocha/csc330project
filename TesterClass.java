package edu.cuny.csi.csc330.groupproject;

public class TesterClass {
	public static void main(String[] args) {
//		HostView hv = new HostView("Host Login", 400, 300);
//		AttendeeView av = new AttendeeView("Find an Event", 400, 500);
		
		Host h = new Host("host", "host", "host");
		Attendee a1 = new Attendee("a1", "a", "b");
		Attendee a2 = new Attendee("a2", "c", "d");
		Attendee a3 = new Attendee("a3", "e", "f");
		
		
		AppointmentDatabase.schedule("myid", new Appointment(a1));
//		AppointmentDatabase.printHostEvents("myid");
		
		AppointmentDatabase.hostsByID.forEach((k, v) -> System.out.println(k + " " + v));
	}
}