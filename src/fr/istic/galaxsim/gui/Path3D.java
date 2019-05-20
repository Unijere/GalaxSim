package fr.istic.galaxsim.gui;

import javafx.geometry.Point3D;

import java.util.ArrayList;

public class Path3D {

    private Point3D startPoint;
    private ArrayList<Point3D> targets = new ArrayList<Point3D>();
    private int currentTargetPos = -1;

    public Path3D(Point3D startPoint) {
        this.startPoint = startPoint;
    }

    public void addTarget(Point3D target) {
        targets.add(target);
        if(targets.size() == 1) {
            // Le premier point ajoute devient la premiere destination
            currentTargetPos = 0;
        }
    }

    /**
     * Retourne le vecteur de direction pointant vers la premiere destination
     * en partant du point de depart
     *
     * @return vecteur normalise
     */
    public Point3D getDirection(Point3D currentPosition) {
        return (currentTargetPos >= 0) ? getTarget().subtract(currentPosition).normalize() :
                                        startPoint.normalize();
    }

    public Point3D getTarget() {
    	
    	
        return targets.get(currentTargetPos);
        
    }

    public double getTotalDistance() {
        double distance = (targets.size() >= 1) ? startPoint.distance(targets.get(0)) : 0.0;

        for(int i = 1;i < targets.size();i++) {
            distance += targets.get(i - 1).distance(targets.get(i));
        }

        return distance;
    }

    public Point3D getStartPoint() {
        return startPoint;
    }

    public boolean hasMoreTargets(boolean isReverse) {
        if(isReverse) {
            return currentTargetPos > 0;
        }
        else {
            return currentTargetPos < targets.size() - 1;
        }
    }

    public Point3D nextTarget() {
        return nextTarget(false);
    }

    public Point3D nextTarget(boolean reverse) {
        if(reverse) {
            currentTargetPos = Math.max(currentTargetPos - 1, 0);
        }
        else {
            currentTargetPos = Math.min(currentTargetPos + 1, targets.size() - 1);
        }
        return getTarget();
    }
    
    
    public int getSize(){
    	return this.targets.size();
    }
    public int getCurrentTarget(){
    	return this.currentTargetPos;
    }

	public void setNext(double d) {
		currentTargetPos = (int) (d * this.targets.size());
		
	}

}
