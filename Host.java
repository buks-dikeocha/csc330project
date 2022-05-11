package edu.cuny.csi.csc330.groupproject;

public class Host extends User {
	
	/**
	 * Creates a Host instance with the provided username.
	 * 
	 * @param userName
	 */
	public Host(String userName) {
		super(AccountType.HOST, userName);
	}
}
