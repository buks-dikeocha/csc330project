package edu.cuny.csi.csc330.groupproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FullCalendar extends JPanel {

	private int currentMonth, currentYear;
	private Calendar calendar;
	private JLabel header;
	private JPanel calendarPanel;
	private JButton previousMonth, nextMonth;
	
	public FullCalendar(int month, int year) {
		currentMonth = month;
		currentYear = year;
		
		header = new JLabel();
		previousMonth = new JButton("<");
		nextMonth = new JButton(">");
		calendarPanel = new JPanel();
		calendar = new Calendar(currentMonth, currentYear);
		
		previousMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPreviousMonth();
			}
		});
		
		nextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayNextMonth();
			}
		});
		
		header.setText(currentMonth + "/" + currentYear + "");
		
		add(header);
		add(previousMonth);
		add(calendarPanel);
		add(nextMonth);
		calendarPanel.add(calendar);
	}
	
	private void displayPreviousMonth() {
		currentMonth--; // might be a better way using java's Calendar class
		if(currentMonth < 1) {
			currentMonth = 12;
			currentYear--;
		}
		
		readd();
	}
	
	private void displayNextMonth() {
		currentMonth++;
		if(currentMonth > 12) {
			currentMonth = 1;
			currentYear++;
		}
		
		readd();
	}
	
	private void readd() {
		header.setText(currentMonth + "/" + currentYear + "");
		
		calendar.setVisible(false);
		calendarPanel.remove(calendar);
		
		calendar = new Calendar(currentMonth, currentYear);
		calendarPanel.add(calendar);
	}
}
