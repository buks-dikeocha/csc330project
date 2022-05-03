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
	public JButton confirm, cancel;
	private JPanel options = new JPanel();
	private Host host;
	private AvailabilityWindow me;
	
	public AvailabilityWindow(Host h, String title, int width, int height) {
		super();
		
		host = h;
		me = this;
		
		options.setLayout(new GridLayout(10, 1));
		
		confirm = new JButton("Back");
		
		
		// pad the top
		options.add(new JLabel(""));
		
		ArrayList<Boolean> currentAv = Database.getAvailability(host.userID);
		for(int j = 0; j < 7; j++){				
			avs[j] = new JCheckBox(Days.values()[j].toString());
			avs[j].setSelected(currentAv.get(j));
			options.add(avs[j]);
		}
		
		options.add(new JLabel(""));
		options.add(confirm);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(me, "Make this your new availability?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
				if(confirmed == 0) {
					ArrayList<Boolean> newAv = new ArrayList<Boolean>();
					for(int j = 0; j < 7; j++) {
						newAv.add(avs[j].isSelected());
					}
					
					try {
						Database.setAvailability(host.userID, newAv);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
				} else if (confirmed == 1) {
					me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
				} else {}
			}
		});
		
		
		
		add(options);
		displaySelf(title, width, height);
	}
}