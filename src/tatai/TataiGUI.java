package tatai;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TataiGUI extends JFrame {
	
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel welcomeLabel;
	private JLabel descriptionLabel1;
	private JLabel descriptionLabel2;
	private JButton btnBeginner;
	private JButton btnAdvanced;
	private JButton btnHelp;
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TataiGUI frame = new TataiGUI();
				frame.setVisible(true);
			}
		});

	}
	
	public TataiGUI() {
		super("Tātai");
		setSize(450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		welcomeLabel = new JLabel("Welcome to Tātai!");
		welcomeLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		welcomeLabel.setVerticalAlignment(SwingConstants.TOP);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(119, 48, 216, 57);
		getContentPane().add(welcomeLabel);
		
		
		descriptionLabel1 = new JLabel("An interactive application which helps develop your ");
		descriptionLabel1.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel1.setBounds(12, 84, 426, 44);
		getContentPane().add(descriptionLabel1);
		
		
		descriptionLabel2 = new JLabel("Māori number pronounciation");
		descriptionLabel2.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel2.setBounds(102, 101, 250, 37);
		getContentPane().add(descriptionLabel2);
		
		
		btnBeginner = new JButton("Beginner");
		btnBeginner.setBounds(35, 174, 117, 25);
		btnBeginner.addActionListener(new BeginnerListener());
		getContentPane().add(btnBeginner);
		
		
		btnAdvanced = new JButton("Advanced");
		btnAdvanced.setBounds(299, 174, 117, 25);
		btnAdvanced.addActionListener(new AdvancedListener());
		getContentPane().add(btnAdvanced);
		
		
		btnHelp = new JButton("Help");
		btnHelp.setBounds(179, 174, 93, 25);
		btnHelp.addActionListener(new HelpListener());
		getContentPane().add(btnHelp);
		
	}
	
	public class HelpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			HelpPanel help = new HelpPanel();
			
		}
		
	}
	
	public class AdvancedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class BeginnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
