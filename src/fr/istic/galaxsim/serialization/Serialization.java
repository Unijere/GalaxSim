package fr.istic.galaxsim.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {
	
	
	// serialization of an object "objectToSerialize" to the file "fileName"
	public static void serialize(Object objectToSerialize,String fileName){
		try{
			FileOutputStream fileOutputStream
		      = new FileOutputStream(fileName);
		    ObjectOutputStream objectOutputStream 
		      = new ObjectOutputStream(fileOutputStream);
		    objectOutputStream.writeObject(objectToSerialize);
		    objectOutputStream.flush();
		    objectOutputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// deserialization of an object from the file "fileName"
	public static Object deserialize(String fileName){
		
		Object res = null;
		try{
			FileInputStream fileInputStream
		      = new FileInputStream(fileName);
		    ObjectInputStream objectInputStream
		      = new ObjectInputStream(fileInputStream);
		    res =  objectInputStream.readObject();
		    objectInputStream.close(); 
		}catch(IOException  | ClassNotFoundException e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	
}
