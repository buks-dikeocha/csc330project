package edu.cuny.csi.csc330.groupproject;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EventConfirmation extends JPanel {
	private JLabel label;
	private JPanel details;
	
	/**
	 * Creates a component that lists event details
	 */
	public EventConfirmation() {
		super(new GridLayout(2, 1));
		init();
	}
	
	/**
	 * Update details of component. Uses a string for host username and string for date.
	 * Date format follows yyyy-mm-dd
	 * 
	 * @param hostName
	 * @param date
	 */
	public void setDetails(String hostName, String date) {
		details.removeAll();
		
		details.add(new JLabel("With: " + hostName));
		details.add(new JLabel("Date: " + date));
	}
	
	private void init() {
		initVars();
		displayAllComponents();
	}
	
	private void initVars() {
		label = new JLabel("Reserve this day?");
		details = new JPanel(new GridLayout(3, 1));
	}
	
	private void displayAllComponents() {
		add(label);
		add(details);
	}
}
