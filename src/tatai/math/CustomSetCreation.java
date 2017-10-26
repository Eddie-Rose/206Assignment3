package tatai.math;

import java.awt.Color;
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
import tatai.main.BashCommands;
import tatai.main.MainGUI;


/**
 * 
 * This class is responsible for creating the custom Sets 
 * of questions people may want to implement.
 * 
 * The main screen shown in this frame asks for the name of your new set
 * Name is restrictive (only accepts numbers and letters)
 * 
 * Next main thing this frame shows is the the "creation of equations"
 * Which shows 2 parameters (only accepts 2 digits) and 4 buttons for parameters
 * 
 * It restricts the user to only pick suitable questions. 
 * 
 * @author Edwin Roesli
 *
 */
public class CustomSetCreation  {


	//sets the initial position of the frame to be in the centre of the screen
	private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private static JFrame customQuestionFrame = null;


	//Initialises all the necessary components5
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
	private JButton btnMultiply;
	private JButton btnDivide;
	private JButton btnSubtract;
	private JButton btnAddition;



	//Private method to call the createCustomFrame 
	private CustomSetCreation() {


		createCustomQuestionFrame();

	}


	@SuppressWarnings("unused")
	private static CustomSetCreation instance;





	//Static method to check if it has already initialised 
	//Singleton pattern
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



