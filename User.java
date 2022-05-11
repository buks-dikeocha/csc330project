package edu.cuny.csi.csc330.groupproject;

import java.time.LocalDateTime;

public abstract class User {
	private String userID;
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

		this.accCreated = LocalDateTime.now();
		this.accType = type;
	}

	/**
	 * Returns username.
	 * Returns String
	 *  
	 * @return
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Returns accType variable. 
	 * Returns AccountType.HOST or AccountType.ATTENDEE
	 *  
	 * @return
	 */
	public AccountType getAccType() {
		return accType;
	}

	/**
	 * Returns accCreated variable. Date and time when user was created.
	 * Returns LocalDateTime
	 *  
	 * @return
	 */
	public LocalDateTime getAccCreated() {
		return accCreated;
	}
	
	
}
