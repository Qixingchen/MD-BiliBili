package me.qixingchen.mdbilibili.utils;

import android.util.Log;

/**
 * Created by shenvsv on 14-4-2.
 */

public class DLog {
    public static void i(String msg) {
        if (isDebug()) {
            Log.i(getCallerName(), msg);
        }
    }

    public static void d(String msg) {
        if (isDebug()) {
            Log.d(getCallerName(), msg);
        }
    }

    public static void v(String msg) {
        if (isDebug()) {
            Log.v(getCallerName(), msg);
        }
    }

    public static void e(String msg) {
        if (isDebug()) {
            Log.e(getCallerName(), msg);
        }
    }

    public static void w(String msg) {
        if (isDebug()) {
            Log.w(getCallerName(), msg);
        }
    }

    private static String getCallerName() {
        StackTraceElement[] elements = new Throwable().getStackTrace();
        return elements[2].getClassName();
    }

    private static Boolean isDebug() {
//        return BuildConfig.DEBUG;

        return true;
    }
}
