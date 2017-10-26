package application.models;

import java.io.Serializable;
import java.util.*;

/*
 * This class is used to instantiate Game objects.
 */
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<String> questions;
	private ArrayList<Integer> answers;
	private int highScore;
	
	/*
	 * The Game constructor
	 */
	public Game() {
		name = "";
		questions = new ArrayList<String>();
		answers = new ArrayList<Integer>();
		highScore = 0;
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
	
	/*
	 * Returns the high score
	 */
	public int getHighScore() {
		return highScore;
	}
	
}

