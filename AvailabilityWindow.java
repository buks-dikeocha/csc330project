package edu.cuny.csi.csc330.groupproject;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AvailabilityWindow extends Window {
	private final JCheckBox[][] avs = new JCheckBox[24][7];
	private final String hours[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
	public JButton confirm, cancel;
	private JPanel options = new JPanel();
	private JPanel confirmPanel = new JPanel();
	
	public AvailabilityWindow(String title, int width, int height) {
		super();		
		
		options.setLayout(new GridLayout(8, 25));
		confirmPanel.setLayout(new GridLayout(1, 2));
		
		confirm = new JButton("Confirm");
		cancel = new JButton("Cancel");
//		confirm.setPreferredSize(new Dimension(30, 30));
//		cancel.setPreferredSize(new Dimension(30, 30));
//		confirmPanel.add(confirm);
//		confirmPanel.add(cancel);
		
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
		
		
		
		add(options);
		add(confirmPanel);
		display(title, width, height);
	}
}

/*
POSSIBLE DO:
click label to fill whole row/column


*/