	//Main method to initialise the frame of the createCustomFrame
	private void createCustomQuestionFrame() {


		//Counter to see how many questions the user has created
		questionNumber = 1;


		//Sets the frame and initial input of name
		customQuestionFrame = new JFrame();
		customQuestionFrame.setResizable(false);
		customQuestionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		customQuestionFrame.setBounds(100, 100, 1100, 700);
		customQuestionFrame.setLocation(dim.width/2-customQuestionFrame.getSize().width/2, dim.height/2-customQuestionFrame.getSize().height/2);

		customQuestionFrame.setLayout(null);


		//Prompts the user to input a name for their new Set
		welcomingLabel = new JLabel("<html>Name your new set: </html>");
		welcomingLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		welcomingLabel.setBounds(0, 200, 1100, 50);
		welcomingLabel.setHorizontalAlignment(JLabel.CENTER);
		customQuestionFrame.getContentPane().add(welcomingLabel);


		//Label to assert that the name of the Set must only be numbers and letters
		requirementLabel = new JLabel("<html>Your set name must only consist of letters and numbers </html>");
		requirementLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		requirementLabel.setBounds(0, 223, 1100, 50);
		requirementLabel.setHorizontalAlignment(JLabel.CENTER);
		customQuestionFrame.getContentPane().add(requirementLabel);


		//Initialises the JTextField which stores the name of the Set
		nameOfSet = new JTextField();
		nameOfSet.setBounds(158, 260, 800, 150);
		nameOfSet.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		nameOfSet.setHorizontalAlignment(JLabel.CENTER);
		nameOfSet.setTransferHandler(null);

		//Add a key listener to assert the condition of only numbers and letters
		nameOfSet.addKeyListener(new KeyTypedListener());
		customQuestionFrame.getContentPane().add(nameOfSet);


		//Initialise the "Create" button
		btnCreateSet = new JButton("Create Set");
		btnCreateSet.setBounds(450, 450, 200, 70);
		btnCreateSet.addActionListener (new CreateSetListener());
		customQuestionFrame.getContentPane().add(btnCreateSet);



		//Initialise the main label  
		lblCreateYourOwn = new JLabel("Create your own custom questions!");
		lblCreateYourOwn.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		lblCreateYourOwn.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateYourOwn.setBounds(150, 37, 800, 33);
		customQuestionFrame.getContentPane().add(lblCreateYourOwn);


		//Initialise the label describing what the left JTextField is
		lblLeftParameter = new JLabel("Left parameter");
		lblLeftParameter.setBounds(200, 115, 114, 15);
		customQuestionFrame.getContentPane().add(lblLeftParameter);
		lblLeftParameter.setVisible(false);


		//Initialise the label which describes what the middle 4 buttons are 
		lblOperation = new JLabel("operation");
		lblOperation.setBounds(520, 115, 91, 15);
		customQuestionFrame.getContentPane().add(lblOperation);
		lblOperation.setVisible(false);


		//Initialise the label which describes what the Right JtextField is
		lblRightParameter = new JLabel("Right parameter");
		lblRightParameter.setBounds(832, 115, 134, 15);
		customQuestionFrame.getContentPane().add(lblRightParameter);
		lblRightParameter.setVisible(false);


		//Initialise the area in which the user writes what the left parameter is 
		leftParameter = new JTextField();
		leftParameter.setBounds(158, 229, 217, 162);
		leftParameter.setHorizontalAlignment(SwingConstants.CENTER);
		leftParameter.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		customQuestionFrame.getContentPane().add(leftParameter);
		//Adds a key listener to limit the field to only 2 digits and numbers
		leftParameter.addKeyListener(new ParameterKeyListener());	
		leftParameter.setTransferHandler(null);
		leftParameter.setVisible(false);



		//Initialise the area in which the user writes what the right parameter is 
		rightParameter = new JTextField();
		rightParameter.setTransferHandler(null);
		rightParameter.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		rightParameter.setBounds(793, 229, 217, 162);
		rightParameter.setHorizontalAlignment(SwingConstants.CENTER);
		//Adds a key listener to limit the field to only 2 digits and numbers
		rightParameter.addKeyListener(new ParameterKeyListener());	
		customQuestionFrame.getContentPane().add(rightParameter);
		rightParameter.setVisible(false);



		//Initialise the next button and add its listener
		btnNext = new JButton("Next");
		btnNext.setBounds(477, 537, 233, 51);
		customQuestionFrame.getContentPane().add(btnNext);
		btnNext.setVisible(false);
		btnNext.addActionListener(new NextListener());



		//INitialise the Exit Button and add its listener 
		btnMenu = new JButton("Exit");
		btnMenu.setBounds(932, 635, 117, 25);
		customQuestionFrame.getContentPane().add(btnMenu);
		btnMenu.addActionListener(new menuListener());



		//Initialise the label which tells the user what question he is on
		lblQuestionX = new JLabel("Question: "+questionNumber+" /10");
		lblQuestionX.setBounds(903, 46, 134, 15);
		customQuestionFrame.getContentPane().add(lblQuestionX);
		lblQuestionX.setVisible(false);



		//Initialise the multiply button
		btnMultiply = new JButton("X");
		btnMultiply.setBounds(510, 260, 50,50);
		customQuestionFrame.getContentPane().add(btnMultiply);
		btnMultiply.setVisible(false);

		//Adds the multiply button listener 
		btnMultiply.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {

				//Disables the multiply button and enable the rest of the operations
				btnMultiply.setEnabled(false);
				btnAddition.setEnabled(true);
				btnSubtract.setEnabled(true);
				btnDivide.setEnabled(true);

			}
		});


		//Add the division button
		btnDivide = new JButton("/");
		btnDivide.setBounds(570, 260, 50,50);
		customQuestionFrame.getContentPane().add(btnDivide);
		btnDivide.setVisible(false);
		btnDivide.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {


				//Disables the division button and enable the rest of the operations
				btnMultiply.setEnabled(true);
				btnAddition.setEnabled(true);
				btnSubtract.setEnabled(true);
				btnDivide.setEnabled(false);

			}
		});


		//Add the addition button
		btnAddition = new JButton("+");
		btnAddition.setBounds(510, 320, 50,50);
		customQuestionFrame.getContentPane().add(btnAddition);
		btnAddition.setVisible(false);
		btnAddition.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {


				//Disables the addition button and enable the rest of the operations
				btnMultiply.setEnabled(true);
				btnAddition.setEnabled(false);
				btnSubtract.setEnabled(true);
				btnDivide.setEnabled(true);

			}
		});


		//Add the Subtract button
		btnSubtract = new JButton("-");
		btnSubtract.setBounds(570, 320, 50,50);
		customQuestionFrame.getContentPane().add(btnSubtract);
		btnSubtract.setVisible(false);

		btnSubtract.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {

				//Disables the subtract button and enable the rest of the operations
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


					//Initialise the Option pane with 3 choices 
					String[] options = {"Cancel" , "Delete" , "Save"};
					int option = JOptionPane.showOptionDialog(null, "Would you like save current progress and fill the blanks with random questions or delete current progress? ", "Exit", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					//Option Save: keeps the txt file and exits the frame and sets the field to null
					//So it can be initialised again 
					if (option == 2) {
						customQuestionFrame.dispose();
						customQuestionFrame = null;

					}

					//Option Delete: call the deleteSet command in BashCommand class and
					//Set the field to null so this frame can be initialised again when called upon
					else if (option == 1) {
						BashCommands command = BashCommands.getInstance();
						command.deleteSet(nameOfSet.getText());
						customQuestionFrame.dispose();
						customQuestionFrame = null;					
					}
				}

				//Only happens when exit is called on the main screen so there is no need to delete 
				//The text file since no text file was created
				else {
					customQuestionFrame.dispose();
					customQuestionFrame = null;
				}

			}
		});



		customQuestionFrame.setVisible(true);
	}


	//Method to set up creating the custom questions
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

	//Method to set up creating the next question
	private void setNextQuestion() {

		//If all 10 questions have been made, call the finish frame method  
		if (questionNumber == 11) {
			finishFrame();
			return;
		}

		//else clear the parameters and enable all the operation buttons
		leftParameter.setText("");
		rightParameter.setText(""); 
		btnAddition.setEnabled(true);
		btnSubtract.setEnabled(true);
		btnMultiply.setEnabled(true);
		btnDivide.setEnabled(true);


		//update the question label
		lblQuestionX.setText("Question: "+questionNumber+" /10");


	}

	//Method to set up the frame when all questions have been filled up  
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

		//Set up the confirm label to confirm that all 10 questions were created
		JLabel confirmLabel = new JLabel();
		confirmLabel.setText("<html>New Set: \"" + nameOfSet.getText() +"\" has been created!</html>"); 
		confirmLabel.setBounds(400, 300, 1100, 50);
		confirmLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		customQuestionFrame.getContentPane().add(confirmLabel);

		btnMenu.setBounds(477, 537, 233, 51);


	}

	//Custom Listener class to control what happens when the next button has been clicked
	private class NextListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			//If any parameter is empty output a error message
			if ((leftParameter.getText().length() == 0 ) || (rightParameter.getText().length() == 0)) {

				JOptionPane.showMessageDialog(null, "Fill in all parameters");
			}


			//If no operation is chosen, output a error message
			else if ((btnMultiply.isEnabled()) && (btnDivide.isEnabled()) && (btnAddition.isEnabled()) && (btnSubtract.isEnabled())) {

				JOptionPane.showMessageDialog(null, "Select Operation");

			}

			else {

				//Parse the left and right parameter into an integer
				int left = Integer.parseInt(leftParameter.getText());
				int right = Integer.parseInt(rightParameter.getText());
				BashCommands command = BashCommands.getInstance();


				//If the addition button is chosen
				if (!(btnAddition.isEnabled())) {

					//set the answer and check if the answer is within the bounds 1 - 99
					int answer = left + right;
					if (( answer >= 99 ) || ( answer <= 0 )) {

						JOptionPane.showMessageDialog(null, "Answer out of Bounds, must be between 1 - 99");

					}
					else {

						//if it meets the criteria, append it into the text file and invoke the netQuestion method
						command.addEquation(nameOfSet.getText(), left + " + " + right , "" + answer);
						questionNumber++;
						setNextQuestion();


					} 
				}

				//If the subtract button is chosen 
				else if (!(btnSubtract.isEnabled())) {

					//Set the answer and check if the answer is within the bounds 1 - 99
					int answer = left - right;
					if (( answer >= 99 ) || ( answer <= 0 )) {

						JOptionPane.showMessageDialog(null, "Answer out of Bounds, must be between 1 - 99");

					}
					else {

						//if it does meet the criteria append it into the txt file and invoke the nextQuestion method
						command.addEquation(nameOfSet.getText(), left + " - " + right , "" + answer);
						questionNumber++;
						setNextQuestion();


					} 

				}

				//If the multiply button is chosen
				else if (!(btnMultiply.isEnabled())) {

					//Set the answer and check for its validity
					int answer = left * right;
					if (( answer >= 99 ) || ( answer <= 0 )) {

						JOptionPane.showMessageDialog(null, "Answer out of Bounds, must be between 1 - 99");

					}
					else {

						//if it does meet the criteria append it into the txt file and invoke the nextQuestion method
						command.addEquation(nameOfSet.getText(), ""+left + " X " + right , "" + answer);
						questionNumber++;
						setNextQuestion();


					} 

				}

				//If the division button is chosen
				else if (!(btnDivide.isEnabled())) {

					int answer = 0;

					//If the operation was 0/0 output error message 
					try {
						answer = left / right;
					} catch (ArithmeticException e1) {
						JOptionPane.showMessageDialog(null, "0/0 is not accepted");
						return;
					} String[] options = {"Cancel" , "Delete" , "Save"};
					int option = JOptionPane.showOptionDialog(null, "Would you like save current progress and fill the blanks with random questions or delete current progress? ", "Exit", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


					//Option = Save, save the file and go back to the main menu
					if (option == 2) {
						customQuestionFrame.dispose();
						customQuestionFrame = null;
						new MainGUI();

					}
					
					//Option = delete, delete the file and go back to the main menu
					else if (option == 1) {
						
						command.deleteSet(nameOfSet.getText());
						customQuestionFrame.dispose();
						customQuestionFrame = null;		

						//Checks if left / right returns a whole number 
						if ((left % right) != 0) {

							JOptionPane.showMessageDialog(null, "Answer must return a whole number");


						}

						//Checks if the answer is within the bounds 1 - 99 
						else if (( answer >= 99 ) || ( answer <= 0 )) {

							JOptionPane.showMessageDialog(null, "Answer out of Bounds, must be between 1 - 99");

						}
						else {

							//if it does meet the criteria append it into the txt file and invoke the nextQuestion method
							command.addEquation(nameOfSet.getText(), left + " / " + right , "" + answer);
							questionNumber++;
							setNextQuestion();


						}
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

		//Custom keyListener for the parameter TextField
		private class ParameterKeyListener implements KeyListener {

			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();

				//Cast the component into a JTextField as it should always be used with a JTextField
				JTextField comp = (JTextField) e.getComponent();
				int length = comp.getText().length();

				//length of the JTextField must always be 2 
				if (Character.isLetterOrDigit(c) && (length == 2)) {

					e.consume();
				}

				//If the character is not a digit, cancel the input
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


		//KeyListener for the JtextField which stores the new Set name
		private class KeyTypedListener implements KeyListener {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				//If the key pressed is a backspace, set the condition label back to black
				if (c == KeyEvent.VK_BACK_SPACE ) {
					requirementLabel.setForeground(Color.BLACK);

				}

				//If the key pressed is not a letter nor a digit set the condition label 
				//colour to red to show that it is invalid and cancel the key pressed event.
				else if (!(Character.isLetterOrDigit(c)))  {
					Toolkit.getDefaultToolkit().beep();
					requirementLabel.setForeground(Color.RED);
					e.consume();
				}

				//if the key pressed is a digit or a number, set the colour of the 
				//condition label back to black
				else if (requirementLabel.getForeground() == Color.RED) {
					requirementLabel.setForeground(Color.BLACK);
				}
				
				
				//Limits the name to be less than 10 characters
				JTextField comp = (JTextField) e.getComponent();
				int length = comp.getText().length();

				//length of the JTextField must be less than 10
				if  (length == 10) {

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



		//Action listener for the menu button 
		private class menuListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {

				//If all the questions have been set, go back to the main menu
				if (questionNumber == 11) {

					customQuestionFrame.dispose();
					customQuestionFrame = null;
					new MainGUI();



				}


				//if the new set name has not been created, ask the user if they want to exit now or cancel.
				else if (btnCreateSet.isVisible()) {

					//Output the Option pane the user can choose from 
					int YesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","exit", JOptionPane.YES_NO_OPTION);

					//If the option was to exit, dispose the frame and show the main menu
					if (YesOrNo == 0) {

						customQuestionFrame.dispose();
						customQuestionFrame = null;
						new MainGUI();


					}
				}

				//If they are in the middle of creating questions ask if they want to delete their current progress or save it 
				else if (questionNumber != 11) {

					String[] options = {"Cancel" , "Delete" , "Save"};
					int option = JOptionPane.showOptionDialog(null, "Would you like save current progress and fill the blanks with random questions or delete current progress? ", "Exit", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


					//
					if (option == 2) {
						customQuestionFrame.dispose();
						customQuestionFrame = null;
						new MainGUI();

					}

					else if (option == 1) {
						BashCommands command = BashCommands.getInstance();
						command.deleteSet(nameOfSet.getText());
						customQuestionFrame.dispose();
						customQuestionFrame = null;		
						new MainGUI();
					}

				}


			}


		}
	



}
