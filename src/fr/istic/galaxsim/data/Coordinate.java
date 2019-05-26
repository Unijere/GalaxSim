package fr.istic.galaxsim.data;

/**
 * classe permettant de définir une coordonnee 3d
 * @author anaofind
 *
 */
public class Coordinate {
	
	/**
	 * coordonnée x
	 */
	private double x;
	
	/**
	 * coordonnée y
	 */
	private double y;
	
	/**
	 * coordonnée z
	 */
	private double z;
	
	/**
	 * constructeur
	 * @param x la coordonnée x
	 * @param y la coordonnée y
	 * @param z la coordonnée z
	 */
	public Coordinate(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * getter coordonnée x
	 * @return la coordonnée x
	 */
	public double getX() {
		return x;
	}

	/**
	 * getter coordonnée y
	 * @return la coordonnée y
	 */
	public double getY() {
		return y;
	}

	/**
	 * getter coordonnée z
	 * @return la coordonnée z
	 */
	public double getZ() {
		return z;
	}
	
	
}
