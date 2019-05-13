package fr.istic.galaxsim.gui;

import java.io.Serializable;
import java.util.ArrayList;

import fr.istic.galaxsim.serialization.*;

public class Galaxy implements Serializable{
	
	
	private static final long serialVersionUID = -2780014384183101128L;
	
	private String name = "default";
	private Coordonnees currentCoordonnes;
	private ArrayList<Coordonnees> coordonneesFuture = new ArrayList<Coordonnees>();
	private ArrayList<Coordonnees> coordonneesPasse = new ArrayList<Coordonnees>();

	private Vitesse currentVitesse;
	private ArrayList<Vitesse> vitesseFuture = new ArrayList<Vitesse>();
	private ArrayList<Vitesse> vitessePasse = new ArrayList<Vitesse>();
	
	public Galaxy (String name){
		this.name = name;
	}
	
	public void addFuturCoordonnee(Coordonnees c){
		this.coordonneesFuture.add(c);
	}
	
	public void addPastCoordonnee(Coordonnees c){
		this.coordonneesPasse.add(c);
	}
	
	
	public void setCurrentCoordonnees(Coordonnees c){
		this.currentCoordonnes = c;
	}
	
	public Coordonnees getCoordonnees(){
		return this.currentCoordonnes;
	}
	
	public Coordonnees getCoordonnees(int time){
		return (time<0?
					this.coordonneesPasse.get(-time-1):
				time> 0 ? 
					this.coordonneesFuture.get(time-1): 
				this.currentCoordonnes);
	}
	
	
	private void setCurrentVitesse(Vitesse vitesse) {
		this.currentVitesse = vitesse;
		
	}
	
	public Vitesse getVitesse(){
		return this.currentVitesse;
	}
	
	public Vitesse getVitesse(int time){
		return (time<0?
					this.vitessePasse.get(-time-1):
				time> 0 ? 
					this.vitesseFuture.get(time-1): 
				this.currentVitesse);
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
	
	
	public static void main(String[] args){
		
		/*
		 * Test pour création de galaxies avec vitesses
		 * 
		 */
		Galaxy g1 = new Galaxy("g1");
		g1.setCurrentCoordonnees(new Coordonnees(5, 6, 2));
		g1.setCurrentVitesse( new Vitesse (1,3,-2));
		for(int i=1;i<50;i++){
			g1.addFuturCoordonnee(new Coordonnees(
					(g1.getCoordonnees(i-1).x+g1.getVitesse().x) ,
					(g1.getCoordonnees(i-1).y+g1.getVitesse().y) ,
					(g1.getCoordonnees(i-1).z+g1.getVitesse().z)		));
		}
		
		
		
		Galaxy g2 = new Galaxy("g2");
		g2.setCurrentCoordonnees(new Coordonnees(25, 12, 2));
		g2.setCurrentVitesse( new Vitesse (8,-5,-2));
		for(int i=1;i<50;i++){
			g2.addFuturCoordonnee(new Coordonnees(
					(g2.getCoordonnees(i-1).x+g2.getVitesse().x) ,
					(g2.getCoordonnees(i-1).y+g2.getVitesse().y) ,
					(g2.getCoordonnees(i-1).z+g2.getVitesse().z)		));
		}
		
		Galaxy g3 = new Galaxy("g3");
		g3.setCurrentCoordonnees(new Coordonnees(5, -7, 11));
		g3.setCurrentVitesse( new Vitesse (-3,3,2));
		for(int i=1;i<50;i++){
			g3.addFuturCoordonnee(new Coordonnees(
					(g3.getCoordonnees(i-1).x+g3.getVitesse().x) ,
					(g3.getCoordonnees(i-1).y+g3.getVitesse().y) ,
					(g3.getCoordonnees(i-1).z+g3.getVitesse().z)		));
		}
		
		
		g1.serialize();
		g2.serialize();
		g3.serialize();
	}

	public int getNbCoordonnesFuture() {
		
		return this.coordonneesFuture.size();
	}

	
	
	
}
