package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	private String _fileName;
	
	public Serializer(String name) {
		_fileName = "/data/" + name + ".ser";
	}
	
	public void saveGame(Game game) {
		try {
			FileOutputStream fileOutput = new FileOutputStream(_fileName);
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
			FileInputStream fileInput = new FileInputStream(_fileName);
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
}
