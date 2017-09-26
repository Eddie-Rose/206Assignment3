package tatai;

public class Beginner extends Level {

	
	
	public Beginner(String name, int minNum, int maxNum) {
		super(name, minNum, maxNum);
	
	}

	@Override
	public int setNum() {
		int random = (int )(Math.random() * 9 + 1);
		return random;


	}	

}




