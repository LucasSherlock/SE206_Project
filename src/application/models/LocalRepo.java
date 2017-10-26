package application.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/*
 * Implementation of the Repo interface
 */
@SuppressWarnings("serial")
public class LocalRepo implements Repo {
	private String _rootDirectory = System.getProperty("user.home") + File.separator + "titai";
	private String _userDirectory;
	
	
	public LocalRepo() {
		_userDirectory = _rootDirectory + File.separator + "users";
		
		// Ensures the output directories exist
		new File(_rootDirectory).mkdirs();
		new File(_userDirectory).mkdir();
	}
	
	@Override
	public boolean putUser(User user, String id) {
		try {
			String outputDirectory = _userDirectory + File.separator + id;
			String outputFile = outputDirectory + File.separator + id + ".ser";
			
			// Ensures the output directory exists
			new File(outputDirectory).mkdir();
			
			// The file to write the serialized user to
			File file = new File(outputFile);
			
			FileOutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			
			// Stream the serialized object into the file
			objectOutput.writeObject(user);
	       
			objectOutput.close();
	        fileOutput.close();
	        
	        System.out.println("Serialised user saved to " + outputFile);
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override	
	public User getUser(String id) {
		User user = null;
		
		try {
			String userPath = _userDirectory + File.separator + id + File.separator + id + ".ser";
			File userFile = new File(userPath);
			
			FileInputStream fileInput = new FileInputStream(userFile);
	        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
	        
	        user = (User) objectInput.readObject();
	        
	        objectInput.close();
	        fileInput.close();
	        
	        System.out.println("Serialised user read from " + userPath);
	        
	        return user;
		}
		catch(IOException i) {
			return null;
	    }
		catch (ClassNotFoundException c) {
	    	c.printStackTrace();
	    }
		
		return user;
	}
	
	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> output = new ArrayList<User>();
		
		File userFolder = new File(_userDirectory);
		File[] fileList = userFolder.listFiles();

		if (fileList != null) {
			for (File file : fileList) {
			    if (file.isDirectory()) {
			    	User user = getUser(file.getName());
			    	
			    	if (user != null) {
			    		output.add(user);
			    	}
			    }
			}
		}
		
		return output;
	}

	@Override
	public boolean deleteUser(String id) {
		String directoryPath = _userDirectory + File.separator + id;
		File directory = new File(directoryPath);
		
		return recursiveDelete(directory);
	}
	
	/*
	 * Recursively deletes all files in a directory, including all sub-directories
	 */
	private boolean recursiveDelete(File directory) throws RuntimeException {
		try {
			for (File file: directory.listFiles()) {
				if (file.isDirectory()) {
					recursiveDelete(file);
				}
				else {
					deleteOrThrow(file);
				}
			}
			
			deleteOrThrow(directory);
		}
		catch (RuntimeException e) {
			e.printStackTrace();			
			return false;
		}
		
		return true;
	}
	
	/*
	 * Tries to delete a file, if unsuccessful throws RunTimeException
	 */
	private void deleteOrThrow(File file) throws RuntimeException {
		if (file.delete() == false) {
			throw new RuntimeException("Failed to delete file.");
		}
	}
}
