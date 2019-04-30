package fr.istic.galaxsim.gui;

import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * Camera libre permettant de changer sa position et son angle de vue
 *
 * Touches A et Z : deplacement en avant ou en arriere
 * Maintient du clique droit : deplacement lateral de la vue sur l'axe des X
 * Maintient du clique molette : changement de l'angle de vue sur les axes X et Y
 */
public class FreeCamera extends PerspectiveCamera {

    public static final double DEFAULT_MOVE_SPEED = 20f;

    private Translate camTranslate = new Translate();
    private Rotate camRotateX = new Rotate(-20, Rotate.X_AXIS);
    private Rotate camRotateY = new Rotate(0, Rotate.Y_AXIS);

    private double moveSpeed;

    private double lastMouseClickPosX;
    private double lastMouseClickPosY;

    public FreeCamera(Stage stage) {
        this(stage, false);
    }

    public FreeCamera(Stage stage, boolean fixedEyeAtCameraZero) {
        super(fixedEyeAtCameraZero);

        moveSpeed = DEFAULT_MOVE_SPEED;

        getTransforms().addAll(camRotateX, camRotateY, camTranslate);

        /* Sauvegarde des coordonnees de la souris lorsque le bouton droit
        ou le clique molette est active afin de calculer le deplacement de
        la camera */
        stage.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if(event.isSecondaryButtonDown() || event.isMiddleButtonDown()) {
                lastMouseClickPosX = event.getSceneX();
                lastMouseClickPosY = event.getSceneY();
            }
        });

        /* Deplacement de la camera a l'aide du clique droit ou du clique
        molette */
        stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double mouseDeltaX = event.getSceneX() - lastMouseClickPosX;
            double mouseDeltaY = event.getSceneY() - lastMouseClickPosY;

            if(event.isMiddleButtonDown()) {
                camRotateX.setAngle(camRotateX.getAngle() - mouseDeltaY);
                camRotateY.setAngle(camRotateY.getAngle() + mouseDeltaX);
            }
            else if(event.isSecondaryButtonDown()) {
                camTranslate.setX(camTranslate.getX() - mouseDeltaX);
                camTranslate.setY(camTranslate.getY() - mouseDeltaY);
            }

            // Sauvegarde de la position de la souris pour les prochains deplacement
            lastMouseClickPosX = event.getSceneX();
            lastMouseClickPosY = event.getSceneY();

        });

        // Deplacement en avant ou en arriere a l'aide des touches Z et S
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch(event.getCode()) {
                case Z:
                    moveForward(moveSpeed);
                    break;
                case S:
                    moveBackward(moveSpeed);
            }
        });
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void moveBackward(double distance) {
        camTranslate.setZ(camTranslate.getZ() - distance);
    }

    public void moveForward(double distance) {
        moveBackward(-distance);
    }

    /**
     * Definir la distance a parcourir lorsque la camera avance ou recule
     *
     * @param moveSpeed nombre de pixels parcourus
     */
    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

}
