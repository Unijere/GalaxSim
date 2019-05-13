package sample;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

import fr.istic.galaxsim.gui.Path3D;
import fr.istic.galaxsim.gui.Path3DTransition;

public class Main extends Application {

	
	
	public static void main(String[] args) { launch(args); }
	
	
    public Double boundRandomXIntoBox(Box b) {
        double max = b.getWidth() / 2;
        double min = b.getTranslateX() - max;

        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public Double boundRandomYIntoBox(Box b) {
        double max = b.getHeight() / 2;
        double min = b.getTranslateY() - max;

        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public Double boundRandomZIntoBox(Box b) {
        double max = b.getDepth() / 2;
        double min = b.getTranslateZ() - max;

        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public Point3D createRandomPoint(Box b) {
        return new Point3D(boundRandomXIntoBox(b), boundRandomYIntoBox(b), boundRandomZIntoBox(b));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Box box = new Box(50, 50, 50);
        box.setDrawMode(DrawMode.LINE);
        Group interfaceGroup = new Group();

        Sphere s2 = new Sphere(1);
        s2.setMaterial(new PhongMaterial(Color.GREEN));
        interfaceGroup.getChildren().add(s2);

        for(int i = 0;i < 5000;i++) {
            Sphere s1 = new Sphere(0.4);
            Color c = Color.rgb(ThreadLocalRandom.current().nextInt(256), ThreadLocalRandom.current().nextInt(256), ThreadLocalRandom.current().nextInt(256));
            s1.setMaterial(new PhongMaterial(c));

            Point3D p = createRandomPoint(box);
            s1.setTranslateX(p.getX());
            s1.setTranslateY(p.getY());
            s1.setTranslateZ(p.getZ());
            interfaceGroup.getChildren().add(s1);

            Path3D path = new Path3D(p);
            for(int j = 0;j < 4;j++) {
                Point3D p2;
                while((p2 = createRandomPoint(box)).equals(p)) { }
                path.addTarget(p2);
            }

            Path3DTransition trans = new Path3DTransition(s1, path, 20);
            trans.play();
        }

        interfaceGroup.getChildren().add(box);

        Translate t = new Translate(box.getTranslateX(), box.getTranslateY(), box.getTranslateZ());

        Rotate r = new Rotate(0, Rotate.Y_AXIS);


        Camera camera = new PerspectiveCamera(true);
        Scene mainScene = new Scene(interfaceGroup, 800, 600);
        mainScene.setCamera(camera);

        camera.setNearClip(1);
        camera.setFarClip(5000);
        camera.getTransforms().addAll(t, r, new Rotate(-20, Rotate.X_AXIS), new Translate(0, 0, -200));

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch(event.getCode()) {
                case Z:
                    camera.translateZProperty().setValue(camera.getTranslateZ() + 50);
                    break;
                case S:
                    camera.translateZProperty().setValue(camera.getTranslateZ() - 50);
                    break;
                case Q:
                    r.setAngle(r.getAngle() + 45);
                    break;
                case D:
                    r.setAngle(r.getAngle() - 45);
                    break;
            }
        });

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

}
