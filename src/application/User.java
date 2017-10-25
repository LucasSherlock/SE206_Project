package application;

import java.util.*;

public class User {
	
	private String username;
	private GameList userGames;
	
	public User(String name) {
		username = name;
		userGames = new GameList();
	}
	
	public void addGame(Game g) {
		userGames.add(g);
	}
}
