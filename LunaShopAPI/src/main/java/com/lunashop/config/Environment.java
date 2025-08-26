package com.lunashop.config;

import com.lunashop.enums.EnvironmentType;
import io.github.cdimascio.dotenv.Dotenv;

public class Environment {
    private final EnvironmentType environmentType;
    private final Dotenv dotenv;

    public Environment() {
        String appEnv = System.getenv().getOrDefault("APP_ENV", "development");

        String dotenvFile;

        switch (appEnv) {
            case "production" -> {
                this.environmentType = EnvironmentType.Production;
                dotenvFile = ".env.production";
            }
            case "test" -> {
                this.environmentType = EnvironmentType.Test;
                dotenvFile = ".env.test";
            }
            default -> {
                this.environmentType = EnvironmentType.Development;
                dotenvFile = ".env.development";
            }
        };

        this.dotenv = Dotenv.configure()
                .filename(dotenvFile)
                .ignoreIfMissing()
                .load();

    }

    public boolean isTest() {
        return this.environmentType == EnvironmentType.Test;
    }

    public boolean isProduction() {
        return this.environmentType == EnvironmentType.Production;
    }

    public boolean isDevelopment() {
        return this.environmentType == EnvironmentType.Development;
    }

    public EnvironmentType getEnvironmentType() {
        return this.environmentType;
    }

    public Dotenv getDotenv() {
        return this.dotenv;
    }
}
