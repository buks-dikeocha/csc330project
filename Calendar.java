package edu.cuny.csi.csc330.groupproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
//import javax.swing.JPanel;

public class Calendar extends JPanel {
	private int startDay;
	private int month;
	private LocalDate today;
	
	private final static int[] MONTH_SIZES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private JPanel calendarCells[] = new JPanel[42]; // 42 because the most rows a month can take up is 6, 7x6 is 42 boxes
	
	public Calendar(int month, int year) {
		redraw(month, year);
	}
	
	public void redraw(int month, int year) {
		setLayout(new GridLayout(6, 7));
		LocalDate today = LocalDate.now();
		
		LocalDate first = LocalDate.of(year, month, 1);
		startDay = first.getDayOfWeek().getValue();
		
		for(int i = 0; i < 42; i++) {
			calendarCells[i] = new JPanel(new FlowLayout());
		}

		int day = 0;
		for (int i = startDay; i <= 42; i++) {
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
		            		System.out.println(((JLabel) c).getText());
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
		
		for (int i = 0; i < 42; i++) {
			add(calendarCells[i]);
		}
		
		
		
		
		
	}
	
	
	
	// real redraw(month num, year): called when user clicks through arrows to change month
	// show be parent to HostCalendar and AttendeeCalendar?
}
