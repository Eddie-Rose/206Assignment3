package tatai.main;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import tatai.applications.HelpPanel;
import tatai.applications.MathMenuGUI;
import tatai.applications.StatsPanel;
import tatai.practice.Practise;

/**
 * 
 * Initialises the main GUI with 5 main applications:
 * Practice where you go to practice pronouncing Maori numbers
 * Math where you choose a level depending on your mathematical skill level
 * Help which is just a helper function which guides you through the GUI
 * Statistics which goes through your history and outputs your highest score
 * And a User login for different users
 * 
 *  Button listeners are written here as well.
 *
 */

public class MainGUI extends JFrame {
	
	/**
	 * Initialises all the field values for all the labels/ buttons in the main GUI
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
	
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JLabel lblUsername;
	private JLabel lblPassWord;
	private JButton btnLogIn;
	private JButton btnSignUp;
	
	private JLabel lblUsernameWelcome;
	private JButton btnDeleteUser;
	private JButton btnLogOut;
	
	
	private String username = "anonymos";
	
	/**
	 * Initialises the main application
	 * Calls for the Main GUI
	 * 
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainGUI frame = new MainGUI();
				frame.setVisible(true);
			}
		});

	}
	
	
	/**
	 * Constructor for the main GUI, sets all the frame/ buttons/ labels 
	 * Resizeable so full screen mode is allowed.
	 */
	public MainGUI() {
		super("Tātai");
		setSize(1100, 700);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		//Sets the background image for this GUI
		
		try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./back.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
	
		
		//Sets the welcoming label and all the details
		welcomeLabel = new JLabel("<html>Welcome to Tātai!</html>");
		welcomeLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 40));
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setVerticalAlignment(SwingConstants.TOP);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(80, 48, 280, 57);
		welcomeLabel.setBackground(Color.WHITE);
		getContentPane().add(welcomeLabel);
		
		
		//Sets the welcoming description label and all its details
		descriptionLabel1 = new JLabel("<html>An interactive application which helps develop your Māori number pronounciation </html>");
		descriptionLabel1.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		descriptionLabel1.setForeground(Color.WHITE);
		descriptionLabel1.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(descriptionLabel1);
		
		
		
		//Sets all the buttons and adds its listeners 
		btnPractise = new JButton("Practice");
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
		
		
		//Sets all the userName sets like UsernameTextField PasswordTextField
		//login and signup buttons
		
		usernameInput = new JTextField();
		getContentPane().add(usernameInput);
		
		passwordInput = new JPasswordField();
		getContentPane().add(passwordInput);
		
		btnLogIn = new JButton("LogIn");
		btnLogIn.addActionListener(new LogInListener());
		getContentPane().add(btnLogIn);
		
		btnSignUp = new JButton("SignUp");
		btnSignUp.addActionListener(new SignUpListener());
		getContentPane().add(btnSignUp);
		
		lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		getContentPane().add(lblUsername);
		
		lblPassWord = new JLabel("Password");
		lblPassWord.setForeground(Color.WHITE);
		getContentPane().add(lblPassWord);
		
		
		//These labels/buttons are visible when logging in.
		btnDeleteUser = new JButton ("Delete User");
		btnDeleteUser.addActionListener(new DeleteUserListener());
		getContentPane().add(btnDeleteUser);
		btnDeleteUser.setVisible(false);
		
		btnLogOut = new JButton("Logout");
		btnLogOut.addActionListener(new LogOutListener());
		getContentPane().add(btnLogOut);
		btnLogOut.setVisible(false);
		
		
		//Sets the welcoming message depending on the user.
		lblUsernameWelcome = new JLabel();
		lblUsernameWelcome.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		lblUsernameWelcome.setForeground(Color.WHITE);
		getContentPane().add(lblUsernameWelcome);
		lblUsernameWelcome.setVisible(false);
		
		
		
		//Sets all the positions of all the labels, and moves it automatically when resizing 
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				btnPractise.setBounds(c.getWidth()/13, c.getHeight()/17*10, c.getWidth()/4, c.getHeight()/12);
				btnStatistics.setBounds(c.getWidth()/26*10, c.getHeight()/17*10, c.getWidth()/4, c.getHeight()/12);
				btnHelp.setBounds(c.getWidth()/13*10, c.getHeight()/115*90, c.getWidth()/8, c.getHeight()/10);
				btnMath.setBounds(c.getWidth()/15*10, c.getHeight()/17*10, c.getWidth()/4, c.getHeight()/12);
				welcomeLabel.setBounds(c.getWidth()/56*10, c.getHeight()/6, c.getWidth()/16*10, c.getHeight()/5);
				descriptionLabel1.setBounds(c.getWidth()/15, c.getHeight()/3, c.getWidth()/105*100, c.getHeight()/7);
				btnQuit.setBounds(c.getWidth()/13*8, c.getHeight()/115*90, c.getWidth()/8, c.getHeight()/10);
				
				
				usernameInput.setBounds(c.getWidth()/50*1, c.getHeight()/115*109, c.getWidth()/4, c.getHeight()/25);
				passwordInput.setBounds(c.getWidth()/50*15, c.getHeight()/115*109, c.getWidth()/4, c.getHeight()/25);
				btnLogIn.setBounds(c.getWidth()/50*35, c.getHeight()/115*109, c.getWidth()/8, c.getHeight()/20);
				btnSignUp.setBounds(c.getWidth()/50*42, c.getHeight()/115*109, c.getWidth()/8, c.getHeight()/20);
				
				lblUsername.setBounds(c.getWidth()/50*1, c.getHeight()/115*105, c.getWidth()/4, c.getHeight()/25);
				lblPassWord.setBounds(c.getWidth()/50*15, c.getHeight()/115*105, c.getWidth()/4, c.getHeight()/25);
				
				btnLogOut.setBounds(c.getWidth()/50*35, c.getHeight()/115*109, c.getWidth()/8, c.getHeight()/20);
				btnDeleteUser.setBounds(c.getWidth()/50*42, c.getHeight()/115*109, c.getWidth()/8, c.getHeight()/20);
				
				lblUsernameWelcome.setBounds(c.getWidth()/50*1, c.getHeight()/115*107, c.getWidth()/4, c.getHeight()/25);
				
			}
		});
		
	}
	
	
	//Help listener, Generates the help panel upon clicking
	public class HelpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			@SuppressWarnings("unused")
			HelpPanel help = new HelpPanel();
			
		}
		
	}
	
	//Generates the Math window upon clicking
	public class MathListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			MathMenuGUI mathWindow = new MathMenuGUI();
			
		}
		
	}
	
	
	//Generates the practice window upon clicking 
	public class PractiseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			@SuppressWarnings("unused")
			Practise practiseWindow = new Practise();
			
		}
	}
	
	
	//Generates the statistics window upon clicking
	public class StatisticsListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				StatsPanel help = new StatsPanel();
				
			}
		
	}
	
	//Generates a Option Pane upon clicking asking if you want to quit or not.
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
	
	
	//Tests the authenticity of log in details upon clicking
	private class LogInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Fields must be non empty
			if ((usernameInput.getText().isEmpty()) || (passwordInput.getPassword().length == 0 )) {
				JOptionPane.showMessageDialog(null, "Username or password has not been filled");
				
			}
			
			else {
				
				
				//Calls the bashCommand function and returns a string
				
				BashCommands commands = BashCommands.getInstance();
				String verifyLogin = commands.verifyLogin(usernameInput.getText(), String.valueOf(passwordInput.getPassword()));
				
				
				//if it returns a null string then there must have been an error 
				if (verifyLogin == null) {
					
					usernameInput.setText("");
					passwordInput.setText("");
					
					
				//else it returns the users name.
				//And Sets all the labels/ buttons 
				} else {
					
					username = verifyLogin;
					
					usernameInput.setVisible(false);
					passwordInput.setVisible(false);
					lblPassWord.setVisible(false);
					lblUsername.setVisible(false);
					
					btnLogIn.setVisible(false);
					btnSignUp.setVisible(false);
					
					btnDeleteUser.setVisible(true);
					btnLogOut.setVisible(true);
					
					lblUsernameWelcome.setText("Welcome " + username);
					lblUsernameWelcome.setVisible(true);
					
					
					
					
					
					
				}
				
				
			}
			
			
		}
		
	}
	
	//Initialises the signUp frame using the singleton design pattern
	
	private class SignUpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			UserHandler.signUpFrame();
			
			
		}
		
	}
	
	
	//Generates a Option Pane upon clicking asking if you want to logOut or not.
	private class LogOutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to Logout?","Logout", JOptionPane.YES_NO_OPTION);



			if (YesOrNo == 0) {
				
				username = "anonymos";
				lblUsernameWelcome.setVisible(false);
				
				usernameInput.setText("");
				passwordInput.setText("");
				
				usernameInput.setVisible(true);
				passwordInput.setVisible(true);
				lblPassWord.setVisible(true);
				lblUsername.setVisible(true);
				
				btnLogIn.setVisible(true);
				btnSignUp.setVisible(true);
				
				btnDeleteUser.setVisible(false);
				btnLogOut.setVisible(false);
				
				
			


		
			
			
			}
			
		}
		
	}
	
	
	//Generates a Option Pane upon clicking asking if you want to delete their user details or not.
	private class DeleteUserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete your User details?","Delete", JOptionPane.YES_NO_OPTION);



			if (YesOrNo == 0) {
				
				
				//If they confirm it, call upon the bash commands instance and delete their details
				BashCommands commands = BashCommands.getInstance();
				commands.deleteUser(usernameInput.getText());
				
				username = "anonymos";
				lblUsernameWelcome.setVisible(false);
				
				usernameInput.setText("");
				passwordInput.setText("");
				
				usernameInput.setVisible(true);
				passwordInput.setVisible(true);
				lblPassWord.setVisible(true);
				lblUsername.setVisible(true);
				
				btnLogIn.setVisible(true);
				btnSignUp.setVisible(true);
				
				btnDeleteUser.setVisible(false);
				btnLogOut.setVisible(false);
				
				
				
			}

			


		}

	}


}
