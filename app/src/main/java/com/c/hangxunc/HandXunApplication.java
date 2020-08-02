package com.c.hangxunc;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.c.hangxunc.utils.LanguageSp;
import com.c.hangxunc.utils.LanguageUtil;


public class HandXunApplication extends Application {

    private static HandXunApplication mHandXunApplication;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate() {
        super.onCreate();
        mHandXunApplication = this;

        /**
         * 对于7.0以下，需要在Application创建的时候进行语言切换
         */
        String language = LanguageSp.getInstance().getCode();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(HandXunApplication.getInstance(), language);
        }
        CrashExceptionHandler.init(this);
    }

    public static HandXunApplication getInstance() {
        return mHandXunApplication;
    }

}
