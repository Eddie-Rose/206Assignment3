package tatai;

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

public class UserHandler {
	
	private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private static JFrame frame = null;
	
	public static void signUpFrame() {
		
		if (frame != null) {
			return;
		}
		
		frame = new JFrame("New User");
		frame.setSize(500, 700);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		
		
		JLabel lblNewUser = new JLabel("New User");
		lblNewUser.setFont(new Font("DejaVu Sans", Font.BOLD, 40));
		frame.getContentPane().add(lblNewUser);
		
		
		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		frame.getContentPane().add(lblFullName);
		
		JTextField txtFullName = new JTextField();
		txtFullName.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		frame.getContentPane().add(txtFullName);
		
		
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		frame.getContentPane().add(lblUserName);
		
		JTextField txtUsername = new JTextField();
		txtUsername.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		frame.getContentPane().add(txtUsername);
		
		
		
		JLabel lblPassWord = new JLabel("Password:");
		lblPassWord.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		frame.getContentPane().add(lblPassWord);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		frame.getContentPane().add(txtPassword);
		
		
		
		JLabel lblConfirmPassWord = new JLabel("Confirm Password:");
		lblConfirmPassWord.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		frame.getContentPane().add(lblConfirmPassWord);
		
		JPasswordField txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		frame.getContentPane().add(txtConfirmPassword);
		
		
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean validUserName = true;
				
				
				for ( int i = 0; i < txtUsername.getText().length(); i++) {
					if (txtUsername.getText().charAt(i) == ' ' ) {
						
						validUserName = false;
						
					}
				}
				
			
				if ((txtConfirmPassword.getPassword().length == 0)  || (txtFullName.getText().isEmpty()) || (txtPassword.getPassword().length == 0) || (txtUsername.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Some fields not filled in");
				}
				
				else if (validUserName == false) {
					JOptionPane.showMessageDialog(null, "Not a valid UserName, no spaces allowed");
				}
				
				else if (!(Arrays.equals(txtPassword.getPassword(), txtConfirmPassword.getPassword()))) {
					
					JOptionPane.showMessageDialog(null, "Password fields do not match");
					
				}
				else {
					System.out.println(txtPassword.getPassword().toString());
					BashCommands commands = BashCommands.getInstance();
					commands.makeUserDir(txtUsername.getText(), txtFullName.getText(), String.valueOf(txtPassword.getPassword()));
					frame.dispose();
					
				}
				
			}
		});
		frame.getContentPane().add(btnCreateUser);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to Cancel?","Cancel", JOptionPane.YES_NO_OPTION);
				
				
				if (YesOrNo == 1) {
					return;
					
				} else if (YesOrNo == 0)  {
					frame.dispose();
					frame = null;
					
				} else {
					return;
				}
				
			}
		});
		
		frame.getContentPane().add(btnCancel);
		
		
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
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                frame = null;
            }
        });
		
		frame.setVisible(true);
		
		
		
		
		
	}
	
	
	

}
