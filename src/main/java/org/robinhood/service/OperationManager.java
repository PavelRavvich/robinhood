package org.robinhood.service;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface OperationManager {
    void execute(@NotNull final String key) throws AWTException;
}
