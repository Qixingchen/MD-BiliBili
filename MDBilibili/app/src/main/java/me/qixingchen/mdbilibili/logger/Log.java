package me.qixingchen.mdbilibili.logger;

/**
 * Created by farble on 2015/5/7.
 */
public class Log {
    private static final boolean DEBUG = false;

    public static void e(String tag,String message) {
        if (DEBUG) {
            android.util.Log.e(tag,message);
        }
    }
    public static void i(String tag,String message) {
        if (DEBUG) {
            android.util.Log.i(tag,message);
        }
    }
    public static void d(String tag,String message) {
        if (DEBUG) {
            android.util.Log.d(tag,message);
        }
    }
    public static void w(String tag,String message) {
        if (DEBUG) {
            android.util.Log.w(tag,message);
        }
    }
    public static void wtf(String tag,String message) {
        if (DEBUG) {
            android.util.Log.wtf(tag,message);
        }
    }
    public static void v(String tag,String message) {
        if (DEBUG) {
            android.util.Log.v(tag,message);
        }
    }
}
