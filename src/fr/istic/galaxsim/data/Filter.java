package fr.istic.galaxsim.data;

import java.util.ArrayList;
/**
 * classe de parser de données de galaxies
 * @author Yusoig
 *
 */
public class Filter {

	/**
	 * methode retournant la liste de galaxies se situant à un distance inférieure ou égale à celle indiquée en paramètre
	 * @param distance 
	 * @return une liste de galaxies
	 */
	public static ArrayList<Galaxy> filterDistanceGalaxy(double distance) {
		ArrayList<Galaxy> listGalaxy= new ArrayList<Galaxy>();
		for(int i =0 ; i<DataBase.tableGalaxies.size();i++) {
			if(DataBase.tableGalaxies.get(i).getDistance()<=distance) {
				listGalaxy.add(DataBase.tableGalaxies.get(i));
			}
		}
		return listGalaxy;
	}
	
	/**
	 * methode retournant la liste d'amas se situant à un distance inférieure ou égale à celle indiquée en paramètre
	 * @param distance 
	 * @return une liste de d'amas
	 */
	public static ArrayList<Amas> filterDistanceAmas(double distance) {
		ArrayList<Amas> listAmas= new ArrayList<Amas>();
		for(int i =0 ; i<DataBase.tableAmas.size();i++) {
			if(DataBase.tableAmas.get(i).getDistance()<=distance) {
				listAmas.add(DataBase.tableAmas.get(i));
			}
		}
		return listAmas;
	}
	
	
	/**
	 * methode retournant la liste de galaxies aillant une vélocité inférieure ou égale à celle indiquée en paramètre
	 * @param velocity 
	 * @return une liste de galaxies
	 */
	public static ArrayList<Galaxy> filterVelocityGalaxy(double velocity) {
		ArrayList<Galaxy> listGalaxy= new ArrayList<Galaxy>();
		for(int i =0 ; i<DataBase.tableGalaxies.size();i++) {
			if(DataBase.tableGalaxies.get(i).getVelocity()<=velocity) {
				listGalaxy.add(DataBase.tableGalaxies.get(i));
			}
		}
		return listGalaxy;
	}
	
	/**
	 * methode retournant la liste d'amas aillant une vélocité inférieure ou égale à celle indiquée en paramètre
	 * @param velocity 
	 * @return une liste de d'amas
	 */
	public static ArrayList<Amas> filterVelocityAmas(double velocity) {
		ArrayList<Amas> listAmas= new ArrayList<Amas>();
		for(int i =0 ; i<DataBase.tableAmas.size();i++) {
			if(DataBase.tableAmas.get(i).getVelocity()<=velocity) {
				listAmas.add(DataBase.tableAmas.get(i));
			}
		}
		return listAmas;
	}
	
	
	/**
	 * methode retournant la liste d'amas aillant une masse inférieure ou égale à celle indiquée en paramètre
	 * @param mass 
	 * @return une liste de d'amas
	 */
	public static ArrayList<Amas> filterMassAmas(double mass) {
		ArrayList<Amas> listAmas= new ArrayList<Amas>();
		for(int i =0 ; i<DataBase.tableAmas.size();i++) {
			if(DataBase.tableAmas.get(i).getMass()<=mass) {
				listAmas.add(DataBase.tableAmas.get(i));
			}
		}
		return listAmas;
	}
	
	/**
	 * methode retournant la liste d'amas se trouvant dans les intervalles de coordonées [x1,x2], [y1,y2], [z1,z2] à un moment t
	 * @param position en x1, x2, y1, y2, z1, z2, et le moment t de l'action
	 * @return une liste de d'amas
	 */
	public static ArrayList<Amas> filterCoordinateAmas(double x1,double x2, double y1,double y2,double z1,double z2, int t){
		ArrayList<Amas> listAmas= new ArrayList<Amas>();
		for(int i =0 ; i<DataBase.tableAmas.size();i++) {
			if(DataBase.tableAmas.get(i).getCoordinate(t).isIn(x1, x2, y1, y2, z1, z2)) {
				listAmas.add(DataBase.tableAmas.get(i));
			}
		}
		return listAmas;
	}
}
