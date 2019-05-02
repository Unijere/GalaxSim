package fr.istic.galaxsim.gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import org.fxyz3d.shapes.primitives.ConeMesh;

public class AxesIndicator extends Group {

    public AxesIndicator(double scale) {
        // Definition des textures pour chaque axe
        PhongMaterial materialX = new PhongMaterial(Color.RED);
        PhongMaterial materialY = new PhongMaterial(Color.GREEN);
        PhongMaterial materialZ = new PhongMaterial(Color.BLUE);

        Rotate xRotate = new Rotate(90, Rotate.Z_AXIS);
        Rotate zRotate = new Rotate(-90, Rotate.X_AXIS);

        // Creation des cylindres representant chaque axe
        Cylinder xAxis = new Cylinder(0.8f, 20f);
        xAxis.getTransforms().add(xRotate);
        xAxis.setMaterial(materialX);

        Cylinder yAxis = new Cylinder(0.8f, 20f);
        yAxis.setMaterial(materialY);

        Cylinder zAxis = new Cylinder(0.8f, 20f);
        zAxis.getTransforms().add(zRotate);
        zAxis.setMaterial(materialZ);

        // Un cone est ajoute au bout de chaque cylindre pour representer la direction de l'axe
        ConeMesh coneX = new ConeMesh(0.8f, 1.5f);
        coneX.setMaterial(materialX);
        coneX.getTransforms().addAll(new Translate(xAxis.getHeight() / 2 + coneX.getHeight(), 0, 0), xRotate);

        ConeMesh coneY = new ConeMesh(0.8f, 1.5f);
        coneY.setMaterial(materialY);
        coneY.getTransforms().add(new Translate(0, -yAxis.getHeight() / 2 - coneY.getHeight(), 0));

        ConeMesh coneZ = new ConeMesh(0.8f, 1.5f);
        coneZ.setMaterial(materialZ);
        coneZ.getTransforms().addAll(new Translate(0, 0, zAxis.getHeight() / 2 + coneZ.getHeight()), zRotate);

        getChildren().addAll(xAxis, yAxis, zAxis, coneX, coneY, coneZ);
        getTransforms().add(new Scale(scale, scale, scale));
    }

}
