package edu.cuny.csi.csc330.groupproject;

public class Host extends User {
	public Host(String userName, String firstName, String lastName) {
		super(AccountType.HOST, userName, firstName, lastName);
		new HostView(this, "Host Login", 400, 300);
		Database.addHost(this.userID, this);
	}
}
