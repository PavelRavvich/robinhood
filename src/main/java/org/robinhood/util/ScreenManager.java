package org.robinhood.util;

import com.sun.istack.internal.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface ScreenManager {

    boolean doScreen() throws IOException, AWTException;

    BufferedImage grabScreen() throws AWTException;

    default Point findImgFragment(@NotNull final File subImage, @NotNull final File screenshot) throws IOException {
        final BufferedImage src = ImageIO.read(screenshot);
        final BufferedImage dst = ImageIO.read(subImage);
        //Brute force N^2 check all places in the image.
        for (int i = 0; i <= src.getWidth() - dst.getWidth(); i++) {
            check_subimage:
            for (int j = 0; j <= src.getHeight() - dst.getHeight(); j++) {
                for (int ii = 0; ii < dst.getWidth(); ii++) {
                    for (int jj = 0; jj < dst.getHeight(); jj++) {
                        if (dst.getRGB(ii, jj) != src.getRGB(i + ii, j + jj)) {
                            continue check_subimage;
                        }
                    }
                }
                //If here, all pixels matched.
                return new Point(i, j);
            }
        }
        return new Point(-1,-1);
    }
}
