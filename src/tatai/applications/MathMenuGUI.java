package tatai.applications;

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
import tatai.math.AdvancedMath;
import tatai.math.BeginnerMath;
import tatai.math.MediumMath;
import tatai.math.ViewCustomSet;

/**
 * 
 * This class sets up the Main math menu of the 
 * application, in this frame the user can choose to
 * do the beginner, medium or advanced level or choose
 * the custom menu
 * 
 * @author Edwin Roesli and Harpreet Singh
 *
 */

public class MathMenuGUI extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel lblMath;
	private JLabel lblMathIntro;
	private JButton btnBeginner;
	private JButton btnAdvanced;
	private JButton btnMenu;
	private JButton btnMedium;
	private JButton btnCustom;
	private int frameWidth =1100;
	private int frameHeight= 700;

	
	//Sets up the main Math menu's main components
	public MathMenuGUI() {
		
		//Sets the properties of the main frame
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, frameWidth, frameHeight);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		//Sets the main label
		lblMath = new JLabel("Math");
		lblMath.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		getContentPane().add(lblMath);
		
		
		//Sets the main math introduction with a description
		lblMathIntro = new JLabel("<html>Welcome to the Math menu,\nHere you can practice your mathematical skills. \nSelect a level to start.</html>");
		lblMathIntro.setFont(new Font("Dialog", Font.PLAIN, 17));
		getContentPane().add(lblMathIntro);
		
		
		//Sets beginner button
		btnBeginner = new JButton("Beginner");
		btnBeginner.addActionListener(new BeginnerListener());
		getContentPane().add(btnBeginner);
		
		
		//Sets the advanced button
		btnAdvanced = new JButton("Advanced");
		btnAdvanced.addActionListener(new AdvancedListener());
		getContentPane().add(btnAdvanced);
		
		
		//Sets the medium button
		btnMedium = new JButton("Medium");
		btnMedium.setBounds(594, 325, 275, 75);
		btnMedium.addActionListener(new MediumListener());
		getContentPane().add(btnMedium);
		
		
		//Sets the custom button
		btnCustom = new JButton("Custom");
		btnCustom.addActionListener(new CustomListener());
		btnCustom.setVisible(true);
		getContentPane().add(btnCustom);
		
		
		//Sets the main menu button
		btnMenu = new JButton("Main Menu");
		btnMenu.addActionListener(new MenuListener());
		getContentPane().add(btnMenu);
		
		
		//Sets all the bounds for the components
		lblMath.setBounds(418, 69, 162, 118);
		lblMathIntro.setBounds(121 , 185 , 842, 140);
		btnBeginner.setBounds(220, 325, 275, 75);
		btnMedium.setBounds(594, 325, 275, 75);
		btnAdvanced.setBounds(220, 437, 275, 75);
		btnCustom.setBounds(594, 437, 294, 75);
		btnMenu.setBounds(418, 548, 275, 75);
		
		
		//Sets the resizable class and stores all the components in the Resizable array
		Resizable[] resizableComp = new Resizable[7];
		
		resizableComp[0] = new Resizable(lblMath, frameWidth, frameHeight);
		resizableComp[1] = new Resizable(lblMathIntro, frameWidth, frameHeight);
		resizableComp[2] = new Resizable(btnBeginner, frameWidth, frameHeight);
		resizableComp[3] = new Resizable(btnMedium, frameWidth, frameHeight);
		resizableComp[4] = new Resizable(btnAdvanced, frameWidth, frameHeight);
		resizableComp[5] = new Resizable(btnCustom, frameWidth, frameHeight);
		resizableComp[6] = new Resizable(btnMenu, frameWidth, frameHeight);
		
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
	
	//Menu listener, initialises the menu frame
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new MainGUI();
			dispose();
			
			
			
		}
		
	}
	
	//Beginner listener, initialises the beginner frame
	private class BeginnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			@SuppressWarnings("unused")
			Level beginnerMathWindow = new BeginnerMath("Beginner", 0 , 0);
			
			
		}
		
	}
	
	//MediumListener, initialises the medium frame
	private class MediumListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			@SuppressWarnings("unused")
			Level mediumMathWindow = new MediumMath("Medium", 0, 0);
			
			
		}
		
	}
	
	//AdvancedListener, initialises the advanced frame
	private class AdvancedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			@SuppressWarnings("unused")
			Level advancedWindow = new AdvancedMath("Advanced", 0, 0);
			
			
		}
		
	}
	
	//CustomListener, initialises the custom frame
	private class CustomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ViewCustomSet.getInstance();
			
		}
		
	}
	
	
		
}
	
		

