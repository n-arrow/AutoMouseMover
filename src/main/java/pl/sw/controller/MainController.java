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
        timeToMoveLabel.setText("Click this field");
        counterLauncher();
    }

    private void counterLauncher() {
        timeToMoveLabel.setOnMouseClicked(event -> countAndMoveMouse());
        timeWhenMouseWillMoveLabel.setOnMouseClicked(event -> countAndMoveMouse());
    }

    public void countAndMoveMouse() {
//        if(timeCounter()) moveMouse(); // test - will run only once
        while(timeCounter()) moveMouse(); // mouse moved only when timeCounter() == True
    }

    private boolean timeCounter() {
        try {
            getNewTime();
            long i = TIME_INTERVAL_IN_SECONDS;
            while(--i > 0) {
                timeToMoveLabel.setText(timeToDisplay(LocalTime.ofSecondOfDay(i)));
                Thread.sleep(1000); // Process finished with exit code -805306369 (0xCFFFFFFF) - Why?
            }
            return true;
        } catch (InterruptedException e) {
            //e.printStackTrace();
            timeToMoveLabel.setText("Thread error");
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
            //e.printStackTrace();
            timeToMoveLabel.setText("Robot error");
        }
    }
}
