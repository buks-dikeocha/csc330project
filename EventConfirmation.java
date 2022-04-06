package edu.cuny.csi.csc330.groupproject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class EventConfirmation extends Window {
	private JLabel label;
	private JButton confirm, cancel;
	
	public EventConfirmation() {
		super(new FlowLayout());
		
		label = new JLabel("Go");
		
		add(label);
		add(confirm);
		add(cancel);


//		search.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		
		
		
		display();
	}
}
