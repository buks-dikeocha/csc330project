package edu.cuny.csi.csc330.groupproject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class HostView extends Window {
	private int month, year;
	private JButton editAvailability;
	private Host host;
	private FullCalendar cal;
	
	public HostView(Host h) {
		super(new GridLayout(1, 3));
		init(h);
	}
	
	private void init(Host h) {
		LocalDate today = LocalDate.now();
		year = today.getYear();
		month = today.getMonthValue();
		
		host = h;
		
		initVars();
		displayAllComponents();
		addEditButtonListener();
		displaySelf("Home", 500, 300);
	}
	
	private void initVars() {
		editAvailability = new JButton("Edit My Availability");
		cal = new FullCalendar(month, year, false, host.userID);
	}
	
	private void displayAllComponents() {
		add(editAvailability);
		add(cal);
	}
	
	private void addEditButtonListener() {
		editAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AvailabilityWindow(host);
			}
		});
	}
}
