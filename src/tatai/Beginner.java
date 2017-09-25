package tatai;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class Beginner extends JFrame {

	private JPanel contentPane;
	JButton btnBegin = new JButton("Start");
	JButton btnRecord = new JButton("record");
	JLabel lblYouAreCorrect = new JLabel("You are Correct!");
	JTextArea txtrWelcomeToThe;
	JLabel lblLabel;
	JLabel BeginnerLevel = new JLabel("Beginner Level");
	int wrongAttempt = 0;
	int rightAttempt = 0;
	int totalAttempt = 0;
	int random;
	Number moariNumber;
	/**
	 * Create the frame.
	 */
	public Beginner() {
		setTitle("Beginner Level");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		random = (int )(Math.random() * 9 + 1);
		moariNumber = new Number(random);
		
		JLabel BeginnerLevel = new JLabel("Beginner Level");
		BeginnerLevel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		BeginnerLevel.setBounds(128, 40, 192, 18);
		getContentPane().add(BeginnerLevel);
		
		txtrWelcomeToThe = new JTextArea();
		txtrWelcomeToThe.setEnabled(false);
		txtrWelcomeToThe.setEditable(false);
		txtrWelcomeToThe.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtrWelcomeToThe.setText("Welcome to the beginner level,\nNumbers asked are from 1 to 9,\nPress \"Start\" to begin.");
		txtrWelcomeToThe.setBounds(103, 94, 264, 69);
		getContentPane().add(txtrWelcomeToThe);

		lblLabel = new JLabel(""+random);
		lblLabel.setVisible(false);
		lblLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 30));
		lblLabel.setBounds(187, 66, 48, 64);
		contentPane.add(lblLabel);
		
		btnBegin.setBounds(158, 190, 117, 25);
		getContentPane().add(btnBegin);
		
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (btnBegin.getText().equals("Start") || btnBegin.getText().equals("Try Again")) {
					
					QuestionDisplay();
					
				} else if(btnBegin.getText().equals("Next") || btnBegin.getText().equals("Continue")) {
					if(totalAttempt == 10) {
						lblYouAreCorrect.setText("You scored "+ rightAttempt + "/10");
						finalDisplay();
					} else {
					random = (int )(Math.random() * 9 + 1);
					moariNumber = new Number(random);
					lblLabel.setText(""+random);
					
					QuestionDisplay();
					}
					
				}
			}
		});
		
		JLabel lblDfasf = new JLabel("Score:");
		lblDfasf.setVisible(false);
		lblDfasf.setBounds(27, 214, 70, 15);
		contentPane.add(lblDfasf);
		
		lblYouAreCorrect.setBounds(158, 95, 128, 15);
		lblYouAreCorrect.setVisible(false);
		contentPane.add(lblYouAreCorrect);
		
		JLabel label = new JLabel("0/10");
		label.setVisible(false);
		label.setBounds(90, 214, 70, 15);
		contentPane.add(label);
		
		
		btnRecord.setVisible(false);
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String command = "cd MaoriNumbers ; ./Go";
					ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

					Process process = pb.start();

					BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
					BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

					int exitStatus = process.waitFor();

					if (exitStatus == 0) {
						String line;
						boolean found = false;
						String saidNumber = "";
						int wordCount = 0;
						while ((line = stdout.readLine()) != null) {
							if(line.equals("sil")) {
								found = !found;
							}
							if(found == true && !line.equals("sil")) {
								if(wordCount > 0) {
									saidNumber = saidNumber + " " + line;
								} else {

								saidNumber = saidNumber + line;

								}
								wordCount++;
							}
							
						}
						
						
						
						if(saidNumber.equals(moariNumber.outputMaoriNumber())) {
							System.out.println("correct");
							/*
							 * make label of number and button disappear
							 * make label saying correct or wrong appear 
							 * make button saying next appear
							 * button press would make current things disappear and past things appear
							 */
							AttemptDisplay();
							
							lblYouAreCorrect.setText("You are correct!");
							btnBegin.setText("Next");
							rightAttempt++;
							totalAttempt++;
						} else if(saidNumber.equals("")) {
							System.out.println("No number found");
							
							AttemptDisplay();
							if(wrongAttempt > 0) {
								lblYouAreCorrect.setText("Wrong Again!");
								btnBegin.setText("Continue");
								wrongAttempt = 0;
								totalAttempt++;
								
							} else {
								lblYouAreCorrect.setText("No number heard!");
								btnBegin.setText("Try Again");
								wrongAttempt++;
							}
						} else {
							System.out.println("wrong");
							AttemptDisplay();
							if(wrongAttempt > 0) {
								lblYouAreCorrect.setText("Wrong Again!");
								btnBegin.setText("Continue");
								wrongAttempt = 0;
								totalAttempt++;
								
							} else {
								lblYouAreCorrect.setText("That's wrong!");
								btnBegin.setText("Try Again");
								wrongAttempt++;
								
							}
							
						}
					} else {
						String line;
						while ((line = stderr.readLine()) != null) {
							System.err.println(line);
						}
					}

				} catch (Exception g) {
					g.printStackTrace();
				}
				
				
			}
		});
		btnRecord.setForeground(Color.BLACK);
		btnRecord.setBackground(Color.WHITE);
		btnRecord.setBounds(173, 120, 48, 25);
		btnRecord.setBorder(null);
		contentPane.add(btnRecord);
		
	}
	public void QuestionDisplay() {
		btnBegin.setVisible(false);
		lblYouAreCorrect.setVisible(false);	
		btnBegin.setVisible(false);
		txtrWelcomeToThe.setVisible(false);
		
		lblLabel.setVisible(true);
		btnRecord.setVisible(true);
		BeginnerLevel.setVisible(true);
	}
	
	public void AttemptDisplay() {
		btnRecord.setVisible(false);
		lblLabel.setVisible(false);
		BeginnerLevel.setVisible(false);
		
		btnBegin.setVisible(true);
		lblYouAreCorrect.setVisible(true);
	}
	
	public void finalDisplay() {
		btnRecord.setVisible(false);
		lblLabel.setVisible(false);
		BeginnerLevel.setVisible(false);
		btnBegin.setVisible(false);
		
		lblYouAreCorrect.setVisible(true);
	}
	
}
