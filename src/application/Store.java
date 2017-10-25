package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Store implements Serializable {
	private String _outputDirectory;
	private static final String rootDirectory = System.getProperty("user.home") + File.separator + "titai";
	
	
	/*
	 * Accepts a string argument, indicating the directory - relative to titai root dir - to serialize to
	 */
	public Store(String directory) {
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
	        
	        System.out.printf("Serialised object saved to " + fileName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object deserializeUser(String name) {
		User user = null;
		
		try {
			String fileName = _outputDirectory + File.separator + name + ".ser";
			File file = new File(fileName);
			
			FileInputStream fileInput = new FileInputStream(file);
	        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
	        
	        user = (User) objectInput.readObject();
	        
	        objectInput.close();
	        fileInput.close();
	        
	        System.out.printf("Serialised object read from " + fileName);
	        
	        return user;
		}
		catch (FileNotFoundException f) {
			return null;
		}
		catch(IOException i) {
			i.printStackTrace();
	    }
		catch (ClassNotFoundException c) {
	    	c.printStackTrace();
	    }
		
		return user;
	}       
}
