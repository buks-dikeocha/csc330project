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
	private boolean calReservesEvent;
	private String calendarOf;
	
	private int eventConfirmed;
	private EventConfirmation confirmation;
	
	private final static int[] MONTH_SIZES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private JPanel calendarCells[] = new JPanel[49]; // 42 because the most rows a month can take up is 6, 7x6 is 42 boxes
	
	public Calendar(int month, int year, boolean reservesEvent, String hostID) {
		confirmation = new EventConfirmation();
		calReservesEvent = reservesEvent;
		calendarOf = hostID;
		redraw(month, year);
	}
	
	public void redraw(int month, int year) {
		setLayout(new GridLayout(7, 7));
		
		LocalDate first = LocalDate.of(year, month, 1);
		startDay = first.getDayOfWeek().getValue();
		
		drawCalendar(month, year);
	}
	
	private void drawCalendar(int month, int year) {
		initCells();
		addDaysHeader();

		prePadding();
		addDays(month, year);
		
		addAllCells();
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
		Calendar cal = this;
		int day = 0;
		for (int i = 7 + startDay; i <= 49; i++) {
			day++;
			calendarCells[i - 1] = new JPanel(new FlowLayout());
			calendarCells[i - 1].setBorder(BorderFactory.createLineBorder(Color.black));
			calendarCells[i - 1].add(new JLabel(day + ""));
			calendarCells[i - 1].addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		            Object clicked = me.getSource();
		            
		            for (Component c : ((Container) clicked).getComponents()) {
		            	if(c instanceof JLabel && calReservesEvent) {
		            		// on date click
		            		String date = "" + month + "." + ((JLabel) c).getText() + "." + year;
		            		LocalDate thisDate = LocalDate.of(year, month, Integer.parseInt(((JLabel) c).getText()));
		            		ArrayList<Boolean> hostAv = Database.getAvailability(calendarOf);
		            		
		            		
		            		if(hostAv.get(thisDate.getDayOfWeek().getValue() - 1)) {
		            			confirmation.setDetails(calendarOf, date);
								eventConfirmed = JOptionPane.showConfirmDialog(cal, confirmation, "Confirm", JOptionPane.YES_NO_OPTION);
								
								if(eventConfirmed == 0) {
									Database.addEvent(thisDate);
								}
		            		}
		            		else {
		            			JOptionPane.showMessageDialog(cal, calendarOf + " is not available on this day.", "Host Unavailable", JOptionPane.ERROR_MESSAGE);
		            		}
		            	}
		            }
		          } 
		        });
			
			if(day >= MONTH_SIZES[month - 1]) {
				break;
			}
		}
	}
	
	private void addAllCells() {
		for (int i = 0; i < 49; i++) {
			add(calendarCells[i]);
		}
	}
	
	// real redraw(month num, year): called when user clicks through arrows to change month
}
