package org.robinhood.site;

import org.jetbrains.annotations.NotNull;
import org.robinhood.util.ImgUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Author : Pavel Ravvich.
 * Created : 01/12/2017.
 * <p>
 * OperationImage
 */
public abstract class OperationImage implements OperationSimple {
    @Override
    public Point getTargetPoint(@NotNull final BufferedImage... screenshot) {
        try {
            final BufferedImage subImage = ImgUtil.getSubImg(key());
            return ImgUtil.findSubImg(subImage, screenshot[0]);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new Point();
    }
}
