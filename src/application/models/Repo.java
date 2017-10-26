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
	 * 
	 * @param user The user object
	 * @param name The name to store the user with - will be used later for retrieval
	 * @return     Boolean indicating if the user was successfully persisted
	 */
	public boolean putUser(User user, String id);
	
	/*
	 * Retrieves a user with a given `id` from the Repo.
	 * 
	 * @param name The id of the user to retrieve
	 * @return     The user object or null
	 */
	public User getUser(String id);
	
	/*
	 * Deletes a user with a given `id` from the Repo.
	 * 
	 * @param id The id of the user to delete
	 * @return   Boolean indicating if the user was successfully deleted
	 */
	public boolean deleteUser(String id);
	
	/*
	 * Retrieves all users from the Repo.
	 * 
	 * @return A list of all User objects in the repo
	 */
	public ArrayList<User> getAllUsers();
}
