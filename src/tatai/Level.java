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

	
		
	JButton btnBegin = new JButton("Start");
	JButton btnRecord = new JButton("Record");
	JLabel lblNewLabel = new JLabel();
	JLabel correct = new JLabel();
	JTextArea txtrWelcomeToThe = new JTextArea();
	JButton mainMenu = new JButton("Main Menu");
	
	
	int attempts = 0;
	int correctAttempt = 0;
	int totalAttempts = 0;
	Number maoriNumber = new Number();
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Level(String name, int minNum, int maxNum) {
		
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAdvancedLevel = new JLabel(name);
		lblAdvancedLevel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblAdvancedLevel.setBounds(150, 40, 192, 18);
		getContentPane().add(lblAdvancedLevel);
		
		
		txtrWelcomeToThe.setEnabled(false);
		txtrWelcomeToThe.setEditable(false);
		txtrWelcomeToThe.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtrWelcomeToThe.setText("Welcome to the "+name+" level,\nNumbers asked are from "+ minNum+" to "+ maxNum + " ,\nPress \"Start\" to begin.");
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
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(205, 120, 150, 15);
		getContentPane().add(lblNewLabel);
		
		int testNumber = setNum();
		maoriNumber.setNumber(testNumber);
		lblNewLabel.setText("" + testNumber);
		
		correct.setVisible(false);
		correct.setFont(new Font("Dialog", Font.PLAIN, 14));
		correct.setBounds(50, 120, 350, 15);
		correct.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(correct);
		
		
		
		
		
		
		btnBegin.addActionListener(new ButtonBeginListener());
		mainMenu.addActionListener(new ButtonMenuListener());
		btnRecord.addActionListener(new ButtonRecordListener());
			
		

	}
	

	public abstract int setNum();

	
	public void QuestionDisplay() {
		btnBegin.setVisible(false);
		txtrWelcomeToThe.setVisible(false);
		correct.setVisible(false);
		
		lblNewLabel.setVisible(true);
		btnRecord.setVisible(true);
		
		
	}
	
	public void AttemptDisplay() {
		btnRecord.setVisible(false);
		lblNewLabel.setVisible(false);
		
		btnBegin.setVisible(true);
		correct.setVisible(true);
		
	}
	
	public void finalDisplay() {
		btnRecord.setVisible(false);
		lblNewLabel.setVisible(false);
		btnBegin.setVisible(false);
		
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
			
			System.out.println(saidNumber);
			System.out.println(maoriNumber.outputMaoriNumber());
			if(saidNumber.equals(maoriNumber.outputMaoriNumber())) {
				System.out.println("correct");
				

				AttemptDisplay();
				btnBegin.setText("Next");
				correct.setText("You are correct");
				totalAttempts++;
				correctAttempt++;


			} else if(saidNumber.equals("")) {
				System.out.println("No number found");
				if (attempts == 1) {

					AttemptDisplay();
					totalAttempts++;
					correct.setText("No recording found, try next number");
					btnBegin.setText("Continue");
					attempts= 0;
					btnBegin.setVisible(true);
				}
				else {
					AttemptDisplay();
					correct.setText("No recording heard, one more try.");
					btnBegin.setText("Try Again");
					attempts++;
					

				}
			} else {
				System.out.println("wrong");
				if (attempts == 1) {

					AttemptDisplay();
					totalAttempts++;
					correct.setText("Wrong again");
					correct.setHorizontalTextPosition(SwingConstants.CENTER);
					attempts = 0;
					btnBegin.setText("Continue");

				}
				else {
					AttemptDisplay();
					correct.setText("Wrong pronounciation, one more chance");
					btnBegin.setText("Try Again");
					attempts++;

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


			if (btnBegin.getText().equals("Start") || btnBegin.getText().equals("Try Again")) {

				QuestionDisplay();
			}
			else if(btnBegin.getText().equals("Next") || btnBegin.getText().equals("Continue")) {
				if(totalAttempts == 10) {
					correct.setText("You scored "+ correctAttempt + "/10");
					finalDisplay();
				} else {
					int testNumber = setNum();
					maoriNumber.setNumber(testNumber);
					lblNewLabel.setText("" + testNumber);

					QuestionDisplay();
				}

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
		
	
	
	
	
	
	
	
	
	
	
	
}
