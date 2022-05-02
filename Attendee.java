package edu.cuny.csi.csc330.groupproject;

public class Attendee extends User {
	public Attendee(String userName) {
		super(AccountType.ATTENDEE, userName);
	}
}
