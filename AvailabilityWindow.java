package edu.cuny.csi.csc330.groupproject;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AvailabilityWindow extends Window {
	private final JCheckBox[][] avs = new JCheckBox[24][7];
	private final String hours[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
	public JButton confirm, cancel;
	private JPanel options = new JPanel();
	private JPanel confirmPanel = new JPanel();
	private Host host;
	private AvailabilityWindow me;
	
	public AvailabilityWindow(Host h, String title, int width, int height) {
		super();
		
		host = h;
		me = this;
		
		options.setLayout(new GridLayout(8, 25));
		confirmPanel.setLayout(new GridLayout(1, 2));
		
		confirm = new JButton("Back");
		confirmPanel.add(confirm);
		
		// make top
		options.add(new JLabel(""));
		for(int i = 0; i < 24; i++) {
			options.add(new JLabel(hours[i]));
		}
		
		for(int j = 0; j < 7; j++) {
			options.add(new JLabel(Days.values()[j].getShorthand()));
			
			for(int i = 0; i < 24; i++){				
				avs[i][j] = new JCheckBox("");
				avs[i][j].setPreferredSize(new Dimension(30, 30));
				options.add(avs[i][j]);
			}
		}
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(me, "sumn", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
				if(confirmed == 0) {
					ArrayList<ArrayList<Boolean>> av = Database.availabilityByHostID.get(h.userID);
					for(int i = 0; i < 24; i++) {
						for(int j = 0; j < 7; j++) {
							av.get(i).set(j, avs[i][j].isSelected());
						}
					}
					
					for(int i = 0; i < 24; i++) {
						for(int j = 0; j < 7; j++) {
							System.out.println(av.get(i).get(j));
						}
					}
					
					me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
				} else if (confirmed == 1) {
					me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
				} else {}
			}
		});
		
		
		
		add(options);
		add(confirmPanel);
		display(title, width, height);
	}
}

/*
POSSIBLE DO:
click label to fill whole row/column


*/