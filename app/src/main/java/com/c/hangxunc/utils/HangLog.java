package com.c.hangxunc.utils;

import android.util.Log;

public class HangLog {
    private static final String PRE = "B_Hang_";

    private static boolean mIsShowLog = true;

    public static void setShowLog(boolean isShow) {
        HangLog.mIsShowLog = isShow;
    }

    public static void v(Object object) {
        if (mIsShowLog) {
            Log.v(PRE, object + "");
        }
    }

    public static void v(String tag, Object object) {
        if (mIsShowLog) {
            Log.v(PRE + tag, object + "");
        }
    }

    public static void d(Object object) {
        if (mIsShowLog) {
            Log.d(PRE, object + "");
        }
    }

    public static void d(String tag, Object object) {
        if (mIsShowLog) {
            Log.d(PRE + tag, object + "");
        }
    }

    public static void i(Object object) {
        if (mIsShowLog) {
            Log.i(PRE, object + "");
        }
    }

    public static void i(String tag, Object object) {
        if (mIsShowLog) {
            Log.i(PRE + tag, object + "");
        }
    }

    public static void w(Object object) {
        if (mIsShowLog) {
            Log.w(PRE, object + "");
        }
    }

    public static void w(String tag, Object object) {
        if (mIsShowLog) {
            Log.w(PRE + tag, object + "");
        }
    }

    public static void e(Object object) {
        if (mIsShowLog) {
            Log.e(PRE, object + "");
        }
    }

    public static void e(String tag, Object object) {
        if (mIsShowLog) {
            Log.e(PRE + tag, object + "");
        }
    }
}
