package com.example.util;


import com.example.exception.TestException;
import com.example.logging.LogFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * Helper class to be used or operaion that may not succeed immediately
 * */
public class Retryer {
    private static final int DEFAULT_TOTAL_TIME_MS = 8_000;
    private static final long[] SLEEPS = new long[] {
            20, 75, 200, 500, 1_000, 1_500, 2_000, 3_500, 5_000, 5_000, 10_000, 10_000, 10_000
    };

    private static final Logger LOG = LogFactory.getLogger(Retryer.class);

    private Retryer() {
        // Private constructor to prevent instantiation
    }

    public static void retry(Retryable operation) {
        retry(operation, DEFAULT_TOTAL_TIME_MS);
    }

    /**
     * Retry an operation a number of times
     * @param operation the operation to retry
     * */
    public static void retry(Retryable operation, long maxTotalSleepTime) {
        Throwable caught = new IllegalStateException("First iteration error");
        Iterator<Long> sleeps = getSleepIterator();
        AtomicLong totalSleepTime = new AtomicLong(0);
        while (sleeps.hasNext()) {
            if (totalSleepTime.get() > maxTotalSleepTime) {
                break;
            }
            try {
                operation.thingsToRetry();
            } catch (AssertionError | Exception e) {
                caught = e;
                // Ignore and try again
                LOG.warning(String.format("Retry operation, total sleep time %s ms --> %s", totalSleepTime.get(), e));
            }
            long nextSleep = sleeps.next();
            totalSleepTime.addAndGet(nextSleep);
            ThreadHelper.sleep(nextSleep);
        }
        final String message = "Retry time limit exceeded: " + caught.getMessage();
        LOG.severe(message);
        throw new TestException(message, caught);
    }

    public static <T> T retryWithReturn(RetryableWithReturn<T> operation) {
        return retryWithReurn(operation, DEFAULT_TOTAL_TIME_MS);
    }

    /**
     * Retry an operation with a return value a number of times
     * @param operation the operation to retry
     * @return the operation return value
     * */
    public static <T> T retryWithReurn(RetryableWithReturn<T> operation, long maxTotalSleepTime) {
        Throwable caught = new IllegalStateException("First iteration error");
        Iterator<Long> sleeps = getSleepIterator();
        AtomicLong totalSleepTime = new AtomicLong(0);
        while (sleeps.hasNext()) {
            if (totalSleepTime.get() > maxTotalSleepTime) {
                break;
            }
            try {
               return operation.thingsToRetry();
            } catch (AssertionError | Exception e) {
                caught = e;
                // Ignore and try again
                LOG.warning(String.format("Retry operation, total sleep time %s ms --> %s", totalSleepTime.get(), e));
            }
            long nextSleep = sleeps.next();
            totalSleepTime.addAndGet(nextSleep);
            ThreadHelper.sleep(nextSleep);
        }
        final String message = "Retry time limit exceeded: " + caught.getMessage();
        LOG.severe(message);
        throw new TestException(message, caught);
    }

    private static Iterator<Long> getSleepIterator() {
        return Arrays.stream(SLEEPS).iterator();
    }
}
