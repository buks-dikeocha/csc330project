package edu.cuny.csi.csc330.groupproject;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class SignInView extends Window{
	private JButton hostButton;
	private JButton userButton; 
	private JLabel question;
	
	public SignInView(String title, int width, int height) {
		
		
		question = new JLabel();
		question.setText("Are you the host or user?");
		
		add(question);
		
		hostButton = new JButton("Host");
		
		add(hostButton);
			
		hostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.launchHost("snuff");
			}
		});
		
		userButton = new JButton("User");
		add(userButton);
		
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.launchAttendee("a1");
			}
		});
		
		displaySelf(title, width, height);
	}
}
