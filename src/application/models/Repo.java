package application.models;

import java.io.Serializable;
import java.util.ArrayList;


/*
 * Defines an interface for a repository, which can be used to store and
 * retrieve persisted data.
 */
public interface Repo extends Serializable {
	/*
	 * Persists a user in the Repo, this user can later be retrieved.
	 */
	public void putUser(User user, String name);
	
	/*
	 * Retrieves a user with a given `name` from the Repo.
	 */
	public User getUser(String name);
	
	/*
	 * Retrieves all users from the Repo.
	 */
	public ArrayList<User> getAllUsers();
}
