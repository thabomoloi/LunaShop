package com.lunashop.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ApplicationPropertiesTest {
    private final Environment environment = new Environment();
    private final ApplicationProperties propertiesConfig = new ApplicationProperties(environment);

    @Test
    public void getString_whenExists_ReturnsString() {
        String property = propertiesConfig.getString("test.string");
        assertEquals("String", property);
    }

    @Test
    public void getString_whenNotExists_ReturnsNull() {
        String property = propertiesConfig.getString("test.string-null");
        assertNull(property);
    }

    @Test
    public void getString_whenNotExists_ReturnsDefaultValue() {
        String property = propertiesConfig.getString("test.string-default");
        assertEquals("DefaultString", property);
    }

    @Test
    public void getString_whenN_ReturnsDefaultValue() {
        String property = propertiesConfig.getString("test.string-default");
        assertEquals("DefaultString", property);
    }
}
