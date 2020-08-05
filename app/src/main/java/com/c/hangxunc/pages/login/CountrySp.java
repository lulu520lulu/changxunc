package com.c.hangxunc.pages.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.c.hangxunc.HangXunApplication;
import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CountryBean;
import com.c.hangxunc.utils.CurrencySp;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CountrySp {

    private static final String COUNTRY_SP = "country_sp";
    private static final String COUNTRY_SP_DATA = "country_sp_data";

    private static final String TAG = CurrencySp.class.getSimpleName();

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


    private CountrySp() {
        mPreferences = HangXunApplication.getInstance().getSharedPreferences(COUNTRY_SP, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    private static class Singleton {
        private static CountrySp instance = new CountrySp();
    }

    public static CountrySp getInstance() {
        return CountrySp.Singleton.instance;
    }


    private CountryBean mCountryBean;

    public void saveCountry(CountryBean data) {
        if (null == data) {
            return;
        }
        mCountryBean = data;
        Gson gson = new Gson();
        String strJson = gson.toJson(data);
        Log.d(TAG, "setData, json:" + strJson);
        mEditor.clear();
        mEditor.putString(COUNTRY_SP_DATA, strJson);
        mEditor.commit();
    }

    public CountryBean getCountry() {
        if (mCountryBean != null) {
            return mCountryBean;
        }
        CountryBean data = null;
        String strJson = mPreferences.getString(COUNTRY_SP_DATA, null);
        if (null == strJson) {
            return null;
        }
        Log.d(TAG, "getData, json:" + strJson);
        try {
            Gson gson = new Gson();
            JsonElement jsonElement = new JsonParser().parse(strJson);
            data = gson.fromJson(jsonElement, CountryBean.class);
            mCountryBean = data;
        } catch (Exception e) {
            Log.e(TAG, "Exception : " + e.getMessage());
        }
        return data;
    }

}
