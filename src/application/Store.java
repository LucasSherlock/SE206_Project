package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Store {
	private String _fileName;
	private File _file;
	public static String directory = System.getProperty("user.home") + File.separator + "titai";
	
	
	public Store(File file) {
		_file = file;
		_fileName = file.toString();
	}
	
	public Store(Game game) {
		this(game.getName());
	}
	
	public Store(String name) {		
		new File(directory).mkdir();
	
		_fileName = directory + "/" + name + ".ser";
        _file = new File(_fileName);
	}
	
	public void saveGame(Game game) {
		try {			
			FileOutputStream fileOutput = new FileOutputStream(_file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			
			objectOutput.writeObject(game);
	       
			objectOutput.close();
	        fileOutput.close();
	        
	        System.out.printf("Serialised game saved to " + _fileName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Game loadGame() {
		Game game = null;
		
		try {
			FileInputStream fileInput = new FileInputStream(_file);
	        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
	        
	        game = (Game) objectInput.readObject();
	        
	        objectInput.close();
	        fileInput.close();
		}
		catch(IOException i) {
			i.printStackTrace();
	    }
		catch (ClassNotFoundException c) {
	    		c.printStackTrace();
	    }
		
		return game;
	}
	
	public void deleteGame() {
		_file.delete();
	}
}
