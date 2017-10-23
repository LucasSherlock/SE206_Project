package application;

import java.util.*;

public class GameList {
	private ArrayList<Game> games = new ArrayList<Game>();
	
	public void add(Game game) {
		if(!games.contains(game)) {
			games.add(game);
			ListController.items.add(game.getName());
		}
	}
	
	public void remove(Game game) {
		if(!games.contains(game)) {
			games.remove(game);
			//ListController.items.remove(game.getName());
		}
	}
	
	public Game findGame(String name) {
		for(Game g : games) {
			if(g.getName().equals(name)) {
				return g;
			}
		}
		return null;
	}
	
}
