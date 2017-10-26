package tatai.math;

import tatai.main.Level;



/**
 * 
 * Beginner stage of the mathematics level
 * This stage only consists of addition and 
 * subtraction questions. 
 * 
 * 
 * @author Edwin Roesli and Harpreet Singh
 *
 */
public class BeginnerMath extends Level {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int value1;
	private int value2;
	private int answer;
	
	
	public BeginnerMath(String name, int num , int num1) {
		super (name, num, num1);
		txtrWelcomeToThe.setText("<html>Welcome to the "+name+" level,\nSimple addition and subtraction questions are asked  ,\nPress \"Start\" to begin.</html>");

		
		
	}
	
	
	/**
	 * Overrides the setNum() operation in the abstract class "Level" 
	 */
	protected int setNum() {
		
		int operationValue = (int)(Math.random() * 2);
		
		//Generates a number between 1 and 50
		value1 = (int)(Math.random() * 50 + 1);
		
		//addition operation 
		if (operationValue == 0) {
			
			//Value 2 is generated so that the addition of value 1 and 
			//value 2 is between 1 and 99
			value2 = (int)(Math.random() * (99 - value1) + 1);
			answer = value1 + value2;
			
			
			//Sets the question label
			lblNewLabel.setText("" +value1 + " + " + value2); 
			
		}
		
		
		//Subtraction operation 
		else if (operationValue == 1) {
			value2 = (int)(Math.random() * (value1-1) + 1);
			answer = value1 - value2;
			
			
			
			//Sets the question label 
			lblNewLabel.setText("" + value1 + " - " + value2);
			
		}
		
	
		
		
		return answer;
		
	}
}
