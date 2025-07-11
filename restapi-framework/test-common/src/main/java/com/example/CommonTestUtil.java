package com.example;

import com.example.id.IntegerIdHelper;
import com.example.id.LongIdHelper;
import com.example.id.StringIdHelper;
import com.example.logging.LogFactory;
import com.example.util.RandomUtil;
import com.example.util.ThreadHelper;
import org.testng.SkipException;

import java.util.logging.Logger;

public class CommonTestUtil {
    private static Long seed;
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

    public synchronized RandomUtil random() {
            if (random == null) {
                if (seed == null) {
                    seed = System.currentTimeMillis();
                }
                random = new RandomUtil(seed);
            }
            return random;
    }

    public long anyLong() { return random.nextLong(); }

    /**
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

    /**
    * Call a thread sleep
    *
    * @param milliseconds declare the limit to sleep
    * */

    public void sleep(final long milliseconds) {
        ThreadHelper.sleep(milliseconds);
    }

    /**
    * Generates a new unique long id.
    * Counter is base lined at currentTimeMills and then incremented by 1 for each request
    *
    * @returns a unique long id*/

    public long newId() { return LongIdHelper.instance().getUniqueId(); }

    /**
    * Generates a new long id bounded by the given range.
    * */

    public long newId(long minInclusive, long maxInclusive) { return random().getRand(minInclusive, maxInclusive); }

    /**
    * Generates a new unique integer id
    * Counter s base lined at currentTimeMillis ad then incremented by 1 for each request
    *
    * @return a unique integer id
    * */

    public int newIntegerId() { return IntegerIdHelper.instance().getUniqueId(); }

    /**
    * @return Returns unique id, generated long number as String
    * */
    public String idAsString() { return String.valueOf(LongIdHelper.instance().getUniqueId()); }

    /**
    * @return Returns a string with prefix "ClientI_" followed by unique generated long number
    * */
    public String newClientId() { return StringIdHelper.instance().newClientId(); }

    /**
    * @return Returns a String with the provided prefix followed by unique long number
    *  */
    public String newString(String prefix) { return StringIdHelper.instance().newString(prefix); }

    /**
     * @return Returns a String with the provided prefix followed by unique long number, ending with the suffix
     *  */
    public String newString(String prefix, String suffix) {
        return StringIdHelper.instance().newString(prefix,suffix);
    }

    public int anyInt() { return random().nextInt(); }


}
