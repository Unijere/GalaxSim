package fr.istic.galaxsim.data;

import java.util.ArrayList;
/**
 * classe de filtre
 * @author Yusoig
 *
 */
public class Filter {
	
	/**
	 * boolean indiquant si le filtre de distance est activé ou pas
	 */
	private static boolean filterDistanceActived = false;
	
	/**
	 * boolean indiquant si le filtre de vitesse est activé ou pas
	 */
	private static boolean filterVelocityActived = false;
	
	/**
	 * boolean indiquant si le filtre de masse est activé ou pas
	 */
	private static boolean filterMassActived = false;
	
	/**
	 * boolean indiquant si le filtre de coordonnée est activé ou pas
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
	 * filtres de coordonnée
	 */
	private static double x1 = -1, x2 = -1, y1 = -1, y2 = -1, z1 = -1, z2 = -1;
	
	/**
	 * le temps du filtre de coordonnée
	 */
	private static int t = -1;
	
	
	/**
	 * methode permettant de filtrer les amas
	 * @return tableau d'amas
	 */
	public static Amas[] filterAmas() {
		ArrayList<Amas> listAmas = new ArrayList<Amas>();
		for (int i = 0; i<DataBase.tableAmas.size(); i++) {
			Amas a = DataBase.tableAmas.get(i);
			if (goodDistance(a) && goodVelocity(a) && goodMassAmas(a) && goodCoordinate(a)){
				listAmas.add(a);
			}
		}
		return listAmas.toArray(new Amas[listAmas.size()]);
	}
	
	/**
	 * méthode permettant de filtrer les galaxies
	 * @return tableau de galaxies
	 */
	public static Galaxy[] filterGalaxy() {
		ArrayList<Galaxy> listGalaxies = new ArrayList<Galaxy>();
		for (int i = 0; i<DataBase.tableGalaxies.size(); i++) {
			Galaxy g = DataBase.tableGalaxies.get(i);
			if (goodDistance(g) && goodVelocity(g) && goodCoordinate(g)){
				listGalaxies.add(g);
			}
		}
		return listGalaxies.toArray(new Galaxy[listGalaxies.size()]);
	}
	
	/**
	 * méthode permettant de changer le filtre de distance max
	 * @param d la distance max
	 */
	public static void setDistanceFilter(double d){
		distance = d;
		filterDistanceActived = true;
	}
	
	/**
	 * méthode permettant de changer le filtre de vitesse max
	 * @param v la vitesse max
	 */
	public static void setVelocityFilter(double v){
		velocity = v;
		filterVelocityActived = true;
	}
	
	/**
	 * méthode permettant de changer le filtre de masse max
	 * @param m la masse max
	 */
	public static void setMassFilter(double m){
		mass = m;
		filterMassActived = true;
	}
	
	/**
	 * méthode permettant de changer le fltre de coordonnée max par rapport à un temps
	 * @param X1 coordonnée max x1
	 * @param X2 coordonnée max x2
	 * @param Y1 coordonnée max y1
	 * @param Y2 coordonnée max y2
	 * @param Z1 coordonnée max z1
	 * @param Z2 coordonnée max z2
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
	 * méthode permettant de supprimer les veleurs de filtres
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
	 * methode permettant de savoir si un objet se situe à un distance inférieure ou égale à celle indiquée en paramètre
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
	 * methode permettant de savoir si un objet a une vitesse inférieure ou égale à celle indiquée en paramètre
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
	 * methode permettant de savoir si un amas a une masse inférieure ou égale à celle indiquée en paramètre
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
	 * methode permettant de savoir si un objet se trouve dans les intervalles de coordonées [x1,x2], [y1,y2], [z1,z2] à un moment t
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
