package com.lunashop.config;

public class JdbcConfig {
    private final String url;
    private final String username;
    private final String password;

    public JdbcConfig(ApplicationProperties applicationProperties) {
        this.url = applicationProperties.getString("database.url");
        this.username = applicationProperties.getString("database.username");
        this.password = applicationProperties.getString("database.password");
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
