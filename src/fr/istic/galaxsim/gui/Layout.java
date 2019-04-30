package fr.istic.galaxsim.gui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Layout {
	
	@FXML private AnchorPane layout;
	@FXML private VBox formPanel;
	
	
	public Layout(){
		
	}
	
	
	@FXML
    public void initialize() {
		
		
	}
	
	
	
	
	public void addFormOption(Node n){
		
		
		Rectangle rectangle = new Rectangle();
		rectangle.setStroke(Color.BLACK);
		rectangle.setFill(Color.grayRgb(200));
		rectangle.setWidth(500);
		rectangle.setHeight(n.getBaselineOffset()+20);
		if(n.getBaselineOffset() <30){
			rectangle.setHeight(50);
		}
		
		
		StackPane pane = new StackPane();
		pane.getChildren().add(rectangle);
		pane .getChildren().add(n);
		this.formPanel.getChildren().add(pane);
		
	}
	
	
	
	
}
