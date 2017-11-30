package org.robinhood;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 * <p>
 * Screen
 */
public class Screen {
    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        Thread.sleep(4000);
        Robot robot = new Robot();
        final BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        File file = new File("/Users/pavel/Desktop/Screen2.png");
        ImageIO.write(screenCapture, "png", file);
    }
}
