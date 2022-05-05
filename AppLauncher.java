package edu.cuny.csi.csc330.groupproject;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppLauncher {
	public static void main(String[] args) throws IOException{
		launchApp();
	}
	
	private static void launchApp() throws IOException {
		Database.start();
		new SignInView();
	}
}