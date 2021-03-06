package application.models;

import java.io.Serializable;
import java.util.*;


/*
 * Class to instantiate User objects, users have unique lists of games
 */
public class User implements Serializable {	
	private static final long serialVersionUID = 1L;
	private String _username;
	private ArrayList<Game> _gamesList = new ArrayList<Game>();
	private LocalRepo _repo;
	
	/*
	 * The user constructor is private to ensure that only one user with a given name
	 * can ever exist - a singleton-like pattern.
	 */
	private User(LocalRepo store, String username) {
		_repo = store;
		_username = username;
		
		// Writes the user to disk
		saveUser();
	}
	
	/*
	 * The user factory, if a user with the given `username` does not exist in the Store
	 * one will be instantiated, otherwise the stored user will be returned.
	 */
	public static User getUser(String username) {
		LocalRepo repo = new LocalRepo();
		User loadedUser;
		
		// Attempt to load the user, if user exists return the user
		if ((loadedUser = loadUser(repo, username)) != null) {
			return loadedUser;
		}
		
		// User does not yet exist, create a new user
		return new User(repo, username);
	}
	
	/*
	 * Persists a user instance in the Repo
	 */
	public void saveUser() {
		_repo.putUser(this, _username);
	}
	
	/*
	 * Deletes the user
	 */
	public boolean deleteUser() {
		return _repo.deleteUser(_username);
	}
	
	/*
	 * Loads a persisted user instance from the Store. If the user does not exists null is returned
	 */
	private static User loadUser(LocalRepo store, String username) {
		return (User) store.getUser(username);
	}
	
	/*
	 * Static method. Returns a boolean indicating if a user exists in the Store.
	 */
	public static boolean userExists(String username) {
		LocalRepo store = new LocalRepo();	
		
		// Attempt to load the user, if user exists return true
		if (loadUser(store, username) != null) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * add a game object to user's game list
	 */
	public void addGame(Game game) {
		_gamesList.add(game);
	}
	
	/*
	 * Deletes a game from the users list
	 */
	public void deleteGame(String name) {
		Game game = findGame(name);
		
		if (game != null) {
			_gamesList.remove(game);
		}
	}
	public void deleteGame(Game game) {
		_gamesList.remove(game);
	}
	
	/*
	 * Returns a game with a given name
	 */
	public Game findGame(String name) {
		for (Game game: _gamesList) {
			if (game.getName().equals(name)) {
				return game;
			}
		}
		
		// Named game not found
		return null;
	}
	
	/*
	 * Returns all the users games
	 */
	public ArrayList<Game> getGames() {
		return _gamesList;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return _username;
	}	
}
