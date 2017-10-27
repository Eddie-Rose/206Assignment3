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
	String c4 = "<html><div style='text-align: center;'>If you exit before completeing all 10 questions, an option frame will pop up asking you if you want to delete your custom set or save it. If you save it the application will randomly generate 2 extra  </div></html>";
	String c5 = "<html><div style='text-align: center;'>When you play your custom set, your set will be randomised so you will not be asked questions in the same order in which you implemented them </div></html>";

	
	//String represents the help texts in the "User" part of the help frame
	String u1 = "<html><div style='text-align: center;'> The user implementation is found in the menu of the application with this you will be able to log in or create a user, if you play the game without logging in, your name in the score board will be \"anonymous\" </div></html>";
	String u2 = "<html><div style='text-align: center;'> When sigining up, you will be prompted to fill in 4 fields, your display name, username, password and the confirm password field. These fields must only contain alphaNumeric characters  </div></html>";
	String u3 = "<html><div style='text-align: center;'> Upon logging in, a welcoming message will be shown with your given name in the bottom left hand corner, now whenever you complete a level it will be recorded in the statistics frame</div></html>";

	
	//String representing the help texts in the "recording" part of the help frame
	String r1 = "<html><div style='text-align: center;'> When you hit the record button, most of the buttons will be disabled and the timer will start to fill up. The timer represents how much time you have to pronounce the number </div></html>";
	String r2 = "<html><div style='text-align: center;'> When the record button has finished, you will see the result for this question. If you get it correct you can move on to the next question, but if you get it wrong or no recording was hear you will be given 1 more chance to get it right. You are also given the chance to review your recording  </div></html>";

	
	
	//
	String[] practiceLabel = {p1, p2, p3, p4, p5, p6, p7};
	String[] mathLabel = {m1, m2, m3, m4, m5 };
	String[] statisticLabel = {s1};
	String[] customLabel = {c1, c2, c3, c4, c5};
	String[] userLabel = {u1, u2, u3};
	String[] recordingLabel = {r1, r2};


	private int imageNumber = 1;
	private int totalPages = 0;
	private String practiceURL = "./206Assignment3Images/Practice/";
	private String mathURL = "./206Assignment3Images/Math/";
	private String statisticURL = "./206Assignment3Images/Statistic/";
	private String UserURL = "./206Assignment3Images/User/";
	private String customURL = "./206Assignment3Images/Custom/";
	private String recordingURL = "./206Assignment3Images/Recording/";
	private String[] practiceImages = {practiceURL+"1.png", practiceURL+"2.png", practiceURL+"3.png", practiceURL+"4.png", practiceURL+"5.png", practiceURL+"6.png", practiceURL+"7.png"};
	private String[] mathImages = {mathURL+"1.png", mathURL+"2.png", mathURL+"3.png", mathURL+"4.png", mathURL+"5.png"};
	private String[] statisticImages = {statisticURL+"1.png"};
	private String[] customImages = {customURL+"1.png", customURL+"2.png", customURL+"3.png", customURL+"4.png", customURL+"5.png"};
	private String[] userImages = {UserURL+"1.png", UserURL+"2.png", UserURL+"3.png"};
	private String[] recordingImages = {recordingURL+"1.png", recordingURL+"2.png"};
	

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

	
	//Sets up the help frame
	private HelpPanel() {


		//Sets up the main components
		helpFrame.setTitle("Help");
		helpFrame.setResizable(false);
		helpFrame.setBounds(100, 100, 700, 400);
		helpFrame.setLocation(dim.width/2-helpFrame.getSize().width/2, dim.height/2-helpFrame.getSize().height/2);
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helpFrame.getContentPane().setLayout(null);


		//Sets the label of the title
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		lblHelp.setBounds(170, 05, 335, 70);
		helpFrame.getContentPane().add(lblHelp);


		//Sets the introductory label
		introductoryLabel.setBounds(205, 100, 271, 17);
		introductoryLabel.setFont(new Font(null, Font.PLAIN, 15));
		introductoryLabel.setText("Select which field you need help with");
		helpFrame.getContentPane().add(introductoryLabel);

		
		//Sets all the buttons bounds/ sizes
		btnPractice.setBounds(180, 130, 150, 40);
		btnMaths.setBounds(180, 175, 150, 40);
		btnScoreBoard.setBounds(180, 225, 150, 40);
		btnMainMenu.setBounds(235, 300, 200, 40);
		btnCustom.setBounds(355, 130, 150, 40);
		btnUser.setBounds(355, 175, 150, 40 );
		btnRecording.setBounds(355, 225, 150, 40);
		
		
		
		//Adds all the components into the content pane
		helpFrame.getContentPane().add(btnCustom);
		helpFrame.getContentPane().add(btnUser);
		helpFrame.getContentPane().add(btnPractice);
		helpFrame.getContentPane().add(btnMaths);
		helpFrame.getContentPane().add(btnScoreBoard);
		helpFrame.getContentPane().add(btnMainMenu);
		helpFrame.getContentPane().add(btnRecording);

		
		//Sets the other bounds 
		back.setBounds(50, 150, 70, 30);
		next.setBounds(570, 150, 70, 30);
		helpMenu.setBounds(275, 350, 120, 20);
		thumb.setBounds(165, 70, 350, 170);
		helpTxt.setBounds(20, 260, 650, 70);
		helpTxt.setOpaque(true);
		helpTxt.setBackground(Color.WHITE);
		helpTxt.setFont(new Font(null, Font.PLAIN, 11));
		helpTxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		helpTxt.setHorizontalAlignment(SwingConstants.CENTER);
		page.setBounds(600, 20, 100, 20);


		
		//Adds the other labels and buttons to the content pane
		helpFrame.getContentPane().add(page);
		helpFrame.getContentPane().add(thumb);
		helpFrame.getContentPane().add(next);
		helpFrame.getContentPane().add(back);
		helpFrame.getContentPane().add(helpMenu);
		helpFrame.getContentPane().add(helpTxt);
		
		
		//Initially set some of the buttons and labels to be false
		thumb.setVisible(false);
		page.setVisible(false);
		next.setVisible(false);
		back.setVisible(false);
		helpMenu.setVisible(false);
		helpTxt.setVisible(false);






		//Adds all the button listeners
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


		
		//Set the frame to be visible
		helpFrame.setVisible(true);


	}
	
	//Sets the next button , if we are at the last image, set the next button to be disabled
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
			else if (state.equals("custom")){
				customHelpFrameInitialisation();
			}
			else if (state.equals("user")){
				userHelpFrameInitialisation();
			}
			else if (state.equals("recording")){
				recordingHelpFrameInitialisation();
			}


		}
	}
	
	//Sets the back button, if we are at the first image, set the back button to be disabled
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
			else if (state.equals("custom")){
				customHelpFrameInitialisation();
			}
			else if (state.equals("user")){
				userHelpFrameInitialisation();
			}
			else if (state.equals("recording")){
				recordingHelpFrameInitialisation();
			}


		}
	}

	//sets up the button so that it will reset the main menu
	private class ButtonHelpMenuListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			totalPages = 0;
			imageNumber = 1;
			setHelpMenu();
		}
	}
	
	//Sets up the button so that it will dispose of the frame and return to the main menu
	private class ButtonMainMenuListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent e) {
			helpFrame.dispose();
			helpFrame= null;

		}
	}

	
	//Sets up the custom listener so it will bring up the custom help 
	private class ButtonCustomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			customHelpFrameInitialisation();
			state = "custom";


		}
	}


	//Sets up the user listener so it will bring up the user help 
	private class ButtonUserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			userHelpFrameInitialisation();
			state = "user";


		}


	}
	//Sets up the practice listener so it will bring up the practice help 
	private class ButtonPracticeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			practiceHelpFrameInitialisation();
			state = "practice";

		}


	}

	//Sets up the recording listener so it will bring up the recording help 
	private class ButtonRecordingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			recordingHelpFrameInitialisation();
			state = "recording";

		}


	}
	
	
	//Sets up the math listener so it will bring up the math help 
	private class ButtonMathListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			mathHelpFrameInitialisation();
			state = "math";

		}


	}
	
	//Sets up the scoreboard listener so it will bring up the scoreboard help 
	private class ButtonScoreBoardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen();
			scoreBoardHelpInitialisation();
			state = "scoreBoard";

		}


	}
	
	//Sets up the initial menu
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
	
	
	//Sets up the help dcreen with an image pane, 2 buttons to indicate next or back and a return to menu button
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
	
	//practice initialisation 
	private void practiceHelpFrameInitialisation() {


		totalPages = 7;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(practiceLabel[imageNumber-1]);

		imageLoader = new SwingLoader(practiceImages[imageNumber-1]);
		imageLoader.execute();



	}
	
	//math initialisation 
	private void mathHelpFrameInitialisation() {
		totalPages = 5;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(mathLabel[imageNumber-1]);

		imageLoader = new SwingLoader(mathImages[imageNumber-1]);
		imageLoader.execute();


	}

	//scoreboard initialisation 
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
		totalPages = 3;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(userLabel[imageNumber-1]);
		

		imageLoader = new SwingLoader(userImages[imageNumber-1]);
		imageLoader.execute();



	}
	
	//Initialises the image and the help text of the custom help frame
	private void customHelpFrameInitialisation() {
		totalPages = 5;
		page.setText("Page: " + imageNumber + "/" + totalPages);
		helpTxt.setText(customLabel[imageNumber-1]);
		

		imageLoader = new SwingLoader(customImages[imageNumber-1]);
		imageLoader.execute();



	}
	
	//Initialises the image and the help text of the recording help frame
		private void recordingHelpFrameInitialisation() {
			totalPages = 2;
			page.setText("Page: " + imageNumber + "/" + totalPages);
			helpTxt.setText(recordingLabel[imageNumber-1]);
			

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
			ImageIcon icon = new ImageIcon(name); 
			thumb.setIcon(icon);
			return null;
		}

	}

}
