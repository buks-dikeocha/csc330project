package edu.cuny.csi.csc330.groupproject;

import java.io.IOException;

public class AppLauncher {
	public static void main(String[] args) throws IOException{
		Database.start();
		new SignInView();
	}
}
