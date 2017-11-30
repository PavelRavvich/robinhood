package org.robinhood.service;

import org.jetbrains.annotations.NotNull;
import org.robinhood.image.ImagesHandler;
import org.robinhood.site.Action;
import org.robinhood.site.vktargent.Service.ResetTaskList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.robinhood.service.IODeviceManager.Command.UPDATE_TASK_LIST;


/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class IODeviceManagerImpl implements IODeviceManager {

    private ImagesHandler imagesHandler;

    private RobotWrapper robot;

    //list action
    private List<Action> actions = new ArrayList<>();

    private void initActions() {
        actions.add(new ResetTaskList());
        // TODO: 30/11/2017 add actions .
    }

    @Override
    public BufferedImage process() throws IOException, AWTException {
        final BufferedImage screenshot = imagesHandler.doScreen();
        for (Action action : actions) {
            final BufferedImage subImage = action.getFragment();
            final Point target = imagesHandler.findSubImg(subImage, screenshot);
            if (target.x != 0 && target.y != 0) {
                robot.fire(target);
                break;
            }
        }
        return imagesHandler.doScreen();
    }

    public boolean processParallel() throws IOException, AWTException {
        final BufferedImage screenshot = imagesHandler.doScreen();
        return actions.parallelStream().filter(action ->
                imagesHandler.findSubImg(action.getFragment(), screenshot).x != 0)
                .findFirst().orElse(null) != null;
    }
}
