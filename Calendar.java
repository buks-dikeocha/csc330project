package edu.cuny.csi.csc330.groupproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Calendar extends JPanel {
	private int startDay;
	private LocalDate today;
	
	private final static int[] MONTH_SIZES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private JPanel calendarCells[] = new JPanel[49]; // 42 because the most rows a month can take up is 6, 7x6 is 42 boxes
	
	public Calendar(int month, int year) {
		redraw(month, year);
	}
	
	public void redraw(int month, int year) {
		setLayout(new GridLayout(7, 7));
		LocalDate today = LocalDate.now();
		
		LocalDate first = LocalDate.of(year, month, 1);
		startDay = first.getDayOfWeek().getValue();
		
		drawCalendar(month, year);
	}
	
	private void drawCalendar(int month, int year) { // make sure we draw the month in the correct year no always 2022
		initCells();
		addDaysHeader();

		prePadding();
		addDays(month);
		
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
	

	private void addDays(int month) {
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
		            	if(c instanceof JLabel) {
		            		// on date click
		            		System.out.println(((JLabel) c).getText()); // pop up, send date object
		            	}
		            }
		          } 
		        }); 
			
			// set size, but also resized all cells as content is added to any
			
			// add content: display events(s) : attendee username and time
			
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
	// show be parent to HostCalendar and AttendeeCalendar?
}
