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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TataiGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel welcomeLabel;
	private JLabel descriptionLabel1;
	private JButton btnPractise;
	private JButton btnMath;
	private JButton btnHelp;
	private JButton btnStatistics;
	private JButton btnQuit;
	
	
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
		setSize(700, 500);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		
		try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./back.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
	
		
		welcomeLabel = new JLabel("<html>Welcome to Tātai!</html>");
		welcomeLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 40));
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setVerticalAlignment(SwingConstants.TOP);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(80, 48, 280, 57);
		welcomeLabel.setBackground(Color.WHITE);
		getContentPane().add(welcomeLabel);
		
		
		descriptionLabel1 = new JLabel("<html>An interactive application which helps develop your Māori number pronounciation </html>");
		descriptionLabel1.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		descriptionLabel1.setForeground(Color.WHITE);
		descriptionLabel1.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(descriptionLabel1);
		
		
		btnPractise = new JButton("Practise");
		//btnBeginner.setBounds(35, 174, 117, 25);	
		btnPractise.addActionListener(new PractiseListener());
		getContentPane().add(btnPractise);
		
		
		btnQuit = new JButton ("Quit");
		btnQuit.addActionListener(new QuitListener());
		getContentPane().add(btnQuit);
		
		
		btnMath = new JButton("Math");
		//btnAdvanced.setBounds(299, 174, 117, 25);
		btnMath.addActionListener(new MathListener());
		getContentPane().add(btnMath);
		
		btnHelp = new JButton("Help");
		
		//btnHelp.setBounds(550, 400, 90, 50);
		btnHelp.addActionListener(new HelpListener());
		getContentPane().add(btnHelp);
//		btnHelp.setContentAreaFilled(false);
//		btnHelp.setFocusPainted(false);
//		btnHelp.setBorderPainted(false);
		btnHelp.setIcon(new ImageIcon("/home/hpt09/Documents/softeng206/206Assignment3/rsz_help-icon.png"));
		
		
		btnStatistics = new JButton("Statistics");
		//btnStatistics.setBounds(172, 174, 110, 25);
		btnStatistics.addActionListener(new StatisticsListener());
		getContentPane().add(btnStatistics);
		
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				btnPractise.setBounds(c.getWidth()/13, c.getHeight()/17*10, c.getWidth()/4, c.getHeight()/12);
				btnStatistics.setBounds(c.getWidth()/26*10, c.getHeight()/17*10, c.getWidth()/4, c.getHeight()/12);
				btnHelp.setBounds(c.getWidth()/13*10, c.getHeight()/115*100, c.getWidth()/8, c.getHeight()/10);
				btnMath.setBounds(c.getWidth()/15*10, c.getHeight()/17*10, c.getWidth()/4, c.getHeight()/12);
				welcomeLabel.setBounds(c.getWidth()/56*10, c.getHeight()/6, c.getWidth()/16*10, c.getHeight()/5);
				descriptionLabel1.setBounds(c.getWidth()/15, c.getHeight()/3, c.getWidth()/105*100, c.getHeight()/7);
				btnQuit.setBounds(c.getWidth()/13*8, c.getHeight()/115*100, c.getWidth()/8, c.getHeight()/10);
			//	System.out.println(""+c.getWidth()+c.getHeight());
				
			}
		});
		
	}
	
	public class HelpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			HelpPanel help = new HelpPanel();
			
		}
		
	}
	
	public class MathListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Level advancedWindow = new Advanced("Advanced", 1, 99);
			
		}
		
	}
	
	public class PractiseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Practise practiseWindow = new Practise();
			
		}
	}
		
	public class StatisticsListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				StatsPanel help = new StatsPanel();
				
			}
		
	}
	
	public class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to Quit?","Quit", JOptionPane.YES_NO_OPTION);
			
			
			
			if (YesOrNo == 1) {
				return;
			}
			
			else if (YesOrNo == 0)  {
				dispose();
			}
			
			else {
				return;
			}
			
		}
	
}
	
	

}
