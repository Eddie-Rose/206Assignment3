package tatai.math;

import java.util.ArrayList;

import tatai.main.Level;
import tatai.main.Number;


/**
 * 
 * This is the advanced math level where the questions
 * vary from additional, subtraction, division and multiplication
 * additon/ subtraction answers vary from 1 - 99
 * divison asks you to divide numbers from 1 - 99
 * multiplicaton questions range from 1 - 12
 *
 * This class extends from the abstract class "Level" to avoid 
 * duplicate code. 
 * 
 * @author Edwin Roesli and Harpreet Singh
 *
 */
public class AdvancedMath extends Level{
	
	
	private static final long serialVersionUID = 1L;
	private int value1 = 0;
	private int value2 = 0;
	private int answer = 0;
	
	ArrayList<Integer> factors;
	
	public AdvancedMath(String name, int num, int num1){
		super(name, num, num1);
		
		//Overrides the Title of the Level frame
		txtrWelcomeToThe.setText("<html>Welcome to the "+name+" level,\nAdvanced addition, subtraction, multiplication and division questions are asked  ,\nPress \"Start\" to begin.</html>");

	
	}
	
	
	//Overrides the Levels setNum method
	protected int setNum() {
		
		
		//randomly generates an operation from (+ - * /)
		int operationValue = (int)(Math.random() * 4);




		
	
		
		//addition
		if (operationValue == 0) {
			
			//Generates a number between 1 - 98
			value1 = (int)( 1 + (Math.random() * 98));
			
			//Generates a number between 1 and (99 - value 1)
			value2 = (int)(1 + (Math.random() * (99 - value1)));
			answer = value1 + value2;
			
			//Sets the question label
			lblNewLabel.setText("" +value1 + " + " + value2); 
			
		}
		
		//Subtraction
		else if (operationValue == 1) {
			
			
			//randomly generates a number between 50 and 98
			value1 = (int)(50 + (Math.random() * 99));
			
			//Generates a number so that value 1 - value 2 is within the range of 1 - 99
			value2 = (int)(1 + (Math.random() * (value1) - 1));
			answer = value1 - value2;
			
			//Sets the question label
			lblNewLabel.setText("" + value1 + " - " + value2);
			
		}
		
		//Multiplicaton, randomly generates a numebr between 1 - 11 and makes it so 
		//that value 1 * value 2 is within the range of 1 - 99
		else if (operationValue == 2) {
			
			value1 = (int)( 1 + (Math.random() * 11));

			if (value1 == 11) {
				value2 = (int)( 1 + (Math.random() * 9));
			}
			
			else if (value1 == 10) {
				value2 = (int)( 1 + (Math.random() * 9));

			}
			
			else if (value1 == 9) {
				value2 = (int)( 1 + (Math.random() * 11));

			}
			
			else {
				value2 = (int)( 1 + (Math.random() * 12));

			}
			
			answer = value1 * value2;
			
			
			//Sets the question label
			lblNewLabel.setText("" + value1 + " * " + value2);
			
		}
		
		//Divison
		else if (operationValue == 3) {
			
			//Generates a number between 1 and 99
			value1 = (int)( 1 + (Math.random() * 99));
			
			
			//Returns the factors of that number 
			factors = Number.getFactors(value1);
			
			
			//Randomly generates a number in that arrayList 
			int size = factors.size();
			int index = (int)((Math.random() * size));
			
			value2 = factors.get(index);
			answer = value1 / value2;
			

			//Sets the question label
			lblNewLabel.setText("" + value1 + " / " + value2);
			
		}
		
	
		
		//Returns the answer depending on what the operation was set as.
		return answer;
		
	}

}



