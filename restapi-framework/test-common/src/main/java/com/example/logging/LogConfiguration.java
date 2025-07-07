package com.example.logging;

import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.logging.LogManager;

public class LogConfiguration {
    private final LogManager manager;

    public LogConfiguration() throws SecurityException, IOException {
        this("/logging.properties");
    }

    public LogConfiguration(String configFile) throws SecurityException, IOException {
        InputStream fis = getClass().getResourceAsStream(configFile);
        manager = LogManager.getLogManager();
        manager.readConfiguration(fis);
        Security.setProperty("networkaddress.cache.ttl", "60");
    }

    public LogConfiguration(InputStream fis) throws SecurityException, IOException {
        System.out.println("Read Custom LogConfiguration from InputStream!");
        manager = LogManager.getLogManager();
        manager.readConfiguration(fis);
        Security.setProperty("networkaddress.cache.ttl", "60");
    }

    public LogManager getLogManager() {
        return manager;
    }
}
