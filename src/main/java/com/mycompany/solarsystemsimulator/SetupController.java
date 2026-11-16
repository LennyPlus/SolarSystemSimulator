package com.mycompany.solarsystemsimulator;

import java.io.IOException;
import javafx.fxml.FXML;

public class SetupController {

    @FXML
    private void startSimulation() throws IOException {
        App.setRoot("simulation");
    }

    @FXML
    private void backToMenu() throws IOException {
        App.setRoot("primary");
    }
}
