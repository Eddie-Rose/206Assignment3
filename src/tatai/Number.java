package tatai;

import java.util.ArrayList;
import java.util.HashSet;

public class Number {
	
	private int number;
	private int tens;
	private int ones;
	private String maoriNumber;
	
	private final String[] maori= new String[]{"tahi", "rua", "toru", "whaa", "rima", "ono", "whitu", "waru", "iwa"};
	private final String maoriTen = "tekau";
	private final String connecter = "maa";
	
	
	public Number(int number) {
		
		this.number = number;
		
			
	}
	
	public Number () {
		
		this.number = 0;
		
	}
	
	public void setNumber(int number) {
		
		this.number = number;
	}
	
	public void splitNumber() {
		
		tens = number/10;
		ones = number - (tens*10);
		
	}
	
	public String outputMaoriNumber() {
		
		splitNumber();
		
		if (tens == 0) {
			maoriNumber = maori[ones - 1];
			return maoriNumber;
		}
		
		else if (ones == 0) {
			if (tens == 1) {
				return maoriTen;
				
			}
			else {
				maoriNumber = maori[tens - 1]  + " " + maoriTen;
				return maoriNumber;
			}
		}
		
		else if (tens == 1){
			maoriNumber = maoriTen + " " + connecter + " " + maori[ones - 1];
			return maoriNumber;
		
		}
		
		else {
			maoriNumber = maori[tens - 1] + " " + maoriTen + " " + connecter + " " + maori[ones - 1];
			return maoriNumber;
		}
	}
	
	public static ArrayList<Integer> getFactors(int number) {
		
		

		ArrayList<Integer> factors = new ArrayList<>();
		
	
		

        for (int i = 0; i < number; i++) {
            if ((number % (i+1)) == 0) {
                factors.add(i+1);
                System.out.println("Factor= " + (i+1));
            }
        }
        
        return factors;
	}
	
	
}
