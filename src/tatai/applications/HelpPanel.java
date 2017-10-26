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

	//Sets up the main components of the frame
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private static JFrame helpFrame = null;
	
	//Is used to centre the frame 
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
	
	//Sets up the introductory labels
	JLabel introductoryLabel = new JLabel();
	JLabel lblHelp = new JLabel("HELP");
	
	
	//Sets up the Buttons used in this frame
	JButton btnPractice = new JButton("Practice");
	JButton btnMaths = new JButton("Math");
	JButton btnCustom = new JButton("Custom Math");
	JButton btnUser = new JButton("User");
	JButton btnScoreBoard = new JButton("Statistic");
	JButton btnRecording = new JButton("Recording");
	JButton btnMainMenu = new JButton("Return to Main Menu");

	JButton next = new JButton("Next");
	JButton back = new JButton("Back");
	JButton helpMenu = new JButton("Help Menu");
	JLabel thumb = new JLabel();
	JLabel helpTxt = new JLabel("HI");
	JLabel page = new JLabel();
	
	
	//String p 1 - 7 represents the help texts in the "practice" part of the help frame
	String p1 = "<html><div style='text-align: center;'>This is the frame which pops up when you press the \"Practice\" button. From here you can choose to either practice pronouncing numbers from 1 - 9 in beginner or 1 - 99 in advanced.  </div></html>";
	String p2 = "<html><div style='text-align: center;'>Upon choosing a level you will be see a introductory message, a title showing you what level you are on, the Start button and a Back button on the top left corner. The back button brings you back to the main menu but will not save your progress.</div></html>";
	String p3 = "<html><div style='text-align: center;'>Upon choosing to start you will be shown a screen similar to the image shown above. The main points to see here is there is now a big bold number shown in the middle of the screen, this is the number you are prompted to pronounce when you press the \"record\" buttton.</div></html>";
	String p4 = "<html><div style='text-align: center;'>The top right hand corner shows your current score and the current highscore. Bottom left hand corner hows what question you are on. And the button to the right of the number is a \"skip button\" which allows you to skip the current question, however you will automatically get the question incorrect.</div></html>";
	String p5 = "<html><div style='text-align: center;'>The bar beneath the record button is a timer which shows how much time you have to prnounce the number.</div></html>";
	String p6 = "<html><div style='text-align: center;'>After answering all 10 questions you will be shown with a log of what questions you got right and wrong as with a final score. You will be then allowed to leave head back to the main screen via the \"Main Menu\" button</div></html>";
	String p7 = "<html><div style='text-align: center;'>If you manage to get 8 or above in the beginner level you will be presented the option to head to the advance level</div></html>";
	
	
	//String m 1 - 5 represents the help texts in the "math" part of the help frame
	String m1 = "<html><div style='text-align: center;'>The math menu is similar to the practice menu except that the math menu offers a beginner level, advanced level as well as a medium and custom level .</div></html>";
	String m2 = "<html><div style='text-align: center;'>The beginner level generates simple addition and subtraction arithmetic equations that gives an asnwer between 1 - 40 .</div></html>";
	String m3 = "<html><div style='text-align: center;'>The medium level generates trivial addition, subtraction arithmetic equations that invlolves a number between 1 - 50, so the maximum possible addition equation would be (50 + 50), maximum subtraction equation would be (50 - 1), maximum multiplication would be (7 X 7)</div></html>";
	String m4 = "<html><div style='text-align: center;'>The advanced level generates questions from addition, subtraction and division.The questions asked will invlove number between 1 - 99 but the answer will definitely be a whole number, between 1 - 99 </div></html>";
	String m5 = "<html><div style='text-align: center;'>The custom level is a unique level which allows you to create/ play your own custom equations</div></html>";
	
	
	//String s represents the help texts in the "statistic" part of the help frame
	String s1 = "<html><div style='text-align: center;'>Shows your saved scores for beginner and advanced level. You have the option to clear the field.</div></html>";
	
	
	//String c represents the help texts of the "custom" part of the help frame 
	String c1 = "<html><div style='text-align: center;'>The custom math section is responsible for representing all the custom sets of questions. In this menu you can either play or delete a pre exisiting set or you can create your very own set.</div></html>";
	String c2 = "<html><div style='text-align: center;'>When creating your own set there are some conditions that must be followed. These conditons are the name of the set must have less than 11 characters and the characters used must be alphanumerical </div></html>";
	String c3 = "<html><div style='text-align: center;'>When creating your own questions you will see 2 text boxes which are editable, you will only be allowed too input digits and the character limit is 2. You must click 1 operation. When you press next the appplication will check if your question is a suitable one or else it will output an error message. </div></html>";
	String c4 = "<html><div style='text-align: center;'>If you exit before completeing all 10 questions, an option frame will pop up saking you if you want to delete your custom set or save it. If you save it the application will randomly generate 2 extra  </div></html>";
	
	
	//String represents the help texts in the "User" part of the help frame
	String u1 = null;
	
	
	//String representing the help texts in the "recording" part of the help frame
	String r1 = null;
	
	String[] practiceLabel = {p1, p2, p3, p4, p5, p6, p7};
	String[] mathLabel = {m1, m2, m3, m4, m5 };
	String[] statisticLabel = {s1};
	String[] customLabel = {c1};
	String[] userLabel = {u1};
	String[] recordingLabel = {r1};


	private int imageNumber = 1;
	private int totalPages = 0;
	private String beginnerURL = "./206Assignment3Images/Beginner/";
	private String advanceURL = "./206Assignment3Images/Advanced/";
	private String statisticURL = "./206Assignment3Images/Statistic/";
	private String[] practiceImages = {beginnerURL+"1.png", beginnerURL+"2.png", beginnerURL+"3.png", beginnerURL+"4.png", beginnerURL+"5.png", beginnerURL+"6.png", beginnerURL+"6.png"};
	private String[] mathImages = {advanceURL+"1.png", advanceURL+"2.png", advanceURL+"3.png", advanceURL+"4.png", advanceURL+"5.png"};
	private String[] statisticImages = {statisticURL+"1.png"};
	private String[] customImages = {statisticURL+"1.png"};
	private String[] userImages = {statisticURL+"1.png"};
	private String[] recordingImages = {statisticURL+"1.png"};
	

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
		lblHelp.setBounds(170, 05, 335, 70);
		helpFrame.getContentPane().add(lblHelp);



		introductoryLabel.setBounds(205, 100, 271, 17);
		introductoryLabel.setFont(new Font(null, Font.PLAIN, 15));
		introductoryLabel.setText("Select which field you need help with");
		helpFrame.getContentPane().add(introductoryLabel);


		btnPractice.setBounds(180, 130, 150, 40);
		btnMaths.setBounds(180, 175, 150, 40);
		btnScoreBoard.setBounds(180, 225, 150, 40);
		btnMainMenu.setBounds(235, 300, 200, 40);
		btnCustom.setBounds(355, 130, 150, 40);
		btnUser.setBounds(355, 175, 150, 40 );
		btnRecording.setBounds(355, 225, 150, 40);


		helpFrame.getContentPane().add(btnCustom);
		helpFrame.getContentPane().add(btnUser);
		helpFrame.getContentPane().add(btnPractice);
		helpFrame.getContentPane().add(btnMaths);
		helpFrame.getContentPane().add(btnScoreBoard);
		helpFrame.getContentPane().add(btnMainMenu);
		helpFrame.getContentPane().add(btnRecording);


		back.setBounds(50, 150, 70, 30);
		next.setBounds(570, 150, 70, 30);
		helpMenu.setBounds(275, 350, 120, 20);
		thumb.setBounds(165, 70, 350, 170);
		thumb.setOpaque(true);
		thumb.setBackground(Color.BLACK);
		helpTxt.setBounds(20, 260, 650, 70);
		helpTxt.setOpaque(true);
		helpTxt.setBackground(Color.WHITE);
		helpTxt.setFont(new Font(null, Font.PLAIN, 11));
		helpTxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		helpTxt.setHorizontalAlignment(SwingConstants.CENTER);
		page.setBounds(600, 20, 100, 20);



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







		btnPractice.addActionListener(new ButtonPracticeListener());
		btnRecording.addActionListener(new ButtonRecordingListener());
		btnMaths.addActionListener(new ButtonMathListener());
		btnScoreBoard.addActionListener(new ButtonScoreBoardListener());
		btnMainMenu.addActionListener(new ButtonMainMenuListener());
		helpMenu.addActionListener(new ButtonHelpMenuListener());
		btnCustom.addActionListener(new ButtonCustomListener());
		btnUser.addActionListener(new ButtonUserListener());
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
			if (state.equals("practice")) {
				practiceHelpFrameInitialisation();
			}
			else if (state.equals("math")) {
				mathHelpFrameInitialisation();
			}
			else if (state.equals("scoreBoard")){
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

			if (state.equals("practice")) {
				practiceHelpFrameInitialisation();
			}
			else if (state.equals("math")) {
				mathHelpFrameInitialisation();
			}
			else if (state.equals("scoreBoard")){
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


	private class ButtonCustomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			customHelpFrameInitialisation();
			state = "custom";


		}
	}



	private class ButtonUserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			userHelpFrameInitialisation();
			state = "user";


		}


	}
	private class ButtonPracticeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			practiceHelpFrameInitialisation();
			state = "practice";

		}


	}

	
	private class ButtonRecordingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			practiceHelpFrameInitialisation();
			state = "practice";

		}


	}
	
	
	private class ButtonMathListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			mathHelpFrameInitialisation();
			state = "math";

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
		btnPractice.setVisible(true);
		btnMaths.setVisible(true);
		btnCustom.setVisible(true);
		btnUser.setVisible(true);
		btnRecording.setVisible(true);
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
		btnPractice.setVisible(false);
		btnCustom.setVisible(false);
		btnRecording.setVisible(false);
		btnUser.setVisible(false);
		btnMaths.setVisible(false);
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

	private void practiceHelpFrameInitialisation() {


		totalPages = 7;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(practiceLabel[imageNumber-1]);

		imageLoader = new SwingLoader(practiceImages[imageNumber-1]);
		imageLoader.execute();



	}

	private void mathHelpFrameInitialisation() {
		totalPages = 5;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(mathLabel[imageNumber-1]);

		imageLoader = new SwingLoader(mathImages[imageNumber-1]);
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
	
	//Initialises the image and the help text of the User help frame
	private void userHelpFrameInitialisation() {
		totalPages = 1;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(userLabel[imageNumber-1]);
		next.setEnabled(false);

		imageLoader = new SwingLoader(userImages[imageNumber-1]);
		imageLoader.execute();



	}
	
	//Initialises the image and the help text of the custom help frame
	private void customHelpFrameInitialisation() {
		totalPages = 1;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(customLabel[imageNumber-1]);
		next.setEnabled(false);

		imageLoader = new SwingLoader(customImages[imageNumber-1]);
		imageLoader.execute();



	}
	
	//Initialises the image and the help text of the recording help frame
		private void recordingHelpFrameInitialisation() {
			totalPages = 1;
			page.setText("Page: " + imageNumber + "/" + totalPages);
			helpTxt.setText(recordingLabel[imageNumber-1]);
			next.setEnabled(false);

			imageLoader = new SwingLoader(recordingImages[imageNumber-1]);
			imageLoader.execute();



		}

	//This is the SwingWorker for the class which loads up the image in another thread to prevent the freezing of a GUI 
	public class SwingLoader extends SwingWorker<Void, String> {

		private String name;

		public SwingLoader(String name) {
			this.name = name;
		}

		@Override
		protected Void doInBackground() throws Exception {
			//ImageIcon icon = new ImageIcon(name); 
			//thumb.setIcon(icon);
			return null;
		}

	}

}
