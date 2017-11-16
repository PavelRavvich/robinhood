package org.robinhood.config.properties;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesLoader {
    /**
     * Represents a persistent set of properties.
     */
    private final Properties properties = new Properties();

    public void handle(Object obj) {
        final Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Prop.class)) {
                final Prop annotation = field.getAnnotation(Prop.class);
            }
        }
    }

    public PropertiesLoader() {
        final String FILENAME = "application.properties";
        try (final InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILENAME)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get get.
     *
     * @param key of get.
     * @return get of Prop by key.
     */
    public String get(@NotNull final String key) {
        return properties.getProperty(key);
    }
}
