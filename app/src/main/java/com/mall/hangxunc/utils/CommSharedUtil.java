package com.mall.hangxunc.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class CommSharedUtil {

    private static final String SHARED_PATH = "hangxun_app_info";
    private static CommSharedUtil helper;
    private SharedPreferences sharedPreferences;

    public static final String FIRST_ENTER_KEY = "first_enter_key";


    private CommSharedUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE);
    }

    public static CommSharedUtil getInstance(Context context) {
        if (helper == null) {
            helper = new CommSharedUtil(context);
        }
        return helper;
    }

    public void putInt(String key, int value) {
        Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }


    public void putString(String key, String value) {
        Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.apply();
    }


    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }


    public void putBoolean(String key, boolean value) {
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }


    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public void remove(String key) {
        Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.apply();
    }

    public <T> void saveBean2Sp(String keyName, T t) {
        ByteArrayOutputStream bos;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            byte[] bytes = bos.toByteArray();
            String ObjStr = Base64.encodeToString(bytes, Base64.DEFAULT);
            Editor edit = sharedPreferences.edit();
            edit.putString(keyName, ObjStr);
            edit.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <T extends Object> T getBeanFromSp(String keyNme) {
        byte[] bytes = Base64.decode(sharedPreferences.getString(keyNme, ""), Base64.DEFAULT);
        ByteArrayInputStream bis;
        ObjectInputStream ois = null;
        T obj = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
