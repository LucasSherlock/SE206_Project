package application;

import java.util.*;

public class Game {
	private String name;
	private ArrayList<String> questions;
	private ArrayList<Integer> answers;
	
	public Game() {
		name = "";
		questions = new ArrayList<String>();
		answers = new ArrayList<Integer>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void add(int arg1, int arg2) {
		questions.add(arg1+"+"+arg2);
		answers.add(arg1+arg2);
	}
	
	public int size() {
		return questions.size();
	}
	
	public String getQuestion(int index) {
		return questions.get(index);
	}
	
	public int getAnswer(int index) {
		return answers.get(index);
	}
	
}

