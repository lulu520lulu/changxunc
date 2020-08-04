package com.c.hangxunc.utils;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class LanguageUtil {

    private static final String TAG = "LanguageUtil";

    /**
     * @param context
     * @param newLanguage 想要切换的语言类型 比如 "en" ,"zh"
     */
    @SuppressWarnings("deprecation")
    public static boolean changeAppLanguage(Context context, String newLanguage) {
        if (TextUtils.isEmpty(newLanguage)) {
            return false;
        }
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        //获取想要切换的语言类型
        Locale locale = getLocaleByLanguage(newLanguage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        Log.e("lulu", "changeAppLanguage :" + locale.getDisplayName() + ",  :" + locale.getLanguage());

        // updateConfiguration
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);
        return true;
    }

    /**
     * @param context
     * @param newLanguage 想要切换的语言类型 比如 "en" ,"zh"
     */
    @SuppressWarnings("deprecation")
    public static void changeLanguageAndKill(Context context, String newLanguage) {
        if (changeAppLanguage(context, newLanguage)) {
            killSelf(context);
        }
    }

    public static Locale getLocaleByLanguage(String language) {
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        if (TextUtils.isEmpty(language)) {
            return locale;
        }
        if (TextUtils.equals(language, LanguageType.CHINESE.getLanguage())) {
            locale = Locale.SIMPLIFIED_CHINESE;
        } else if (TextUtils.equals(language, LanguageType.ENGLISH.getLanguage())) {
            locale = Locale.ENGLISH;
        } else if (TextUtils.equals(language, LanguageType.RU.getLanguage())) {
            locale = new Locale("ru");
        }
        Log.d(TAG, "getLocaleByLanguage: " + locale.getDisplayName());
        return locale;
    }

    public static Context attachBaseContext(Context context, String language) {
        Log.d(TAG, "attachBaseContext: " + Build.VERSION.SDK_INT);
        Log.e("lulu", "attachBaseContext:");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        } else {
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Resources resources = context.getResources();
        Locale locale = LanguageUtil.getLocaleByLanguage(language);

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    private static void killSelf(Context context) {
        //重启
        Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(context, 0, intent, 0);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
        System.exit(0);
    }
}
