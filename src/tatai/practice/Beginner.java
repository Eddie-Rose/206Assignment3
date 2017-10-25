package tatai.practice;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

import tatai.main.Level;
import tatai.main.Level.ButtonBeginListener;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JProgressBar;

public class Beginner extends Level {

	JLabel finish;
	JButton finishButton;
	
	public Beginner(String name, int minNum, int maxNum) {
		super(name, minNum, maxNum);
		finish = new JLabel("Good Score! Go to Next Level:");
		finish.setFont(new Font("Dialog", Font.BOLD, 17));
		getContentPane().add(finish);
		finish.setBounds(291, 630, 299, 41);
		finish.setVisible(false);
		
		finishButton = new JButton("Advanced");
		getContentPane().add(finishButton);
		finishButton.setBounds(607, 629, 173, 41);
		finishButton.setVisible(false);
		
		
		finishButton.addActionListener(new ButtonFinishListener());
		
	}
	
	public void finalDisplay() {
		if(correctAttempt > 7) {
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
			skip.setVisible(false);
			lblPersonalBest.setVisible(false);
			btnBack.setVisible(false);
			
			correct.setVisible(true);
			mainMenu.setVisible(true);
			finish.setVisible(true);
			finishButton.setVisible(true);
			scrollPane.setVisible(true);

			answerField.setVisible(true);
		} else {
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
			skip.setVisible(false);
			lblPersonalBest.setVisible(false);
			btnBack.setVisible(false);

			correct.setVisible(true);
			mainMenu.setVisible(true);
			scrollPane.setVisible(true);

		}
		
	}
	
	public class ButtonFinishListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			dispose();
			Level advancedWindow = new Advanced("Advanced", 1, 99);
			
			

		}
	}
}




