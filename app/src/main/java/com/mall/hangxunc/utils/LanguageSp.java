package com.mall.hangxunc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mall.hangxunc.HangXunApplication;
import com.mall.hangxunc.bean.home.LanguageListBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class LanguageSp {

    private static final String TAG = LanguageSp.class.getSimpleName();
    private static final String LANGUAGE_SP = "language_sp";
    private static final String LANGUAGE_SP_BEAN_LIST = "language_sp_bean_list";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private LanguageListBean mLanguageListBean;


    private LanguageSp() {
        mPreferences = HangXunApplication.getInstance().getSharedPreferences(LANGUAGE_SP, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    private static class Singleton {
        private static LanguageSp instance = new LanguageSp();
    }

    public static LanguageSp getInstance() {
        return Singleton.instance;
    }

    public String getName() {
        String code = getCode();
        if (code.equals(LanguageType.ENGLISH.getLanguage())) {
            return "English";
        } else if (code.equals(LanguageType.RU.getLanguage())) {
            return "Russian";
        }
        return "简体中文";
    }

    public String getCode() {
        if (mLanguageListBean == null) {
            mLanguageListBean = getLanguageList();
        }
        if (mLanguageListBean != null) {
            return mLanguageListBean.getCode();
        }
        return "";
    }

    public void saveLanguageList(LanguageListBean data) {
        if (null == data) {
            return;
        }
        mLanguageListBean = data;
        Gson gson = new Gson();
        String strJson = gson.toJson(data);
        Log.d(TAG, "setData, json:" + strJson);
        mEditor.clear();
        mEditor.putString(LANGUAGE_SP_BEAN_LIST, strJson);
        mEditor.commit();
    }

    public LanguageListBean getLanguageList() {
        if (mLanguageListBean != null) {
            return mLanguageListBean;
        }
        LanguageListBean data = null;
        String strJson = mPreferences.getString(LANGUAGE_SP_BEAN_LIST, null);
        if (null == strJson) {
            return null;
        }
        Log.d(TAG, "getData, json:" + strJson);
        try {
            Gson gson = new Gson();
            JsonElement jsonElement = new JsonParser().parse(strJson);
            data = gson.fromJson(jsonElement, LanguageListBean.class);
            mLanguageListBean = data;
        } catch (Exception e) {
            Log.e(TAG, "Exception : " + e.getMessage());
        }
        return data;
    }


}
