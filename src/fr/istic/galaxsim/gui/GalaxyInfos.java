package fr.istic.galaxsim.gui;

import fr.istic.galaxsim.data.Galaxy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
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

    @FXML
    private Label identLabel;
    @FXML
    private Label velocityLabel;
    @FXML
    private Label distanceLabel;
    @FXML
    private Label sglongLabel;
    @FXML
    private Label sglatLabel;

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

    }

    public void setGalaxy(Galaxy g) {
        identLabel.setText(String.valueOf(g.getIdent()));
        velocityLabel.setText(String.valueOf(g.getVelocity()));
        distanceLabel.setText(String.valueOf(g.getDistance()));
        sglongLabel.setText(String.valueOf(g.getSuperGalacticLon()));
        sglatLabel.setText(String.valueOf(g.getSuperGalacticLat()));
    }

    @FXML
    private void hidePane(MouseEvent event) {
        setVisible(false);
    }

    @FXML
    private void movePane(MouseEvent event) {
        if(event.isPrimaryButtonDown()) {
            setTranslateX(getTranslateX() + event.getX() - getWidth() / 2);
            setTranslateY(getTranslateY() + event.getY() - controlBar.getHeight() / 2);
        }
    }

}
