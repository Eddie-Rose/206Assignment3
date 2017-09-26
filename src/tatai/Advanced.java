package tatai;

import java.util.Random;



public class Advanced extends Level {

	
	
	public Advanced(String name, int minNum, int maxNum) {
		super(name, minNum, maxNum);
	
	}

	@Override
	public int setNum() {
		Random r = new Random();
		int random = r.nextInt(89) + 10;
		return random;


	}
	
	
	
	
	


}




