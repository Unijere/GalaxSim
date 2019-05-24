package fr.istic.galaxsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GalaxSim extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/MainWindows.fxml"));
        Scene scene = new Scene(root, 800, 600);

        scene.getStylesheets().add(getClass().getResource("gui/style.css").toExternalForm());

        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("GalaxSim");
        stage.setScene(scene);
        stage.show();
    }
}
