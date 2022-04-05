package edu.cuny.csi.csc330.groupproject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class HostView extends Window {
	private JButton editAvailability;	
	
	public HostView(String title, int width, int height) {
		super(new FlowLayout());
		
		editAvailability = new JButton("Edit My Availability");
		Calendar cal = new Calendar(4, 2022);
		Calendar cal1 = new Calendar(5, 2022);
		
		add(editAvailability);
		
		add(cal);

		
		
		editAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvailabilityWindow av = new AvailabilityWindow("Host Login", 500, 500);
			}
		});
		
		display(title, width, height);

		// bit of fun lol
//		for(int i = 1; i <= 12; i++)
//		{
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			cal.removeAll();
//			cal.redraw(i, 2022);
//			
//			SwingUtilities.updateComponentTreeUI(this);
//		}
	}
}
