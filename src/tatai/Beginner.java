package tatai;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import tatai.Level.ButtonBeginListener;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Beginner extends Level {

	JLabel finish;
	JButton finishButton;
	
	public Beginner(String name, int minNum, int maxNum) {
		super(name, minNum, maxNum);
		finish = new JLabel("Good Score! Go to Next Level:");
		getContentPane().add(finish);
		finish.setBounds(120, 140, 250, 25);
		finish.setVisible(false);
		
		finishButton = new JButton("Advanced");
		getContentPane().add(finishButton);
		finishButton.setBounds(158, 165, 117, 25);
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
			
			correct.setVisible(true);
			mainMenu.setVisible(true);
			finish.setVisible(true);
			finishButton.setVisible(true);
		} else {
			btnRecord.setVisible(false);
			lblNewLabel.setVisible(false);
			btnBegin.setVisible(false);
			btnPlay.setVisible(false);
			lblHearPreviousRecording.setVisible(false);
			lblScore.setVisible(false);
			
			correct.setVisible(true);
			mainMenu.setVisible(true);
			finish.setVisible(false);
			finishButton.setVisible(false);
		}
		
	}
	
	public class ButtonFinishListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			dispose();
			Level advancedWindow = new Advanced("Advanced", 1, 99);
			
			

		}
	}
}




