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

public class Practise extends JFrame {

	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel lblPractise;
	private JLabel lblPractiseIntro;
	private JButton btnBeginner;
	private JButton btnAdvanced;
	private JButton btnMenu;
	private int frameWidth = 1100;
	private int frameHeight = 700;
	
	public Practise() {
		
		
		
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, frameWidth, frameHeight);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblPractise = new JLabel("Practice");
		lblPractise.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		getContentPane().add(lblPractise);
		
		lblPractiseIntro = new JLabel("<html>Welcome to the Practice menu,\nHere you can practice your māori number pronounciation. \nSelect a level to start.</html>");
		lblPractiseIntro.setFont(new Font("Dialog", Font.PLAIN, 20));
		getContentPane().add(lblPractiseIntro);
		
		btnBeginner = new JButton("Beginner");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnBeginner.addActionListener(new BeginnerListener());
		getContentPane().add(btnBeginner);
		
		btnAdvanced = new JButton("Advanced");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnAdvanced.addActionListener(new AdvancedListener());
		getContentPane().add(btnAdvanced);
		
		
		btnMenu = new JButton("Main Menu");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnMenu.addActionListener(new MenuListener());
		getContentPane().add(btnMenu);
		
		lblPractise.setBounds(384, 60, 289, 140);
		lblPractiseIntro.setBounds(220 , 140, 680, 279);
		btnBeginner.setBounds(147, 400, 281, 76);
		btnAdvanced.setBounds(625, 400, 275, 76);
		btnMenu.setBounds(384, 516, 289, 76);
		
		
		Resizable[] resizableComp = new Resizable[5];
		
		resizableComp[0] = new Resizable(lblPractise, frameWidth, frameHeight);
		resizableComp[1] = new Resizable(lblPractiseIntro, frameWidth, frameHeight);
		resizableComp[2] = new Resizable(btnBeginner, frameWidth, frameHeight);
		resizableComp[3] = new Resizable(btnAdvanced, frameWidth, frameHeight);
		resizableComp[4] = new Resizable(btnMenu, frameWidth, frameHeight);
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				for(Resizable comp : resizableComp) {
					comp.Resize(c.getWidth(), c.getHeight());
				}
				
			}
		});
		
		
		
		
		
	}
	
	
	public class BeginnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			Level beginnerWindow = new Beginner("Beginner", 1, 9);
			
		}
		
	}
	
	
	public class AdvancedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			Level advancedWindow = new Advanced("Advanced", 1, 99);
			
		}
		
	}
	
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new MainGUI();
			dispose();
			
			
		}
		
	}
	
		
}
