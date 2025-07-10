package com.example.id;

public class StringIdHelper {
    private static StringIdHelper cInstance;
    private static final String CLIENT_ID_PREFIX = "ClientID_";

    private StringIdHelper() {
    }

    public static StringIdHelper instance() {
        synchronized (StringIdHelper.class) {
            if (cInstance == null) {
                cInstance = new StringIdHelper();
            }
        }
        return cInstance;
    }

    /****
     * Creates a String with prefix
     * clientID_ followed by a unique long number
     * @return unique client id*/

    public synchronized String newClientId() { return CLIENT_ID_PREFIX + LongIdHelper.instance().getUniqueId(); }

    public synchronized String newClientId(String prefix) { return prefix + LongIdHelper.instance().getUniqueId(); }

    public synchronized String newStringId() { return String.valueOf(LongIdHelper.instance().getUniqueId()); }

    public synchronized String newString(String prefix) { return prefix + LongIdHelper.instance().getUniqueId(); }

    public synchronized String newString(String prefix, String suffix) { return newString(prefix) + suffix; }
}
