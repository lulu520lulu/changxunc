package com.c.hangxunc;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.c.hangxunc.utils.HangLog;
import com.c.hangxunc.utils.LanguageSp;
import com.c.hangxunc.utils.LanguageUtil;


public class HangXunApplication extends Application {

    private static HangXunApplication mHangXunApplication;
    public int MAIN_SELECT_ITEM = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate() {
        super.onCreate();
        mHangXunApplication = this;
        HangLog.setShowLog(TextUtils.equals(BuildConfig.BUILD_TYPE, "debug"));
        /**
         * 对于7.0以下，需要在Application创建的时候进行语言切换
         */
        String language = LanguageSp.getInstance().getCode();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(HangXunApplication.getInstance(), language);
        }
        CrashExceptionHandler.init(this);
    }

    public static HangXunApplication getInstance() {
        return mHangXunApplication;
    }

}
