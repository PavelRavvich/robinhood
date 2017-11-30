package org.robinhood.site.vktarget.Service;

import org.robinhood.site.Action;
import org.robinhood.util.PropertiesLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class ResetTaskList implements Action {

    private BufferedImage fragment;

    public ResetTaskList() throws IOException {
        fragment = ImageIO.read(new File(PropertiesLoader.getPropStr("ResetTaskList")));
    }

    @Override
    public BufferedImage getFragment() {
        return fragment;
    }
}
