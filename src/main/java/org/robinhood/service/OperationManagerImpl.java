package org.robinhood.service;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.jetbrains.annotations.NotNull;
import org.robinhood.site.OperationImage;
import org.robinhood.site.OperationSimple;
import org.robinhood.site.vktarget.Service.Available;
import org.robinhood.site.vktarget.Service.CheckExecutable;
import org.robinhood.site.vktarget.Service.CurrentTabCloser;
import org.robinhood.site.vktarget.Service.StartExecute;
import org.robinhood.site.vktarget.YouTube.YouTubeLogo;
import org.robinhood.util.ImgUtil;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : Pavel Ravvich.
 * Created : 30/11/2017.
 */
public class OperationManagerImpl implements OperationManager {
    /**
     * Actions list contain all system actions.
     */
    private Map<String, OperationSimple> actions;

    private RobotWrapper robot;

    public OperationManagerImpl() {
        actions = new HashMap<>();
        robot = new RobotWrapper();
        initActions();
    }

    private void initActions() {
        actions.put("Available" ,new Available());
        actions.put("CheckExecutable" ,new CheckExecutable());
        actions.put("CurrentTabCloser" ,new CurrentTabCloser());
        actions.put("StartExecute" ,new StartExecute());
        actions.put("YouTubeLogo" ,new YouTubeLogo());
    }

    @Override
    public void execute(@NotNull final String key) throws AWTException {
        Point target;
        final OperationSimple operationSimple = actions.get(key);
        if (operationSimple instanceof OperationImage) {
            target = operationSimple.getTargetPoint(ImgUtil.printScreen());
        } else {
            target = operationSimple.getTargetPoint();
        }
        robot.go(target);
    }


    public class RobotWrapper {

        private Robot robot;

        private RandomDataGenerator generator;

        public RobotWrapper() {
            generator = new RandomDataGenerator();
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

        public void go(@NotNull final Point point) {

            robot.delay(generator.nextInt(1_111, 7_777));

            final Point currentPos = MouseInfo.getPointerInfo().getLocation();
            for (int i = 0; i < 100; i++) {
                int x = ((point.x * i) / 100) + (currentPos.x * (100 - i) / 100);
                int y = ((point.y * i) / 100) + (currentPos.y * (100 - i) / 100);
                robot.mouseMove(x, y);
                robot.delay(generator.nextInt(2, 20));
            }

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(generator.nextInt(7_777, 11_111));
        }
    }
}
