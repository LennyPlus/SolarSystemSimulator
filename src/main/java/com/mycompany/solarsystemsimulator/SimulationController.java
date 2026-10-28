/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.canvas.Canvas;
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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initialized");
        // Make the canvas the same size as its parent
        simulationCanvas.widthProperty().bind(simulationPane.widthProperty());
        simulationCanvas.heightProperty().bind(simulationPane.heightProperty());
        
        
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
        
        
        Simulation simulation = new Simulation(1, bodies);
        SimulationView simulationView = new SimulationView(simulationCanvas, simulation);
        
        
        // Updates and renders the simulation each frame
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                simulation.update();
                simulationView.render();
            }
        };
        
        animationTimer.start();
    }    
    
}
