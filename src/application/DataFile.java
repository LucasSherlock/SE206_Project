package application;

import java.util.ArrayList;

/*
 * Contains vital information for current session
 */
public class DataFile {
	
	public static boolean practiceMode = false; //is the user practicing
	
	
	//track progress in current game or practice
	public static int score = 0;
	public static int Level = 0;
	public static int trial = 1;
	public static boolean CorrectAnswer = false;
	
	//information for practice sessions
	public static String difficulty = "EASY";
	public static int[] practiceGame  = {1,9,2,5,5,6,6,7,8,9};
	public static ArrayList<String> hardScores = new ArrayList<String>();
	public static ArrayList<String> easyScores = new ArrayList<String>();
	
	
	public static Game game; //currently selected game
	
	public static User user; //currently selected user

}
