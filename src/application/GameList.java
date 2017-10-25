package application;

import java.util.*;


/*
 * Object containing a list of games, belongs to a User
 */
public class GameList {
	private ArrayList<Game> games = new ArrayList<Game>();
	
	
	/*
	 * Add a new game to the list and also input the name into the observable list
	 * for the list view screen
	 */
	public void add(Game game) {
		if(!games.contains(game)) {
			//if it doesn't already exist
			
			games.add(game);
			ListController.items.add(game.getName()); //add name to observable list
		}
	}
	
	
	/*
	 * remove a game from the gamelist
	 */
	public void remove(Game game) {
		if(!games.contains(game)) {
			games.remove(game);
		}
	}
	
	/*
	 * given the name of a game (selected via the list) return the corresponding game object
	 */
	public Game findGame(String name) {
		for(Game g : games) {
			if(g.getName().equals(name)) {
				return g;
			}
		}
		return null;
	}
	
	/*
	 * delete all games in the list
	 */
	public void clear() {
		games.clear();
	}
}
