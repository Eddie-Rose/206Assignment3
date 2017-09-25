package tatai;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;



public class Advanced extends JFrame {
	
	JButton btnBegin = new JButton("Start");
	JButton btnRecord = new JButton("Record");
	JLabel lblNewLabel = new JLabel();
	JLabel correct = new JLabel("You are correct");
	JTextArea txtrWelcomeToThe = new JTextArea();
	
	int correctAnswers = 0;
	int attempts = 0;
	Number maoriNumber = new Number();
	
	public Advanced() {
		
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
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
		
		btnRecord.setVisible(false);
		btnRecord.setBounds(158, 190, 117, 25);
		getContentPane().add(btnRecord);
		
		lblNewLabel.setVisible(false);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(205, 120, 150, 15);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				btnBegin.setVisible(false);
				txtrWelcomeToThe.setVisible(false);

				Random r = new Random();



				int testNumber = r.nextInt(89) + 10;
				maoriNumber.setNumber(testNumber);
				lblNewLabel.setText("" + testNumber);
				lblNewLabel.setVisible(true);
				btnRecord.setVisible(true);
				





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
								if(wordCount > 1) {
									saidNumber = saidNumber + " " + line;
								} else {
									saidNumber = saidNumber + line;

								}
								wordCount++;
							}
							
						}
						if(saidNumber.equals(maoriNumber.outputMaoriNumber())) {
							System.out.println("correct");
							/*
							 * make label of number and button disappear
							 * make label saying correct or wrong appear 
							 * make button saying next appear
							 * button press would make current things disappear and past things appear
							 */
						} else if(saidNumber.equals("")) {
							System.out.println("No number found");
							if (attempts == 1) {
								btnRecord.setVisible(false);
								lblNewLabel.setText("Incorrect, try next number");
								btnBegin.setText("Continue");
								btnBegin.setVisible(true);
							}
							else {
								lblNewLabel.setText("No recording heard, one more try.");
								attempts++;
								
							}
						} else {
							System.out.println("wrong");
							if (attempts == 1) {
								btnRecord.setVisible(false);
								lblNewLabel.setText("");
								btnBegin.setText("Continue");
								btnBegin.setVisible(true);
							}
							else {
								lblNewLabel.setText("Wrong pronounciation, one more chance");
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

	
	public class AdvancedTest extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			
			
		
				
			

			
			
			return null;
		}
		
		
	}
}




