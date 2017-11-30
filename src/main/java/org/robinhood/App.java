package org.robinhood;

import org.robinhood.image.ImagesHandler;
import org.robinhood.image.ImagesHandlerImpl;
import org.robinhood.service.IODeviceManager;
import org.robinhood.service.IODeviceManagerImpl;
import org.robinhood.service.RobotWrapper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author : Pavel Ravvich.
 * Created : 13/11/2017.
 * <p>
 * App
 */
public class App {
    public static void main(String[] args) {
        RobotWrapper robotWrapper = new RobotWrapper();
        ImagesHandler imagesHandler = new ImagesHandlerImpl(robotWrapper.getRobot());
        IODeviceManager manager = new IODeviceManagerImpl(imagesHandler, robotWrapper);
        robotWrapper.getRobot().delay(5000);
        final boolean process;
        try {
            process = manager.process();
            System.out.println(process);
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
