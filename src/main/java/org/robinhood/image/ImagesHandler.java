package org.robinhood.image;


import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface ImagesHandler {

    BufferedImage getScreen();

    /**
     * Find center sub image in large image.
     *
     * @param subImage   must have maximum little size.
     * @return center of target img.
     */
    Point findSubImg(@NotNull final BufferedImage subImage);
    Point findSubImgStream(@NotNull final File subImage, @NotNull final File screenshot) throws IOException;
}
