package com.lunashop.main;

import com.lunashop.config.Env;
import com.lunashop.config.EnvConfigProvider;
import com.lunashop.config.PropertiesConfigProvider;
import com.lunashop.controllers.UsersController;

public class AppConfig {
    public final Env env;
    public final EnvConfigProvider envConfig;
    public final PropertiesConfigProvider fileConfig;

    public final UsersController usersController;

    public AppConfig() {
        this.env = new Env();

        this.envConfig = new EnvConfigProvider(this.env.getDotenv());
        this.fileConfig = new PropertiesConfigProvider(this.env);

        this.usersController = new  UsersController();
    }
}
