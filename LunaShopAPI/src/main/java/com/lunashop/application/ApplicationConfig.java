package com.lunashop.application;

import com.lunashop.config.Environment;
import com.lunashop.config.ApplicationProperties;
import com.lunashop.controllers.UsersController;

public class ApplicationConfig {
    public final Environment environment;
    public final ApplicationProperties applicationProperties;

    public final UsersController usersController;

    public ApplicationConfig() {
        this.environment = new Environment();

        this.applicationProperties = new ApplicationProperties(this.environment);

        this.usersController = new  UsersController();
    }
}
