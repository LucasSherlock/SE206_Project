package application;

import java.util.*;

/*
 * Class to instantiate User objects, users have unique lists of games
 */
public class User {
	
	private String username; 
	private GameList userGames;
	
	/*
	 * User constructor
	 */
	public User(String name) {
		username = name;
		userGames = new GameList();
	}
	
	/*
	 * add a game object to user's game list
	 */
	public void addGame(Game g) {
		userGames.add(g);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	
}
