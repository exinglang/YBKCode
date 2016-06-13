package com.puxtech.ybk.qidong;

import android.util.Log;

/**
 * Created by fanshuo on 16/4/20.
 */
public class QiDongLogger {

    private static final String sDebugTag = "ybk_qidong";
    private static boolean is_show = true;

    // VERBOSE
    public static void v(final String pMessage) {
        if (is_show)
            QiDongLogger.v(pMessage, null);
    }

    public static void v(final Throwable pThrowable) {
        if (is_show)
            QiDongLogger.v("", pThrowable);
    }

    public static void v(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.v(QiDongLogger.sDebugTag, pMessage, pThrowable);
        }
    }

    // DEBUG
    public static void d(final String pMessage) {
        if (is_show)
            QiDongLogger.d(pMessage, null);
    }

    public static void d(final Throwable pThrowable) {
        if (is_show)
            QiDongLogger.d("", pThrowable);
    }

    public static void d(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.d(QiDongLogger.sDebugTag, pMessage, pThrowable);
        }
    }

    // INFO
    public static void i(final String pMessage) {
        QiDongLogger.i(pMessage, null);
    }

    // INFO
    public static void is(final String pMessage) {
//		if (is_show)
//			Log.i("PriceDetailActivity", pMessage);
    }

    // INFO
    public static void temp(final String pMessage) {
        // Log.i("PriceDetailActivity1", pMessage);
    }

    // INFO
    public static void klp(final String pMessage) {
        if (is_show)
            Log.i("KLinePortrait", pMessage);
    }

    // INFO
    public static void kls(final String pMessage) {
        if (is_show)
            Log.i("KLineLandScape", pMessage);
    }

    public static void i(final Throwable pThrowable) {
        if (is_show)
            QiDongLogger.i("", pThrowable);
    }

    public static void i(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.i(QiDongLogger.sDebugTag, pMessage, pThrowable);
        }
    }

    // WARNING
    public static void w(final String pMessage) {
        if (is_show)
            QiDongLogger.w(pMessage, null);
    }

    public static void w(final Throwable pThrowable) {
        if (is_show)
            QiDongLogger.w("", pThrowable);
    }

    public static void w(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.w(QiDongLogger.sDebugTag, pMessage, pThrowable);
        }
    }

    // ERROR
    public static void e(final String pMessage) {
        if (is_show)
            QiDongLogger.e(pMessage, null);
    }

    public static void e(final Throwable pThrowable) {
        if (is_show)
            QiDongLogger.e("", pThrowable);
    }

    public static void e(final String pMessage, final Throwable pThrowable) {
        if (is_show) {
            Log.e(QiDongLogger.sDebugTag, pMessage, pThrowable);
        }
    }



}
