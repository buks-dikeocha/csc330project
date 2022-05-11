package edu.cuny.csi.csc330.groupproject;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AttendeeView extends Window {
	private JButton search, clear;	
	private JTextField searchbar;
	private JPanel results;
	private String hostSearch;
	private FullCalendar cal, resultsCal;
	private Attendee attendee;
	
	private int month, year;
	
	/**
	 * Opens a window which the attendee can search and schedule events.
	 * Takes an Attendee object.
	 * 
	 * @param user
	 */
	public AttendeeView(Attendee user) {
		super(new FlowLayout());
		init(user);
	}
	
	private void init(Attendee user) {
		LocalDate today = LocalDate.now();
		year = today.getYear();
		month = today.getMonthValue();
		
		attendee = user;
		
		initVars();
		addSearchButtonsListeners();
		displayAllComponents();
		displaySelf("Home", 400, 500);
	}
	
	private void initVars() {
		clear = new JButton("X");
		search = new JButton("Go");
		searchbar = new JTextField(20);
		cal = new FullCalendar(month, year, false, attendee.getUserID());
		
		results = new JPanel(new GridLayout(12, 3));
	}

	private void addSearchButtonsListeners() {
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hostSearch = searchbar.getText();
				results.setVisible(false);
				results.removeAll();
				
				ArrayList<Boolean> hostAv = Database.getAvailability(hostSearch);
				if(hostAv != null) {
					resultsCal = new FullCalendar(month, year, true, hostSearch, attendee.getUserID());
					results.add(resultsCal);
				}
				else {
					results.add(new JLabel("No hosts by that id."));
				}
				
				setToReultsView();
			}
		});
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchbar.setText("");
				setToCalendarView();
			}
		});
	}
	
	private void displayAllComponents() {
		add(clear);
		add(searchbar);
		add(search);
		
		add(cal);
		add(results);
	}
	
	private void setToCalendarView() {
		results.setVisible(false);
		results.removeAll();
		
		cal.setVisible(true);
	}
	
	private void setToReultsView() {
		cal.setVisible(false);	
		results.setVisible(true);
	}
}
