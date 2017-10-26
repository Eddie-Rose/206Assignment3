package tatai.practice;

import java.awt.event.ActionEvent;
import tatai.main.Level;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 
 * Initialise the beginner level frame
 * extends the class Level and overrides the
 * final display method to display an option 
 * to go to the next level if the player is 
 * good enough  
 * 
 * @author Edwin Roesli and Harpreet Singh
 *
 */
public class Beginner extends Level {

	
	private static final long serialVersionUID = 1L;
	JLabel finish;
	JButton nextLevelButton;
	
	//Adds additional components to the level to provide the functionality 
	//for the advance level if the player gets a high enough scores
	public Beginner(String name, int minNum, int maxNum) {
		super(name, minNum, maxNum);
		finish = new JLabel("Good Score! Go to Next Level:");
		finish.setFont(new Font("Dialog", Font.BOLD, 17));
		getContentPane().add(finish);
		finish.setBounds(291, 630, 299, 41);
		finish.setVisible(false);
		
		nextLevelButton = new JButton("Advanced");
		getContentPane().add(nextLevelButton);
		nextLevelButton.setBounds(607, 629, 173, 41);
		nextLevelButton.setVisible(false);
		
		
		//adds the action listener 
		nextLevelButton.addActionListener(new ButtonFinishListener());
		
	}
	
	//Overrides the finalDisplay to set the advancement option 
	//if the score is high enough
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
			nextLevelButton.setVisible(true);
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
	
	//This button disposes this frame and sets up the advance level frame
	public class ButtonFinishListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			dispose();
			@SuppressWarnings("unused")
			Level advancedWindow = new Advanced("Advanced", 1, 99);
			
			

		}
	}
}




