package org.robinhood.image;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ImagesHandlerImpl handle img tasks.
 */
@Data
@Component
public class ImagesHandlerImpl implements ImagesHandler {
    /**
     * Template of filename.
     */
    private final String NAME_TEMPLATE = "SCREEN_#";
    /**
     * Current amount of screens in target dir.
     */
    private int fileNumber = 0;
    /**
     * Path to target dir.
     */
    @Value("${max_amount_screenshot}")
    private String baseDir;
    /**
     * Maximum files for save in base dir.
     */
    @Value("${max_amount_screenshot}")
    private Integer maxAmountFiles;

    /**
     * Do print screen.
     */
    public boolean doScreen() throws IOException, AWTException {
        final Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        final BufferedImage buffImage = RobotSingleton.getInstance().createScreenCapture(rectangle);
        if (fileNumber >= maxAmountFiles) {
            deleteOldScreens();
        }
        final File file = new File(baseDir, NAME_TEMPLATE.replace("#", Integer.toString(fileNumber++)));
        return ImageIO.write(buffImage, "png", file);
    }

    /**
     * Delete all old screens files.
     */
    private void deleteOldScreens() {
        final File baseDirectory = new File(baseDir);
        final File[] garbageImg = baseDirectory.listFiles();
        if (!baseDirectory.isDirectory() || garbageImg == null || garbageImg.length == 0) {
            throw new IllegalStateException("Illegal directory!");
        }
        for (File file : garbageImg) {
            file.delete();
        }
        fileNumber = 0;
    }

    /**
     * Print ImagesHandler.
     *
     * @return byte representation of screen image.
     */
    public BufferedImage grabScreen() throws AWTException {
        final Robot robot = new Robot();
        final Dimension screenSze = Toolkit.getDefaultToolkit().getScreenSize();
        final Rectangle rectangle = new Rectangle(screenSze);
        return robot.createScreenCapture(rectangle);
    }

    /**
     * Find center sub image in large image.
     *
     * @param subImage   must have maximum little size.
     * @param screenshot of screen.
     * @return center of target img.
     */
    public Point findSubImg(@NotNull final File subImage, @NotNull final File screenshot) throws IOException {
        final BufferedImage src = ImageIO.read(screenshot);
        final BufferedImage dst = ImageIO.read(subImage);

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
                //If all pixels matched return center of sub image.
                return new Point(i + dst.getWidth() / 2, j + dst.getHeight() / 2);
            }
        }
        return new Point(-1, -1);
    }
}
