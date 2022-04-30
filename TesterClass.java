package edu.cuny.csi.csc330.groupproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TesterClass {
	public static void main(String[] args) {
		Host h1 = new Host("squish", "host", "host");
//		Host h2 = new Host("turtle", "host", "host");
//		Host h3 = new Host("moon_man", "host", "host");
//		Host h4 = new Host("nini", "host", "host");
		
//		Attendee a1 = new Attendee("a1", "a", "b");
//		Attendee a2 = new Attendee("a2", "c", "d");
//		Attendee a3 = new Attendee("a3", "e", "f");
		
		
		
		
		
		
		
		
//		Database.schedule("myid", new Appointment(a1));
//		Database.printHostEvents("myid");
		
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\chukwuebuka.dikeocha\\eclipse-workspace\\workspace\\src\\edu\\cuny\\csi\\csc330\\groupproject\\availabilityByID.json"));
			JSONArray a = (JSONArray) obj;
			JSONObject o = (JSONObject) a.get(0);
			
			((JSONArray) o.get("myid")).get(0);
			
			System.out.println(o.get("myid"));
			
//			((JSONObject) a.get(0)).get("myid");
//			System.out.println(o.get(o));
		}
		catch(FileNotFoundException e) { e.printStackTrace(); }
		catch(IOException e) { e.printStackTrace(); }
		catch(ParseException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
		      
	}
}