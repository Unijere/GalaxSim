package fr.istic.galaxsim.gui;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

public class Space3D {
	private Group space = new Group();
	private ArrayList<Path3DTransition> transitions = new ArrayList<Path3DTransition>();
	
	
	public Space3D(){
		/*
		Box box = new Box(50, 50, 50);
		box.setDrawMode(DrawMode.LINE);
		space.getChildren().add(box);*/
		
		//pour que l'écran ne s'adapte pas aux positions des spheres
		space.setManaged(false);
		space.setLayoutX(600);
		space.setLayoutY(400);
		
	}
	public void addGalaxy(Galaxy g){
		Sphere s1 = new Sphere(5);
        Color c = Color.RED;
        s1.setMaterial(new PhongMaterial(c));
        s1.setTranslateX(g.getCoordonnees().x);
        s1.setTranslateY(g.getCoordonnees().y);
        s1.setTranslateZ(g.getCoordonnees().z);
        
        space.getChildren().add(s1);
        
        
        Path3D path = new Path3D(g.getCoordonnees().getPoint3D());
        for(int j = 1;j < g.getNbCoordonnesFuture();j++) {
            Point3D p2;
            p2 = g.getCoordonnees(j).getPoint3D();
            path.addTarget(p2);
        }

        Path3DTransition trans = new Path3DTransition(s1, path, 10);
        
        this.transitions.add(trans);
           
	}
	
	public ArrayList<Path3DTransition> getTransitions(){
		return this.transitions;
	}
	
	public void play(){
		for(Path3DTransition pt : this.transitions){
			pt.play();
		}
	}
	
	public void play(double d){
		
		for(Path3DTransition pt : this.transitions){
			//pt.stop();
		}
		
		for(Path3DTransition pt : this.transitions){
			pt.playFrom(Duration.seconds(d));
			pt.getPath().setNext(d/pt.getTotalDuration().toSeconds());
			
		}
		for(Path3DTransition pt : this.transitions){
			//pt.play();
			
		}
	}
	
	
	
	public Group getSpace(){
		return this.space;
	}
}
