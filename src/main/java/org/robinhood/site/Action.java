package org.robinhood.site;

import org.jetbrains.annotations.NotNull;
import org.robinhood.util.PropertiesLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public interface Action {
    BufferedImage getFragment();

    default BufferedImage init(@NotNull final String key) throws IOException, URISyntaxException {
        final String prop = PropertiesLoader.getPropStr(key);
        final URL resourceURL = Thread.currentThread().getContextClassLoader().getResource(prop);
        assert resourceURL != null;
        final URI resourceURI = resourceURL.toURI();
        final File file = new File(resourceURI);
        return ImageIO.read(file);
    }
}
