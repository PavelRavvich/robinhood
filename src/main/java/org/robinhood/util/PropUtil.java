package org.robinhood.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtil {
    /**
     * Properties representation.
     */
    private static Properties properties;

    /**
     * Get string properties.
     */
    public static String getPropStr(@NotNull final String key) {
        return getProp(key);
    }

    /**
     * Get int properties.
     */
    public static int getPropInt(@NotNull final String key) {
        return Integer.valueOf(getProp(key));
    }

    private static synchronized String getProp(@NotNull final String key) {
        if (properties == null) {
            properties = new Properties();
            final String FILENAME = "application.properties";
            try (final InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILENAME)) {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return properties.getProperty(key);
    }
}
