package com.lunashop.config;

import com.lunashop.enums.Environment;
import io.github.cdimascio.dotenv.Dotenv;

public class Env {
    private final Environment env;
    private final Dotenv dotenv;

    public Env() {
        String appEnv = System.getenv().getOrDefault("APP_ENV", "development");

        String dotenvFile;

        switch (appEnv) {
            case "production" -> {
                this.env = Environment.Production;
                dotenvFile = ".env.production";
            }
            case "test" -> {
                this.env = Environment.Test;
                dotenvFile = ".env.test";
            }
            default -> {
                this.env = Environment.Development;
                dotenvFile = ".env.development";
            }
        };

        this.dotenv = Dotenv.configure()
                .filename(dotenvFile)
                .ignoreIfMissing()
                .load();

    }

    public boolean isTest() {
        return this.env == Environment.Test;
    }

    public boolean isProduction() {
        return this.env == Environment.Production;
    }

    public boolean isDevelopment() {
        return this.env == Environment.Development;
    }

    public Environment getEnv() {
        return this.env;
    }

    public Dotenv getDotenv() {
        return this.dotenv;
    }
}
