module com.mycompany.solarsystemsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.solarsystemsimulator to javafx.fxml;
    exports com.mycompany.solarsystemsimulator;
}
