package org.robinhood;

import java.awt.*;

/**
 * Author : Pavel Ravvich.
 * Created : 19.09.17.
 * <p>
 * Main
 */
public class Main {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(13,500);
        robot.delay(1000);
        robot.mouseMove(1300,500);
    }

}
