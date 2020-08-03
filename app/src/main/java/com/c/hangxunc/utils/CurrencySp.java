package com.c.hangxunc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.c.hangxunc.HangXunApplication;
import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CurrencyListBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CurrencySp {

    private static final String CURRENCY_SP = "CURRENCY_SP";
    private static final String CURRENCY_SP_BEAN = "currency_sp_bean";

    private static final String TAG = CurrencySp.class.getSimpleName();

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


    private CurrencySp() {
        mPreferences = HangXunApplication.getInstance().getSharedPreferences(CURRENCY_SP, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    private static class Singleton {
        private static CurrencySp instance = new CurrencySp();
    }

    public static CurrencySp getInstance() {
        return CurrencySp.Singleton.instance;
    }


    public String getName() {
        String code = getCode();
        if (TextUtils.equals(code, "USD")) {
            return "US Dollar";
        }
        return HangXunApplication.getInstance().getResources().getString(R.string.rmb);
    }

    public String getCode() {
        if (mCurrencyListBean == null) {
            mCurrencyListBean = getCurrencyList();
        }
        if (mCurrencyListBean != null) {
            return mCurrencyListBean.getCode();
        }
        return "";
    }

    private CurrencyListBean mCurrencyListBean;

    public void saveCurrencyList(CurrencyListBean data) {
        if (null == data) {
            return;
        }
        mCurrencyListBean = data;
        Gson gson = new Gson();
        String strJson = gson.toJson(data);
        Log.d(TAG, "setData, json:" + strJson);
        mEditor.clear();
        mEditor.putString(CURRENCY_SP_BEAN, strJson);
        mEditor.commit();
    }

    public CurrencyListBean getCurrencyList() {
        if (mCurrencyListBean != null) {
            return mCurrencyListBean;
        }
        CurrencyListBean data = null;
        String strJson = mPreferences.getString(CURRENCY_SP_BEAN, null);
        if (null == strJson) {
            return null;
        }
        Log.d(TAG, "getData, json:" + strJson);
        try {
            Gson gson = new Gson();
            JsonElement jsonElement = new JsonParser().parse(strJson);
            data = gson.fromJson(jsonElement, CurrencyListBean.class);
            mCurrencyListBean = data;
        } catch (Exception e) {
            Log.e(TAG, "Exception : " + e.getMessage());
        }
        return data;
    }

}
