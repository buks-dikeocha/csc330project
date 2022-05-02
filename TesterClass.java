package edu.cuny.csi.csc330.groupproject;

import java.io.IOException;

public class TesterClass {
	public static void main(String[] args) throws IOException {
		Database.start();
		
				
//		Database.registerHost("gibble");
		
		Database.launchHost("snuff");
		Database.launchAttendee("a1");
		
		
		
	}
}