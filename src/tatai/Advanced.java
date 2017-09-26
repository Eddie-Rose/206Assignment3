package tatai;

public class Advanced extends Level {

	
	public Advanced(String name, int minNum, int maxNum) {
		super(name, minNum, maxNum);
	
	}

	@Override
	public int setNum() {
		int random = (int )(Math.random() * 99 + 1);
		return random;


	}	

}




