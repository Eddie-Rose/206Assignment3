package tatai.applications;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;


/**
 * 
 * This class is where the Help Panel Frame is initialised
 * Here the first thing the user sees is what the areas in which they 
 * might need help on such as Practice or Math or the User login.
 * 
 * @author Edwin Roesli and Harpreet Singh
 *
 */
public class HelpPanel {


	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private static JFrame helpFrame = null;

	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	

	JLabel introductoryLabel = new JLabel();
	JLabel lblHelp = new JLabel("HELP");

	JButton btnBeginner = new JButton("Beginner");
	JButton btnAdvanced = new JButton("Advanced");
	JButton btnScoreBoard = new JButton("Statistic");
	JButton btnMainMenu = new JButton("Return to Main Menu");

	JButton next = new JButton("Next");
	JButton back = new JButton("Back");
	JButton helpMenu = new JButton("Help Menu");
	JLabel thumb = new JLabel();
	JLabel helpTxt = new JLabel("HI");
	JLabel page = new JLabel();

	String bp1 = "<html>Starting menu of the beginner level.</html>";
	String bp2 = "<html>Numbers from 1 - 9 will be chosen at random, the one chosen will appear in the middle of the screen. Upon pressing record you will have 2 seconds to pronounce the number in maori.</html>";
	String bp3 = "<html>If pronounced incorrectly the application will tell you what you what you said, you will have the option of replaying your recording and you will have 1 more chance to attempt this number.</html>";
	String bp4 = "<html>If correct your overall score on the top right hand corner of the screen will increase by 1.</html>";
	String bp5 = "<html>At the end of the 10 random questions you will be shown your total score</html>";
	String bp6 = "<html>If you manage to get 8 or above you will be presented the option to head to the advance level</html>";

	String ap1 = "<html>Numbers from 1 - 99 will be chosen at random, the one chosen will appear in the middle of the screen. Upon pressing record you will have 2 seconds to pronounce the number in maori.</html>";
	String ap2 = "<html>If no recording was heard then this page will pop up, however a no recording found event still counts as an incorrect attempt.</html>";
	String ap3 = "<html>If pronounced incorrectly the application will tell you what you what you said, you will have the option of replaying your recording and you will have 1 more chance to attempt this number.</html>";
	String ap4 = "<html>If correct your overall score on the top right hand corner of the screen will increase by 1 .</html>";
	String ap5 = "<html>At the end of the 10 random questions you will be shown your total score.</html>";

	String s1 = "<html>Shows your saved scores for beginner and advanced level. You have the option to clear the field.</html>";
	
	String bm1 = null;

	String[] beginnerLabel = {bp1, bp2, bp3, bp4, bp5, bp6};
	String[] advanceLabel = {ap1, ap2, ap3, ap4, ap5 };
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


	//Singleton method called by outside classes
	//Ensures that there is only instance of this at any time.
	public static void getInstance() {

		if (helpFrame == null) {

			helpFrame = new JFrame();
			new HelpPanel();
			return;

		}
		else  {


			helpFrame.setVisible(true);
			return;

		}
	}

	private HelpPanel() {



		helpFrame.setTitle("Help");
		helpFrame.setResizable(false);
		helpFrame.setBounds(100, 100, 700, 400);
		helpFrame.setLocation(dim.width/2-helpFrame.getSize().width/2, dim.height/2-helpFrame.getSize().height/2);
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helpFrame.getContentPane().setLayout(null);

		
		
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		lblHelp.setBounds(170, 30, 335, 70);
		helpFrame.getContentPane().add(lblHelp);
	


		introductoryLabel.setBounds(205, 100, 271, 17);
		introductoryLabel.setFont(new Font(null, Font.PLAIN, 15));
		introductoryLabel.setText("Select which field you need help with");
		helpFrame.getContentPane().add(introductoryLabel);
		

		btnBeginner.setBounds(260, 130, 150, 40);
		btnAdvanced.setBounds(260, 175, 150, 40);
		btnScoreBoard.setBounds(260, 225, 150, 40);
		btnMainMenu.setBounds(235, 285, 200, 40);

		helpFrame.getContentPane().add(btnBeginner);
		helpFrame.getContentPane().add(btnAdvanced);
		helpFrame.getContentPane().add(btnScoreBoard);
		helpFrame.getContentPane().add(btnMainMenu);


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



		helpFrame.getContentPane().add(page);
		helpFrame.getContentPane().add(thumb);
		helpFrame.getContentPane().add(next);
		helpFrame.getContentPane().add(back);
		helpFrame.getContentPane().add(helpMenu);
		helpFrame.getContentPane().add(helpTxt);

		thumb.setVisible(false);
		page.setVisible(false);
		next.setVisible(false);
		back.setVisible(false);
		helpMenu.setVisible(false);
		helpTxt.setVisible(false);






		btnBeginner.addActionListener(new ButtonBeginnerListener());
		btnAdvanced.addActionListener(new ButtonAdvancedListener());
		btnScoreBoard.addActionListener(new ButtonScoreBoardListener());
		btnMainMenu.addActionListener(new ButtonMainMenuListener());
		helpMenu.addActionListener(new ButtonHelpMenuListener());
		next.addActionListener(new ButtonNextListener());
		back.addActionListener(new ButtonBackListener());
		
		
		
		helpFrame.setVisible(true);


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

			if (imageNumber == 1) {
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
			helpFrame.dispose();
			helpFrame= null;

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
		introductoryLabel.setVisible(true);
		btnMainMenu.setVisible(true);
		btnBeginner.setVisible(true);
		btnAdvanced.setVisible(true);
		btnScoreBoard.setVisible(true);
		next.setEnabled(true);
		back.setEnabled(true);


		next.setVisible(false);
		back.setVisible(false);
		helpMenu.setVisible(false);
		thumb.setVisible(false);
		helpTxt.setVisible(false);
		page.setVisible(false);
	}

	private void setScreen() {
		introductoryLabel.setVisible(false);
		btnMainMenu.setVisible(false);
		btnBeginner.setVisible(false);
		btnAdvanced.setVisible(false);
		btnScoreBoard.setVisible(false);

		if (imageNumber == 1) {
			back.setEnabled(false);
		}

		if (imageNumber == totalPages) {
			next.setEnabled(false);
		}

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
		next.setEnabled(false);

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
