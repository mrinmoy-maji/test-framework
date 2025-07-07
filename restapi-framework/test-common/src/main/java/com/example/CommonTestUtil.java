package com.example;

import com.example.logging.LogFactory;

import java.util.logging.Logger;

public class CommonTestUtil {
    private static CommonTestUtil cInstance;
//    private static RandomUtil random;
    private final Logger log;
    protected CommonTestUtil() {
        log = LogFactory.getLogger(CommonTestUtil.class);
    }

    public static CommonTestUtil instance() {
        synchronized (CommonTestUtil.class) {
            if (cInstance == null) {
                cInstance = new CommonTestUtil();
            }
        }
        return cInstance;
    }


}
