package tatai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import tatai.Level.ButtonBeginListener;

public class HelpPanel extends JFrame {

	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
	
	JTextArea textField = new JTextArea();
	JPanel panel = new JPanel();
	JLabel lblHelp = new JLabel("HELP");
	JPanel panel_1 = new JPanel();
	
	JButton beginner = new JButton("Beginner");
	JButton advanced = new JButton("Advanced");
	JButton scoreBoard = new JButton("ScoreBoard");
	
	
	
	public HelpPanel() {


		
		setTitle("Help");
		setVisible(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(177, 5, 81, 36);
		getContentPane().add(panel);
		panel.setLayout(null);

		
		lblHelp.setBounds(10, 5, 59, 26);
		panel.add(lblHelp);
		lblHelp.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);

		
		panel_1.setBounds(120, 60, 190, 18);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		

		
		textField.setBounds(4, 2, 179, 15);
		textField.setEditable(false);
		textField.setFont(new Font(null, Font.PLAIN, 10));
		textField.setText("Select which field you need help with");
		panel_1.add(textField);
		textField.setColumns(10);
		
		beginner.setBounds(140, 100, 150, 40);
		advanced.setBounds(140, 150, 150, 40);
		scoreBoard.setBounds(140, 200, 150, 40);
		
		getContentPane().add(beginner);
		getContentPane().add(advanced);
		getContentPane().add(scoreBoard);
		
		
		
		beginner.addActionListener(new ButtonBeginnerListener());
		advanced.addActionListener(new ButtonAdvancedListener());
		scoreBoard.addActionListener(new ButtonScoreBoardListener());
		
	}
	
	private class ButtonBeginnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			beginnerHelpFrameInitialisation();
			
		}
		
		
	}
	
	private class ButtonAdvancedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			advancedHelpFrameInitialisation();
			
		}
		
		
	}
	
	private class ButtonScoreBoardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			scoreBoardHelpInitialisation();
			
		}
		
		
	}
	
	private void beginnerHelpFrameInitialisation() {
		
		
		
	}
	
	private void advancedHelpFrameInitialisation() {
		
		
		
	}
	
	private void scoreBoardHelpInitialisation() {
		
		
		
		
	}

}
