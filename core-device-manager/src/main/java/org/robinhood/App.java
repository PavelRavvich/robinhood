package org.robinhood;

import org.robinhood.image.ImagesHandler;
import org.robinhood.image.ImagesHandlerImpl;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;

/**
 * Author : Pavel Ravvich.
 * Created : 13/11/2017.
 * <p>
 * App
 */
public class App {
    public static void main(String[] args) throws AWTException, IOException {
        final ImagesHandler imagesHandler = new ImagesHandlerImpl("", 3);
        final File src = new File("/Users/pavel/Desktop/img_comparing/src.png");
        final File dst = new File("/Users/pavel/Desktop/img_comparing/dst.png");
        final Point subImgStream = imagesHandler.findSubImgStream(dst, src);
        final Robot robot = new Robot();
        robot.mouseMove(subImgStream.x, subImgStream.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }
}
