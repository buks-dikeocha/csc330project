package edu.cuny.csi.csc330.groupproject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AvailabilityWindow extends Window {
	private final JCheckBox[] avs = new JCheckBox[7];
	private JButton confirm;
	private JPanel options = new JPanel();
	private Host host;
	private AvailabilityWindow me;
	
	public AvailabilityWindow(Host h) {
		super();
		init(h);
	}
	
	
	private void init(Host h) {
		host = h;
		me = this;
		
		initVars();
		displayAvailability();
		addSaveButtonListener();
		displayAllComponents();
		displaySelf("Edit Availability", 300, 340);
	}
	
	
	private void initVars() {
		options.setLayout(new GridLayout(10, 1));
		confirm = new JButton("Save");
	}
	
	private void displayAvailability() {
		// pad the top
		options.add(new JLabel(""));
		
		ArrayList<Boolean> currentAv = Database.getAvailability(host.getUserID());
		for(int j = 0; j < 7; j++){				
			avs[j] = new JCheckBox(Days.values()[j].toString());
			avs[j].setSelected(currentAv.get(j));
			options.add(avs[j]);
		}
		
		// pad the bottom
		options.add(new JLabel(""));
	}
	
	private void addSaveButtonListener() {
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(me, "Make this your new availability?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
				if(confirmed == 0) {
					ArrayList<Boolean> newAv = new ArrayList<Boolean>();
					for(int j = 0; j < 7; j++) {
						newAv.add(avs[j].isSelected());
					}
					
					try {
						Database.setAvailability(host.getUserID(), newAv);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
				} else if (confirmed == 1) {
					me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
				} else {}
			}
		});
	}
	
	private void displayAllComponents() {
		options.add(confirm);
		add(options);
	}
}