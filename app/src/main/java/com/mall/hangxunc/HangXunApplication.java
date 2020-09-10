package com.mall.hangxunc;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.mall.hangxunc.utils.HangLog;
import com.mall.hangxunc.utils.LanguageSp;
import com.mall.hangxunc.utils.LanguageUtil;


public class HangXunApplication extends Application {

    private static HangXunApplication mHangXunApplication;
    public String mHybridUrl;
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