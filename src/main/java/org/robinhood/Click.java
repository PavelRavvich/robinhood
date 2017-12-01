package org.robinhood;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 * <p>
 * Click
 */
public class Click {

    public static void main(String[] args) throws AWTException, InterruptedException {
        Thread.sleep(4000);
        Robot robot = new Robot();
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(1000); // Click one second
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

}
