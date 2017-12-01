package org.robinhood.site.vktarget.Service;

import org.robinhood.site.OperationImage;

/**
 * Проверить выполненно ли задание.
 */
public class CheckExecutable extends OperationImage {
    @Override
    public String key() {
        return "CurrentTabCloser";
    }
}
