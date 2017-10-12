package tatai;

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

public class Practise extends JFrame {

	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel lblPractise;
	private JLabel lblPractiseIntro;
	private JButton btnBeginner;
	private JButton btnAdvanced;
	private JButton btnMenu;
	
	public Practise() {
		
		
		
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblPractise = new JLabel("Practice");
		lblPractise.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		getContentPane().add(lblPractise);
		
		lblPractiseIntro = new JLabel("<html>Welcome to the Practice menu,\nHere you can practice your māori number pronounciation. \nSelect a level to start.</html>");
		lblPractiseIntro.setFont(new Font("Dialog", Font.PLAIN, 14));
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
		
		
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				
				
			//	System.out.println(""+c.getWidth()+c.getHeight());
				lblPractise.setBounds(c.getWidth()/8 *3, c.getHeight()/15, c.getWidth()/16*10, c.getHeight()/5);
				lblPractiseIntro.setBounds(c.getWidth()/5 , c.getHeight()/10 * 2, c.getWidth()/16*10, c.getHeight()/2);
				btnBeginner.setBounds(c.getWidth()/5, c.getHeight()/17*11, c.getWidth()/4, c.getHeight()/12);
				btnAdvanced.setBounds(c.getWidth()/15*8, c.getHeight()/17*11, c.getWidth()/4, c.getHeight()/12);
				btnMenu.setBounds(c.getWidth()/13*5, c.getHeight()/115*114, c.getWidth()/4, c.getHeight()/10);
				
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
			dispose();
			
			
		}
		
	}
	
		
}
