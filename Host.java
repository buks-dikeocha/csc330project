package edu.cuny.csi.csc330.groupproject;

public class Host extends User {

	public Host(String userName, String firstName, String lastName) {
		super(AccountType.HOST, userName, firstName, lastName);
		AppointmentDatabase.addHost(this.userID, this);
	}
	
	public void schudule() {
		// call AppointmentDatabase schedule method
	}
}
