package edu.cuny.csi.csc330.groupproject;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AttendeeView extends Window {
	private Window mainWindow;
	private JButton search, clear;	
	private JTextField searchbar;
	private JPanel results;
	private String hostSearch;
	private FullCalendar cal, resultsCal;
	private List<JButton> times;
	
	private int eventConfirmed;
	private EventConfirmation confirmation;
	private Attendee attendee;
	
	private int month, year;
	
	public AttendeeView(Attendee a, String title, int width, int height) {
		super(new FlowLayout());
		mainWindow = this;
		
		LocalDate today = LocalDate.now();
		year = today.getYear();
		month = today.getMonthValue();
		
		times = new ArrayList<JButton>();
		confirmation = new EventConfirmation();
		
		clear = new JButton("X");
		search = new JButton("Go");
		searchbar = new JTextField(20);
		cal = new FullCalendar(month, year);
		resultsCal = new FullCalendar(month, year);
		results = new JPanel(new GridLayout(12, 3));
		
		
		add(clear);
		add(searchbar);
		add(search);
		
		add(cal);
		add(results);
		
		attendee = a;
		

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hostSearch = searchbar.getText();
				results.setVisible(false);
				results.removeAll();
				
				// lookup host times from id
//				try {
//					results.removeAll();
//					Host h = Database.getHostByID(hostSearch);
//					ArrayList<ArrayList<Boolean>> hostAv = Database.availabilityByHostID.get(h.userID);
//					
//					
//					System.out.println(h.userID);
//					
//					// -- somewhere below give null pointer exception
//					// display host times
//					// idea: use a calendar, when a day is clicked do a pop up wind that shows available times
//					for(int i = 0; i < 24; i++) {
//						for(int j = 0; j < 7; j++) {
////							System.out.println(hostAv.get(i).get(j));
//							if(hostAv.get(i).get(j)) {
//								times.add(new JButton("" + Days.values()[j].getShorthand() + " " + i + ":00"));
//								results.add(times.get(i));
//								
//								times.get(i).addActionListener(new ActionListener() {
//									public void actionPerformed(ActionEvent e) {
//										confirmation.setDetails(h.userID, "ddd", "ttt");
//										eventConfirmed = JOptionPane.showConfirmDialog(mainWindow, confirmation, "Confirm", JOptionPane.YES_NO_OPTION);
//										
//										if(eventConfirmed == 0) {
//											System.out.println("yee boii");
//											// schedule event
//											// Database.schedule(, new Appointment(a1));
//										}
//									}
//								});
//							}
//						}
//					}
//					
//				} catch(Exception NullPointerException) {
//					results.removeAll();
//					results.add(new JLabel("No hosts by that id."));
//					System.err.println("No host with that ID!");
//					
//				} finally {
//					setToReultsView();
//				}
				
				
				results.add(resultsCal);
				
				
				
				
				
				setToReultsView();
			}
		});
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setToCalendarView();
				searchbar.setText("");
			}
		});
		
		display(title, width, height);
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
