package com.lunashop.config;

import com.lunashop.exception.ConfigException;

import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationProperties {
    private final Properties properties = new Properties();
    private static final Pattern ENV_VAR_PATTERN = Pattern.compile("\\$\\{([^:}]+)(:([^}]*))?}");
    private final Environment environment;

    public ApplicationProperties(Environment environment) {
        this.environment = environment;

        // Load defaults
        loadProperties("application.properties");

        // Override
        loadProperties(this.getEnvProperties());
    }

    private void loadProperties(String filepath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filepath)) {
            if (inputStream == null) {
                throw new ConfigException("Configuration file not found: " + filepath);
            }

            Properties raw = new Properties();
            raw.load(inputStream);

            raw.forEach((k, v) -> properties.put(k, resolveValue(v.toString())));

        } catch (Exception e) {
            throw new ConfigException("Failed to load configuration file: " + filepath, e);
        }
    }

    private String resolveValue(String value) {
        Matcher matcher = ENV_VAR_PATTERN.matcher(value);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String envKey = matcher.group(1).trim();
            String defaultValue = matcher.group(3) != null ? matcher.group(3) : "";
            String replacement = environment.getDotenv().get(envKey, defaultValue);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public String getString(String key) {
        return this.properties.getProperty(key);
    }

    public String getString(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    public int getInt(String key) {
        return Integer.parseInt(this.properties.getProperty(key));
    }

    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(this.properties.getProperty(key, defaultValue+""));
    }

    private String getEnvProperties() {
        return switch (environment.getEnvironmentType()) {
            case Development -> "application-dev.properties";
            case Production -> "application-prod.properties";
            case Test -> "application-test.properties";
        };
    }
}
