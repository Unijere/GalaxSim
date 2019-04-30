package fr.istic.galaxsim.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class View extends Application {
	
	
	public static void main(String[] args) { launch(args); }

	@Override
	public void start(Stage stage) throws Exception {


		
		
		
		
		//FXMLLoader fxmlloader = new FXMLLoader();
		//Parent root = fxmlloader.load(getClass().getResource("Layout.fxml"));
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout.fxml"));
	    Parent root = loader.load();
	    Layout mylayout = loader.getController();
	    
	    
		Scene scene = new Scene(root, 100, 100);
		stage.setTitle("GalaxSim");
		stage.initStyle(StageStyle.DECORATED);
		stage.setMaximized(true);
		stage.setHeight(600);
		stage.setWidth(800);
		stage.setScene(scene);
		stage.show();
		
		
		
		fillOption(mylayout);
		
		
		
		//quit actions
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
				
		
	}
	
	
	public void fillOption(Layout mylayout){
		mylayout.addFormOption(new Rectangle(50,82));
		TextField t = new TextField("info");
		t.setMaxWidth(350);
		mylayout.addFormOption(t);
		mylayout.addFormOption(new Rectangle(20,30));
		mylayout.addFormOption(new Label("hey, salut"));
		mylayout.addFormOption(new Circle(23));
		mylayout.addFormOption(new Rectangle(58,12));
	}
	
	
}
