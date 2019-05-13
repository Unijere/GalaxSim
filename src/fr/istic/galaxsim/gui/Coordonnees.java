package fr.istic.galaxsim.gui;

import java.io.Serializable;

import javafx.geometry.Point3D;

public class Coordonnees implements Serializable{
	
	private static final long serialVersionUID = -140577560008492292L;
	
	public double x, y, z;
	
	public Coordonnees (double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D getPoint3D(){
		return new Point3D(this.x, this.y, this.z);
	}
	
	
	
}
