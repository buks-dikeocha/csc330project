package edu.cuny.csi.csc330.groupproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Calendar extends JPanel {
	private int startDay;
	private boolean calReservesEvent; // will this calendar be clickable
	private String calendarOf;
	
	private int eventConfirmed;
	private EventConfirmation confirmation;
	
	private final static int[] MONTH_SIZES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private JPanel calendarCells[] = new JPanel[49]; // 42 because the most rows a month can take up is 6, 7x6 is 42 boxes, +7 for header
	
	public Calendar(int month, int year, boolean reservesEvent, String hostID) {
		init(month, year, reservesEvent, hostID);
	}
	
	private void init(int month, int year, boolean reservesEvent, String hostID) {
		setLayout(new GridLayout(7, 7));
		
		confirmation = new EventConfirmation();
		calReservesEvent = reservesEvent;
		calendarOf = hostID;
		
		drawCalendar(month, year);
	}
	
	private void drawCalendar(int month, int year) {
		LocalDate first = LocalDate.of(year, month, 1);
		startDay = first.getDayOfWeek().getValue();
		
		initCells();
		addDaysHeader();

		prePadding();
		addDays(month, year);
		
		displayAllCells();
	}
	
	private void initCells() {
		for(int i = 0; i < 49; i++) {
			calendarCells[i] = new JPanel(new FlowLayout());
		}
	}
	
	private void addDaysHeader() {
		for(int i = 0; i < 7; i++) {
			calendarCells[i].add(new JLabel(Days.values()[i].getFirst()));
		}
	}
	
	private void prePadding() {
		for(int i = 7; i < startDay; i++) {
			calendarCells[i].add(new JLabel(""));
		}
	}

	private void addDays(int month, int year) {
		int day = 0;
		for (int i = 7 + startDay; i <= 49; i++) {
			day++;
			
			addBasicCell(i - 1, day);
			highlightIfHasEvent(i - 1, month, day, year);
			highlightIfToday(i - 1, month, day, year);

			if(calReservesEvent) {
				addListener(i - 1, this, month, year);
			}
			
			if(day >= MONTH_SIZES[month - 1]) {
				break;
			}
		}
	}
	
	private void addBasicCell(int cell, int day) {
		calendarCells[cell] = new JPanel(new FlowLayout());
		calendarCells[cell].setBorder(BorderFactory.createLineBorder(Color.black));
		calendarCells[cell].add(new JLabel(day + ""));
	}
	
	private void highlightIfHasEvent(int cell, int month, int day, int year) {
		if(Database.getHostByID(calendarOf) != null && !calReservesEvent) { // aka is this a host && not a searched calendar
			ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
			
			// add all dates in host event database to this array
			for(Appointment app : Database.eventsByID.get(calendarOf)) {
				dates.add(app.getDate());
			}
			
			for(LocalDate s : dates) {
				if(year == s.getYear() && month == s.getMonthValue() && day == s.getDayOfMonth()) {
					calendarCells[cell].setBorder(BorderFactory.createLineBorder(Color.red));
				}
			}
		}
	}
	
	private void highlightIfToday(int cell, int month, int day, int year) {
		LocalDate today = LocalDate.now();
		
		if(year == today.getYear() && month == today.getMonthValue() && day == today.getDayOfMonth()) {
			calendarCells[cell].setBorder(BorderFactory.createLineBorder(Color.blue));
		}
	}
	
	private void addListener(int cell, Calendar self, int month, int year) {
		calendarCells[cell].addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	            Object clicked = me.getSource();
	            
	            for (Component c : ((Container) clicked).getComponents()) {
	            	if(c instanceof JLabel) {
	            		// on date click
	            		String date = "" + month + "." + ((JLabel) c).getText() + "." + year;
	            		LocalDate thisDate = LocalDate.of(year, month, Integer.parseInt(((JLabel) c).getText()));
	            		ArrayList<Boolean> hostAv = Database.getAvailability(calendarOf);
	            		
	            		
	            		if(hostAv.get(thisDate.getDayOfWeek().getValue() - 1)) {
	            			confirmation.setDetails(calendarOf, date);
							eventConfirmed = JOptionPane.showConfirmDialog(self, confirmation, "Confirm", JOptionPane.YES_NO_OPTION);
							
							if(eventConfirmed == 0) {
								try {
									Database.addEvent(thisDate, calendarOf);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
	            		}
	            		else {
	            			JOptionPane.showMessageDialog(self, calendarOf + " is not available on this day.", "Host Unavailable", JOptionPane.ERROR_MESSAGE);
	            		}
	            	}
	            }
	          } 
	        });
	}
	
	private void displayAllCells() {
		for (int i = 0; i < 49; i++) {
			add(calendarCells[i]);
		}
	}
}
