package edu.cuny.csi.csc330.groupproject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class HostView extends Window {
	private int startDay;
	private int month, year;
//	private int showingMonth, showingYear; // if we need it
	private JButton editAvailability;
	
	public HostView(String title, int width, int height) {
		super(new FlowLayout());
		
		
		LocalDate today = LocalDate.now();
		year = today.getYear();
		month = today.getMonthValue();
		
		startDay = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
		
		
		editAvailability = new JButton("Edit My Availability");
		FullCalendar cal = new FullCalendar(month, year);
		
		add(editAvailability);
		
		add(cal);
		
		editAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvailabilityWindow av = new AvailabilityWindow("Edit Availability", 800, 300);
			}
		});
		
		display(title, width, height);

		// bit of fun lol
//		for(int i = 1; i <= 12; i++)
//		{
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			cal.removeAll();
//			cal.redraw(i, 2022);
//			
//			SwingUtilities.updateComponentTreeUI(this);
//		}
	}
}
