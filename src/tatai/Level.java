package tatai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import tatai.Level.Worker;



public abstract class Level extends JFrame {
	
	
	String name;
	int minNum;
	int maxNum;
	JButton btnBegin = new JButton("Start");
	JButton btnRecord = new JButton("Record");
	JLabel lblNewLabel = new JLabel();
	JLabel correct = new JLabel();
	JLabel lblHearPreviousRecording;
	JLabel lblScore;
	JLabel lblAttempts;
	JButton btnPlay;
	JLabel txtrWelcomeToThe = new JLabel();
	JButton mainMenu = new JButton("Main Menu");
	
	int wrongAttempt = 0;
	int correctAttempt = 0;
	int totalAttempts = 0;
	int displayAttempts = totalAttempts+1;
	Number maoriNumber = new Number();
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Level(String lvlName, int minimumNum, int maximumNum) {
		
		name = lvlName;
		minNum = minimumNum;
		maxNum = maximumNum;
		setResizable(false);
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAdvancedLevel = new JLabel(name);
		lblAdvancedLevel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblAdvancedLevel.setBounds(160, 40, 192, 25);
		getContentPane().add(lblAdvancedLevel);
		
		
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
		
		lblNewLabel.setVisible(false);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		lblNewLabel.setBounds(205, 110, 150, 40);
		getContentPane().add(lblNewLabel);
		
		int testNumber = setNum();
		maoriNumber.setNumber(testNumber);
		lblNewLabel.setText("" + testNumber);
		
		lblScore = new JLabel("Score: "+correctAttempt+"/10");
		lblScore.setVisible(false);
		lblScore.setBounds(349, 12, 89, 15);
		getContentPane().add(lblScore);
		
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
		
		
		
		
		btnBegin.addActionListener(new ButtonBeginListener());
		mainMenu.addActionListener(new ButtonMenuListener());
		btnRecord.addActionListener(new ButtonRecordListener());
		btnPlay.addActionListener(new ButtonPlayListener());	
		

	}
	

	public int setNum() {
		int random = (int )(Math.random() * maxNum + minNum);
		return random;

	}

	
	public void QuestionDisplay() {
		btnBegin.setVisible(false);
		txtrWelcomeToThe.setVisible(false);
		correct.setVisible(false);
		btnPlay.setVisible(false);
		lblHearPreviousRecording.setVisible(false);
		
		lblNewLabel.setVisible(true);
		btnRecord.setVisible(true);
		lblScore.setVisible(true);
		lblAttempts.setVisible(true);
		
	}
	
	public void AttemptDisplay() {
		btnRecord.setVisible(false);
		lblNewLabel.setVisible(false);
		
		btnPlay.setVisible(true);
		lblHearPreviousRecording.setVisible(true);
		
		btnBegin.setVisible(true);
		correct.setVisible(true);
		lblAttempts.setVisible(true);
		lblScore.setVisible(true);
	}
	
	public void finalDisplay() {
		btnRecord.setVisible(false);
		lblNewLabel.setVisible(false);
		btnBegin.setVisible(false);
		btnPlay.setVisible(false);
		lblHearPreviousRecording.setVisible(false);
		lblScore.setVisible(false);
		lblAttempts.setVisible(false);
		
		correct.setVisible(true);
		mainMenu.setVisible(true);
	}
	
	public class Worker extends SwingWorker<String, Void> {
		
		protected void done() {
			btnRecord.setEnabled(true);
			
			String saidNumber = "";
			
			try {
				saidNumber = get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(saidNumber.equals(maoriNumber.outputMaoriNumber())) {
				
				AttemptDisplay();
				btnBegin.setText("Next");
				correct.setText("You are correct");
				totalAttempts++;
				correctAttempt++;
				wrongAttempt= 0;
				

			} else if(saidNumber.equals("")) {
				
				if (wrongAttempt == 1) {

					AttemptDisplay();
					totalAttempts++;
					correct.setText("No recording found, try next number");
					btnBegin.setText("Continue");
					wrongAttempt= 0;
					btnBegin.setVisible(true);
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
					int testNumber = setNum();
					maoriNumber.setNumber(testNumber);
					lblNewLabel.setText("" + testNumber);
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
