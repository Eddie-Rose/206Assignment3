package tatai.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 *Purpose of this class is to create a new JFrame with using the 
 *singleton design pattern and handle all user inputs including 
 *errors when inputting information.
 *
 *
 *
 */


public class UserHandler {

	private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private static JFrame frame = null;


	private UserHandler() {

		
		signUpFrame();

	}


	private static UserHandler instance;

	public static void getInstance(){

		if (frame == null) {

			instance = new UserHandler();
			return;

		}
		else  {


			frame.setVisible(true);
			return;

		}
	}




	//Initialises the JFrame for signing up, made it into a singleton to avoid multiple signUp frames

	public void signUpFrame() {

		

		// Set up the frame
		frame = new JFrame("New User");
		frame.setSize(500, 700);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);



		//Set up the mian title
		JLabel lblNewUser = new JLabel("New User");
		lblNewUser.setFont(new Font("DejaVu Sans", Font.BOLD, 40));
		frame.getContentPane().add(lblNewUser);


		//Sets up the "FullName" label along with its editable JTextField
		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		frame.getContentPane().add(lblFullName);

		JTextField txtFullName = new JTextField();
		txtFullName.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		frame.getContentPane().add(txtFullName);



		//Sets up the "Username" label along with its editable JTextField
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		frame.getContentPane().add(lblUserName);

		JTextField txtUsername = new JTextField();
		txtUsername.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		frame.getContentPane().add(txtUsername);



		//Sets up the "Password" label along with its editable JPasswordField
		JLabel lblPassWord = new JLabel("Password:");
		lblPassWord.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		frame.getContentPane().add(lblPassWord);

		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		frame.getContentPane().add(txtPassword);



		//Sets up the "Confirm Password" label along with its editable JPasswordField
		//Added this label to ensure the user has typed up the desired password correctly
		JLabel lblConfirmPassWord = new JLabel("Confirm Password:");
		lblConfirmPassWord.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		frame.getContentPane().add(lblConfirmPassWord);

		JPasswordField txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		frame.getContentPane().add(txtConfirmPassword);


		//Sets up the "Create User" button 
		JButton btnCreateUser = new JButton("Create User");

		//Adds an action listener 
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//checks whether creating the new user is valid or not
				boolean validUserName = true;

				//Cannot have spaces in the username
				for ( int i = 0; i < txtUsername.getText().length(); i++) {
					if (txtUsername.getText().charAt(i) == ' ' ) {

						validUserName = false;

					}
				}


				//Checks if all text fields are fileld in 
				if ((txtConfirmPassword.getPassword().length == 0)  || (txtFullName.getText().isEmpty()) || (txtPassword.getPassword().length == 0) || (txtUsername.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Some fields not filled in");
				}

				//Username cannot have spaces
				else if (validUserName == false) {
					JOptionPane.showMessageDialog(null, "Not a valid UserName, no spaces allowed");
				}

				//Checks if the 2 passowrd fields are the same
				else if (!(Arrays.equals(txtPassword.getPassword(), txtConfirmPassword.getPassword()))) {

					JOptionPane.showMessageDialog(null, "Password fields do not match");

				}

				//If all conditions are met, application tries to create the user
				//If user already exists, returns with a error message dialogue
				else {

					BashCommands commands = BashCommands.getInstance();
					int outcome = commands.makeUserDir(txtUsername.getText(), txtFullName.getText(), String.valueOf(txtPassword.getPassword()));


					if (outcome == 1) {
						frame.dispose();
						frame = null;
					}



				}

			}
		});
		frame.getContentPane().add(btnCreateUser);


		//Sets the cancel button in the createUser gui 
		JButton btnCancel = new JButton("Cancel");

		//Adds an action listener
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				//Sets up an option dialogue to confirm your choice to cancel 
				int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit?","Exit", JOptionPane.YES_NO_OPTION);

				//if no, option dialogue goes away
				if (YesOrNo == 1) {
					return;

					//if exit, exits out of the Jframe and back into the main GUI frame  
				} else if (YesOrNo == 0)  {
					frame.dispose();
					frame = null;

				} else {
					return;
				}

			}
		});

		frame.getContentPane().add(btnCancel);

		//Sets all positions of the labels, buttons and TextFields
		frame.getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();

				lblNewUser.setBounds(c.getWidth()/10*3, c.getHeight()/17*2, c.getWidth(), c.getHeight()/12);

				lblFullName.setBounds(c.getWidth()/20*2, c.getHeight()/17*5, c.getWidth(), c.getHeight()/12);
				txtFullName.setBounds(c.getWidth()/20*10, c.getHeight()/34*11, c.getWidth()/30*14, c.getHeight()/20);

				lblUserName.setBounds(c.getWidth()/20*2, c.getHeight()/17*7, c.getWidth(), c.getHeight()/12);
				txtUsername.setBounds(c.getWidth()/20*10, c.getHeight()/34*15, c.getWidth()/30*14, c.getHeight()/20);

				lblPassWord.setBounds(c.getWidth()/20*2, c.getHeight()/17*9, c.getWidth(), c.getHeight()/12);
				txtPassword.setBounds(c.getWidth()/20*10, c.getHeight()/34*19, c.getWidth()/30*14, c.getHeight()/20);

				lblConfirmPassWord.setBounds(c.getWidth()/20*2, c.getHeight()/17*11, c.getWidth(), c.getHeight()/12);
				txtConfirmPassword.setBounds(c.getWidth()/20*10, c.getHeight()/34*23, c.getWidth()/30*14, c.getHeight()/20);

				btnCreateUser.setBounds(c.getWidth()/20*5, c.getHeight()/17*13, c.getWidth()/2, c.getHeight()/12);
				btnCancel.setBounds(c.getWidth()/20*5, c.getHeight()/17*15, c.getWidth()/2, c.getHeight()/12);

			}
		});



		frame.addWindowListener(new WindowAdapter()
		{

			// If window is closing, must set frame field to null or else when prompted to 
			//Open "SignUp" again it won't allow it.
			@Override
			public void windowClosing(WindowEvent e)
			{

				frame = null;
			}
		});

		frame.setVisible(true);





	}




}
