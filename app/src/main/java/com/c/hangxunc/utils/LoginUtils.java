package com.c.hangxunc.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;


import com.c.hangxunc.HangXunApplication;

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

    public void setLoginInfo(String session, String customer_id) {
        SharedPreferences.Editor editor = mSharedPreferencesContext.edit();
        editor.putString("session", session);
        editor.putString("customer_id", customer_id);
        editor.commit();
    }


    public void loginOut() {
        SharedPreferences.Editor editor = mSharedPreferencesContext.edit();
        editor.putString("customer_id", "");
        editor.commit();
    }


    public String getCustomerId() {
        return mSharedPreferencesContext.getString("customer_id", "");
    }

    public String getSession() {
        return mSharedPreferencesContext.getString("session", "");
    }


    public boolean isLogin() {
        String customerId = getCustomerId();
        String session = getSession();
        if (!TextUtils.isEmpty(session) && !TextUtils.isEmpty(customerId)) {
            return true;
        }
        return false;
    }
}
