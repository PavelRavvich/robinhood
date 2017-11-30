package org.robinhood.site.vktarget.Service;

import org.robinhood.site.Action;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class CurrentTabCloser implements Action {

    private BufferedImage fragment;

    public CurrentTabCloser() throws IOException {
        fragment = init("CurrentTabCloser");
    }

    @Override
    public BufferedImage getFragment() {
        return fragment;
    }
}
