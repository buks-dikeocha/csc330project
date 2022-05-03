package edu.cuny.csi.csc330.groupproject;

import java.io.IOException;

public class TesterClass {
	public static void main(String[] args) throws IOException {
		Database.start();
		
				
//		Database.registerHost("gibble");
		
		
		
		
		System.out.println(Database.hostsByID);
		
		SignIn a = new SignIn();
		
	}
}