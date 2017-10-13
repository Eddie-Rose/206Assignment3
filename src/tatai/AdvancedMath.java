package tatai;

import java.util.ArrayList;



public class AdvancedMath extends Level{
	
	private int value1 = 0;
	private int value2 = 0;
	private int answer = 0;
	
	ArrayList<Integer> factors;
	
	public AdvancedMath(String name, int num, int num1){
		super(name, num, num1);
		txtrWelcomeToThe.setText("<html>Welcome to the "+name+" level,\nAdvanced addition, subtraction, multiplication and division questions are asked  ,\nPress \"Start\" to begin.</html>");

	
	}
	
	
	//private String[] operationValue = {"+", "-", "*", "/"};
	protected int setNum() {
	
		//int operationValue = (int)(Math.random() * 4);




		
		int operationValue = 3;
		
		
		if (operationValue == 0) {
			
			value1 = (int)( 1 + (Math.random() * 98));
			value2 = (int)(1 + (Math.random() * (99 - value1)));
			answer = value1 + value2;
			
			lblNewLabel.setText("" +value1 + " + " + value2); 
			
		}
		
		else if (operationValue == 1) {
			
			value1 = (int)(50 + (Math.random() * 99));
			value2 = (int)(1 + (Math.random() * (value1) - 1));
			answer = value1 - value2;
			
			lblNewLabel.setText("" + value1 + " - " + value2);
			
		}
		
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
			
			lblNewLabel.setText("" + value1 + " * " + value2);
			
		}
		
		else if (operationValue == 3) {
			
			value1 = (int)( 1 + (Math.random() * 99));
			System.out.println(answers);
			
			factors = Number.getFactors(value1);
			
			

			int size = factors.size();
			int index = (int)((Math.random() * size));
			
			value2 = factors.get(index);
			answer = value1 / value2;
			

			
			
			
			
			lblNewLabel.setText("" + value1 + " / " + value2);
			
		}
		
	
		
		
		return answer;
		
	}

}



