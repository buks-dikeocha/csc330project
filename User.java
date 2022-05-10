package edu.cuny.csi.csc330.groupproject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
	private String userID;
	private List<Appointment> events;
	private AccountType accType;
	private LocalDateTime accCreated;
	
	public User(AccountType type, String userName) {
		init(type, userName);
	}
	
	public String toString() {
		return (accType.name() + " " + userID);
	}
	
	private void init(AccountType type, String userName) {
		this.userID = userName;
		
		this.events = new ArrayList<Appointment>();
		this.accCreated = LocalDateTime.now();
		this.accType = type;
	}

	public String getUserID() {
		return userID;
	}

	public List<Appointment> getEvents() {
		return events;
	}

	public AccountType getAccType() {
		return accType;
	}

	public LocalDateTime getAccCreated() {
		return accCreated;
	}
	
	
}
