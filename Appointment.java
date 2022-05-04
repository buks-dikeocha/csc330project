package edu.cuny.csi.csc330.groupproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Appointment {
	private LocalDate date;
	private Attendee attendee;
	
	public Appointment(Attendee user, String appointmentDate) {
		init(user, appointmentDate);
	}
	
	private void init(Attendee user, String appointmentDate) {
		attendee = user;
		date = LocalDate.parse(appointmentDate, DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public Attendee getAttendee() {
		return attendee;
	}
	
	public String toString() {
		return (attendee.toString() + ": " + date);
	}
}
