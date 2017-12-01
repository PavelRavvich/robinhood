package org.robinhood.site;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public interface OperationSimple {
    String key();
    Point getTargetPoint(@NotNull final BufferedImage... screenshot);
}
