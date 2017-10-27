package tatai.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import tatai.applications.StatsPanel;
import javax.swing.JScrollPane;

/**
 * 
 * Essentially the most important class in this project
 * 
 * Generic abstract class of the session that 
 * users use when answering the questions. 
 * It is extended and edit in the unique levels class where 
 * edit is needed.
 *
 *
 *author: Edwin Roesli and Harpreet Singh 
 */

public abstract class Level extends JFrame {


	//initialises the main components and fields required in this abstract level
	private static final long serialVersionUID = 1L;
	String name;
	String username;
	int testNumber;
	int minNum;
	int maxNum;
	int frameWidth = 1100;
	int frameHeight = 700;
	
	//Initialises the main components
	protected JButton btnBegin = new JButton("Start");
	protected JButton btnRecord = new JButton("");
	protected JButton btnBack;
	protected JLabel lblMainTitle;
	protected JLabel lblNewLabel = new JLabel();
	protected JLabel correct = new JLabel();
	protected JLabel lblHearPreviousRecording;
	protected JLabel lblScore;
	protected JLabel lblhighScore;
	protected JLabel lblPersonalBest;
	protected JLabel lblAttempts;
	protected JButton btnPlay;
	protected JLabel txtMainLevelDescription = new JLabel();
	protected JButton mainMenu = new JButton("Main Menu");
	protected JButton skip;
	protected JTextArea answerField = new JTextArea();
	protected JProgressBar progressBar;
	final static int interval = 20;
	int i;
	Timer t;

