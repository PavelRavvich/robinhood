package org.robinhood.site.vktarget.Service;

import org.robinhood.site.Action;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class Available implements Action{

    private BufferedImage fragment;

    public Available() throws IOException {
        fragment = init("Available");
    }

    @Override
    public BufferedImage getFragment() {
        return fragment;
    }
}
