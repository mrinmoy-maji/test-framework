package com.example.id;

import com.example.logging.LogFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class IntegerIdHelper {

    private static IntegerIdHelper cInstance;
    private final AtomicInteger idGenerator;

    private IntegerIdHelper() {
        int initialValue = (int) System.currentTimeMillis() & 0xfffffff; //truncate long value then cast to int
        idGenerator = new AtomicInteger(initialValue);
        Logger log = LogFactory.getLogger(IntegerIdHelper.class);
        log.info(() -> "IntegerIdHelper initialized with initial value: " + initialValue);
    }

    public static IntegerIdHelper instance() {
        synchronized (IntegerIdHelper.class) {
            if (cInstance == null) {
                cInstance = new IntegerIdHelper();
            }
        }
        return cInstance;
    }

    public synchronized int getUniqueId() {
        return idGenerator.getAndIncrement();
    }
}
