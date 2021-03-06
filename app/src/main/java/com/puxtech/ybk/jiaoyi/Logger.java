package com.puxtech.ybk.jiaoyi;

import android.util.Log;

/**
 * 交易 log打印工具
 */
public class Logger {
	private static final String sDebugTag = "trade";
	public static boolean is_show = true;
	public static boolean is_time_show = false;//是否显示循环的日志

	// VERBOSEO
	public static void timeV(final String pMessage) {
		if (is_time_show) {
			Logger.v(pMessage, null);

		}
	}
	// VERBOSE
	public static void v(final String pMessage) {
			Logger.v(pMessage, null);

	}
	public static void v(final Throwable pThrowable) {
		Logger.v("", pThrowable);
	}

	public static void v(final String pMessage, final Throwable pThrowable) {
		if (is_show) {
			Log.v(Logger.sDebugTag, pMessage, pThrowable);
		}
	}

	// DEBUG
	public static void d(final String pMessage) {
		Logger.d(pMessage, null);
	}

	public static void d(final Throwable pThrowable) {
		Logger.d("", pThrowable);
	}

	public static void d(String pMessage, final Throwable pThrowable) {
		if (is_show) {
			pMessage = pMessage.replaceAll("\n", "");
			Log.d(Logger.sDebugTag, pMessage, pThrowable);
		}
	}

	// INFO
	public static void i(final String pMessage) {
		Logger.i(pMessage, null);
	}

	public static void i(final Throwable pThrowable) {
		Logger.i("", pThrowable);
	}

	public static void i(final String pMessage, final Throwable pThrowable) {
		if (is_show) {
			Log.i(Logger.sDebugTag, pMessage, pThrowable);
		}
	}

	// WARNING
	public static void w(final String pMessage) {
		Logger.w(pMessage, null);
	}

	public static void w(final Throwable pThrowable) {
		Logger.w("", pThrowable);
	}

	public static void w(final String pMessage, final Throwable pThrowable) {
		if (is_show) {
			Log.w(Logger.sDebugTag, pMessage, pThrowable);
		}
	}

	// ERROR
	public static void e(final String pMessage) {
		Logger.e(pMessage, null);
	}

	public static void e(final Throwable pThrowable) {
		Logger.e("", pThrowable);
	}

	public static void e(final String pMessage, final Throwable pThrowable) {
		if (is_show) {
			Log.e(Logger.sDebugTag, pMessage, pThrowable);
		}
	}
}