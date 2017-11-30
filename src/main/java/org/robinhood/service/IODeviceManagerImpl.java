package org.robinhood.service;

import org.jetbrains.annotations.NotNull;
import org.robinhood.image.ImagesHandler;
import org.robinhood.site.Action;
import org.robinhood.site.vktarget.Service.Available;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class IODeviceManagerImpl implements IODeviceManager {
    /**
     * Handler of image.
     */
    private ImagesHandler imagesHandler;
    /**
     * Input control.
     */
    private RobotWrapper robot;
    /**
     * Actions list contain all system actions.
     */
    private List<Action> actions;

    public IODeviceManagerImpl(@NotNull final ImagesHandler imagesHandler, @NotNull final RobotWrapper robot) {
        this.imagesHandler = imagesHandler;
        this.robot = robot;
        actions = new ArrayList<>();
        initActions();
    }

    private void initActions() {
        try {
            actions.add(new Available());
//            actions.add(new CurrentTabCloser());
//            actions.add(new ResetTaskList());
            // TODO: 30/11/2017 add actions .
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean process() throws IOException, AWTException {
        boolean result = false;
        for (Action action : actions) {
            final BufferedImage subImage = action.getFragment();
            final Point target = imagesHandler.findSubImg(subImage);
            if (target.x != 0 && target.y != 0) {
                robot.go(target);
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean processParallel() throws IOException, AWTException {
        return actions.parallelStream().filter(action -> imagesHandler
                .findSubImg(action.getFragment()).x != 0).findFirst().orElse(null) != null;
    }
}
