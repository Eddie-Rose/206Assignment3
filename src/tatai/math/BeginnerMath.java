package tatai.math;

import tatai.main.Level;

public class BeginnerMath extends Level {
	
	
	private int value1;
	private int value2;
	private int answer;
	
	
	public BeginnerMath(String name, int num , int num1) {
		super (name, num, num1);
		txtrWelcomeToThe.setText("<html>Welcome to the "+name+" level,\nSimple addition and subtraction questions are asked  ,\nPress \"Start\" to begin.</html>");

		
		
	}
	
	
	//private String[] operations = {"+", "-"}; Say something like what operations there are
	protected int setNum() {
		
		int operationValue = (int)(Math.random() * 2);
		
		value1 = (int)(Math.random() * 9 + 2);
		
		if (operationValue == 0) {
			value2 = (int)(Math.random() * (10 - value1) + 1);
			answer = value1 + value2;
			
			lblNewLabel.setText("" +value1 + " + " + value2); 
			
		}
		
		else if (operationValue == 1) {
			value2 = (int)(Math.random() * (value1-1) + 1);
			answer = value1 - value2;
			
			lblNewLabel.setText("" + value1 + " - " + value2);
			
		}
		
	
		
	
		return answer;
		
	}
}
