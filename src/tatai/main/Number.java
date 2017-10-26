package tatai.main;

import java.util.ArrayList;


/**
 * 
 * Contains all the functionality of a number class
 * This class is responsible for converting a number into its maori words  
 * It is also responsible for returning all of a given numbers factors 
 * 
 * @author Edwin Roesli
 *
 */
public class Number {
	
	private int number;
	private int tens;
	private int ones;
	private String maoriNumber;
	
	//Final string containing all of the needed maori words
	private final String[] maori= new String[]{"tahi", "rua", "toru", "whaa", "rima", "ono", "whitu", "waru", "iwa"};
	private final String maoriTen = "tekau";
	private final String connecter = "maa";
	
	
	//Constructor for the Number class
	public Number(int number) {
		
		this.number = number;
		
			
	}
	
	
	//Default constructor if no number is inputed 
	public Number () {
		
		this.number = 0;
		
	}
	
	//Sets the number 
	public void setNumber(int number) {
		
		this.number = number;
	}
	
	
	//Splits the number into tens and ones
	private void splitNumber() {
		
		tens = number/10;
		ones = number - (tens*10);
		
	}
	
	
	
	//Outs the given number in maori format, uses this slpitNumber method
	public String outputMaoriNumber() {
		
		splitNumber();
		
		
		//If the number is less than ten output the ones maori number with no modification
		if (tens == 0) {
			maoriNumber = maori[ones - 1];
			return maoriNumber;
		}
		
		
		//If the number has a 0 for its ones
		else if (ones == 0) {
			
			//If the number is specifically 10 out put maori 10 with no modifications
			if (tens == 1) {
				return maoriTen;
				
			}
			
			//Else output the 2 numbers joined with "maa"
			else {
				maoriNumber = maori[tens - 1]  + " " + maoriTen;
				return maoriNumber;
			}
		}
		
		//When the number inputed is in the 10's
		else if (tens == 1){
			maoriNumber = maoriTen + " " + connecter + " " + maori[ones - 1];
			return maoriNumber;
		
		}
		
		
		else {
			maoriNumber = maori[tens - 1] + " " + maoriTen + " " + connecter + " " + maori[ones - 1];
			return maoriNumber;
		}
	}
	
	
	/**
	 * 
	 * 
	 * @param number Number we want the factors of
	 * @return	An arrayList containing all of the inputed numbers factors
	 */
	public static ArrayList<Integer> getFactors(int number) {
		
		
		//Initialise the arrayList
		ArrayList<Integer> factors = new ArrayList<>();
		
	
		
		//Loop through the number (1 - number)
		//If it is a factor add it in to the arrayList
        for (int i = 0; i < number; i++) {
            if ((number % (i+1)) == 0) {
                factors.add(i+1);
                
            }
        }
        
        return factors;
	}
	
	
}
