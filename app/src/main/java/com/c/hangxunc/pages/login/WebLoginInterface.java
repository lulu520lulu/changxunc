package com.c.hangxunc.pages.login;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.c.hangxunc.HangXunApplication;
import com.c.hangxunc.change.ChangeLanguageActivity;

public class WebLoginInterface {

    Context mContext;

    public WebLoginInterface(Context context) {
        this.mContext = context;
    }


    @JavascriptInterface
    public void loginOut() {

    }

    @JavascriptInterface
    public void changeLanguage() {
        Log.e("lulu", "changeLanguage");

        ChangeLanguageActivity.launch(HangXunApplication.getInstance(), ChangeLanguageActivity.LANGUAGE_FLAG);
    }

    @JavascriptInterface
    public void changeCurrency() {
        Log.e("lulu", "changeCurrency");
        ChangeLanguageActivity.launch(HangXunApplication.getInstance(), ChangeLanguageActivity.CURRENCY_FLAG);

    }
}
