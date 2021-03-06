package com.puxtech.ybk.hangqing;

import android.util.Log;

/**
 * Created by fanshuo on 16/4/20.
 */
public class HangQingLogger {

    private static final String sDebugTag = "ybk_hangqing";
    private static boolean is_show = false;

    // VERBOSE
    public static void v(final String pMessage) {
        if (is_show)
            HangQingLogger.v(pMessage, null);
    }

    public static void v(final Throwable pThrowable) {
        if (is_show)
            HangQingLogger.v("", pThrowable);
    }

    public static void v(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.v(HangQingLogger.sDebugTag, pMessage, pThrowable);
        }
    }

    // DEBUG
    public static void d(final String pMessage) {
        if (is_show)
            HangQingLogger.d(pMessage, null);
    }

    public static void d(final Throwable pThrowable) {
        if (is_show)
            HangQingLogger.d("", pThrowable);
    }

    public static void d(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.d(HangQingLogger.sDebugTag, pMessage, pThrowable);
        }
    }

    // INFO
    public static void i(final String pMessage) {
        HangQingLogger.i(pMessage, null);
    }

    public static void i(final Throwable pThrowable) {
        if (is_show)
            HangQingLogger.i("", pThrowable);
    }

    public static void i(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.i(HangQingLogger.sDebugTag, pMessage, pThrowable);
        }
    }

    // WARNING
    public static void w(final String pMessage) {
        if (is_show)
            HangQingLogger.w(pMessage, null);
    }

    public static void w(final Throwable pThrowable) {
        if (is_show)
            HangQingLogger.w("", pThrowable);
    }

    public static void w(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.w(HangQingLogger.sDebugTag, pMessage, pThrowable);
        }
    }

    // ERROR
    public static void e(final String pMessage) {
        if (is_show)
            HangQingLogger.e(pMessage, null);
    }

    public static void e(final Throwable pThrowable) {
        if (is_show)
            HangQingLogger.e("", pThrowable);
    }

    public static void e(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.e(HangQingLogger.sDebugTag, pMessage, pThrowable);
        }
    }



}
