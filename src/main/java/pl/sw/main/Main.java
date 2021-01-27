package pl.sw.main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.sw.controller.MainController;

import java.io.IOException;

public class Main extends Application {

    @FXML
    private MainController mainController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("AutoMouseMover");
        stage.show();
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainController.countAndMoveMouse();
        });
    }

    private static void runCounter(MainController mainController) {
        mainController.countAndMoveMouse();
    }

}
