package com.qq.xgdemo.util;

import android.util.Log;

/**
 * Created by agprincefu on 2017/10/10.
 */

public class LogUtil {
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int NOTHING = 6;
    private static int level = VERBOSE;
    private static final String TAG = "agtest";

    public static void v(String msg) {
        if (level <= VERBOSE) {
            Log.v(TAG, msg);
        }

    }

    public static void v(String msg, Throwable tr) {
        if (level <= VERBOSE) {
            Log.v(TAG, msg, tr);
        }
    }

    public static void d(String msg) {
        if (level <= DEBUG) {
            Log.d(TAG, msg);
        }

    }

    public static void d(String msg, Throwable tr) {
        if (level <= DEBUG) {
            Log.d(TAG, msg, tr);
        }
    }

    public static void i(String msg) {
        if (level <= INFO) {
            Log.i(TAG, msg);
        }

    }

    public static void i(String msg, Throwable tr) {
        if (level <= INFO) {
            Log.i(TAG, msg, tr);
        }
    }

    public static void w(String msg) {
        if (level <= WARN) {
            Log.w(TAG, msg);
        }

    }

    public static void w(String msg, Throwable tr) {
        if (level <= WARN) {
            Log.w(TAG, msg, tr);
        }
    }

    public static void e(String msg) {
        if (level <= ERROR) {
            Log.e(TAG, msg);
        }

    }

    public static void e(String msg, Throwable tr) {
        if (level <= ERROR) {
            Log.e(TAG, msg, tr);
        }
    }

}
