/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author anasi
 */
public class SimulationController implements Initializable {

    @FXML
    private Label simulationTimeLabel;

    private double elapsedDays;

    @FXML
    private Canvas simulationCanvas;
    
    @FXML
    private Pane simulationPane;

    @FXML
    private ChoiceBox<String> planetSelector;

    @FXML
    private Label planetFacts;

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
        
        // Make the canvas the same size as its parent
        simulationCanvas.widthProperty().bind(simulationPane.widthProperty());
        simulationCanvas.heightProperty().bind(simulationPane.heightProperty());
        
        // Initialize mouse dragging
        initializeMouseControls();
        
        
        List<Body> bodies = SolarSystemData.newSimulationBodies();
        simulation = new Simulation(SetupController.getTimestep(), bodies);
        simulationView = new SimulationView(simulationCanvas, simulation);

        List<Body> planets = bodies.stream().filter(body -> body.getDescription() != null).toList();
        planetSelector.getItems().setAll(planets.stream().map(Body::getName).toList());
        planetSelector.getSelectionModel().selectedIndexProperty().addListener((obs, previous, selected) -> {
            int index = selected.intValue();
            planetFacts.setText(index < 0 ? "" : planets.get(index).getDescription());
        });
        planetSelector.getSelectionModel().selectFirst();
        simulationCanvas.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.isStillSincePress()) {
                Body planet = simulationView.findPlanetAt(event.getX(), event.getY());
                if (planet != null) {
                    planetSelector.getSelectionModel().select(planets.indexOf(planet));
                }
            }
        });
        
        
        // Updates and renders the simulation each frame
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (keys.get(KeyCode.W)) {
                    simulationView.moveCameraBy(0, 10);
                }
                if (keys.get(KeyCode.S)) {
                    simulationView.moveCameraBy(0, -10);
                }
                if (keys.get(KeyCode.D)) {
                    simulationView.moveCameraBy(-10, 0);
                }
                if (keys.get(KeyCode.A)) {
                    simulationView.moveCameraBy(10, 0);
                }
                
                
                int length = SetupController.getSimulationLength();
                if (elapsedDays < length) {
                    double step = SetupController.getTimestep();
                    if (elapsedDays + step >= length) {
                        step = length - elapsedDays;
                    }
                    simulation.setTimestep(step);
                    simulation.update();
                    elapsedDays += step;
                    if (elapsedDays >= length) {
                        simulationTimeLabel.setText("Simulation complete: " + length + " days");
                    } else {
                        simulationTimeLabel.setText("Day " + (int) elapsedDays + " / " + length);
                    }
                }
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

            keys.put(event.getCode(), true);
            
        });

        simulationPane.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
        
        // Handle zooming and dragging with the mouse
        simulationPane.setOnScroll(event -> {
            double oldZoom = simulationView.getZoomX();
            double zoomFactor = event.getDeltaY() > 0 ? 1.2 : 0.8;
            double newZoom = oldZoom * zoomFactor;

            double mouseX = event.getX();
            double mouseY = event.getY();

            double translateX = simulationView.getCameraX();
            double translateY = simulationView.getCameraY();

            double newTranslateX = mouseX - (mouseX - translateX) * zoomFactor;
            double newTranslateY= mouseY - (mouseY - translateY) * zoomFactor;

            simulationView.setZoomX(newZoom);
            simulationView.setZoomY(newZoom);

            simulationView.setCameraX(newTranslateX);
            simulationView.setCameraY(newTranslateY);
            
        });
        
        animationTimer.start();
    }    
    
    // Used to calculate the amount we should move the canvas by when the user drags their mouse
    private double previousMouseX;
    private double previousMouseY;

    // Initialize the mouse dragging
    private void initializeMouseControls() {
        simulationPane.setOnMousePressed(event -> {
            previousMouseX = event.getX();
            previousMouseY = event.getY();
        });

        simulationPane.setOnMouseDragged(event -> {
            double deltaX = event.getX() - previousMouseX;
            double deltaY = event.getY() - previousMouseY;

            simulationView.moveCameraBy(deltaX, deltaY);

            previousMouseX = event.getX();
            previousMouseY = event.getY();
        });
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
    
    //ActionEvents for the menu items
    @FXML
    void onMenuItemExitToMainMenu(ActionEvent event) throws IOException {
        animationTimer.stop();
        App.setRoot("primary");
    }
    
    @FXML
    void onMenuItemExit(ActionEvent event) {
        App.closeWindow();
    }
    
    @FXML
    void onMenuItemPauseSimulation(ActionEvent event) {
        System.out.println("MenuItemPauseSimulation");
        pauseSimulation();
    }

    @FXML
    void onMenuItemSaveSimulation(ActionEvent event) {
        // TODO
    }
    
    @FXML
    void onMenuItemLoadSimulation(ActionEvent event) {
        // TODO
    }

}
