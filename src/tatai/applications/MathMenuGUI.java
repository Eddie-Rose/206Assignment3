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
import javax.swing.JTextField;

import tatai.main.Level;
import tatai.main.Resizable;
import tatai.math.AdvancedMath;
import tatai.math.BeginnerMath;
import tatai.math.MediumMath;
import tatai.math.ViewCustomSet;



public class MathMenuGUI extends JFrame {
	
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel lblMath;
	private JLabel lblMathIntro;
	private JButton btnBeginner;
	private JButton btnAdvanced;
	private JButton btnMenu;
	private JButton btnMedium;
	private JButton btnCustom;
	private JButton btnContinue;
	private String name;
	private int frameWidth =1100;
	private int frameHeight= 700;

	

	public MathMenuGUI() {
				
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, frameWidth, frameHeight);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblMath = new JLabel("Math");
		lblMath.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		getContentPane().add(lblMath);
		
		lblMathIntro = new JLabel("<html>Welcome to the Math menu,\nHere you can practice your mathematical skills. \nSelect a level to start.</html>");
		lblMathIntro.setFont(new Font("Dialog", Font.PLAIN, 17));
		getContentPane().add(lblMathIntro);
		
		btnBeginner = new JButton("Beginner");
		btnBeginner.addActionListener(new BeginnerListener());
		getContentPane().add(btnBeginner);
		
		btnAdvanced = new JButton("Advanced");
		btnAdvanced.addActionListener(new AdvancedListener());
		getContentPane().add(btnAdvanced);
		
		btnMedium = new JButton("Medium");
		btnMedium.setBounds(594, 325, 275, 75);
		btnMedium.addActionListener(new MediumListener());
		getContentPane().add(btnMedium);
		
		btnCustom = new JButton("Custom");
		btnCustom.addActionListener(new CustomListener());
		btnCustom.setVisible(true);
		getContentPane().add(btnCustom);
		
		
		btnMenu = new JButton("Main Menu");
		btnMenu.addActionListener(new MenuListener());
		getContentPane().add(btnMenu);
		
		lblMath.setBounds(418, 69, 162, 118);
		lblMathIntro.setBounds(121 , 185 , 842, 140);
		btnBeginner.setBounds(220, 325, 275, 75);
		btnMedium.setBounds(594, 325, 275, 75);
		btnAdvanced.setBounds(220, 437, 275, 75);
		btnCustom.setBounds(594, 437, 294, 75);
		btnMenu.setBounds(418, 548, 275, 75);
		
		Resizable[] resizableComp = new Resizable[7];
		
		resizableComp[0] = new Resizable(lblMath, frameWidth, frameHeight);
		resizableComp[1] = new Resizable(lblMathIntro, frameWidth, frameHeight);
		resizableComp[2] = new Resizable(btnBeginner, frameWidth, frameHeight);
		resizableComp[3] = new Resizable(btnMedium, frameWidth, frameHeight);
		resizableComp[4] = new Resizable(btnAdvanced, frameWidth, frameHeight);
		resizableComp[5] = new Resizable(btnCustom, frameWidth, frameHeight);
		resizableComp[6] = new Resizable(btnMenu, frameWidth, frameHeight);
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				for(Resizable comp : resizableComp) {
					comp.Resize(c.getWidth(), c.getHeight());
				}
			}
		});
		
		
		
	}
	
	
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
			
			
		}
		
	}
	
	private class BeginnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			Level beginnerMathWindow = new BeginnerMath("Beginner", 0 , 0);
			
			
		}
		
	}
	
	private class MediumListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			Level mediumMathWindow = new MediumMath("Medium", 0, 0);
			
			
		}
		
	}
	
	private class AdvancedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			Level advancedWindow = new AdvancedMath("Advanced", 0, 0);
			
			
		}
		
	}
	
	private class CustomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ViewCustomSet.getInstance();
			
		}
		
	}
	
	
		
}
	
		

