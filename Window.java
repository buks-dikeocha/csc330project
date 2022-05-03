package edu.cuny.csi.csc330.groupproject;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{
	LayoutManager layout;
	
	public Window() {
		this.layout = new FlowLayout();
	}
	
	public Window(LayoutManager layout) {
		this.layout = layout;
	}
	
	public void displaySelf() {
		init("New Window", 300, 200);
	}
	
	public void displaySelf(String title) {
		init(title, 300, 200);
	}
	
	public void displaySelf(int width, int height) {
		init("New Window", width, height);
	}
	
	public void displaySelf(String title, int width, int height) {
		init(title, width, height);
	}
	
	private void init(String title, int width, int height) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(layout);
		setSize(width, height);
		setTitle(title);
		setVisible(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getSize().width/2, dim.height/2 - getSize().height/2);
	}
}
