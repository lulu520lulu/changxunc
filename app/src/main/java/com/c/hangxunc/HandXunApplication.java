package com.c.hangxunc;

import android.app.Application;

public class HandXunApplication extends Application {

    private static HandXunApplication mHandXunApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mHandXunApplication = this;
        CrashExceptionHandler.init(this);
    }

    public static HandXunApplication getInstance() {
        return mHandXunApplication;
    }

}
