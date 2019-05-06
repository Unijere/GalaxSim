package fr.istic.galaxsim.gui;

import java.io.Serializable;
import java.util.ArrayList;

import fr.istic.galaxsim.serialization.*;

public class Galaxy implements Serializable{
	
	
	private static final long serialVersionUID = -2780014384183101128L;
	
	private String name = "default";
	private ArrayList<Coordonnees> coordonneesFuture = new ArrayList<Coordonnees>();
	private ArrayList<Coordonnees> coordonneesPasse = new ArrayList<Coordonnees>();
	
	public Galaxy (String name){
		this.name = name;
	}
	
	public void addFuturCoordonnee(Coordonnees c){
		this.coordonneesFuture.add(c);
	}
	
	public void serialize() {
		Serialization.serialize(this, "data/"+this.name);
		System.out.println("finished : "+this.name);
	}
	
	public static Galaxy deserialize(String name){
		try{
			return (Galaxy) Serialization.deserialize("data/"+name);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
