package tatai.math;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import tatai.main.BashCommands;
import tatai.main.Level;
import tatai.main.UserHandler;

public class CustomSetCreation  {

	private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private static JFrame customQuestionFrame = null;


	private int questionNumber = 0;
	private JLabel welcomingLabel;
	private JLabel requirementLabel;
	private JTextField nameOfSet;
	private JButton btnCreateSet;
	private JLabel lblCreateYourOwn; 
	private JLabel lblLeftParameter;
	private JLabel lblOperation;
	private JLabel lblRightParameter;
	private JTextField leftParameter;
	private JTextField rightParameter; 
	private JButton btnNext;
	private JButton btnMenu ;
	private JLabel lblQuestionX;
	private JTextField operation;
	private JButton btnMultiply;
	private JButton btnDivide;
	private JButton btnSubtract;
	private JButton btnAddition;

	private CustomSetCreation() {


		createCustomQuestionFrame();

	}


	private static CustomSetCreation instance;




	public static void getInstance(){

		if (customQuestionFrame == null) {

			instance = new CustomSetCreation();
			return;

		}
		else  {


			customQuestionFrame.setVisible(true);
			return;

		}
	}



	private void createCustomQuestionFrame() {


		questionNumber = 1;

		customQuestionFrame = new JFrame();
		customQuestionFrame.setResizable(false);
		customQuestionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		customQuestionFrame.setBounds(100, 100, 1100, 700);
		customQuestionFrame.setLocation(dim.width/2-customQuestionFrame.getSize().width/2, dim.height/2-customQuestionFrame.getSize().height/2);

		customQuestionFrame.setLayout(null);

		welcomingLabel = new JLabel("<html>Name your new set: </html>");
		welcomingLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		welcomingLabel.setBounds(0, 200, 1100, 50);
		welcomingLabel.setHorizontalAlignment(JLabel.CENTER);
		customQuestionFrame.getContentPane().add(welcomingLabel);



		requirementLabel = new JLabel("<html>Your set name must only consist of letters and numbers </html>");
		requirementLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		requirementLabel.setBounds(0, 223, 1100, 50);
		requirementLabel.setHorizontalAlignment(JLabel.CENTER);
		customQuestionFrame.getContentPane().add(requirementLabel);


		nameOfSet = new JTextField();
		nameOfSet.setBounds(158, 260, 800, 150);
		nameOfSet.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		nameOfSet.setHorizontalAlignment(JLabel.CENTER);
		nameOfSet.setTransferHandler(null);
		nameOfSet.addKeyListener(new KeyTypedListener());
		customQuestionFrame.getContentPane().add(nameOfSet);


		btnCreateSet = new JButton("Create Set");
		btnCreateSet.setBounds(450, 450, 200, 70);
		btnCreateSet.addActionListener (new CreateSetListener());
		customQuestionFrame.getContentPane().add(btnCreateSet);




		lblCreateYourOwn = new JLabel("Create your own custom questions!");
		lblCreateYourOwn.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		lblCreateYourOwn.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateYourOwn.setBounds(150, 37, 800, 33);
		customQuestionFrame.getContentPane().add(lblCreateYourOwn);


		lblLeftParameter = new JLabel("Left parameter");
		lblLeftParameter.setBounds(200, 115, 114, 15);
		customQuestionFrame.getContentPane().add(lblLeftParameter);
		lblLeftParameter.setVisible(false);

		lblOperation = new JLabel("operation");
		lblOperation.setBounds(520, 115, 91, 15);
		customQuestionFrame.getContentPane().add(lblOperation);
		lblOperation.setVisible(false);


		lblRightParameter = new JLabel("Right parameter");
		lblRightParameter.setBounds(832, 115, 134, 15);
		customQuestionFrame.getContentPane().add(lblRightParameter);
		lblRightParameter.setVisible(false);


		leftParameter = new JTextField();
		leftParameter.setBounds(158, 229, 217, 162);
		leftParameter.setHorizontalAlignment(SwingConstants.CENTER);
		leftParameter.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		customQuestionFrame.getContentPane().add(leftParameter);
		leftParameter.addKeyListener(new ParameterKeyListener());	
		leftParameter.setTransferHandler(null);
		leftParameter.setVisible(false);


		rightParameter = new JTextField();
		rightParameter.setTransferHandler(null);
		rightParameter.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		rightParameter.setBounds(793, 229, 217, 162);
		rightParameter.setHorizontalAlignment(SwingConstants.CENTER);
		rightParameter.addKeyListener(new ParameterKeyListener());	
		customQuestionFrame.getContentPane().add(rightParameter);
		rightParameter.setVisible(false);


		btnNext = new JButton("Next");
		btnNext.setBounds(477, 537, 233, 51);
		customQuestionFrame.getContentPane().add(btnNext);
		btnNext.setVisible(false);
		btnNext.addActionListener(new NextListener());




		btnMenu = new JButton("Exit");
		btnMenu.setBounds(932, 635, 117, 25);
		customQuestionFrame.getContentPane().add(btnMenu);
		btnMenu.addActionListener(new menuListener());




		lblQuestionX = new JLabel("Question: "+questionNumber+" /10");
		lblQuestionX.setBounds(903, 46, 134, 15);
		customQuestionFrame.getContentPane().add(lblQuestionX);
		lblQuestionX.setVisible(false);


		operation = new JTextField();
		operation.setBounds(520, 282, 114, 86);
		customQuestionFrame.getContentPane().add(operation);
		operation.setColumns(2);
		operation.setVisible(false);

		btnMultiply = new JButton("X");
		btnMultiply.setBounds(510, 260, 50,50);
		customQuestionFrame.getContentPane().add(btnMultiply);
		btnMultiply.setVisible(false);
		btnMultiply.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {

				btnMultiply.setEnabled(false);
				btnAddition.setEnabled(true);
				btnSubtract.setEnabled(true);
				btnDivide.setEnabled(true);

			}
		});


		btnDivide = new JButton("/");
		btnDivide.setBounds(570, 260, 50,50);
		customQuestionFrame.getContentPane().add(btnDivide);
		btnDivide.setVisible(false);
		btnDivide.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {

				btnMultiply.setEnabled(true);
				btnAddition.setEnabled(true);
				btnSubtract.setEnabled(true);
				btnDivide.setEnabled(false);

			}
		});


		btnAddition = new JButton("+");
		btnAddition.setBounds(510, 320, 50,50);
		customQuestionFrame.getContentPane().add(btnAddition);
		btnAddition.setVisible(false);
		btnAddition.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {

				btnMultiply.setEnabled(true);
				btnAddition.setEnabled(false);
				btnSubtract.setEnabled(true);
				btnDivide.setEnabled(true);

			}
		});


		btnSubtract = new JButton("-");
		btnSubtract.setBounds(570, 320, 50,50);
		customQuestionFrame.getContentPane().add(btnSubtract);
		btnSubtract.setVisible(false);

		btnSubtract.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {

				btnMultiply.setEnabled(true);
				btnAddition.setEnabled(true);
				btnSubtract.setEnabled(false);
				btnDivide.setEnabled(true);

			}
		});


		customQuestionFrame.addWindowListener(new WindowAdapter()
		{

			// If window is closing, must set frame field to null or else when prompted to 
			//Open "SignUp" again it won't allow it.
			@Override
			public void windowClosing(WindowEvent e)
			{

				if (!(nameOfSet.isVisible())) {


					String[] options = {"Save" , "Delete" , "Cancel"};
					int option = JOptionPane.showOptionDialog(null, "Would you like save current progress and fill the blanks with random questions or delete current progress? ", "Exit", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					if (option == 0) {
						customQuestionFrame.dispose();
						customQuestionFrame = null;

					}

					else if (option == 1) {
						BashCommands command = BashCommands.getInstance();
						command.deleteSet(nameOfSet.getText());
						customQuestionFrame.dispose();
						customQuestionFrame = null;					
					}
				}

				else {
					customQuestionFrame.dispose();
					customQuestionFrame = null;
				}

			}
		});



		customQuestionFrame.setVisible(true);
	}


	private void setUpCustomQuestion() {

		welcomingLabel.setVisible(false);
		requirementLabel.setVisible(false);
		nameOfSet.setVisible(false);
		btnCreateSet.setVisible(false);

		lblLeftParameter.setVisible(true);
		lblOperation.setVisible(true);
		lblRightParameter.setVisible(true);
		leftParameter.setVisible(true);
		rightParameter.setVisible(true);
		btnNext.setVisible(true);
		lblQuestionX.setVisible(true);
		btnSubtract.setVisible(true);
		btnAddition.setVisible(true);
		btnMultiply.setVisible(true);
		btnDivide.setVisible(true);


	}

	private void setNextQuestion() {

		if (questionNumber == 11) {
			finishFrame();
			return;
		}

		leftParameter.setText("");
		rightParameter.setText(""); 
		btnAddition.setEnabled(true);
		btnSubtract.setEnabled(true);
		btnMultiply.setEnabled(true);
		btnDivide.setEnabled(true);


		lblQuestionX.setText("Question: "+questionNumber+" /10");


	}

	private void finishFrame() {

		lblLeftParameter.setVisible(false);
		lblRightParameter.setVisible(false);
		btnMultiply.setVisible(false);
		btnAddition.setVisible(false);
		btnDivide.setVisible(false);
		btnSubtract.setVisible(false);
		btnNext.setVisible(false);
		lblQuestionX.setVisible(false);
		rightParameter.setVisible(false);
		leftParameter.setVisible(false);
		lblOperation.setVisible(false);


		JLabel confirmLabel = new JLabel();
		confirmLabel.setText("<html>New Set: \"" + nameOfSet.getText() +"\" has been created!</html>"); 
		confirmLabel.setBounds(400, 300, 1100, 50);
		confirmLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		customQuestionFrame.getContentPane().add(confirmLabel);

		btnMenu.setBounds(477, 537, 233, 51);


	}


	private class NextListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ((leftParameter.getText().length() == 0 ) && (rightParameter.getText().length() == 0)) {

				JOptionPane.showMessageDialog(null, "Fill in all parameters");
			}

			else if ((btnMultiply.isEnabled()) && (btnDivide.isEnabled()) && (btnAddition.isEnabled()) && (btnSubtract.isEnabled())) {

				JOptionPane.showMessageDialog(null, "Select Operation");

			}

			else {

				int left = Integer.parseInt(leftParameter.getText());
				int right = Integer.parseInt(rightParameter.getText());
				BashCommands command = BashCommands.getInstance();

				if (!(btnAddition.isEnabled())) {

					int answer = left + right;
					if (( answer >= 99 ) || ( answer <= 0 )) {

						JOptionPane.showMessageDialog(null, "Answer out of Bounds, must be between 1 - 99");

					}
					else {

						command.addEquation(nameOfSet.getText(), left + " + " + right , "" + answer);
						questionNumber++;
						setNextQuestion();


					} 
				}

				else if (!(btnSubtract.isEnabled())) {

					int answer = left - right;
					if (( answer >= 99 ) || ( answer <= 0 )) {

						JOptionPane.showMessageDialog(null, "Answer out of Bounds, must be between 1 - 99");

					}
					else {

						command.addEquation(nameOfSet.getText(), left + " - " + right , "" + answer);
						questionNumber++;
						setNextQuestion();


					} 

				}
				else if (!(btnMultiply.isEnabled())) {

					int answer = left * right;
					if (( answer >= 99 ) || ( answer <= 0 )) {

						JOptionPane.showMessageDialog(null, "Answer out of Bounds, must be between 1 - 99");

					}
					else {

						command.addEquation(nameOfSet.getText(), ""+left + " X " + right , "" + answer);
						questionNumber++;
						setNextQuestion();


					} 

				}

				else if (!(btnDivide.isEnabled())) {

					int answer = 0;

					try {
						answer = left / right;
					} catch (ArithmeticException e1) {
						JOptionPane.showMessageDialog(null, "0/0 is not accepted");
						return;
					} 

					if ((left % right) != 0) {

						JOptionPane.showMessageDialog(null, "Answer must return a whole number");


					}

					else if (( answer >= 99 ) || ( answer <= 0 )) {

						JOptionPane.showMessageDialog(null, "Answer out of Bounds, must be between 1 - 99");

					}
					else {

						command.addEquation(nameOfSet.getText(), left + " / " + right , "" + answer);
						questionNumber++;
						setNextQuestion();


					}
				}
			}





		}


	}


	private class CreateSetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (nameOfSet.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Please enter a name for your set");

			}

			else {

				BashCommands command = BashCommands.getInstance();
				int status = command.createNewSet(nameOfSet.getText());

				if (status == 0) {

					setUpCustomQuestion();

				}
				else  {
					return;
				}
			}

		}


	}

	private class ParameterKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

			char c = e.getKeyChar();

			JTextField comp = (JTextField) e.getComponent();
			int length = comp.getText().length();
			if (Character.isLetterOrDigit(c) && (length == 2)) {

				e.consume();
			}

			else if (!(Character.isDigit(c))) {

				e.consume();
			}


		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}




	}

	private class KeyTypedListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (c == KeyEvent.VK_BACK_SPACE ) {
				requirementLabel.setForeground(Color.BLACK);

			}
			else if (!(Character.isLetterOrDigit(c)))  {
				Toolkit.getDefaultToolkit().beep();
				requirementLabel.setForeground(Color.RED);
				e.consume();
			}

			else if (requirementLabel.getForeground() == Color.RED) {
				requirementLabel.setForeground(Color.BLACK);
			}


		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}



	private class menuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (questionNumber == 11) {

				customQuestionFrame.dispose();
				customQuestionFrame = null;



			}


			else if (btnCreateSet.isVisible()) {

				int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","exit", JOptionPane.YES_NO_OPTION);
				if (YesOrNo == 0) {

					customQuestionFrame.dispose();
					customQuestionFrame = null;


				}
			}

			else if (questionNumber != 11) {

				String[] options = {"Save" , "Delete" , "Cancel"};
				int option = JOptionPane.showOptionDialog(null, "Would you like save current progress and fill the blanks with random questions or delete current progress? ", "Exit", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if (option == 0) {
					customQuestionFrame.dispose();
					customQuestionFrame = null;

				}

				else if (option == 1) {
					BashCommands command = BashCommands.getInstance();
					command.deleteSet(nameOfSet.getText());
					customQuestionFrame.dispose();
					customQuestionFrame = null;					
				}

			}





		}
	}


}
