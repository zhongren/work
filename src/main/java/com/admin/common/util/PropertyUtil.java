package com.admin.common.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhongr on 2017/7/31.
 */
public class PropertyUtil extends PropertyPlaceholderConfigurer {
    private static Properties properties;

    @Override
    protected Properties mergeProperties() throws IOException {
        properties = super.mergeProperties();
        return super.mergeProperties();
    }


    public static String getProperty(String key, String def) {
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return properties.getProperty(key);
    }

    public static boolean getProperty(String key, boolean def) {
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static int getProperty(String key, int def) {
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return Integer.parseInt(properties.getProperty(key));
    }

}