	int wrongAttempt = 0;
	protected int correctAttempt = 0;
	int totalAttempts = 0;
	int displayAttempts = totalAttempts+1;
	int highScore;
	Number maoriNumber = new Number();
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	protected JScrollPane scrollPane;
	
	
	//constructor takes in a string which is the level name,  minimum/ maximum set number
	public Level(String lvlName, int minimumNum, int maximumNum) {
		
		name = lvlName;
		minNum = minimumNum;
		maxNum = maximumNum;
		
		
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, frameWidth, frameHeight);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//Sets the background of the frame
		try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./background.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		//Sets the main label title
		lblMainTitle = new JLabel(name);
		lblMainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainTitle.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		lblMainTitle.setBounds(373, 41, 335, 70);
		getContentPane().add(lblMainTitle);
		
		
		//Sets the scroll pane for the log at the end of the session 
		scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 191, 594, 306);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(answerField);
		answerField.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
		
		scrollPane.setVisible(false);
		answerField.setEditable(false);

		
		//Sets the main description of the level
		txtMainLevelDescription.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMainLevelDescription.setText("<html>Welcome to the "+name+" level,\nNumbers asked are from "+ minNum+" to "+ maxNum + " ,\nPress \"Start\" to begin.</html>");
		txtMainLevelDescription.setBounds(260, 241, 561, 119);
		getContentPane().add(txtMainLevelDescription);
		
		
		//Initialises the begin button
		btnBegin.setBounds(260, 433, 531, 60);
		getContentPane().add(btnBegin);
		
		
		//Initialises the mainMenu button 
		mainMenu.setVisible(false);
		mainMenu.setBounds(429, 535, 224, 78);
		getContentPane().add(mainMenu);

		
		//Sets the record button and its icon representation 
		btnRecord.setVisible(false);
		btnRecord.setBounds(475, 380, 130, 100);
		btnRecord.setIcon(new ImageIcon("./206Assignment3Images/Icons/record.png"));
		getContentPane().add(btnRecord);
		
		
		//Sets the back button and its icon representation 
		btnBack = new JButton("");
		btnBack.setVisible(true);
		btnBack.setBounds(30, 42, 100, 80);
		btnBack.setIcon(new ImageIcon("./206Assignment3Images/Icons/backIcon.png"));
		getContentPane().add(btnBack);
		
		
		//Sets the label which contains the question 
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVisible(false);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 90));
		lblNewLabel.setBounds(328, 165, 416, 154);
		getContentPane().add(lblNewLabel);



		//Sets the label of the current score of the session
		lblScore = new JLabel("Score: "+correctAttempt+"/10");
		lblScore.setFont(new Font("Dialog", Font.BOLD, 17));
		lblScore.setVisible(false);
		lblScore.setBounds(798, 41, 128, 25);
		getContentPane().add(lblScore);

		
		
		//Gets the current highScore of a level 
		String high = StatsPanel.getHighScore(name);
		highScore = Integer.parseInt(high.split("/")[0]);

		
		//Sets the personal best label
		lblPersonalBest = new JLabel("Personal Best!");
		lblPersonalBest.setForeground(Color.RED);
		lblPersonalBest.setFont(new Font("Dialog", Font.BOLD, 17));
		lblPersonalBest.setVisible(false);
		lblPersonalBest.setBounds(938, 41, 150, 25);
		getContentPane().add(lblPersonalBest);

		
		//Sets the high score label of the level
		lblhighScore = new JLabel("High Score: "+high);
		lblhighScore.setFont(new Font("Dialog", Font.BOLD, 17));
		lblhighScore.setVisible(false);
		lblhighScore.setBounds(760, 83, 166, 36);
		getContentPane().add(lblhighScore);

		
		//Sets the question number the user is currently on 
		lblAttempts = new JLabel("Question# " + displayAttempts );
		lblAttempts.setFont(new Font("Dialog", Font.BOLD, 17));
		lblAttempts.setVisible(false);
		lblAttempts.setBounds(52, 630, 162, 36);
		getContentPane().add(lblAttempts);
		
		
		//Sets the label which tells you whether you are correct or not
		correct.setVisible(false);
		correct.setFont(new Font("Dialog", Font.PLAIN, 18));
		correct.setBounds(292, 124, 416, 50);
		correct.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(correct);

		
		//Sets the play button and its icon 
		btnPlay = new JButton("");
		btnPlay.setBounds(475, 545, 75, 75);
		btnPlay.setIcon(new ImageIcon("./206Assignment3Images/Icons/play.png"));
		getContentPane().add(btnPlay);
		btnPlay.setVisible(false);
		
		
		//Sets the "hear previous recording" label
		lblHearPreviousRecording = new JLabel("Hear previous recording:");
		lblHearPreviousRecording.setFont(new Font("Dialog", Font.BOLD, 17));
		lblHearPreviousRecording.setBounds(214, 569, 271, 31);
		getContentPane().add(lblHearPreviousRecording);
		lblHearPreviousRecording.setVisible(false);
		
		
		//Sets up the progress bar which tells the user how long they have for the recording time 
		progressBar = new JProgressBar();
		progressBar.setBounds(280, 505, 509, 50);
		progressBar.setValue(0);
		getContentPane().add(progressBar);
		progressBar.setVisible(false);

		
		//Sets up the skip button and its icon 
		skip = new JButton("");
		skip.setIcon(new ImageIcon("./206Assignment3Images/Icons/skip.png"));
		skip.setBounds(833, 190, 100, 80);
		skip.setFocusPainted(false);
		skip.setVisible(false);
		getContentPane().add(skip);


		
		//Initialises all the button listeners  
		btnBegin.addActionListener(new ButtonBeginListener());
		mainMenu.addActionListener(new ButtonMenuListener());
		btnRecord.addActionListener(new ButtonRecordListener());
		btnPlay.addActionListener(new ButtonPlayListener());
		skip.addActionListener(new ButtonSkipListener());
		btnBack.addActionListener(new ButtonBackListener());
		
		//Sets the progress bar timer 
		t = new Timer(interval, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(i==100) {
					t.stop();

				} else {
					i++;
					progressBar.setValue(i);
				}

			}
		});
		
		//Sets the resizable class and stores all the components in the Resizable array

		Resizable[] resizableComp = new Resizable[16];
		
		resizableComp[0] = new Resizable(btnBegin, frameWidth, frameHeight);
		resizableComp[1] = new Resizable(mainMenu, frameWidth, frameHeight);
		resizableComp[2] = new Resizable(btnRecord, frameWidth, frameHeight);
		resizableComp[3] = new Resizable(lblNewLabel, frameWidth, frameHeight);
		resizableComp[4] = new Resizable(lblScore, frameWidth, frameHeight);
		resizableComp[5] = new Resizable(lblPersonalBest, frameWidth, frameHeight);
		resizableComp[6] = new Resizable(lblhighScore, frameWidth, frameHeight);
		resizableComp[7] = new Resizable(lblAttempts, frameWidth, frameHeight);
		resizableComp[8] = new Resizable(lblMainTitle, frameWidth, frameHeight);
		resizableComp[9] = new Resizable(correct, frameWidth, frameHeight);
		resizableComp[10] = new Resizable(txtMainLevelDescription, frameWidth, frameHeight);
		resizableComp[11] = new Resizable(btnPlay, frameWidth, frameHeight);
		resizableComp[12] = new Resizable(lblHearPreviousRecording, frameWidth, frameHeight);
		resizableComp[13] = new Resizable(progressBar, frameWidth, frameHeight);
		resizableComp[14] = new Resizable(skip, frameWidth, frameHeight);
		resizableComp[15] = new Resizable(scrollPane, frameWidth, frameHeight);
		
		
		//This is called when the frame resizes
		//This will resize the components in relation to the frame
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				//Loops through all the components and resizes them
				for(Resizable comp : resizableComp) {
					comp.Resize(c.getWidth(), c.getHeight());
				}
				
				
			}
		});

	}

	
	//Sets the question for the level, this will usually be overridden 
	protected int setNum() {
		int random = (int )(Math.random() * maxNum + minNum);
		lblNewLabel.setText("" + random);
		return random;

	}

	
	//Sets up the question display frame by setting some components to be visible
	//and some to be in visible and refreshing some labels
	public void QuestionDisplay() {
		
		btnBegin.setVisible(false);
		txtMainLevelDescription.setVisible(false);
		correct.setVisible(false);
		btnPlay.setVisible(false);
		lblHearPreviousRecording.setVisible(false);
		
		btnBack.setVisible(true);
		progressBar.setVisible(true);

		lblNewLabel.setVisible(true);
		btnRecord.setVisible(true);
		lblScore.setVisible(true);
		
		//if the current score is greater than the high score set the current score as the high score
		if(correctAttempt > highScore) {
			lblPersonalBest.setVisible(true);
			lblhighScore.setText("High Score: "+correctAttempt+"/10");
		}

		lblhighScore.setVisible(true);
		lblAttempts.setVisible(true);
		skip.setVisible(true);

	}
	
	//Shows the result of your attempt at the question 
	public void AttemptDisplay() {
		btnRecord.setVisible(false);
		lblNewLabel.setVisible(false);
		progressBar.setVisible(false);

		btnPlay.setVisible(true);
		btnBack.setVisible(true);
		lblHearPreviousRecording.setVisible(true);

		btnBegin.setVisible(true);
		correct.setVisible(true);
		lblAttempts.setVisible(true);
		lblScore.setVisible(true);
		lblhighScore.setVisible(true);
		if(correctAttempt > highScore) {
			lblPersonalBest.setVisible(true);
			lblhighScore.setText("High Score: "+correctAttempt+"/10");
		}
		skip.setVisible(false);
	}
	
	//Shows the final display which shows the user your end score and a list of 
	//Pronunciation you said correct or incorrectly
	public void finalDisplay() {
		btnRecord.setVisible(false);
		lblNewLabel.setVisible(false);
		btnBegin.setVisible(false);
		btnPlay.setVisible(false);
		lblHearPreviousRecording.setVisible(false);
		lblScore.setVisible(false);
		lblhighScore.setVisible(false);
		lblPersonalBest.setVisible(false);
		lblAttempts.setVisible(false);
		progressBar.setVisible(false);
		answerField.setVisible(true);
		skip.setVisible(false);
		lblPersonalBest.setVisible(false);
		btnBack.setVisible(false);

		correct.setVisible(true);
		mainMenu.setVisible(true);
		scrollPane.setVisible(true);
	}
	
	
	//Swing worker class which is responsible for setting the recording job 
	//into  the doInBackground thread
	public class Worker extends SwingWorker<String, Void> {
		
		
		//Edit the done method to see if the user has said the correct pronounciation or not
		protected void done() {
			btnRecord.setEnabled(true);
			progressBar.setValue(0);

			String saidNumber = "";

			try {
				saidNumber = get();
			} catch (InterruptedException | ExecutionException e) {

				e.printStackTrace();
			}
			
			
			//is called if the user is correct
			//Sets the labels as which show the user is correct
			if(saidNumber.equals(maoriNumber.outputMaoriNumber())) {

				AttemptDisplay();
				btnBegin.setText("Next");
				correct.setText("You are correct");
				totalAttempts++;
				correctAttempt++;
				wrongAttempt= 0;
				answerField.append("Question: " + totalAttempts + ") " + testNumber + "     " + "Correct ✔ \n\n");

				
				//is called if no recording is heard from the user, 
				//still counts as an in correct attempt
			} else if(saidNumber.equals("")) {
				
				//If the user says nothing the first time, they are given 
				//1 more chance
				if (wrongAttempt == 1) {

					AttemptDisplay();
					totalAttempts++;
					correct.setText("No recording found, try next number");
					btnBegin.setText("Continue");
					wrongAttempt= 0;
					btnBegin.setVisible(true);
					answerField.append("Question: " + totalAttempts + ") " + testNumber + "     " + "Incorrect ✖ \n\n");
				}
				else {
					
					//Sets the "incorrect" display and prompts the
					//user to continue to the next question
					AttemptDisplay();
					correct.setText("No recording heard, one more try.");
					btnBegin.setText("Try Again");
					wrongAttempt++;


				}
			
			//called if the user has pronounced the number incorrectly
			} else {
				
				// User has one more chance if the number they pronounce is incorrect
				if (wrongAttempt == 1) {

					AttemptDisplay();
					totalAttempts++;
					correct.setText("<html>Wrong again, you said "+saidNumber+"</html>");
					correct.setHorizontalTextPosition(SwingConstants.CENTER);
					wrongAttempt = 0;
					btnBegin.setText("Continue");
					answerField.append("Question: " + totalAttempts + ") " + testNumber + "     " + "Incorrect ✖ \n\n");

				}
				else {
					
					//If the user has been given 2 chances and failed both 
					//prompt the user to continue to the next question
					AttemptDisplay();
					correct.setText("<html>Wrong, you said "+saidNumber+", one more chance</html>");
					btnBegin.setText("Try Again");
					wrongAttempt++;
				}
			}
		}
		
		//Do in background method returns a string which represents 
		//what HTK has processed their recording to be
		protected String doInBackground() throws Exception {

			btnRecord.setEnabled(false);
			skip.setVisible(false);
			BashCommands commands = BashCommands.getInstance();
			String saidNumber = commands.excecuteGoScript();



			return saidNumber;

		}
	}
	
	
	//Sets up the begin listener 
	public class ButtonBeginListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			
			//If this button is pressed at the beginning stage it will 
			//set up the question display 
			if (btnBegin.getText().equals("Start")) {
				testNumber = setNum();
				maoriNumber.setNumber(testNumber);
				QuestionDisplay();
				
				
			//Gives the user another try, removes the failed recording	
			} else if(btnBegin.getText().equals("Try Again")) {

				QuestionDisplay();

				BashCommands commands = BashCommands.getInstance();
				commands.remove();
			}
			
			//If the user gets 2 incorrect tries or gets it right
			else if(btnBegin.getText().equals("Next") || btnBegin.getText().equals("Continue")) {
				
				
				//If the user has completed all 10 questions
				//Display the final screen with the score log and total summary 
				if(totalAttempts == 10) {
					BashCommands commands = BashCommands.getInstance();
					commands.addStats(correctAttempt, name);
					correct.setText("You scored "+ correctAttempt + "/10");
					lblAttempts.setVisible(false);

					finalDisplay();
					
					
				//increments the question number and set the next question
				} else {
					testNumber = setNum();
					maoriNumber.setNumber(testNumber);
					skip.setVisible(true);

					displayAttempts = totalAttempts+1;
					lblAttempts.setText("Question# " + displayAttempts);
					lblScore.setText("Score: "+correctAttempt+"/10");

					QuestionDisplay();
				}
				
				//either case remove the old recording
				BashCommands commands = BashCommands.getInstance();
				commands.remove();
			}
		}

	}
	
	//Sets up the menu button 
	public class ButtonMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new MainGUI();
			dispose();

		}
	}

	
	//Sets up the record button 
	public class ButtonRecordListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			//Sets up the timer / progress button
			i = 0;
			t.start();
			
			//Execute the worker so the GUI doesn't freeze
			Worker handler = new Worker();
			handler.execute();




		}
	}
	
	//Sets up the play button listener which plays the current recording 
	public class ButtonPlayListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			btnBegin.setEnabled(false);
			btnPlay.setEnabled(false);
			PlayWorker player = new PlayWorker();
			player.execute();



		}
	}
	
	//Sets up the back button which takes you to the main menu 
	public class ButtonBackListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back? Your current progress will not be saved.","Back", JOptionPane.YES_NO_OPTION);			
				
				if (YesOrNo == 1) {
					return;
				}
				
				else if (YesOrNo == 0)  {
					new MainGUI();
					dispose();
				}
				
				else {
					return;
				}
				
			}
	}

	//Sets up Skip listener
	public class ButtonSkipListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			//appends the log
			answerField.append("Question: " + displayAttempts + ") " + testNumber + "     " + "Incorrect ✖ \n\n");
			totalAttempts++;
			
			//if the question limit has been reached go to the final screen
			if(totalAttempts == 10) {
				BashCommands commands = BashCommands.getInstance();
				commands.addStats(correctAttempt, name);
				correct.setText("You scored "+ correctAttempt + "/10");
				lblAttempts.setVisible(false);

				finalDisplay();
			} else {
				testNumber = setNum();
				wrongAttempt = 0;
				maoriNumber.setNumber(testNumber);
				displayAttempts = totalAttempts+1;
				lblAttempts.setText("Question# " + displayAttempts);
				lblScore.setText("Score: "+correctAttempt+"/10");


				QuestionDisplay();
			}




		}
	}

	
	// SwingWorker class which places the recording process into 
	//another thread so that the main thread is not busy with the sub process
	//doing this prevents the GUI from freezing
	public class PlayWorker extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			
			//Calls the bash command class to set up the recording
			BashCommands commands = BashCommands.getInstance();
			commands.playback();
			return null;
		}

		@Override 
		protected void done() {
			btnBegin.setEnabled(true);
			btnPlay.setEnabled(true);
		}

	}










}
