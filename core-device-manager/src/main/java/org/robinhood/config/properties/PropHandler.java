package org.robinhood.config.properties;

import java.lang.reflect.Field;

/**
 * Author : Pavel Ravvich.
 * Created : 17/11/2017.
 */
public class PropHandler {
    public void handle(Object obj) {
        for (Field field : obj.getClass().getFields()) {
            if (field.isAnnotationPresent(Prop.class)) {
                try {
                    final String value = field.get(obj).toString();
                    field.set(obj, new PropertiesLoader().get(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
