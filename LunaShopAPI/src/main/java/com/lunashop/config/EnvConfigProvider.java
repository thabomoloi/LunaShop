package com.lunashop.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfigProvider implements ConfigProvider {
    private  final Dotenv dotenv;

    public EnvConfigProvider(Dotenv dotenv) {
        this.dotenv = dotenv;
    }
    @Override
    public String getString(String key) {
        return dotenv.get(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return dotenv.get(key, defaultValue);
    }

    @Override
    public int getInt(String key) {
        return Integer.parseInt(dotenv.get(key));
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(dotenv.get(key, defaultValue+""));
    }
}
