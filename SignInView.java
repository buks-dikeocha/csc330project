package edu.cuny.csi.csc330.groupproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SignInView extends Window{
	private SignInView me;
	private JButton hostButton, attendeeButton, newUserButton;
	private JTextField userLogin;
	private JLabel loginLabel;
	
	public SignInView() {
		init();
	}
	
	private void init() {
		me = this;
		
		initVars();
		addLoginButtonsListeners();
		displayAllComponents();
		displaySelf("Sign In", 225, 200);
	}
	
	private void initVars() {
		loginLabel = new JLabel("User ID:");
		userLogin = new JTextField(15);
		hostButton = new JButton("Host Login");
		attendeeButton = new JButton("Attendee Login");
		newUserButton = new JButton("New User");
	}
	
	private void addLoginButtonsListeners() {
		hostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchUser(AccountType.HOST);
			}
		});
		
		attendeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchUser(AccountType.ATTENDEE);
			}
		});
		
		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewUserView();
				me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	
	private void launchUser(AccountType acc) {
		
		try {
			if(acc == AccountType.HOST) {
				Database.launchHost(userLogin.getText());
			}
			else {
				Database.launchAttendee(userLogin.getText());
			}
			
			me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
		}
		catch(Exception NullPointerException) {
			JOptionPane.showMessageDialog(me, "This user doesn't exist.", "Invalid Login", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}
	
	private void displayAllComponents() {
		add(loginLabel);
		add(userLogin);
		add(hostButton);
		add(attendeeButton);
		add(newUserButton);
	}
}
