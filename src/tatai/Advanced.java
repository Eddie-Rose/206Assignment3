package tatai;

import java.util.Random;



public class Advanced extends Level {

	Random r = new Random();
	
	public Advanced(String name, int minNum, int maxNum) {
		super(name, minNum, maxNum);
	
	}

	@Override
	public int setNum() {
		return r.nextInt(89) +10;
		
		
	}
	
	
	
}




