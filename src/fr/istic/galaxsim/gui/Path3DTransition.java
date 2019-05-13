package fr.istic.galaxsim.gui;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Point3D;
import javafx.scene.shape.Shape3D;
import javafx.util.Duration;

public class Path3DTransition extends Transition {

    private final Shape3D shape;
    private Path3D path;

    // Durée du parcours en passant par tous les points intermediaires
    private double duration;

    private double totalDistance;
    private double moveSpeed = 0.0;
    private double lastDistance = 0.0;
    private boolean reverse = false;

    public Path3DTransition(Shape3D shape, Path3D path, double duration) {
        super(60);
        this.shape = shape;
        this.path = path;
        this.duration = duration;

        this.totalDistance = path.getTotalDistance();
        this.moveSpeed = totalDistance / duration;

        setInterpolator(Interpolator.LINEAR);
        setCycleDuration(Duration.INDEFINITE);

        lastDistance = path.getStartPoint().distance(path.getTarget());
    }

    public Point3D getCurrentShapePosition() {
        return new Point3D(shape.getTranslateX(), shape.getTranslateY(), shape.getTranslateZ());
    }

    @Override
    protected void interpolate(double v) {
        double d = getCurrentShapePosition().distance(path.getTarget()) - 0.01;
        if(lastDistance > d) {
            Point3D direction = path.getDirection(getCurrentShapePosition());
            direction.multiply(moveSpeed);
            path.getStartPoint().add(direction);

            shape.setTranslateX(shape.getTranslateX() + direction.getX());
            shape.setTranslateY(shape.getTranslateY() + direction.getY());
            shape.setTranslateZ(shape.getTranslateZ() + direction.getZ());
            lastDistance = d;
        }
        else {
            if(path.hasMoreTargets(reverse)) {
                path.nextTarget(reverse);
            }
            else {
                reverse = !reverse;
                path.nextTarget(reverse);
            }
            lastDistance = getCurrentShapePosition().distance(path.getTarget());
        }

    }

	public Path3D getPath() {
		// TODO Auto-generated method stub
		return this.path;
	}
}
