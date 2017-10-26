package application.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Repo implements Serializable {
	private String _outputDirectory;
	private static final String rootDirectory = System.getProperty("user.home") + File.separator + "titai";
	
	
	/*
	 * Accepts a string argument, indicating the directory - relative to titai root dir - to serialize to
	 */
	public Repo(String directory) {
		_outputDirectory = rootDirectory + File.separator + directory;
		
		// Ensures the directory is created if it doesn't yet exist
		new File(_outputDirectory).mkdirs();
	}
	
	public void serializeUser(User user, String name) {
		try {
			String fileName = _outputDirectory + File.separator + name + ".ser";
			File file = new File(fileName);
			
			FileOutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			
			objectOutput.writeObject(user);
	       
			objectOutput.close();
	        fileOutput.close();
	        
	        System.out.println("Serialised object saved to " + fileName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public User deserializeUser(String name) {
		return deserializeUser(name, _outputDirectory);
	}
	
	public User deserializeUser(String name, String sourceDirectory) {
		User user = null;
		
		try {
			String fileName = sourceDirectory + File.separator + name + ".ser";
			File file = new File(fileName);
			
			FileInputStream fileInput = new FileInputStream(file);
	        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
	        
	        user = (User) objectInput.readObject();
	        
	        objectInput.close();
	        fileInput.close();
	        
	        System.out.println("Serialised object read from " + fileName);
	        
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
	
	public ArrayList<User> deserializeAllUsers() {
		ArrayList<User> output = new ArrayList<User>();
		
		File userFolder = new File(_outputDirectory);
		File[] fileList = userFolder.listFiles();

		for (File file : fileList) {
		    if (file.isDirectory()) {
		    	User user = deserializeUser(file.getName(), _outputDirectory + File.separator + file.getName());
		    	
		    	if (user != null) {
		    		output.add(user);
		    	}
		    }
		}
		
		return output;
	}
}
