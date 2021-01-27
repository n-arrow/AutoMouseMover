package pl.sw.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainController {

    @FXML
    private Label timeWhenMouseWillMoveLabel;

    @FXML
    private Label timeToMoveLabel;

    private final long TIME_INTERVAL_IN_SECONDS = 5;

    public void initialize() {
        getNewTime(); //test
    }

    public void countAndMoveMouse() {
        while(timeCounter()) moveMouse(); //mouse moved only when timeCounter() == True
    }

    private boolean timeCounter() {
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

    private void getNewTime() {
        LocalTime timeStamp = LocalTime.now();
        LocalTime timeWhenMouseWillMove = timeStamp.plusSeconds(TIME_INTERVAL_IN_SECONDS);
        timeWhenMouseWillMoveLabel.setText(timeToDisplay(timeWhenMouseWillMove));
        timeToMoveLabel.setText(timeToDisplay(LocalTime.ofSecondOfDay(TIME_INTERVAL_IN_SECONDS)));
    }

    private String timeToDisplay(LocalTime timeLeft) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        return timeLeft.format(timeFormat);
    }

    // method gets mouse pointer info and creates robot which will move the pointer 1px to the right
    private void moveMouse() {
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
