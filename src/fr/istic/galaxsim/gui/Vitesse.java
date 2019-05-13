package fr.istic.galaxsim.gui;

import java.io.Serializable;

public class Vitesse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3823924867458006954L;

	public double x, y, z;
	
	public Vitesse (double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
}
