package com.example.id;

import com.example.logging.LogFactory;

import java.util.logging.Logger;

public class LongIdHelper {

    private static LongIdHelper cInstance;
    private LongIdGenerator longIdGenerator;
    private Logger log = LogFactory.getLogger(LongIdHelper.class);

    private LongIdHelper() {
        long initialValue = System.currentTimeMillis();
        longIdGenerator = new LongIdGenerator(initialValue);
        log.info("INIT with initial value == " + initialValue);
    }

    public static LongIdHelper instance() {
        synchronized (LongIdHelper.instance()) {
            if (cInstance == null) {
                cInstance = new LongIdHelper();
            }
        }
        return cInstance;
    }

    public synchronized  long getUniqueId() { return longIdGenerator.next(); }
}
