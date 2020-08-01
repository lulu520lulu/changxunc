package com.c.hangxunc.pages.login;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class WebLoginInterface {

    Context mContext;

    public WebLoginInterface(Context context) {
        this.mContext = context;
    }


    @JavascriptInterface
    public void loginOut() {

    }
}
