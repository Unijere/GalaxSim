package fr.istic.galaxsim.gui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Universe extends Group {

    private Translate translate = new Translate();
    public Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
    public Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

    private double lastMouseClickPosX;
    private double lastMouseClickPosY;

    public Universe(Node parentContainer) {
        Box box = new Box(50, 50, 50);
        box.setDrawMode(DrawMode.LINE);
        getChildren().add(box);

        getTransforms().addAll(rotateX, rotateY, translate);

        /* Sauvegarde des coordonnees de la souris lorsque le bouton droit
        ou le clique molette est active afin de calculer le deplacement de
        la camera */
        parentContainer.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if(event.isSecondaryButtonDown() || event.isMiddleButtonDown()) {
                lastMouseClickPosX = event.getSceneX();
                lastMouseClickPosY = event.getSceneY();
            }
        });

        /* Deplacement de la camera a l'aide du clique droit ou du clique
        molette */
        parentContainer.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double mouseDeltaX = event.getSceneX() - lastMouseClickPosX;
            double mouseDeltaY = event.getSceneY() - lastMouseClickPosY;

            if(event.isMiddleButtonDown()) {
                rotateX.setAngle(rotateX.getAngle() - mouseDeltaY);
                rotateY.setAngle(rotateY.getAngle() + mouseDeltaX);
            }
            else if(event.isSecondaryButtonDown()) {
                translate.setX(translate.getX() + mouseDeltaX);
                translate.setY(translate.getY() + mouseDeltaY);
            }

            // Sauvegarde de la position de la souris pour les prochains deplacements
            lastMouseClickPosX = event.getSceneX();
            lastMouseClickPosY = event.getSceneY();

        });

        parentContainer.addEventHandler(ScrollEvent.ANY, event -> {
            double amount = (event.getDeltaY() < 0.0) ? 20f : -20f;
            setTranslateZ(getTranslateZ() + amount);
        });
    }

}
