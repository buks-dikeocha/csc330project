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
	
	/**
	 * Returns the date that this appointment is scheduled for.
	 * Returns LocalDate
	 * 
	 * @return
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Returns the user that this appointment is scheduled with.
	 * Returns Attendee
	 * 
	 * @return
	 */
	public Attendee getAttendee() {
		return attendee;
	}
	
	public String toString() {
		return (attendee.toString() + ": " + date);
	}
}
