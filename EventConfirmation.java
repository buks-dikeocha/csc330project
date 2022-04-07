package edu.cuny.csi.csc330.groupproject;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EventConfirmation extends JPanel {
	private JLabel label;
	private JPanel details;
	
	public EventConfirmation() {
		super(new GridLayout(3, 1));//String title, int width, int height
		
		label = new JLabel("Reserve this event?");
		details = new JPanel(new GridLayout(3, 1)); // host name, date, time
		
		add(label);
		add(details);
	}
	
	public void setDetails(String hostName, String date, String time) {
		details.add(new JLabel("With: " + hostName));
		details.add(new JLabel("Date: " + date));
		details.add(new JLabel("Time: " + time));
	}
}
