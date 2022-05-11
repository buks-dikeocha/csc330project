package edu.cuny.csi.csc330.groupproject;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{
	private LayoutManager layout;
	
	/**
	 * Create a new window. Default layout is java.awt.FlowLayout.
	 */
	public Window() {
		this.layout = new FlowLayout();
	}
	
	/**
	 * Create a new window. Default layout is java.awt.FlowLayout.
	 * layout argument can be any Layout defined in javax.awt
	 * 
	 * @param layout
	 */
	public Window(LayoutManager layout) {
		this.layout = layout;
	}
	
	/**
	 * Display window. Default title is "New Window". Default width and height is 300x200.
	 */
	public void displaySelf() {
		init("New Window", 300, 200);
	}
	
	
	/**
	 * Display window. Title of window is set to title. Width and height defaults to 300x200.
	 * 
	 * @param title
	 */
	public void displaySelf(String title) {
		init(title, 300, 200);
	}
	
	/**
	 * Display window. Default title is "New Window". Width and height is set to input.
	 * 
	 * @param width
	 * @param height
	 */
	public void displaySelf(int width, int height) {
		init("New Window", width, height);
	}
	
	/**
	 * Display window. Set window title and dimensions.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public void displaySelf(String title, int width, int height) {
		init(title, width, height);
	}
	
	private void init(String title, int width, int height) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(layout);
		setSize(width, height);
		setTitle(title);
		setVisible(true);
		setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getSize().width/2, dim.height/2 - getSize().height/2);
	}
}
