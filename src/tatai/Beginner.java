package tatai;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		
		int random = (int )(Math.random() * 2 + 1);
		Number moariNumber = new Number(random);

		JLabel lblLabel = new JLabel(""+random);
		lblLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 30));
		lblLabel.setBounds(187, 66, 48, 64);
		contentPane.add(lblLabel);
		
		JLabel lblDfasf = new JLabel("Score:");
		lblDfasf.setBounds(27, 214, 70, 15);
		contentPane.add(lblDfasf);
		
		JLabel label = new JLabel("0/10");
		label.setBounds(90, 214, 70, 15);
		contentPane.add(label);
		
		JButton btnRecord = new JButton("record");
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
								if(wordCount > 1) {
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
						} else if(saidNumber.equals("")) {
							System.out.println("No number found");
						} else {
							System.out.println("wrong");
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
}
