package edu.cuny.csi.csc330.groupproject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
	protected String userID;
	protected String firstName;
	protected String lastName;
	protected List<Appointment> events;
	public AccountType accType;
	public LocalDateTime accCreated;
	
	public User(AccountType type, String userName, String firstName, String lastName) {
		this.userID = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.events = new ArrayList<Appointment>();
		this.accCreated = LocalDateTime.now();
		this.accType = type;
	}
	
	public void scheduler() {}
	
	public void descheduler() {}
	
	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}
	
	public String toString() {
		return (accType + " " + userName + " " + firstName + " " + lastName);
	}
}
