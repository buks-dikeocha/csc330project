package edu.cuny.csi.csc330.groupproject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class HostView extends Window {
	private int startDay;
	private int month, year;
	private JButton editAvailability;
	private Host host;
	public HostView(Host h, String title, int width, int height) {
		super(new FlowLayout());
		
		
		LocalDate today = LocalDate.now();
		year = today.getYear();
		month = today.getMonthValue();
		
		startDay = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
		
		
		editAvailability = new JButton("Edit My Availability");
		FullCalendar cal = new FullCalendar(month, year);
		
		add(editAvailability);
		
		add(cal);
		
		host = h;
		
		editAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AvailabilityWindow(host, "Edit Availability", 800, 350);
			}
		});
		
		display(title, width, height);
	}
}
