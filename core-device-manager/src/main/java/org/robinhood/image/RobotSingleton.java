package org.robinhood.image;

import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * Author : Pavel Ravvich.
 * Created : 14/11/2017.
 * <p>
 * RobotSingleton
 */
@Component
public class RobotSingleton {
    private static Robot instance;

    public static synchronized Robot getInstance() {
        if (instance == null) {
            try {
                instance = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
