package fr.istic.galaxsim.gui;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
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
	@FXML private StackPane stackPane;
	@FXML private VBox formPanel;
	
	
	public Layout(){
		
	}
	
	
	@FXML
    public void initialize() {
		
		
	}
	
	
	
	@FXML private void canvasZoom(ScrollEvent event) {	//zoom
		double vitesseZoom = 0.0012;
		if(stackPane.getScaleX()>0.05 && event.getDeltaY()<0){
			stackPane.setScaleX(stackPane.getScaleX()+event.getDeltaY()*vitesseZoom*stackPane.getScaleX());
			stackPane.setScaleY(stackPane.getScaleY()+event.getDeltaY()*vitesseZoom*stackPane.getScaleY());
		}else if(stackPane.getScaleX() < 50 && event.getDeltaY()>0){
			stackPane.setScaleX(stackPane.getScaleX()+event.getDeltaY()*vitesseZoom*stackPane.getScaleX());
			stackPane.setScaleY(stackPane.getScaleY()+event.getDeltaY()*vitesseZoom*stackPane.getScaleY());
		}
        event.consume();
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


	public void addSpace(Group space) {
		space.setOnScroll((EventHandler<? super ScrollEvent>) event ->{
			canvasZoom(event);
		});
		this.stackPane.getChildren().add(space);
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
