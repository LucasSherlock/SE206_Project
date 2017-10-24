package application;

import java.util.*;

/*
 * This class is used to instantiate Game objects.
 */
public class Game {
	private String name;
	private ArrayList<String> questions;
	private ArrayList<Integer> answers;
	
	/*
	 * The Game constructor
	 */
	public Game() {
		name = "";
		questions = new ArrayList<String>();
		answers = new ArrayList<Integer>();
	}
	
	/*
	 * Returns the name of the game
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * Sets the name of the game
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/*
	 * Adds a question to the game
	 */
	public void add(int a, int b) {
		questions.add(a + "+" + b); // Concatenates the numbers
		answers.add(a+b);
	}
	
	/*
	 * Returns the number of questions
	 */
	public int size() {
		return questions.size();
	}
	
	/*
	 * Returns the question at `index`
	 */
	public String getQuestion(int index) {
		return questions.get(index);
	}
	
	/*
	 * Returns the answer at `index`
	 */
	public int getAnswer(int index) {
		return answers.get(index);
	}
	
}

