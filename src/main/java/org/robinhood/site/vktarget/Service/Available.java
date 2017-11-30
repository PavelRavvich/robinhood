package org.robinhood.site.vktarget.Service;

import org.robinhood.site.Action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class Available implements Action{

    private BufferedImage fragment;

    public Available() throws IOException, URISyntaxException {
        fragment = init("Available");
    }

    @Override
    public BufferedImage getFragment() {
        return fragment;
    }
}
