package org.robinhood;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class PointFinder {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(560, 460);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(367);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(367);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

}
