package edu.cuny.csi.csc330.groupproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Appointment {
	private LocalDate date;
	private Attendee attendee;
	private String hostID;

	public Appointment(Attendee user, String appointmentDate) {
		init(user, appointmentDate);
	}
	public Appointment(Attendee user, LocalDate appointmentDate) {
		init(user, appointmentDate);
	}
	public Appointment(Attendee user, String appointmentDate, String hostID) {
		init(user, appointmentDate, hostID);
	}
	
	private void init(Attendee user, String appointmentDate, String hostID) {
		attendee = user;
		date = LocalDate.parse(appointmentDate, DateTimeFormatter.ISO_LOCAL_DATE);
		this.hostID = hostID;
		
	}
	private void init(Attendee user, String appointmentDate) {
		attendee = user;
		date = LocalDate.parse(appointmentDate, DateTimeFormatter.ISO_LOCAL_DATE);
		hostID = null;
	}
	private void init(Attendee user, LocalDate appointmentDate) {
		attendee = user;
		date = appointmentDate;
		hostID = null;
	}
	
	
	public LocalDate getDate() {
		return date;
	}
	
	public Attendee getAttendee() {
		return attendee;
	}
	
	public String getUserID() {
		return attendee.getUserID();
	}
	
	public String getHostID() {
		return hostID;
	}

	public void setHostID(String hostID) {
		this.hostID = hostID;
	}
	
	public String toString() {
		return (attendee.getUserID() + "," + hostID + "," + date);
	}
}
