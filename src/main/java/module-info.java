module com.mycompany.solarsystemsimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.solarsystemsimulator to javafx.fxml;
    exports com.mycompany.solarsystemsimulator;
}
