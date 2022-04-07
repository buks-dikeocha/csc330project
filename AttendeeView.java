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
	private FullCalendar cal;
	private List<JButton> times;
	
	private int eventConfirmed;
	private EventConfirmation confirmation;
	
	private int month, year;
	
	public AttendeeView(String title, int width, int height) {
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
		results = new JPanel(new GridLayout(12, 3));
		
		
		add(clear);
		add(searchbar);
		add(search);
		
		add(cal);
		add(results);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hostSearch = searchbar.getText();
				System.out.println(hostSearch);
				
				// lookup host times from id
				
				// display host times
				// idea: use a calendar, when a day is clicked do a pop up wind that shows available times
				for(int i = 0; i < 36; i++) {
					times.add(new JButton("applesauce"));
					results.add(times.get(i));
					
					times.get(i).addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							confirmation.setDetails("jjj", "ddd", "ttt");
							eventConfirmed = JOptionPane.showConfirmDialog(mainWindow, confirmation, "Confirm", JOptionPane.YES_NO_OPTION);
							
							if(eventConfirmed == 0) {
								System.out.println("yee boii");
								// schedule event
							}
						}
					});
				}
				
				setToReultsView();
				results.add(new JLabel("No hosts by that id."));
			}
		});
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setToCalendarView();
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
