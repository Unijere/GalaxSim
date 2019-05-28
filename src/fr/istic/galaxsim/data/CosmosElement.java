package fr.istic.galaxsim.data;

import java.util.*;

/**
 * classe abstraite permettant de d�finir un element du cosmos
 * @author anaofind
 *
 */
public abstract class CosmosElement{
	
	/**
	 * l'interval de temps s�parant deux coordonn�es cote � cote dans la liste "coordinatesInTime"
	 */
	public static double intervalTime = 1;
	
	/**
	 * les coordonn�es du corps dans le temps (indice = temps)
	 */
	private ArrayList<Coordinate> coordinatesInTime = new ArrayList<Coordinate>();  
	
	/**
	 * l'identifiant
	 */
	private int ident;
	
	/**
	 * la distance en Mpc
	 */
	private double distance;
	
	/**
	 * la vitesse en km/s
	 */
	private double velocity;
	
	/**
	 * la longitude galactique
	 */
	private double galacticLon = 0;
	
	/**
	 * la latitude galactique
	 */
	private double galacticLat = 0;
	
	/**
	 * la longitude super galactique
	 */
	private double superGalacticLon = 0;
	
	/**
	 * la latitude super galactique
	 */
	private double superGalacticLat = 0;
	
	
	/**
	 * constructeur 
	 * @param ident l'identifiant
	 * @param distance la distance
	 * @param velocity la vitesse
	 */
	public CosmosElement(int ident, double distance, double velocity){
		this.ident = ident;
		this.distance = distance;
		this.velocity = velocity;		
	}
	
	/**
	 * methode permettant d'ajouter une coordonn�e � la liste
	 */
	public void addCoordinate(Coordinate coordinate){
		this.coordinatesInTime.add(coordinate);
	}
	
	/**
	 * methode permettant de supprimer toutes les coordonn�es
	 */
	public void removeAllCoordinate(){
		this.coordinatesInTime = new ArrayList<Coordinate>();
	}
	
	/**
	 * methode permettant de recuperer une coordonn�e
	 * @param time le temps
	 */
	public Coordinate getCoordinate(int time){
		if (time >= 0 && time < this.coordinatesInTime.size()){
			return this.coordinatesInTime.get(time);
		}
		return null;
	}
	
	/**
	 * methode permettant de recuperer le nombre de coordonn�e
	 * @return le nombre de coordonn�e
	 */
	public int getSizeCoordinate(){
		return this.coordinatesInTime.size();
	}

	/**
	 * getter identifiant
	 * @return l'identifiant
	 */
	public int getIdent() {
		return ident;
	}

	/**
	 * getter distance
	 * @return la distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * getter vitesse
	 * @return la vitesse
	 */
	public double getVelocity() {
		return velocity;
	}
	
	/**
	 * methode permettant de changer la longitude et la latidue galactique
	 * @param lon la longitude galactique
	 * @param lat la latitude galactique
	 */
	public void setGalacticLongLat(double lon, double lat){
		this.galacticLon = lon;
		this.galacticLat = lat;
	}
	
	/**
	 * methode permettant de changer la longitude et la latidue super galactique
	 * @param lon la longitude super galactique
	 * @param lat la latitude super galactique
	 */
	public void setSuperGalacticLongLat(double lon, double lat){
		this.superGalacticLon = lon;
		this.superGalacticLat = lat;
	}

	/**
	 * getter longitude galactique
	 * @return la longitude galactique
	 */
	public double getGalacticLon() {
		return galacticLon;
	}
	
	/**
	 * getter latitude galactique
	 * @return la latitude galactique
	 */
	public double getGalacticLat() {
		return galacticLat;
	}

	/**
	 * getter longitude super galactique
	 * @return la longitude super galactique
	 */
	public double getSuperGalacticLon() {
		return superGalacticLon;
	}

	/**
	 * getter latitude super galactique
	 * @return la latitude super galactique
	 */
	public double getSuperGalacticLat() {
		return superGalacticLat;
	}
	
	/**
	 * methode toString
	 */
	public String toString(){
		return ident + " -> disttance: " + distance + " | vitesse: " + velocity + " | " +
				"longLatG: (" + galacticLon + "," + galacticLat + ")" + " | " + 
				"longLatSG: (" + superGalacticLon + "," + superGalacticLat + ")";
	}
}
