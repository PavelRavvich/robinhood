package org.robinhood.api;

import lombok.Data;

import java.awt.event.InputEvent;
import java.io.File;

public interface IODeviceManager {
    ResponseStatus enter(StepSource source, InputEvent clickType);
    void restartVM();

    @Data
    class StepSource {
        private File subImg;
        private File fullImg;
        private File expectedRfagment;
    }

    enum ResponseStatus {
        SUCCESS(200),
        REJECTED(201),
        OVERLOAD(202);

        int STATUS;

        ResponseStatus(int STATUS) {
            this.STATUS = STATUS;
        }
    }
}
