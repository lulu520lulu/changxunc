package com.c.hangxunc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;

import com.c.hangxunc.HandXunApplication;
import com.google.gson.Gson;

import java.util.Locale;

public class LanguageUtils {

    public static boolean mIsLogin = false;
    private SharedPreferences spLocale;


    private LanguageUtils() {
        if (spLocale == null) {
//            mSharedPreferencesContext = HandXunApplication.getInstance()
//                    .getSharedPreferences("Language", MODE_PRIVATE);
            spLocale = HandXunApplication.getInstance().getSharedPreferences(LOCALE_SP, Context.MODE_PRIVATE);

        }
    }

    private static class Singleton {
        private static LanguageUtils instance = new LanguageUtils();
    }

    public static LanguageUtils getInstance() {
        return LanguageUtils.Singleton.instance;
    }

    public String getName() {
        Locale locale = getLocale();
        if (locale == LOCALE_ENGLISH) {
            return "English";
        } else if (locale == LOCALE_RUSSIAN) {
            return "Russian";
        }
        return "简体中文";
    }

    public String getCode() {
        Locale locale = getLocale();
        if (locale == LOCALE_ENGLISH) {
            return "en-gb";
        } else if (locale == LOCALE_RUSSIAN) {
            return "ru-ru";
        }
        return "zh-cn";
    }


    /**
     * 中文
     */
    public static final Locale LOCALE_CHINESE = Locale.CHINESE;
    /**
     * 英文
     */
    public static final Locale LOCALE_ENGLISH = Locale.ENGLISH;
    /**
     * 俄文
     */
    public static final Locale LOCALE_RUSSIAN = new Locale("ru");

    private static final String LOCALE_SP = "LOCALE_SP";
    private static final String LOCALE_SP_KEY = "LOCALE_SP_KEY";


    private Locale getLocale() {
        String localeJson = spLocale.getString(LOCALE_SP_KEY, "");
        Gson gson = new Gson();
        return gson.fromJson(localeJson, Locale.class);
    }


    private void setLocale(Locale pUserLocale) {
        SharedPreferences.Editor edit = spLocale.edit();
        String json = new Gson().toJson(pUserLocale);
        edit.putString(LOCALE_SP_KEY, json);
        edit.apply();
    }


    public boolean updateLocale(String code) {
        Locale locale = LOCALE_CHINESE;
        if (TextUtils.equals(code, "en-gb")) {
            locale = LOCALE_ENGLISH;
        } else if (TextUtils.equals(code, "ru-ru")) {
            locale = LOCALE_RUSSIAN;
        }

        if (needUpdateLocale(locale)) {
            Configuration configuration = HandXunApplication.getInstance().getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
            } else {
                configuration.locale = locale;
            }
            updateResources(HandXunApplication.getInstance(), locale);
//            DisplayMetrics displayMetrics = HandXunApplication.getInstance().getResources().getDisplayMetrics();
//            HandXunApplication.getInstance().getResources().updateConfiguration(configuration, displayMetrics);
            setLocale(locale);
            return true;
        }
        return false;
    }


    private Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }


    public boolean needUpdateLocale(Locale newUserLocale) {
        return newUserLocale != null && !getCurrentLocale().equals(newUserLocale);
    }

    public Locale getCurrentLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
            locale = HandXunApplication.getInstance().getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = HandXunApplication.getInstance().getResources().getConfiguration().locale;
        }
        return locale;
    }

}
