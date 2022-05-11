package edu.cuny.csi.csc330.groupproject;

public class Attendee extends User {
	
	/**
	 * Creates an Attendee instance with the provided username.
	 * 
	 * @param userName
	 */
	public Attendee(String userName) {
		super(AccountType.ATTENDEE, userName);
	}
}
