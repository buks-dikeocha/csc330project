package edu.cuny.csi.csc330.groupproject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AttendeeView extends Window {
	private JButton search;	
	private JTextField searchbar;
	private String hostSearch;
	
	public AttendeeView(String title, int width, int height) {
		super(new FlowLayout());
		
		search = new JButton("Go");
		searchbar = new JTextField(20);
		Calendar cal = new Calendar(4, 2022);
		
		add(searchbar);
		add(search);
		
		add(cal);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hostSearch = searchbar.getText();
				System.out.println(hostSearch);
				
				
				// lookup host times from id
				
				// display host times
			}
		});
		
		
		
		display(title, width, height);
	}
}
