package com.example.util;

import com.example.logging.LogFactory;

import java.util.logging.Logger;

public class ThreadHelper {
    private static final Logger LOG = LogFactory.getLogger(ThreadHelper.class);

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            LOG.warning("Thread interrupted during sleep: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}
