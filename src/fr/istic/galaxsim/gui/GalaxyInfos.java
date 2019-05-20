package fr.istic.galaxsim.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GalaxyInfos extends VBox {

    @FXML
    private GridPane pane;
    @FXML
    private HBox controlBar;

    private double lastMouseClickPosX;
    private double lastMouseClickPosY;

    public GalaxyInfos() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GalaxyInfos.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch(IOException e) {
            System.err.println("Impossible de trouver le fichier GalaxyInfos.fxml");
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        setViewOrder(-1.0);
    }

    @FXML
    private void hidePane(MouseEvent event) {
        setVisible(false);
    }

    @FXML
    private void movePane(MouseEvent event) {
        if(event.isPrimaryButtonDown()) {
            setTranslateX(event.getSceneX() - lastMouseClickPosX);
            setTranslateY(event.getSceneY() - lastMouseClickPosY);
        }
    }

    @FXML
    private void updateMousePos(MouseEvent event) {
        lastMouseClickPosX = event.getSceneX();
        lastMouseClickPosY = event.getSceneY();
    }

}
