package com.example;

import com.example.id.LongIdHelper;
import com.example.logging.LogFactory;
import com.example.util.RandomUtil;
import com.example.util.ThreadHelper;
import org.testng.SkipException;

import java.util.logging.Logger;

public class CommonTestUtil {
    private static CommonTestUtil cInstance;
    private static RandomUtil random;
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

    public long anyLong() { return random.nextLong(); }

    /*
    * Method to mark a test as skipped.
    * Method should be called first in a test method sipped for some reason,
    * the skipMessage should explain the reason, e.g. a Jira ticket
    *
    * @param skipMessage explains why the test is skipped
    * */

    public void skipTest(final String skipMessage) {
        log.warning(() -> String.format("Skipping test, msg: %s", skipMessage));
        throw new SkipException(skipMessage);
    }

    /*
    * Call a thread sleep
    *
    * @param milliseconds declare the limit to sleep
    * */

    public void sleep(final long milliseconds) {
        ThreadHelper.sleep(milliseconds);
    }

    /*
    * Generates a new unique long id.
    * Counter is base lined at currentTimeMills and then incremented by 1 for each request
    *
    * @returns a unique long id*/

    public long newId() { return LongIdHelper.instance().getUniqueId(); }



}
