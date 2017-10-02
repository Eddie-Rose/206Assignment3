package tatai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import tatai.Level.ButtonBeginListener;
import tatai.Level.Worker;

public class HelpPanel extends JFrame {

	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
	
	JTextArea textField = new JTextArea();
	JPanel panel = new JPanel();
	JLabel lblHelp = new JLabel("HELP");
	JPanel panel_1 = new JPanel();
	
	JButton beginner = new JButton("Beginner");
	JButton advanced = new JButton("Advanced");
	JButton scoreBoard = new JButton("Statistic");
	JButton mainMenu = new JButton("Return to Main Menu");
	
	JButton next = new JButton("Next");
	JButton back = new JButton("Back");
	JButton helpMenu = new JButton("Help Menu");
	JLabel thumb = new JLabel();
	JLabel helpTxt = new JLabel("HI");
	JLabel page = new JLabel();
	
	String b1 = "<html>Starting menu of the beginner level.</html>";
	String b2 = "<html>Numbers from 1 - 9 will be chosen at random, the one chosen will appear in the middle of the screen. Upon pressing record you will have 2 seconds to pronounce the number in maori.</html>";
	String b3 = "<html>If pronounced incorrectly the application will tell you what you what you said, you will have the option of replaying your recording and you will have 1 more chance to attempt this number.</html>";
	String b4 = "<html>If correct your overall score on the top right hand corner of the screen will increase by 1.</html>";
	String b5 = "<html>At the end of the 10 random questions you will be shown your total score</html>";
	String b6 = "<html>If you manage to get 8 or above you will be presented the option to head to the advance level</html>";
	
	String a1 = "<html>Numbers from 1 - 99 will be chosen at random, the one chosen will appear in the middle of the screen. Upon pressing record you will have 2 seconds to pronounce the number in maori.</html>";
	String a2 = "<html>If no recording was heard then this page will pop up, however a no recording found event still counts as an incorrect attempt.</html>";
	String a3 = "<html>If pronounced incorrectly the application will tell you what you what you said, you will have the option of replaying your recording and you will have 1 more chance to attempt this number.</html>";
	String a4 = "<html>If correct your overall score on the top right hand corner of the screen will increase by 1 .</html>";
	String a5 = "<html>At the end of the 10 random questions you will be shown your total score.</html>";
	
	String s1 = "<html>Shows your saved scores for beginner and advanced level. You have the option to clear the field.</html>";
	
	String[] beginnerLabel = {b1, b2, b3, b4, b5, b6};
	String[] advanceLabel = {a1, a2, a3, a4, a5 };
	String[] statisticLabel = {s1};
	
	private int imageNumber = 1;
	private int totalPages = 0;
	private String beginnerURL = "./206Assignment3Images/Beginner/";
	private String advanceURL = "./206Assignment3Images/Advanced/";
	private String statisticURL = "./206Assignment3Images/Statistic/";
	private String[] beginnerImages = {beginnerURL+"1.png", beginnerURL+"2.png", beginnerURL+"3.png", beginnerURL+"4.png", beginnerURL+"5.png", beginnerURL+"6.png"};
	private String[] advancedImages = {advanceURL+"1.png", advanceURL+"2.png", advanceURL+"3.png", advanceURL+"4.png", advanceURL+"5.png"};
	private String[] statisticImages = {statisticURL+"1.png"};
	
	
	private String state = "";
	
