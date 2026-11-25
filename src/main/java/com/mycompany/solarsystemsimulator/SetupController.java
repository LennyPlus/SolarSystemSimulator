package com.mycompany.solarsystemsimulator;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SetupController {

    @FXML
    private Slider timestepSlider;
    @FXML
    private Slider lengthSlider;
    @FXML
    private Label timestepValueLabel;
    @FXML
    private Label lengthValueLabel;

    private static double timestep = 1.0;
    private static int simulationLength = 365;

    @FXML
    public void initialize() {
        timestepSlider.setValue(timestep);
        lengthSlider.setValue(simulationLength);
        updateLabels();

        timestepSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateLabels();
        });
        lengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateLabels();
        });
    }

    private void updateLabels() {
        double days = (int) (timestepSlider.getValue() * 10 + 0.5) / 10.0;
        int length = (int) (lengthSlider.getValue() + 0.5);
        timestepValueLabel.setText("Timestep: " + days + " days");
        lengthValueLabel.setText("Simulation length: " + length + " days");
    }

    static double getTimestep() {
        return timestep;
    }

    static int getSimulationLength() {
        return simulationLength;
    }

    @FXML
    private void startSimulation() throws IOException {
        timestep = (int) (timestepSlider.getValue() * 10 + 0.5) / 10.0;
        simulationLength = (int) (lengthSlider.getValue() + 0.5);
        App.setRoot("simulation");
    }

    @FXML
    private void backToMenu() throws IOException {
        App.setRoot("primary");
    }
}
