package fr.istic.galaxsim.gui;

import fr.istic.galaxsim.data.Amas;
import fr.istic.galaxsim.data.Coordinate;
import fr.istic.galaxsim.data.Galaxy;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Universe extends Group {

    private final GalaxyInfos galaxyInfos;

    private Group elements = new Group();

    private Translate translate = new Translate();
    public Rotate rotateX = new Rotate(20, Rotate.X_AXIS);
    public Rotate rotateY = new Rotate(45, Rotate.Y_AXIS);

    private double lastMouseClickPosX;
    private double lastMouseClickPosY;

    public Universe(Node parentContainer, GalaxyInfos galaxyInfos) {
        this.galaxyInfos = galaxyInfos;

        Box box = new Box(50, 50, 50);
        box.setDrawMode(DrawMode.LINE);
        getChildren().addAll(box, elements);

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

    public void addAmas(Amas a) {
        Sphere s = new Sphere(0.6f);
        s.setMaterial(new PhongMaterial(Color.RED));

        Coordinate coord = a.getCoordinate(0);
        s.setTranslateX(coord.getX());
        s.setTranslateY(coord.getY());
        s.setTranslateZ(coord.getZ());

        elements.getChildren().add(s);
    }

    public void addGalaxy(Galaxy g) {
        Sphere s = new Sphere(0.4f);
        s.setMaterial(new PhongMaterial(Color.GREEN));

        Coordinate coord = g.getCoordinate(0);
        s.setTranslateX(coord.getX());
        s.setTranslateY(coord.getY());
        s.setTranslateZ(coord.getZ());

        elements.getChildren().add(s);

        s.setOnMouseClicked((e) -> {
            galaxyInfos.setGalaxy(g);
            galaxyInfos.setVisible(true);
        });
    }

    public void clear() {
        elements.getChildren().clear();
    }

}
