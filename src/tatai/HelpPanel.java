package tatai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class HelpPanel extends JFrame {

	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
	
	
	public HelpPanel() {


		
		setTitle("Help");
		setVisible(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(19, 59, 417, 98);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		

		JTextArea textField = new JTextArea();
		textField.setBounds(1, 1, 415, 95);
		textField.setEditable(false);
		textField.setFont(new Font(null, Font.PLAIN, 10));
		textField.setText("Beginner: \nThis option tests you on the pronounctaiation of numbers from 1 to 9 inclusive.");
		textField.append("\nAfter pronouncing a number the application will give tell you if your pronounctiation\nwas correct or not. You");
		textField.append("will be asked to say all 10 numbers, if you get one wrong you\nwill have a chance to correct yourself.");
		textField.append(" At the end you will be given a score out of 10,\nIf you get higher or equal to 8 you will be presented an option to go to the next level.");
		textField.append("\nWhich will give you more larger numbers to pronounce.");
		panel_1.add(textField);
		textField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(19, 180, 417, 93);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JTextArea textField_1 = new JTextArea();
		textField_1.setBounds(1, 1, 415, 90);
		textField_1.setEditable(false);
		textField_1.setFont(new Font(null, Font.PLAIN, 10));
		textField_1.setText("Advanced: \nThis option will give you 10 numbers to pronounce, ranging from 11 to 99 inclusive.\n");
		textField_1.append("As with the beginner option, if you pronounce a number wrong you will be\nprompted to try again, ");
		textField_1.append("At the end of the 10 pronounctiations you will be given\na summary of how many you got right.");
		textField_1.append("During the pronouncitation process, after\nyou pronounce a word, the recording will be replayed back to you.");
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		;
	}

}
