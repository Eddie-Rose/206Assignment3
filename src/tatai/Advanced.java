package tatai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class Advanced extends JFrame {
	
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
	Random r = new Random();
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Advanced() {
		
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAdvancedLevel = new JLabel("Advanced Level");
		lblAdvancedLevel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblAdvancedLevel.setBounds(128, 40, 192, 18);
		getContentPane().add(lblAdvancedLevel);
		
		
		txtrWelcomeToThe.setEnabled(false);
		txtrWelcomeToThe.setEditable(false);
		txtrWelcomeToThe.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtrWelcomeToThe.setText("Welcome to the advanced level,\nNumbers asked are from 10 to 99,\nPress \"Start\" to begin.");
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
		
		int testNumber = r.nextInt(89) + 10;
		maoriNumber.setNumber(testNumber);
		lblNewLabel.setText("" + testNumber);
		
		correct.setVisible(false);
		correct.setFont(new Font("Dialog", Font.PLAIN, 14));
		correct.setBounds(50, 120, 350, 15);
		correct.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(correct);
		
		
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if (btnBegin.getText().equals("Start") || btnBegin.getText().equals("Try Again")) {

					QuestionDisplay();
				}
				else if(btnBegin.getText().equals("Next") || btnBegin.getText().equals("Continue")) {
					if(totalAttempts == 2) {
						correct.setText("You scored "+ correctAttempt + "/10");
						finalDisplay();
					} else {
						int testNumber = r.nextInt(89) + 10;
						maoriNumber.setNumber(testNumber);
						lblNewLabel.setText("" + testNumber);
					
					QuestionDisplay();
					}
					
				}
			}
		});
		
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			dispose();
				
			}
		});
		
	

		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				btnRecord.setEnabled(false);
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
						System.out.println(saidNumber);
						System.out.println(maoriNumber.outputMaoriNumber());
						if(saidNumber.equals(maoriNumber.outputMaoriNumber())) {
							System.out.println("correct");
							/*
							 * make label of number and button disappear
							 * make label saying correct or wrong appear 
							 * make button saying next appear
							 * button press would make current things disappear and past things appear
							 */
							
							AttemptDisplay();
							btnBegin.setText("Next");
							correct.setText("You are correct");
							totalAttempts++;
							correctAttempt++;
							
							
						} else if(saidNumber.equals("")) {
							System.out.println("No number found");
							if (attempts == 1) {
								
								AttemptDisplay();
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
								totalAttempts++;
								
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
					} else {
						String line;
						while ((line = stderr.readLine()) != null) {
							System.err.println(line);
						}
					}

				} catch (Exception g) {
					g.printStackTrace();
				}
				btnRecord.setEnabled(true);
			
			}
		});
		
	}

	
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
}




