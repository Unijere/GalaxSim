package fr.istic.galaxsim.gui;

import fr.istic.galaxsim.data.Galaxy;
import fr.istic.galaxsim.data.ParserAmasDatas;
import fr.istic.galaxsim.data.ParserCosmosDatas;
import fr.istic.galaxsim.data.ParserGalaxiesDatas;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainWindow {

	@FXML
	private TextField dataFileField;
	@FXML
	private Pane pane3D;
	@FXML
	private ChoiceBox<String> dataTypeField;
	@FXML
    private Label infoLabel;
	@FXML
    private ProgressBar progressBar;
	@FXML
	private Slider slider;
	
	private File currentDataFile;
	
	private Group sceneRoot;
	
	private Space3D space;
	private Group spaceGroup = new Group();
	private Universe universe;
	
	public MainWindow(){
		currentDataFile = null;

	}
	
	@FXML
    public void initialize() {
		
        dataTypeField.getItems().addAll(DataFileType.getDescriptions());
        dataTypeField.setValue(dataTypeField.getItems().get(0));

        // La barre de chargement est uniquement affichee lorsque des donnees sont traitees
        progressBar.setManaged(false);

		sceneRoot = new Group();

		universe = new Universe(pane3D);

		AxesIndicator axes = new AxesIndicator(0.8f);
        //Translate t = new Translate(-50, 70, -80);
        Rotate rx = new Rotate(0, Rotate.X_AXIS);
        Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        rx.angleProperty().bind(universe.rotateX.angleProperty());
        ry.angleProperty().bind(universe.rotateY.angleProperty());
        axes.getTransforms().addAll(new Translate(), rx, ry);

		// Declaration de la camera
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateZ(-200);
		camera.setFarClip(5000);
		camera.setNearClip(1);

		// Creation de la scene contenant la simulation
		SubScene simScene = new SubScene(sceneRoot, 1, 1);
        simScene.setCamera(camera);
		// La scene possede la meme taille que son pere (pane3d)
		simScene.widthProperty().bind(pane3D.widthProperty());
		simScene.heightProperty().bind(pane3D.heightProperty());

        sceneRoot.getChildren().addAll(universe, axes);
        
		pane3D.getChildren().addAll(simScene);
		//this.universe.getChildren().add(this.spaceGroup);
		
		
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
		
		
		//Slider slider = new Slider(min, max, value);
		
		slider.setOnMouseClicked((MouseEvent h) ->{
			space.play(slider.getValue());
			
				});
		
		
		/*
		 * action répéter toutes les secondes pour actualiser le slider
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

	@FXML
	private void openFileBrowser(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("SÃ©lection d'un jeu de donnÃ©es");
		currentDataFile = fileChooser.showOpenDialog(null);

		if(currentDataFile != null) {
            dataFileField.setText(currentDataFile.getAbsolutePath());
        }
	}

	@FXML
	private void startDataAnalysis(ActionEvent event) {
		
		/*
         * fonction test pour avoir un aperçu du résultat
         */
        createSpace();
        
        
        
        
        // @TODO verifier la validite des champs
        if(currentDataFile == null) {
            currentDataFile = new File(dataFileField.getText());
        }

        if(!currentDataFile.isFile()) {
            System.out.println("Le fichier n'existe pas");
            return;
        }

        Task<ParserCosmosDatas> parseDataTask = new Task<ParserCosmosDatas>() {
            @Override
            protected ParserCosmosDatas call() throws Exception {
                progressBar.setManaged(true);
                progressBar.setVisible(true);
                DataFileType type = DataFileType.getTypeFromDescription(dataTypeField.getValue());
                ParserCosmosDatas parser;
                switch(type) {
                    case AMAS:
                        parser = new ParserAmasDatas(currentDataFile.getAbsolutePath());
                        break;
                    case GALAXIES:
                        parser = new ParserGalaxiesDatas(currentDataFile.getAbsolutePath());
                        break;
                    default:
                        cancel(true);
                        return null;
                }

                parser.getBytesReadProperty().addListener((observableValue, oldValue, newValue) -> {
                    Platform.runLater(() -> {
                        double value = (int) newValue;
                        double progress = value / (double) parser.getFileLength();
                        progressBar.setProgress(progress);
                    });
                });

                parser.toParse();
                return parser;
            }
        };
        parseDataTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, taskEvent -> {
            ParserCosmosDatas parser = (ParserCosmosDatas) parseDataTask.getValue();
			infoLabel.setText("Nombre d'elements trouves : " + parser.getAllDatas().length);
            progressBar.setManaged(false);
            progressBar.setVisible(false);
		});
        new Thread(parseDataTask).start();
        
        
        
	}
	
	
	/*
	 * fonction test pour avoir un aperçu du résultat
	 */
	private void createSpace() {
		Galaxy.main(null);
		Space3D space = new Space3D();
		
		File folder = new File("data");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			  System.out.println(Galaxy.deserialize(listOfFiles[i].getName()));
		    space.addGalaxy(Galaxy.deserialize(listOfFiles[i].getName()));
		  }
		}
		
		this.space = space;
		this.spaceGroup.getChildren().add(space.getSpace());
		this.universe.getChildren().add(this.spaceGroup);
		space.play();
		
	}
	
	
	

}
