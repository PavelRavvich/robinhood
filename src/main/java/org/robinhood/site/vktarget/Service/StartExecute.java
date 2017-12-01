package org.robinhood.site.vktarget.Service;

import org.jetbrains.annotations.NotNull;
import org.robinhood.site.OperationSimple;
import org.robinhood.util.PropUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Начать выполнение задания
 */
public class StartExecute implements OperationSimple {
    @Override
    public String key() {
        return "StartExecute";
    }

    @Override
    public Point getTargetPoint(@NotNull BufferedImage... screenshot) {
        final int x = PropUtil.getPropInt("start.execute.x");
        final int y = PropUtil.getPropInt("start.execute.y");
        return new Point(x, y);
    }
}
