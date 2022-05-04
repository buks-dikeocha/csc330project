package edu.cuny.csi.csc330.groupproject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
	protected String userID;
	protected List<Appointment> events;
	public AccountType accType;
	public LocalDateTime accCreated;
	
	public User(AccountType type, String userName) {
		this.userID = userName;
		
		this.events = new ArrayList<Appointment>();
		this.accCreated = LocalDateTime.now();
		this.accType = type;
	}
	
	public String toString() {
		return (accType.name() + " " + userID);
	}
}
