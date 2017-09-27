package tatai;

import javax.swing.JLabel;

public class Beginner extends Level {

	
	
	public Beginner(String name, int minNum, int maxNum) {
		super(name, minNum, maxNum);
		JLabel finish = new JLabel("Advanced");
		getContentPane().add(finish);
		finish.setVisible(false);
	
	}

	@Override
	public int setNum() {
		int random = (int )(Math.random() * 9 + 1);
		return random;


	}	
	
	

}




