package org.robinhood.site;

import org.jetbrains.annotations.NotNull;
import org.robinhood.service.IODeviceManager;
import org.robinhood.util.PropertiesLoader;

import javax.imageio.ImageIO;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 * <p>
 * Action
 */
public interface Action {
    BufferedImage getFragment();

    default BufferedImage init(@NotNull final String key) throws IOException {
        return ImageIO.read(new File(PropertiesLoader.getPropStr(key)));
    }
}
