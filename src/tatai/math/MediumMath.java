package tatai.math;

import tatai.main.Level;
	

/**
 * 
 * This is the Medium maths level where questions from 
 * Addition , Subtraction and multiplication are asked 
 * 
 * This class extends from the Level abstract class to avoid any
 * duplicate code.
 * 
 * @author Edwin Roesli and Harpreet Singh
 *
 */
public class MediumMath extends Level{
	
	

	private static final long serialVersionUID = 1L;
	private int value1 = 0;
	private int value2 = 0;
	private int answer = 0;
	
	public MediumMath(String name, int num, int num1){
		super(name, num, num1);
		
		//Overrides the Levels main description message
		txtMainLevelDescription.setText("<html>Welcome to the "+name+" level,\nSimple addition, subtraction and multiplication questions are asked  ,\nPress \"Start\" to begin.</html>");

	
	}
	
	//Overrides the abstract Levels setNum class
	protected int setNum() {
	
		int operationValue = (int)(Math.random() * 3);
		
		
		
		//The random operation chosen was addition
		if (operationValue == 0) {
			
			//Restricts it so the maximum number the answer can go to is 99
			value1 = (int)( 1 + (Math.random() * 49));
			value2 = (int)(1 + (Math.random() * (49)));
			answer = value1 + value2;
			
			//Set the question label of the level class
			lblNewLabel.setText("" +value1 + " + " + value2); 
			
		}
		
		
		//Subtraction operation 
		else if (operationValue == 1) {
			
			//Restricts it so the answer is in the bound of 0 - 99
			
			value1 = (int)(10 + (Math.random() * 40));
			value2 = (int)(1 + (Math.random() * (value1-1) ));
			answer = value1 - value2;
			
			
			//Sets the question label of the level class 
			lblNewLabel.setText("" + value1 + " - " + value2);
			
		}
		
		
		//Multiplication Operation
		else if (operationValue == 2) {
			
			//Restricts equation so that the number is within the bounds of 0 - 99
			value1 = (int)( 1 + (Math.random() * 7));
			value2 = (int)( 1 + (Math.random() * 7));
			answer = value1 * value2;
			
			
			//Sets the question label of the level class
			lblNewLabel.setText("" + value1 + " * " + value2);
			
		}
		
	
		
		//Returns the answer of the randomly generated equation 
		return answer;
		
	}

}
