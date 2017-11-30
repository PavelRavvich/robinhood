package org.robinhood.service;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class RobotWrapper {

    private Robot robot;

    private RandomDataGenerator generator;

    public RobotWrapper() {
        generator = new RandomDataGenerator();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Robot getRobot() {
        return robot;
    }

    public void go(@NotNull final Point point) {

        robot.delay(generator.nextInt(1_111, 7_777));

        final Point currentPos = MouseInfo.getPointerInfo().getLocation();
        for (int i = 0; i < 100; i++) {
            int x = ((point.x * i) / 100) + (currentPos.x * (100 - i) / 100);
            int y = ((point.y * i) / 100) + (currentPos.y * (100 - i) / 100);
            robot.mouseMove(x, y);
            robot.delay(generator.nextInt(2, 20));
        }

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(generator.nextInt(3, 11));
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(generator.nextInt(7_777, 11_111));
    }
}
