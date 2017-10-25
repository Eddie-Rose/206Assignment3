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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;

import tatai.applications.StatsPanel;
import javax.swing.JScrollPane;

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
	int frameWidth = 1100;
	int frameHeight = 700;
	protected JButton btnBegin = new JButton("Start");
	protected JButton btnRecord = new JButton("Record");
	protected JButton btnBack;
	JLabel lblAdvancedLevel;
	protected JLabel lblNewLabel = new JLabel();
	protected JLabel correct = new JLabel();
	protected JLabel lblHearPreviousRecording;
	protected JLabel lblScore;
	protected JLabel lblhighScore;
	protected JLabel lblPersonalBest;
	protected JLabel lblAttempts;
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
	protected JScrollPane scrollPane;

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
		
//		try {
//            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./maori.jpg")))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
		

		lblAdvancedLevel = new JLabel(name);
		lblAdvancedLevel.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		lblAdvancedLevel.setBounds(373, 41, 335, 70);
		getContentPane().add(lblAdvancedLevel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 191, 594, 306);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(answerField);
		answerField.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
		
		scrollPane.setVisible(false);
		answerField.setEditable(false);


		txtrWelcomeToThe.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtrWelcomeToThe.setText("<html>Welcome to the "+name+" level,\nNumbers asked are from "+ minNum+" to "+ maxNum + " ,\nPress \"Start\" to begin.</html>");
		txtrWelcomeToThe.setBounds(237, 241, 561, 119);
		getContentPane().add(txtrWelcomeToThe);

		btnBegin.setBounds(250, 433, 531, 60);
		getContentPane().add(btnBegin);

		mainMenu.setVisible(false);
		mainMenu.setBounds(429, 535, 224, 78);
		getContentPane().add(mainMenu);

		btnRecord.setVisible(false);
		btnRecord.setBounds(429, 395, 173, 65);
		getContentPane().add(btnRecord);
		
		btnBack = new JButton("<<");
		btnBack.setVisible(false);
		btnBack.setBounds(30, 42, 60, 25);
		getContentPane().add(btnBack);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setVisible(false);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 90));
		lblNewLabel.setBounds(328, 165, 416, 154);
		getContentPane().add(lblNewLabel);



		lblScore = new JLabel("Score: "+correctAttempt+"/10");
		lblScore.setFont(new Font("Dialog", Font.BOLD, 17));
		lblScore.setVisible(false);
		lblScore.setBounds(798, 41, 128, 25);
		getContentPane().add(lblScore);


		String high = StatsPanel.getHighScore();
		highScore = Integer.parseInt(high.split("/")[0]);


		lblPersonalBest = new JLabel("Personal Best!");
		lblPersonalBest.setForeground(Color.RED);
		lblPersonalBest.setFont(new Font("Dialog", Font.BOLD, 17));
		lblPersonalBest.setVisible(false);
		lblPersonalBest.setBounds(938, 41, 150, 25);
		getContentPane().add(lblPersonalBest);

		lblhighScore = new JLabel("High Score: "+high);
		lblhighScore.setFont(new Font("Dialog", Font.BOLD, 17));
		lblhighScore.setVisible(false);
		lblhighScore.setBounds(760, 83, 166, 36);
		getContentPane().add(lblhighScore);

		lblAttempts = new JLabel("Question# " + displayAttempts );
		lblAttempts.setFont(new Font("Dialog", Font.BOLD, 17));
		lblAttempts.setVisible(false);
		//lblAttempts.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAttempts.setBounds(52, 630, 162, 36);
		getContentPane().add(lblAttempts);

		correct.setVisible(false);
		correct.setFont(new Font("Dialog", Font.PLAIN, 18));
		correct.setBounds(292, 124, 416, 50);
		correct.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(correct);

		btnPlay = new JButton("Play");
		btnPlay.setBounds(503, 567, 150, 36);
		getContentPane().add(btnPlay);
		btnPlay.setVisible(false);

		lblHearPreviousRecording = new JLabel("Hear previous recording:");
		lblHearPreviousRecording.setFont(new Font("Dialog", Font.BOLD, 17));
		lblHearPreviousRecording.setBounds(214, 569, 271, 31);
		getContentPane().add(lblHearPreviousRecording);
		lblHearPreviousRecording.setVisible(false);

		progressBar = new JProgressBar();
		progressBar.setBounds(280, 505, 509, 50);
		progressBar.setValue(0);
		getContentPane().add(progressBar);
		progressBar.setVisible(false);

		skip = new JButton("Skip");
		skip.setBounds(833, 292, 128, 41);
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
		resizableComp[14] = new Resizable(skip, frameWidth, frameHeight);
		resizableComp[15] = new Resizable(scrollPane, frameWidth, frameHeight);
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				for(Resizable comp : resizableComp) {
					comp.Resize(c.getWidth(), c.getHeight());
				}
				
				
			}
		});

		//getContentPane().
	}


	protected int setNum() {
		int random = (int )(Math.random() * maxNum + minNum);
		lblNewLabel.setText("" + random);
		return random;

	}


	public void QuestionDisplay() {
		testNumber = setNum();
		maoriNumber.setNumber(testNumber);
		
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
		scrollPane.setVisible(true);
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
				answerField.append("Question: " + totalAttempts + ") " + testNumber + "     " + "Correct ✔ \n\n");


			} else if(saidNumber.equals("")) {

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
					answerField.append("Question: " + totalAttempts + ") " + testNumber + "     " + "Incorrect ✖ \n\n");

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


			answerField.append("Question: " + displayAttempts + ") " + testNumber + "     " + "Incorrect ✖ \n\n");
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
