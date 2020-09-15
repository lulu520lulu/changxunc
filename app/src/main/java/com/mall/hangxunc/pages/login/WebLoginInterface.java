package com.mall.hangxunc.pages.login;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.mall.hangxunc.HangActivityManager;
import com.mall.hangxunc.HangXunApplication;
import com.mall.hangxunc.change.ChangeLanguageActivity;
import com.mall.hangxunc.pages.MainActivity;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.web.HybridActivity;

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


    @JavascriptInterface
    public void webFinish() {
        Log.e("lulu", "webFinish");
        Activity topActivity = HangActivityManager.getInstance().getTopActivity();
        if (TextUtils.equals(topActivity.getClass().getSimpleName(), HybridActivity.class.getSimpleName())) {
            HangActivityManager.getInstance().finishTopActivity();
        }
    }
}
