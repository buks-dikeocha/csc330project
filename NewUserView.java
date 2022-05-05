package edu.cuny.csi.csc330.groupproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NewUserView extends Window{
	private JLabel label;
	private JTextField idInput; 
	private JButton makeHost, makeAttendee, cancel;
	private NewUserView me;
	
	public NewUserView() {
		init();
	}
	
	private void init() {
		me = this;
		
		initVars();
		addButtonsListeners();
		displayAllComponents();
		displaySelf("New", 225, 200);
	}
	
	private void initVars() {
		label = new JLabel("Choose a User ID:");
		idInput = new JTextField(15);
		makeHost = new JButton("Host Account");
		makeAttendee = new JButton("Attendee Account");
		cancel = new JButton("Cancel");
	}
	
	private void addButtonsListeners() {
		makeHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeUser(AccountType.HOST);
			}
		});
		
		makeAttendee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeUser(AccountType.ATTENDEE);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignInView();
				me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	
	private void makeUser(AccountType acc) {
		try {
			if(acc == AccountType.HOST && !Database.hostsByID.containsKey(idInput.getText())) {
				Database.registerHost(idInput.getText());

			}
			else if(acc == AccountType.ATTENDEE && !Database.attendeesByID.containsKey(idInput.getText())) {
				Database.registerAttendee(idInput.getText());
			}
			
			else {
				JOptionPane.showMessageDialog(me, "This username is taken.", "Taken Username", JOptionPane.ERROR_MESSAGE);
			}
			
			new SignInView();
			me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
		}
		catch(Exception IOException) {}
	}
	
	private void displayAllComponents() {
		add(label);
		add(idInput);
		add(makeHost);
		add(makeAttendee);
		add(cancel);
	}
}
