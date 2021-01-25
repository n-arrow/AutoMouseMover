package pl.sw.main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main extends Application {

    @FXML
    private static Label timeToMoveLabel;

    @FXML
    private static Label timeWhenMouseWillMoveLabel;

    private static final long TIME_INTERVAL_IN_SECONDS = 5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("AutoMouseMover");
        stage.show();
    }

    public void initialize() {
        //countAndMoveMouse();
        getNewTime(); //test
        System.out.println(timeToMoveLabel.getText()); //test
        System.out.println(timeWhenMouseWillMoveLabel.getText()); //test
    }

    private static void countAndMoveMouse() {
        while (true) {
            if(timeCounter()) moveMouse(); //mouse moved only when timeCounter() == True
        }
    }

    private static boolean timeCounter() {
        try {
            getNewTime();
            long i = TIME_INTERVAL_IN_SECONDS;
            while(--i > 0) {
                timeToMoveLabel.setText(timeToDisplay(LocalTime.ofSecondOfDay(i)));
                Thread.sleep(1000);
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace(); //temporarily ignored
            return false;
        }
    }

    private static void getNewTime() {
        LocalTime timeStamp = LocalTime.now();
        LocalTime timeWhenMouseWillMove = timeStamp.plusSeconds(TIME_INTERVAL_IN_SECONDS);
        timeWhenMouseWillMoveLabel.setText(timeToDisplay(timeWhenMouseWillMove));
        timeToMoveLabel.setText(timeToDisplay(LocalTime.ofSecondOfDay(TIME_INTERVAL_IN_SECONDS)));
    }

    private static String timeToDisplay(LocalTime timeLeft) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
        return timeLeft.format(timeFormat);
    }

    // method gets mouse pointer info and creates robot which will move the pointer 1px to the right
    private static void moveMouse() {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point location = pointerInfo.getLocation();
        try {
            Robot robot = new Robot();
            robot.mouseMove(location.x + 1, location.y);
        } catch (AWTException e) {
            e.printStackTrace(); //temporarily ignored
        }
    }

}
