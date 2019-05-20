package fr.istic.galaxsim.data;

import java.util.*;

/**
 * classe qui permet de stocker les galaxies et les amas
 * @author anaofind
 *
 */
public class DataBase {
	
	/**
	 * table de galaxies (indice = identifiant)
	 */
	private static ArrayList<Galaxy> tableGalaxies = new ArrayList<Galaxy>();
	
	/**
	 * table d'amas (indice = identiant)
	 */
	private static ArrayList<Amas> tableAmas = new ArrayList<Amas>();
	
	/**
	 * methode permettant de recuperer le tableau de toutes les galaxies
	 * @return le tableau de toutes les galaxies
	 */
	public static Galaxy[] getAllGalaxies(){
		return null;
	}
	
	/**
	 * methode permettant de recuperer le tableau de tout les amas
	 * @return le tableau de tout les amas
	 */
	public static Amas[] getAllAmas(){
		return null;
	}
	
	/**
	 * methode permettant d'ajouter à la table une galaxies
	 * @param g la galaxie que l'on souhaite ajouter
	 */
	public static void addGalaxy(Galaxy g){
		tableGalaxies.add(g);
	}
	
	/**
	 * methode permettant d'ajouter à la table un amas
	 * @param a l'amas que l'on souhaite ajouter
	 */
	public static void addAmas(Amas a){
		tableAmas.add(a);
	}
	
	/**
	 * methode permettant de recuperer un amas
	 * @param l'indice de l'amas
	 * @return l'amas
	 */
	public static Amas getAmas(int indice){
		if (indice >= 0 && indice <= tableAmas.size()) {
			return tableAmas.get(indice);
		}
		return null;
	}
	
	/**
	 * methode permettant de recuperer une galaxy
	 * @param l'indice de la galaxie
	 * @return la galaxy
	 */
	public static Galaxy getGalaxy(int indice){
		if (indice >= 0 && indice <= tableGalaxies.size()) {
			return tableGalaxies.get(indice);
		}
		return null;
	}
	
	/**
	 * methode permettant de supprimer toutes les galaxies
	 */
	public static void removeAllGalaxies(){
		tableGalaxies = new ArrayList<Galaxy>();
	}
	
	/**
	 * methode permettant de supprimer tout les amas
	 */
	public static void removeAllAmas(){
		tableAmas = new ArrayList<Amas>();
	}
	
	/**
	 * methode permettant de sacoir combien il y a de galaxies dans la table
	 * @return le nombre de galaxies
	 */
	public static int getNumberAmas(){
		return tableAmas.size();
	}
	
	/**
	 * methode permettant de savoir combien il y a d'amas dans la table
	 * @return le nombre d'amas
	 */
	public static int getNumberGalaxies(){
		return tableGalaxies.size();
	}
}
