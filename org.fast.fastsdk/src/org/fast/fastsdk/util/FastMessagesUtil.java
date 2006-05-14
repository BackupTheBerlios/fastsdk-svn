package org.fast.fastsdk.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author …Ú»›÷€
 */
public class FastMessagesUtil {
    public static String getMessage(String key) {
        PropertiesConfiguration configure = new PropertiesConfiguration();
        try {
            configure.load(FastMessagesUtil.class
                    .getResourceAsStream("messages.properties"));
        } catch (ConfigurationException exception) {
            exception.printStackTrace();
        }
        return configure.getString(key);
    }
}
