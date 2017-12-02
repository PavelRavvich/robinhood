package org.robinhood.site.vktarget.Service;

import org.jetbrains.annotations.NotNull;
import org.robinhood.site.OperationSimple;
import org.robinhood.util.PropUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Проверить доступные задания.
 */
public class Available implements OperationSimple {

    @Override
    public String key() {
        return "Available";
    }

    @Override
    public Point getTargetPoint(@NotNull BufferedImage... screenshot) {
        final int x = PropUtil.getPropInt("available.x");
        final int y = PropUtil.getPropInt("available.y");
        return new Point(x, y);
    }
}