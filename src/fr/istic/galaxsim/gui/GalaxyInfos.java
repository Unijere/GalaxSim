package fr.istic.galaxsim.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GalaxyInfos extends VBox {

    @FXML
    private GridPane pane;

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

}
