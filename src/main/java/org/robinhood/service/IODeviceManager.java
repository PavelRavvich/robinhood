package org.robinhood.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface IODeviceManager {
    boolean process() throws IOException, AWTException;

    public enum Command {
        UPDATE_TASK_LIST
    }
}
