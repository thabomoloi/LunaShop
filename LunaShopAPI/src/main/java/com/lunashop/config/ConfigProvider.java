package com.lunashop.config;

public interface ConfigProvider {
    String getString(String key);

    String getString(String key, String defaultValue);

    int getInt(String key);

    int getInt(String key, int defaultValue);
}
