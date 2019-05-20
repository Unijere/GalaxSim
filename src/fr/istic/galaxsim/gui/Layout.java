package fr.istic.galaxsim.gui;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
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
	
	private Space3D space;
	private Slider slider;
	
	
	public Layout(){
		
	}
	
	
	@FXML
    public void initialize() {
		
		

		/*
		 * 
		 * Gestion du slider du temps 
		 * 
		 */
		double min=0;
		double max;
		try{
			max =  space.getTransitions().get(0).getTotalDuration().toSeconds();
		}catch(Exception e){
			max = 100;
		}
		
		double value = 0;
		
		
		Slider slider = new Slider(min, max, value);
		
		slider.setOnMouseClicked((MouseEvent h) ->{
			space.play(slider.getValue());
			
			
				});
		this.slider = slider;
		this.addFormOption(slider);
		
		
		
		
		/*
		 * timer pour actualiser le slider
		 */
		final Runnable task = new Runnable() {
            
	        @Override
	        public void run() {
	            double max;
	            try{
	    			max = space.getTransitions().get(0).getTotalDuration().toSeconds();
	    			slider.setValue(space.getTransitions().get(0).getCurrentTime().toSeconds());
	    		}catch(Exception e){
	    			max = 100;
	    		}
	            slider.setMax(max);
	            
	            
	        }
	    };
	         
	    final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
	        
	        
	        
		
		
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


	public void addSpace(Space3D space) {
		space.getSpace().setOnScroll((EventHandler<? super ScrollEvent>) event ->{
			canvasZoom(event);
		});
		this.stackPane.getChildren().add(space.getSpace());
		
		this.space = space;
		
	}
	
	
	
	
}
