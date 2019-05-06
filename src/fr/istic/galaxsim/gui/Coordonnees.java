package fr.istic.galaxsim.gui;

import java.io.Serializable;

public class Coordonnees implements Serializable{
	
	private static final long serialVersionUID = -140577560008492292L;
	
	public double x, y, z, vx, vy, vz;
	
	public Coordonnees (double x, double y, double z, double vx, double vy, double vz){
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}
	
	
	
}
