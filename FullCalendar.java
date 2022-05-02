package edu.cuny.csi.csc330.groupproject;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FullCalendar extends JPanel {

	private int currentMonth, currentYear;
	boolean calReservesEvent;
	private Calendar calendar;
	private JLabel header;
	private JPanel calendarInfoGrid, calendarGridRow, calendarPanel;
	private JButton previousMonth, nextMonth;
	private String calendarOf;
	
	public FullCalendar(int month, int year, boolean reservesEvent, String hostID) {
		setLayout(new GridLayout(2, 1));
		currentMonth = month;
		currentYear = year;
		calReservesEvent = reservesEvent;
		calendarOf = hostID;
		
		header = new JLabel();
		previousMonth = new JButton("<");
		nextMonth = new JButton(">");
		calendarInfoGrid = new JPanel();
		calendarGridRow = new JPanel();
		calendarPanel = new JPanel();
		calendar = new Calendar(currentMonth, currentYear, reservesEvent, calendarOf);
		
		calendarInfoGrid.setLayout(new GridBagLayout());
		calendarGridRow.setLayout(new GridBagLayout());
		
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
		
		header.setText(LocalDate.of(year, month, 1).getMonth() + " " + currentYear);
		header.setHorizontalAlignment(JLabel.CENTER);
		
		calendarInfoGrid.add(previousMonth);
		calendarInfoGrid.add(header);
		calendarInfoGrid.add(nextMonth);
		
		calendarGridRow.add(new JLabel(""));
		calendarGridRow.add(calendarPanel);
		calendarGridRow.add(new JLabel(""));
		calendarPanel.add(calendar);
		
		add(calendarInfoGrid);
		add(calendarGridRow);
	}
	
	private void displayPreviousMonth() {
		currentMonth--; // might be a better way using java's Calendar class
		if(currentMonth < 1) {
			currentMonth = 12;
			currentYear--;
		}
		
		reAdd();
	}
	
	private void displayNextMonth() {
		currentMonth++;
		if(currentMonth > 12) {
			currentMonth = 1;
			currentYear++;
		}
		
		reAdd();
	}
	
	private void reAdd() {
		header.setText(LocalDate.of(currentYear, currentMonth, 1).getMonth() + " " + currentYear);
		
		calendar.setVisible(false);
		calendarPanel.remove(calendar);
		
		calendar = new Calendar(currentMonth, currentYear, calReservesEvent, calendarOf);
		calendarPanel.add(calendar);
	}
}
