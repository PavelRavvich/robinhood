package org.robinhood.service;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 * <p>
 * RobotWrapper
 */
public class RobotWrapper {

    private Robot robot;

    public RobotWrapper() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Robot getRobot() {
        return robot;
    }

    public void fire(@NotNull final Point point) {
        robot.mouseMove(point.x, point.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(new RandomDataGenerator().nextLong(1_000, 7_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
