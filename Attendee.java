package edu.cuny.csi.csc330.groupproject;

public class Attendee extends User {

	public Attendee(String userName, String firstName, String lastName) {
		super(AccountType.ATTENDEE, userName, firstName, lastName);
	}
	
}
