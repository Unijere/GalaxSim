package fr.istic.galaxsim.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class View extends Application {
	
	
	public static void main(String[] args) { launch(args); }

	@Override
	public void start(Stage stage) throws Exception {


		
		
		
		
		
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("GLayout.fxml"));
		
		Scene scene = new Scene(root, 100, 100);
		stage.setTitle("GalaxSim");
		stage.initStyle(StageStyle.DECORATED);
		stage.setMaximized(true);
		stage.setHeight(600);
		stage.setWidth(800);
		stage.setScene(scene);
		stage.show();
		
		//quit actions
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
				
		
	}
	
	
}
