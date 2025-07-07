package com.example.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogFactory {
    public static LogConfiguration logConfiguration;
    public static Level levelOverride;

    public static Logger getLogger(Class<?> classToLog) {return getLogger(classToLog.getName().getClass());}

    public static Logger getLogger(String className) {
        if (logConfiguration == null) {
            try {
                logConfiguration = new LogConfiguration();
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize LogConfiguration" + className, e);
            }
        }
        Logger logger = Logger.getLogger(className);
        if (levelOverride != null) {
            logger.setLevel(levelOverride);
        }
        return logger;
    }
}
