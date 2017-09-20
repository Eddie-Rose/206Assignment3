package tatai;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class HelpPanel extends JFrame {
	
	public HelpPanel() {
		showHelpPanel();
	}
	
	private void showHelpPanel() {
		
		setVisible(true);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(177, 5, 81, 36);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblHelp = new JLabel("HELP");
		lblHelp.setBounds(10, 5, 59, 26);
		panel.add(lblHelp);
		lblHelp.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 49, 414, 69);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 11, 394, 47);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 129, 414, 69);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(10, 11, 394, 47);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 209, 414, 42);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(10, 11, 394, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
	}

}
