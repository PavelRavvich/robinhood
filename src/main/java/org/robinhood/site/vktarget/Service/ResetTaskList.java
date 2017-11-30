package org.robinhood.site.vktarget.Service;

import org.robinhood.site.Action;
import org.robinhood.util.PropertiesLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class ResetTaskList implements Action {

    private BufferedImage fragment;

    public ResetTaskList() throws IOException, URISyntaxException {
        fragment = init("ResetTaskList");
    }

    @Override
    public BufferedImage getFragment() {
        return fragment;
    }
}
