package tatai.math;

import java.util.Map;
import java.util.Set;

import tatai.main.Level;

public class CustomMath extends Level{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Integer> questionMap;


	public CustomMath(String lvlName, int minNum, int maxNum, Map<String, Integer> qMap) {
		
		super(lvlName, 0, 0 );
		questionMap = qMap;
		txtMainLevelDescription.setText("<html>Welcome to your Custom Set of questions named: " + lvlName + " here, the questions you have generated are asked to you in a random order."
				+ " If your custom set contains less than 10 questions, random equations will be generated to fill up the space. Press Start to begin.</html>");
		
	
		lblMainTitle.setText("Custom Set");
		
	}
	
	/**
	 * Overrides the setNum method in the level abstract class
	 */
	protected int setNum() {
		
		//Randomly generates a equation from the hash map and sets it as the next question
		//It then deletes the question/ answer from the HashMap.
		
		int size = questionMap.size();
		int index = (int) (Math.random() * size);
		int answer;
		String question = "";
		
		
		//Converts the question part of the Hash Map into a Set so we can 
		//Randomly generate a question 
		
		Set<String> questionsSet = questionMap.keySet();
		
		Object[] questions = questionsSet.toArray();
		question = (String) questions[index];
		
		
		//Sets the question and answer
		lblNewLabel.setText(question);
		answer = questionMap.get(question);
		
		
		return answer;
		
		
		
	}

}
