module AutoMouseMover {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports pl.sw.main to javafx.graphics;
    opens pl.sw.controller to javafx.fxml, java.desktop;
}