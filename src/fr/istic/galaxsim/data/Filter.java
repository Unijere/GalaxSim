package fr.istic.galaxsim.data;

import java.util.ArrayList;
/**
 * classe de filtre
 * @author Yusoig
 *
 */
public class Filter {
	
	/**
	 * boolean indiquant si le filtre de distance est activ� ou pas
	 */
	private static boolean filterDistanceActived = false;
	
	/**
	 * boolean indiquant si le filtre de vitesse est activ� ou pas
	 */
	private static boolean filterVelocityActived = false;
	
	/**
	 * boolean indiquant si le filtre de masse est activ� ou pas
	 */
	private static boolean filterMassActived = false;
	
	/**
	 * boolean indiquant si le filtre de coordonn�e est activ� ou pas
	 */
	private static boolean filterCoordinateActived = false;
	
	/**
	 * filtre de distance
	 */
	private static double distance = -1;
	
	/**
	 * filtre de vitesse
	 */
	private static double velocity = -1;
	
	/**
	 * filtre de masse
	 */
	private static double mass = -1;
	
	/**
	 * filtres de coordonn�e
	 */
	private static double x1 = -1, x2 = -1, y1 = -1, y2 = -1, z1 = -1, z2 = -1;
	
	/**
	 * le temps du filtre de coordonn�e
	 */
	private static int t = -1;
	
	
	/**
	 * m�thode permettant de savoir si un amas correspond bien au filtres
	 * @param a l'amas
	 * @return boolean
	 */
	public static boolean goodAmas(Amas a){
		return (goodDistance(a) && goodVelocity(a) && goodMassAmas(a) && goodCoordinate(a));
	}
	
	/**
	 * m�thode permettant de savoir si une galaxy correspond bien au filtres
	 * @param g la galaxy
	 * @return boolean
	 */
	public static boolean goodGalaxies(Galaxy g){
		return (goodDistance(g) && goodVelocity(g) && goodCoordinate(g));
	}
	
	/**
	 * m�thode permettant de changer le filtre de distance max
	 * @param d la distance max
	 */
	public static void setDistanceFilter(double d){
		distance = d;
		filterDistanceActived = true;
	}
	
	/**
	 * m�thode permettant de changer le filtre de vitesse max
	 * @param v la vitesse max
	 */
	public static void setVelocityFilter(double v){
		velocity = v;
		filterVelocityActived = true;
	}
	
	/**
	 * m�thode permettant de changer le filtre de masse max
	 * @param m la masse max
	 */
	public static void setMassFilter(double m){
		mass = m;
		filterMassActived = true;
	}
	
	/**
	 * m�thode permettant de changer le fltre de coordonn�e max par rapport � un temps
	 * @param X1 coordonn�e max x1
	 * @param X2 coordonn�e max x2
	 * @param Y1 coordonn�e max y1
	 * @param Y2 coordonn�e max y2
	 * @param Z1 coordonn�e max z1
	 * @param Z2 coordonn�e max z2
	 * @param T temps
	 */
	public static void setCoordinateFilter(double X1, double X2, double Y1, double Y2, double Z1,double Z2, int T){
		x1 = X1;
		x2 = X2;
		y1 = Y1;
		y2 = Y2;
		z1 = Z1;
		z2 = Z2;
		t = T;
		filterCoordinateActived = true;
	}
	
	/**
	 * m�thode permettant de supprimer les veleurs de filtres
	 */
	public static void removeAllFilter(){
		distance = -1;
		velocity = -1;
		mass = -1;
		t = -1;
		
		filterDistanceActived = false;
		filterVelocityActived = false;
		filterMassActived = false;
		filterCoordinateActived = false;
	}
	
	/**
	 * methode permettant de savoir si un objet se situe � un distance inf�rieure ou �gale � celle indiqu�e en param�tre
	 * @param cosmosElement l'objet
	 * @param distance la distance
	 * @return boolean
	 */
	private static boolean goodDistance(CosmosElement cosmosElement) {
		if (cosmosElement.getDistance() <= distance) {
			return true;
		}
		return !filterDistanceActived;
	}
	
	/**
	 * methode permettant de savoir si un objet a une vitesse inf�rieure ou �gale � celle indiqu�e en param�tre
	 * @param cosmosElement l'objet
	 * @param velocity la vitesse
	 * @return boolean
	 */
	private static boolean goodVelocity(CosmosElement cosmosElement) {
		if (cosmosElement.getVelocity() <= velocity) {
			return true;
		}
		return !filterVelocityActived;
	}
		
	/**
	 * methode permettant de savoir si un amas a une masse inf�rieure ou �gale � celle indiqu�e en param�tre
	 * @param amas l'amas
	 * @param mass la masse
	 * @return boolean
	 */
	private static boolean goodMassAmas(Amas amas) {
		if (amas.getMass() <= mass) {
			return true;
		}
		return !filterMassActived;
	}
	
	/**
	 * methode permettant de savoir si un objet se trouve dans les intervalles de coordon�es [x1,x2], [y1,y2], [z1,z2] � un moment t
	 * @param cosmosElement l'objet
	 * @param position en x1, x2, y1, y2, z1, z2, et le moment t de l'action
	 * @return boolean
	 */
	private static boolean goodCoordinate(CosmosElement cosmosElement) {
		Coordinate c = cosmosElement.getCoordinate(t);
		if (c != null && c.isIn(x1, x2, y1, y2, z1, z2)) {
			return true;
		}
		return !filterCoordinateActived;
	}	
}
