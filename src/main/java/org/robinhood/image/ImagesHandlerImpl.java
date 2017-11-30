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

    private Robot robot;

    /**
     * Production constructor.
     */
    public ImagesHandlerImpl(@NotNull final Robot robot) {
        this.robot = robot;
    }

    /**
     * Do print screen.
     */
    public BufferedImage getScreen() {
        return robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    /**
     * Find center sub image in large image.
     *
     * @param subImage   must have maximum little size.
     * @return center of target img.
     */
    public Point findSubImg(@NotNull final BufferedImage subImage) {
        final BufferedImage screenshot = getScreen();
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
