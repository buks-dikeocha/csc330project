package edu.cuny.csi.csc330.groupproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager{
	private static String path = "C:\\Users\\chewb\\eclipse-workspace\\CSC330\\edu\\cuny\\csi\\csc330\\groupproject\\database.txt";
	
	public static void pushDatabase() throws IOException{
		try {
			FileOutputStream f = new FileOutputStream(new File(path));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			// Write objects to file
			o.writeObject(Database.get());
			
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
    }
	
	public static void loadDatabase() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fi = new FileInputStream(new File(path));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Database.set((Database) oi.readObject());
			
			oi.close();
			fi.close();
		}
		catch(FileNotFoundException e) {
			System.err.println("File not found");
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		// fileName = "C:\\Users\\Jhon\\eclipse-workspace\\CSC330-SrcTree";
		
//		ArrayList<Host> listTest = (ArrayList<Host>) Database.hostsByID.values();
//		listTest.add(new Host(""));
//		listTest.add(h2);
		
//		pushDatabase(null);
//		loadDatabase(null);
//		System.out.println(Database.hostsByID);
	}
}