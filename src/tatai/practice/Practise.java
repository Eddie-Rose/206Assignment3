package tatai.practice;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tatai.main.Level;
import tatai.main.MainGUI;
import tatai.main.Resizable;


/**
 * 
 * This class sets up the menu of the practice option
 * This frame provides the  option to choose from 
 * the advanced or the beginner level.
 * 
 * @author Edwin Roesli and Harpreet Singh
 *
 */
public class Practise extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel lblPractise;
	private JLabel lblPractiseIntro;
	private JButton btnBeginner;
	private JButton btnAdvanced;
	private JButton btnMenu;
	private int frameWidth = 1100;
	private int frameHeight = 700;
	
	public Practise() {
		
		
		//Sets the main components in this frame
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, frameWidth, frameHeight);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		//Sets the main practice label 
		lblPractise = new JLabel("Practice");
		lblPractise.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		getContentPane().add(lblPractise);
		
		
		//Sets the frames description
		lblPractiseIntro = new JLabel("<html>Welcome to the Practice menu,\nHere you can practice your māori number pronounciation. \nSelect a level to start.</html>");
		lblPractiseIntro.setFont(new Font("Dialog", Font.PLAIN, 20));
		getContentPane().add(lblPractiseIntro);
		
		
		//Sets the beginner button and listener
		btnBeginner = new JButton("Beginner");
		btnBeginner.addActionListener(new BeginnerListener());
		getContentPane().add(btnBeginner);
		
		
		//Sets the advanced button and listener
		btnAdvanced = new JButton("Advanced");
		btnAdvanced.addActionListener(new AdvancedListener());
		getContentPane().add(btnAdvanced);
		
		
		//Sets the menu button and listener
		btnMenu = new JButton("Main Menu");
		btnMenu.addActionListener(new MenuListener());
		getContentPane().add(btnMenu);
		
		
		//Sets the bounds for all the components
		lblPractise.setBounds(384, 60, 289, 140);
		lblPractiseIntro.setBounds(220 , 140, 680, 279);
		btnBeginner.setBounds(147, 400, 281, 76);
		btnAdvanced.setBounds(625, 400, 275, 76);
		btnMenu.setBounds(384, 516, 289, 76);
		
		
		//Sets the resizable class and stores all the components in the Resizable array
		Resizable[] resizableComp = new Resizable[5];
		
		resizableComp[0] = new Resizable(lblPractise, frameWidth, frameHeight);
		resizableComp[1] = new Resizable(lblPractiseIntro, frameWidth, frameHeight);
		resizableComp[2] = new Resizable(btnBeginner, frameWidth, frameHeight);
		resizableComp[3] = new Resizable(btnAdvanced, frameWidth, frameHeight);
		resizableComp[4] = new Resizable(btnMenu, frameWidth, frameHeight);
		
		
		//This is called when the frame resizes
		//This will resize the components in relation to the frame
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				
				//Loops through all the components and resizes them
				for(Resizable comp : resizableComp) {
					comp.Resize(c.getWidth(), c.getHeight());
				}
				
			}
		});
		
		
		
		
		
	}
	
	
	//Sets the beginner listener, initialises the beginner level when called
	public class BeginnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			@SuppressWarnings("unused")
			Level beginnerWindow = new Beginner("Beginner", 1, 9);
			
		}
		
	}
	
	
	//Sets the advanced listener, initialises the advanced level when called
	public class AdvancedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			@SuppressWarnings("unused")
			Level advancedWindow = new Advanced("Advanced", 1, 99);
			
		}
		
	}
	
	//Sets the menu listener, initialised the menu gui when called
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new MainGUI();
			dispose();
			
			
		}
		
	}
	
		
}
