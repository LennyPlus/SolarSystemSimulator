/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author anasi
 */
public class SimulationController implements Initializable {

    @FXML
    private Canvas simulationCanvas;
    
    @FXML
    private Pane simulationPane;

    private AnimationTimer animationTimer;
    
    Simulation simulation;
    SimulationView simulationView;
    
    private final Map<KeyCode, Boolean> keys = new HashMap<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        keys.put(KeyCode.W, false);
        keys.put(KeyCode.S, false);
        keys.put(KeyCode.D, false);
        keys.put(KeyCode.A, false);
        
        simulationPane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    keys.put(event.getCode(), true);
                });
                
                newScene.setOnKeyReleased(event -> {
                    keys.put(event.getCode(), false);
                });
            }
        });
        
        
        // Make the canvas the same size as its parent
        simulationCanvas.widthProperty().bind(simulationPane.widthProperty());
        simulationCanvas.heightProperty().bind(simulationPane.heightProperty());
        
        // Add the planets
        List<Body> bodies = new ArrayList<>();
        bodies.add(SolarSystemData.SUN);
        bodies.add(SolarSystemData.MERCURY);
        bodies.add(SolarSystemData.VENUS);
        bodies.add(SolarSystemData.EARTH);
        bodies.add(SolarSystemData.MARS);
        bodies.add(SolarSystemData.JUPITER);
        bodies.add(SolarSystemData.SATURN);
        bodies.add(SolarSystemData.URANUS);
        bodies.add(SolarSystemData.NEPTUNE);
        
        simulation = new Simulation(1, bodies);
        simulationView = new SimulationView(simulationCanvas, simulation);
        
        
        // Updates and renders the simulation each frame
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (keys.get(KeyCode.W)) {
                    simulationView.moveCameraBy(0, 10);
                } else if (keys.get(KeyCode.S)) {
                    simulationView.moveCameraBy(0, -10);
                } else if (keys.get(KeyCode.D)) {
                    simulationView.moveCameraBy(-10, 0);
                } else if (keys.get(KeyCode.A)) {
                    simulationView.moveCameraBy(10, 0);
                }
                
                simulation.update();
                simulationView.render();
            }
        };
        
        // Escape opens the pause menu from anywhere on this screen.
        simulationPane.setFocusTraversable(true);
        simulationPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                event.consume();
                pauseSimulation();
            }
        });

        animationTimer.start();
    }    
    
    private void pauseSimulation() {
        animationTimer.stop();

        ButtonType resumeButton = new ButtonType("Resume", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType menuButton = new ButtonType("Back to Main Menu");

        Alert pauseMenu = new Alert(Alert.AlertType.CONFIRMATION);
        pauseMenu.initOwner(simulationPane.getScene().getWindow());
        pauseMenu.setTitle("Pause Menu");
        pauseMenu.setHeaderText("Simulation paused");
        pauseMenu.setContentText("Resume the simulation or return to the main menu.");
        pauseMenu.getButtonTypes().setAll(resumeButton, menuButton);

        Optional<ButtonType> result = pauseMenu.showAndWait();
        if (result.isPresent() && result.get() == menuButton) {
            try {
                App.setRoot("primary");
                return;
            } catch (IOException exception) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Navigation Error");
                error.setHeaderText(null);
                error.setContentText("The main menu could not be opened.");
                error.showAndWait();
            }
        }

        // Resume also handles Escape or closing the pause menu.
        animationTimer.start();
        simulationPane.requestFocus();
    }
    
}
