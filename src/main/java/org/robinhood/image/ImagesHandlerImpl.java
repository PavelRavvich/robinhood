package org.robinhood.image;

import org.jetbrains.annotations.NotNull;
import org.robinhood.util.PropertiesLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * ImagesHandlerImpl handle img tasks.
 */
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
    private String baseDir = PropertiesLoader.getPropStr("screenshot.storage.dir");
    /**
     * Maximum files for save in base dir.
     */
    private int maxAmountFiles = PropertiesLoader.getPropInt("amount.screenshot");;

    private Robot robot;

    /**
     * Constructor for test.
     */
    public ImagesHandlerImpl(@NotNull final String baseDir, final int maxAmountFiles) throws AWTException {
        this.maxAmountFiles = maxAmountFiles;
        this.baseDir = baseDir;
        this.robot = new Robot();
    }

    /**
     * Production constructor.
     */
    public ImagesHandlerImpl(@NotNull final Robot robot) {
        this.robot = robot;
    }

    /**
     * Do print screen.
     */
    public BufferedImage doScreen() throws IOException, AWTException {
        final Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        final BufferedImage buffImage = robot.createScreenCapture(rectangle);
        if (fileNumber >= maxAmountFiles) {
            deleteOldScreens();
        }
        final File file = new File(baseDir, NAME_TEMPLATE.replace("#", Integer.toString(fileNumber++)));
        ImageIO.write(buffImage, "png", file);
        return buffImage;
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
        Arrays.stream(garbageImg).forEach(File::delete);
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
    public Point findSubImg(@NotNull final BufferedImage subImage, @NotNull BufferedImage screenshot) {
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
     * Same findSubImg but using Stream API.
     */
    public Point findSubImgStream(@NotNull final File subImage, @NotNull final File screenshot) throws IOException {
        final BufferedImage src = ImageIO.read(screenshot);
        final BufferedImage dst = ImageIO.read(subImage);
        final Stream<Point> offsets = generateRange(src.getWidth() - dst.getWidth() + 1, src.getHeight() - dst.getHeight() + 1);
        final Point point = offsets.filter(o -> findImgFragmentOnOffset(o, src, dst)).findFirst().orElse(new Point());
        return point.getX() != 0 ? new Point(((int) point.getX() + dst.getWidth() / 2),((int) point.getY() + dst.getWidth() / 2)) : point;
    }

    private Stream<Point> generateRange(final int width, final int height) {
        return IntStream.range(0, width).boxed().flatMap(x -> IntStream.range(0, height).mapToObj(y -> new Point(x, y)));
    }

    private boolean findImgFragmentOnOffset(final Point offset, final BufferedImage src, final BufferedImage dst) {
        return generateRange(dst.getWidth(), dst.getHeight()).allMatch(p ->
                dst.getRGB((int)p.getX(), (int) p.getY()) == src.getRGB((int)(offset.getX() + p.getX()), (int)(offset.getY() + p.getY())));
    }
}
