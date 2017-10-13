package tatai;

public class MediumMath extends Level{
	
	
	private int value1 = 0;
	private int value2 = 0;
	private int answer = 0;
	
	public MediumMath(String name, int num, int num1){
		super(name, num, num1);
		txtrWelcomeToThe.setText("<html>Welcome to the "+name+" level,\nSimple addition, subtraction and multiplication questions are asked  ,\nPress \"Start\" to begin.</html>");

	
	}
	
	
	//private String[] operationValue = {"+", "-", "*"};
	protected int setNum() {
	
		int operationValue = (int)(Math.random() * 3);
		
		
		
		if (operationValue == 0) {
			
			value1 = (int)( 1 + (Math.random() * 19));
			value2 = (int)(1 + (Math.random() * (19 - value1) + 1));
			answer = value1 + value2;
			
			lblNewLabel.setText("" +value1 + " + " + value2); 
			
		}
		
		else if (operationValue == 1) {
			
			value1 = (int)(10 + (Math.random() * 10));
			value2 = (int)(1 + (Math.random() * (value1) - 1));
			answer = value1 - value2;
			
			lblNewLabel.setText("" + value1 + " - " + value2);
			
		}
		
		else if (operationValue == 2) {
			
			value1 = (int)( 1 + (Math.random() * 5));
			value2 = (int)( 1 + (Math.random() * 5));
			answer = value1 * value2;
			
			lblNewLabel.setText("" + value1 + " * " + value2);
			
		}
		
	
		
		
		return answer;
		
	}

}
