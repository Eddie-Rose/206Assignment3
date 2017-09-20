package tatai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class HelpPanel extends JFrame {

	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
	
	
	public HelpPanel() {


		

		setVisible(true);
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
		panel_1.setBounds(10, 49, 414, 110);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JTextField textField = new JTextField();
		textField.setBounds(10, 11, 394, 90);
		textField.setEditable(false);
		panel_1.add(textField);
		textField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 160, 414, 120);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JTextField textField_1 = new JTextField();
		textField_1.setBounds(10, 11, 394, 100);
		textField_1.setEditable(false);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		;
	}

}
