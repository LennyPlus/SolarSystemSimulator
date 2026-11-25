package com.mycompany.solarsystemsimulator;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button newSimulationButton;

    @FXML
    private Button loadSimulationButton;

    @FXML
    private Button creditsButton;

    @FXML
    private Button quitButton;

    @FXML
    private void loadSimulationButtonPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load Simulation");
        alert.setHeaderText(null);
        alert.setContentText("Loading saved simulations is not available yet.");
        alert.showAndWait();
    }

    @FXML
    private void creditsButtonPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to Use");
        alert.setHeaderText("Solar System Simulator - Quick Guide");
        alert.setContentText("1. Click New Simulation.\n\n"
                + "2. Choose the timestep and length using the sliders.\n"
                + "Timestep: days advanced in each update.\n"
                + "Length: total days to simulate.\n\n"
                + "3. Click Start Simulation.\n\n"
                + "4. Move with W, A, S and D.\n"
                + "Zoom with the Up and Down arrows.\n\n"
                + "5. Click a planet or select it to read its facts.\n\n"
                + "6. Press Escape for Resume or Main Menu.\n\n"
                + "Load Simulation is not available yet.");
        alert.showAndWait();
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("setup");
    }

    @FXML
    private void quitButtonPressed(ActionEvent event) {
        App.closeWindow();
    }
}
