package tatai.math;

import java.util.Map;
import java.util.Set;

import tatai.main.Level;

public class CustomMath extends Level{
	
	
	Map<String, Integer> questionMap;


	public CustomMath(String lvlName, int minNum, int maxNum, Map qMap) {
		
		super(lvlName, 0, 0 );
		questionMap = qMap;
		
	}
	
	protected int setNum() {
		
		
		int size = questionMap.size();
		int index = (int) (Math.random() * size);
		int answer;
		String question = "";
		
		
		String[] questions = (String[]) questionMap.keySet().toArray();
		question = questions[index];
		
		
		
		lblNewLabel.setText(question);
		answer = questionMap.get(question);
		
		
		return answer;
		
		
		
	}

}
