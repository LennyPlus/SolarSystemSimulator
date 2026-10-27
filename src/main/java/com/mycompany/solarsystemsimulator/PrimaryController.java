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
        alert.setTitle("Credits");
        alert.setHeaderText("Solar System Simulator");
        alert.setContentText("Anas Issa\nArin Kabir\nAlvee Ahsan Bhuiyan");
        alert.showAndWait();
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void quitButtonPressed(ActionEvent event) {
        App.closeWindow();
    }
}
