package org.robinhood.util;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 *
 * Utils for image handling.
 */
public class ImgUtil {
    /**
     * Find center sub image in large image.
     *
     * @param subImage   must have maximum little size.
     * @param screenshot of screen.
     * @return center of target img.
     */
    public static Point findSubImg(@NotNull final BufferedImage subImage, @NotNull final BufferedImage screenshot) {
        for (int i = 0; i <= screenshot.getWidth() - subImage.getWidth(); i++) {
            checkSubImage:
            for (int j = 0; j <= screenshot.getHeight() - subImage.getHeight(); j++) {
                for (int ii = 0; ii < subImage.getWidth(); ii++) {
                    for (int jj = 0; jj < subImage.getHeight(); jj++) {
                        if (subImage.getRGB(ii, jj) != screenshot.getRGB(i + ii, j + jj)) {
                            continue checkSubImage;
                        }
                    }
                }
                //If all pixels matched return center of sub image.
                return new Point(i + subImage.getWidth() / 2, j + subImage.getHeight() / 2);
            }
        }
        return new Point();
    }

    /**
     * Print ImagesHandler.
     *
     * @return byte representation of screen image.
     */
    public static BufferedImage printScreen() throws AWTException {
        final Robot robot = new Robot();
        final Dimension screenSze = Toolkit.getDefaultToolkit().getScreenSize();
        final Rectangle rectangle = new Rectangle(screenSze);
        return robot.createScreenCapture(rectangle);
    }

    /**
     * Extract BufferedImage from resources storage by application.properties key.
     */
    public static BufferedImage getSubImg(@NotNull final String key) throws IOException, URISyntaxException {
        final String prop = PropUtil.getPropStr(key);
        final URL resourceURL = Thread.currentThread().getContextClassLoader().getResource(prop);
        assert resourceURL != null;
        final URI resourceURI = resourceURL.toURI();
        final File file = new File(resourceURI);
        return ImageIO.read(file);
    }
}
