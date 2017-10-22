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
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import tatai.applications.StatsPanel;

/**
 * 
 * Generic abstract class of the session that 
 * users use when answering the questions. 
 * It is extended and edit in the unique levels class where 
 * edit is needed
 *
 */

public abstract class Level extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String username;
	int testNumber;
	int minNum;
	int maxNum;
	int frameWidth = 450;
	int frameHeight = 300;
	protected JButton btnBegin = new JButton("Start");
	protected JButton btnRecord = new JButton("Record");
	protected JButton btnBack;
	JLabel lblAdvancedLevel;
	protected JLabel lblNewLabel = new JLabel();
	protected JLabel correct = new JLabel();
	protected JLabel lblHearPreviousRecording;
	protected JLabel lblScore;
	JLabel lblhighScore;
	JLabel lblPersonalBest;
	JLabel lblAttempts;
	protected JButton btnPlay;
	protected JLabel txtrWelcomeToThe = new JLabel();
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

	public Level(String lvlName, int minimumNum, int maximumNum) {
		
		name = lvlName;
		minNum = minimumNum;
		maxNum = maximumNum;
		
		//setResizable(false);
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, frameWidth, frameHeight);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		lblAdvancedLevel = new JLabel(name);
		lblAdvancedLevel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblAdvancedLevel.setBounds(160, 40, 192, 25);
		getContentPane().add(lblAdvancedLevel);

		answerField.setVisible(false);
		answerField.setEditable(false);
		getContentPane().add(answerField);


		txtrWelcomeToThe.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtrWelcomeToThe.setText("<html>Welcome to the "+name+" level,\nNumbers asked are from "+ minNum+" to "+ maxNum + " ,\nPress \"Start\" to begin.</html>");
		txtrWelcomeToThe.setBounds(103, 94, 264, 69);
		getContentPane().add(txtrWelcomeToThe);

		btnBegin.setBounds(158, 190, 117, 25);
		getContentPane().add(btnBegin);

		mainMenu.setVisible(false);
		mainMenu.setBounds(158, 190, 117, 25);
		getContentPane().add(mainMenu);

		btnRecord.setVisible(false);
		btnRecord.setBounds(158, 190, 117, 25);
		getContentPane().add(btnRecord);
		
		btnBack = new JButton("<<");
		btnBack.setVisible(false);
		btnBack.setBounds(30, 20, 60, 25);
		getContentPane().add(btnBack);

		lblNewLabel.setVisible(false);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		lblNewLabel.setBounds(205, 110, 150, 40);
		getContentPane().add(lblNewLabel);

		testNumber = setNum();
		maoriNumber.setNumber(testNumber);


		lblScore = new JLabel("Score: "+correctAttempt+"/10");
		lblScore.setVisible(false);
		lblScore.setBounds(349, 12, 89, 15);
		getContentPane().add(lblScore);


		String high = StatsPanel.getHighScore();
		highScore = Integer.parseInt(high.split("/")[0]);


		lblPersonalBest = new JLabel("Personal Best!");
		lblPersonalBest.setVisible(false);
		lblPersonalBest.setBounds(349, 12, 89, 15);
		getContentPane().add(lblPersonalBest);

		lblhighScore = new JLabel("High Score: "+high);
		lblhighScore.setVisible(false);
		lblhighScore.setBounds(349, 30, 89, 15);
		getContentPane().add(lblhighScore);

		lblAttempts = new JLabel("Question# " + displayAttempts );
		lblAttempts.setVisible(false);
		//lblAttempts.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAttempts.setBounds(30, 270, 110, 15);
		getContentPane().add(lblAttempts);

		correct.setVisible(false);
		correct.setFont(new Font("Dialog", Font.PLAIN, 14));
		correct.setBounds(50, 100, 350, 50);
		correct.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(correct);

		btnPlay = new JButton("Play");
		btnPlay.setBounds(250, 240, 117, 25);
		getContentPane().add(btnPlay);
		btnPlay.setVisible(false);

		lblHearPreviousRecording = new JLabel("Hear previous recording:");
		lblHearPreviousRecording.setBounds(60, 245, 176, 15);
		getContentPane().add(lblHearPreviousRecording);
		lblHearPreviousRecording.setVisible(false);

		progressBar = new JProgressBar();
		progressBar.setBounds(140, 230, 150, 25);
		progressBar.setValue(0);
		getContentPane().add(progressBar);
		progressBar.setVisible(false);

		skip = new JButton("Skip");
		skip.setBounds(300, 111, 117, 25);
		skip.setVisible(false);
		getContentPane().add(skip);



		btnBegin.addActionListener(new ButtonBeginListener());
		mainMenu.addActionListener(new ButtonMenuListener());
		btnRecord.addActionListener(new ButtonRecordListener());
		btnPlay.addActionListener(new ButtonPlayListener());
		skip.addActionListener(new ButtonSkipListener());
		btnBack.addActionListener(new ButtonBackListener());

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
		
		Resizable[] resizableComp = new Resizable[16];
		
		resizableComp[0] = new Resizable(btnBegin, frameWidth, frameHeight);
		resizableComp[1] = new Resizable(mainMenu, frameWidth, frameHeight);
		resizableComp[2] = new Resizable(btnRecord, frameWidth, frameHeight);
		resizableComp[3] = new Resizable(lblNewLabel, frameWidth, frameHeight);
		resizableComp[4] = new Resizable(lblScore, frameWidth, frameHeight);
		resizableComp[5] = new Resizable(lblPersonalBest, frameWidth, frameHeight);
		resizableComp[6] = new Resizable(lblhighScore, frameWidth, frameHeight);
		resizableComp[7] = new Resizable(lblAttempts, frameWidth, frameHeight);
		resizableComp[8] = new Resizable(lblAdvancedLevel, frameWidth, frameHeight);
		resizableComp[9] = new Resizable(correct, frameWidth, frameHeight);
		resizableComp[10] = new Resizable(txtrWelcomeToThe, frameWidth, frameHeight);
		resizableComp[11] = new Resizable(btnPlay, frameWidth, frameHeight);
		resizableComp[12] = new Resizable(lblHearPreviousRecording, frameWidth, frameHeight);
		resizableComp[13] = new Resizable(progressBar, frameWidth, frameHeight);
		resizableComp[14] = new Resizable(answerField, frameWidth, frameHeight);
		resizableComp[15] = new Resizable(skip, frameWidth, frameHeight);
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				for(Resizable comp : resizableComp) {
					comp.Resize(c.getWidth(), c.getHeight());
				}
				
				
			}
		});


	}


	protected int setNum() {
		int random = (int )(Math.random() * maxNum + minNum);
		lblNewLabel.setText("" + random);
		return random;

	}


	public void QuestionDisplay() {
		btnBegin.setVisible(false);
		txtrWelcomeToThe.setVisible(false);
		correct.setVisible(false);
		btnPlay.setVisible(false);
		lblHearPreviousRecording.setVisible(false);
		
		btnBack.setVisible(true);
		progressBar.setVisible(true);

		lblNewLabel.setVisible(true);
		btnRecord.setVisible(true);
		lblScore.setVisible(true);
		if(correctAttempt > highScore) {
			lblPersonalBest.setVisible(true);
			lblhighScore.setText("High Score: "+correctAttempt+"/10");
		}

		lblhighScore.setVisible(true);
		lblAttempts.setVisible(true);
		skip.setVisible(true);

	}

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
	}

	public class Worker extends SwingWorker<String, Void> {

		protected void done() {
			btnRecord.setEnabled(true);
			progressBar.setValue(0);

			String saidNumber = "";

			try {
				saidNumber = get();
			} catch (InterruptedException | ExecutionException e) {

				e.printStackTrace();
			}

			if(saidNumber.equals(maoriNumber.outputMaoriNumber())) {

				AttemptDisplay();
				btnBegin.setText("Next");
				correct.setText("You are correct");
				totalAttempts++;
				correctAttempt++;
				wrongAttempt= 0;
				answerField.append("Question: " + totalAttempts + ") " + testNumber + "     " + "correct :)\n");


			} else if(saidNumber.equals("")) {

				if (wrongAttempt == 1) {

					AttemptDisplay();
					totalAttempts++;
					correct.setText("No recording found, try next number");
					btnBegin.setText("Continue");
					wrongAttempt= 0;
					btnBegin.setVisible(true);
					answerField.append("Question: " + totalAttempts + ") " + testNumber + "     " + "incorrect :(\n");
				}
				else {
					AttemptDisplay();
					correct.setText("No recording heard, one more try.");
					btnBegin.setText("Try Again");
					wrongAttempt++;


				}
			} else {
				if (wrongAttempt == 1) {

					AttemptDisplay();
					totalAttempts++;
					correct.setText("<html>Wrong again, you said "+saidNumber+"</html>");
					correct.setHorizontalTextPosition(SwingConstants.CENTER);
					wrongAttempt = 0;
					btnBegin.setText("Continue");
					answerField.append("Question: " + totalAttempts + ") " + testNumber + "     " + "incorrect :(\n");

				}
				else {
					AttemptDisplay();
					correct.setText("<html>Wrong, you said "+saidNumber+", one more chance</html>");
					btnBegin.setText("Try Again");
					wrongAttempt++;
				}
			}
		}

		protected String doInBackground() throws Exception {

			btnRecord.setEnabled(false);
			BashCommands commands = BashCommands.getInstance();
			String saidNumber = commands.excecuteGoScript();



			return saidNumber;

		}
	}

	public class ButtonBeginListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {


			if (btnBegin.getText().equals("Start")) {

				QuestionDisplay();

			} else if(btnBegin.getText().equals("Try Again")) {

				QuestionDisplay();

				BashCommands commands = BashCommands.getInstance();
				commands.remove();
			}
			else if(btnBegin.getText().equals("Next") || btnBegin.getText().equals("Continue")) {
				if(totalAttempts == 10) {
					BashCommands commands = BashCommands.getInstance();
					commands.addStats(correctAttempt, name);
					correct.setText("You scored "+ correctAttempt + "/10");
					lblAttempts.setVisible(false);

					finalDisplay();
				} else {
					testNumber = setNum();
					maoriNumber.setNumber(testNumber);

					displayAttempts = totalAttempts+1;
					lblAttempts.setText("Question# " + displayAttempts);
					lblScore.setText("Score: "+correctAttempt+"/10");

					QuestionDisplay();
				}

				BashCommands commands = BashCommands.getInstance();
				commands.remove();
			}
		}

	}

	public class ButtonMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			dispose();

		}
	}

	public class ButtonRecordListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			i = 0;
			t.start();
			//btn.setEnabled(false);

			Worker handler = new Worker();
			handler.execute();




		}
	}

	public class ButtonPlayListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			btnBegin.setEnabled(false);
			btnPlay.setEnabled(false);
			PlayWorker player = new PlayWorker();
			player.execute();



		}
	}
	
	public class ButtonBackListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back? Your current progress will not be saved.","Back", JOptionPane.YES_NO_OPTION);			
				
				if (YesOrNo == 1) {
					return;
				}
				
				else if (YesOrNo == 0)  {
					dispose();
				}
				
				else {
					return;
				}
				
			}
	}


	public class ButtonSkipListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {


			answerField.append("Question: " + displayAttempts + ") " + testNumber + "     " + "incorrect :(\n");
			totalAttempts++;

			if(totalAttempts == 10) {
				BashCommands commands = BashCommands.getInstance();
				commands.addStats(correctAttempt, name);
				correct.setText("You scored "+ correctAttempt + "/10");
				lblAttempts.setVisible(false);

				finalDisplay();
			} else {
				testNumber = setNum();
				maoriNumber.setNumber(testNumber);
				displayAttempts = totalAttempts+1;
				lblAttempts.setText("Question# " + displayAttempts);
				lblScore.setText("Score: "+correctAttempt+"/10");


				QuestionDisplay();
			}




		}
	}

	public class PlayWorker extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {

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
