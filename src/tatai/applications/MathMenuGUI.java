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
import tatai.math.AdvancedMath;
import tatai.math.BeginnerMath;
import tatai.math.MediumMath;



public class MathMenuGUI extends JFrame {
	
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel lblMath;
	private JLabel lblMathIntro;
	private JButton btnBeginner;
	private JButton btnAdvanced;
	private JButton btnMenu;
	private JButton btnMedium;
	private JButton btnContinue;
	
	

	

	public MathMenuGUI() {
		
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblMath = new JLabel("Math");
		lblMath.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		getContentPane().add(lblMath);
		
		lblMathIntro = new JLabel("<html>Welcome to the Math menu,\nHere you can practice your mathematical skills. \nSelect a level to start.</html>");
		lblMathIntro.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblMathIntro);
		
		btnBeginner = new JButton("Beginner");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnBeginner.addActionListener(new BeginnerListener());
		getContentPane().add(btnBeginner);
		
		btnAdvanced = new JButton("Advanced");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnAdvanced.addActionListener(new AdvancedListener());
		getContentPane().add(btnAdvanced);
		
		btnMedium = new JButton("Medium");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnMedium.addActionListener(new MediumListener());
		getContentPane().add(btnMedium);
		
		btnContinue = new JButton("Continue");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnContinue.addActionListener(new ContinueListener());
		btnContinue.setVisible(false);
		getContentPane().add(btnContinue);
		
		
		btnMenu = new JButton("Main Menu");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnMenu.addActionListener(new MenuListener());
		getContentPane().add(btnMenu);
		
	
		
		
		
		
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				
				
			//	System.out.println(""+c.getWidth()+c.getHeight());
				lblMath.setBounds(c.getWidth()/8 *3, c.getHeight()/15, c.getWidth()/16*10, c.getHeight()/5);
				lblMathIntro.setBounds(c.getWidth()/5 , c.getHeight()/10 , c.getWidth()/16*10, c.getHeight()/2);
				btnBeginner.setBounds(c.getWidth()/5, c.getHeight()/17*9, c.getWidth()/4, c.getHeight()/12);
				btnMedium.setBounds(c.getWidth()/15*8, c.getHeight()/17*9, c.getWidth()/4, c.getHeight()/12);
				btnAdvanced.setBounds(c.getWidth()/5, c.getHeight()/17*11, c.getWidth()/4, c.getHeight()/12);
				btnContinue.setBounds(c.getWidth()/15*8, c.getHeight()/17*11, c.getWidth()/4, c.getHeight()/12);
				
				


				btnMenu.setBounds(c.getWidth()/13*5, c.getHeight()/115*114, c.getWidth()/4, c.getHeight()/10);
				
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
	
	private class ContinueListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
			
		}
		
	}
	
	
		
}
	
		