	SwingLoader imageLoader;
	
	
	
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
		panel.setBounds(177, 5, 95, 40);
		getContentPane().add(panel);
		panel.setLayout(null);

		
		lblHelp.setBounds(10, 5, 75, 30);
		panel.add(lblHelp);
		lblHelp.setFont(new Font("Verdana", Font.BOLD, 25));
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);

		
		panel_1.setBounds(85, 60, 282, 25);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		

		
		textField.setBounds(4, 4, 271, 17);
		textField.setEditable(false);
		textField.setFont(new Font(null, Font.PLAIN, 15));
		textField.setText("Select which field you need help with");
		panel_1.add(textField);
		textField.setColumns(10);
		
		beginner.setBounds(150, 95, 150, 40);
		advanced.setBounds(150, 145, 150, 40);
		scoreBoard.setBounds(150, 195, 150, 40);
		mainMenu.setBounds(125, 245, 200, 40);
		
		getContentPane().add(beginner);
		getContentPane().add(advanced);
		getContentPane().add(scoreBoard);
		getContentPane().add(mainMenu);
		
		
		back.setBounds(10, 130, 70, 30);
		next.setBounds(370, 130, 70, 30);
		helpMenu.setBounds(165, 270, 120, 20);
		thumb.setBounds(100, 53, 255, 150);
		helpTxt.setBounds(20, 210, 400, 55);
		helpTxt.setOpaque(true);
		helpTxt.setBackground(Color.WHITE);
		helpTxt.setFont(new Font(null, Font.PLAIN, 11));
		helpTxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		helpTxt.setHorizontalAlignment(SwingConstants.CENTER);
		page.setBounds(350, 20, 100, 20);
	
		
		
		getContentPane().add(page);
		getContentPane().add(thumb);
		getContentPane().add(next);
		getContentPane().add(back);
		getContentPane().add(helpMenu);
		getContentPane().add(helpTxt);
		
		thumb.setVisible(false);
		page.setVisible(false);
		next.setVisible(false);
		back.setVisible(false);
		helpMenu.setVisible(false);
		helpTxt.setVisible(false);
		
		
		
		  
		
		
		beginner.addActionListener(new ButtonBeginnerListener());
		advanced.addActionListener(new ButtonAdvancedListener());
		scoreBoard.addActionListener(new ButtonScoreBoardListener());
		mainMenu.addActionListener(new ButtonMainMenuListener());
		helpMenu.addActionListener(new ButtonHelpMenuListener());
		next.addActionListener(new ButtonNextListener());
		back.addActionListener(new ButtonBackListener());
		
		
	}
	
	private class ButtonNextListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {

			imageNumber++;
			back.setEnabled(true);
			if (imageNumber == totalPages) {
				next.setEnabled(false);
			}
			if (state.equals("beginner")) {
				beginnerHelpFrameInitialisation();
			}
			else if (state.equals("advance")) {
				advancedHelpFrameInitialisation();
			}
			else {
				scoreBoardHelpInitialisation();
			}


		}
	}

	private class ButtonBackListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {


			imageNumber--;
			next.setEnabled(true);

			if (imageNumber == 0) {
				back.setEnabled(false);
			}
			
			if (state.equals("beginner")) {
				beginnerHelpFrameInitialisation();
			}
			else if (state.equals("advance")) {
				advancedHelpFrameInitialisation();
			}
			else {
				scoreBoardHelpInitialisation();
			}


		}
	}

	private class ButtonHelpMenuListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			totalPages = 0;
			imageNumber = 1;
			setHelpMenu();
		}
	}
	
	private class ButtonMainMenuListener implements ActionListener {
		
		@Override
		public void actionPerformed (ActionEvent e) {
			dispose();
			
		}
	}
	
	private class ButtonBeginnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			beginnerHelpFrameInitialisation();
			state = "beginner";
			
		}
		
		
	}
	
	private class ButtonAdvancedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			advancedHelpFrameInitialisation();
			state = "advance";
			
		}
		
		
	}
	
	private class ButtonScoreBoardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			scoreBoardHelpInitialisation();
			state = "scoreBoard";
			
		}
		
		
	}
	
	private void setHelpMenu() {
		textField.setVisible(true);
		mainMenu.setVisible(true);
		beginner.setVisible(true);
		advanced.setVisible(true);
		scoreBoard.setVisible(true);
		panel_1.setVisible(true);
		
		
		next.setVisible(false);
		back.setVisible(false);
		helpMenu.setVisible(false);
		thumb.setVisible(false);
		helpTxt.setVisible(false);
		page.setVisible(false);
	}
	
	private void setScreen() {
		textField.setVisible(false);
		mainMenu.setVisible(false);
		beginner.setVisible(false);
		advanced.setVisible(false);
		scoreBoard.setVisible(false);
		panel_1.setVisible(false);
		back.setEnabled(false);
		
		page.setVisible(true);
		thumb.setVisible(true);
		next.setVisible(true);
		back.setVisible(true);
		helpMenu.setVisible(true);
		helpTxt.setVisible(true);
	}
	
	private void beginnerHelpFrameInitialisation() {
		
		
		totalPages = 6;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(beginnerLabel[imageNumber-1]);
		
		imageLoader = new SwingLoader(beginnerImages[imageNumber-1]);
		imageLoader.execute();
		
		
		
	}
	
	private void advancedHelpFrameInitialisation() {
		totalPages = 5;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(advanceLabel[imageNumber-1]);
		
		imageLoader = new SwingLoader(advancedImages[imageNumber-1]);
		imageLoader.execute();
		
		
	}
	
	private void scoreBoardHelpInitialisation() {
		totalPages = 1;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(statisticLabel[imageNumber-1]);
		
		imageLoader = new SwingLoader(statisticImages[imageNumber-1]);
		imageLoader.execute();
		
		
		
	}
	
	public class SwingLoader extends SwingWorker<Void, String> {
		
		private String name;
		
		public SwingLoader(String name) {
			this.name = name;
		}
		
		@Override
		protected Void doInBackground() throws Exception {
			ImageIcon icon = new ImageIcon(name); 
			thumb.setIcon(icon);
			return null;
		}
		
	}

}
