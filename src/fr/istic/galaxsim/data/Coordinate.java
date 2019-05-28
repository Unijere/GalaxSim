package fr.istic.galaxsim.data;

import javafx.geometry.Point3D;

/**
 * classe permettant de d�finir une coordonnee 3d
 * @author anaofind
 *
 */
public class Coordinate {
	
	/**
	 * coordonn�e x
	 */
	private double x;
	
	/**
	 * coordonn�e y
	 */
	private double y;
	
	/**
	 * coordonn�e z
	 */
	private double z;
	
	/**
	 * constructeur
	 * @param x la coordonn�e x
	 * @param y la coordonn�e y
	 * @param z la coordonn�e z
	 */
	public Coordinate(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * getter coordonn�e x
	 * @return la coordonn�e x
	 */
	public double getX() {
		return x;
	}

	/**
	 * getter coordonn�e y
	 * @return la coordonn�e y
	 */
	public double getY() {
		return y;
	}

	/**
	 * getter coordonn�e z
	 * @return la coordonn�e z
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * methode permetant de savoir si le point est ou non dans les intervalles de coordon�es [x1,x2], [y1,y2], [z1,z2]
	 * @return 1 si le poit est pr�sent dans l'intervalle, sinon 0
	 */
	public boolean isIn(double x1,double x2, double y1,double y2,double z1,double z2) {
		return x1<=x && x2>=x && y1<= y && y2>=y && z1<=z && z2>=z;
	}

	public Point3D getPoint3D() {
		
		return new Point3D(this.x, this.y, this.z);
	}
	
	
}