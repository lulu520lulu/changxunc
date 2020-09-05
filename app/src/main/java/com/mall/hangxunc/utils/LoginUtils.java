package com.mall.hangxunc.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;


import com.mall.hangxunc.HangXunApplication;

import static android.content.Context.MODE_PRIVATE;

public class LoginUtils {

    public static boolean mIsLogin = false;
    private SharedPreferences mSharedPreferencesContext;


    private LoginUtils() {
        if (mSharedPreferencesContext == null) {
            mSharedPreferencesContext = HangXunApplication.getInstance()
                    .getSharedPreferences("Login", MODE_PRIVATE);
        }
    }

    private static class Singleton {
        private static LoginUtils instance = new LoginUtils();
    }

    public static LoginUtils getInstance() {
        return Singleton.instance;
    }

    public void setLoginInfo(String session, String sco) {
        SharedPreferences.Editor editor = mSharedPreferencesContext.edit();
        editor.putString("session", session);
        editor.putString("sco", sco);
        editor.commit();
    }


    public void loginOut() {
        SharedPreferences.Editor editor = mSharedPreferencesContext.edit();
        editor.putString("sco", "");
        editor.commit();
    }


    public String getScoId() {
        return mSharedPreferencesContext.getString("sco", "");
    }

    public String getSession() {
        return mSharedPreferencesContext.getString("session", "");
    }


    public boolean isLogin() {
        String customerId = getScoId();
        String session = getSession();
        if (!TextUtils.isEmpty(session) && !TextUtils.isEmpty(customerId)) {
            return true;
        }
        return false;
    }
}
