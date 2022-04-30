package edu.cuny.csi.csc330.groupproject;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
	private LocalDate date;
	private LocalTime time;
	private Attendee attendee;
	
	
	
	public Appointment(Attendee a) {
		attendee = a;
	}
	// github commit testa
	
	public String toString() {
		return attendee.toString();
	}
}
