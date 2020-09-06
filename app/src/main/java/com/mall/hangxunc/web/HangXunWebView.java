package com.mall.hangxunc.web;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.mall.hangxunc.message.MessageGoHome;
import com.mall.hangxunc.message.MessageGoLogin;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.CurrencyListBean;
import com.mall.hangxunc.http.ApiConstants;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.message.MessageLogin;
import com.mall.hangxunc.pages.login.WebLoginInterface;
import com.mall.hangxunc.utils.CurrencySp;
import com.mall.hangxunc.utils.CurrencyType;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.LoginUtils;

import org.greenrobot.eventbus.EventBus;


public class HangXunWebView extends LinearLayout {

    private Context mContext = null;
    private WebView mWebView = null;
    private int mBarHeight = 5;
    private ProgressBar mProgressBar = null;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public HangXunWebView(Context context) {
        super(context);
        init(context);
    }

    public HangXunWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(VERTICAL);
        mProgressBar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.mall_web_progress_horizontal, null);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        addView(mProgressBar, LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mBarHeight, getResources().getDisplayMetrics()));

        mWebView = new WebView(context);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        addJavascriptInterface();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            mWebView.setWebContentsDebuggingEnabled(true);

        }

        LayoutParams lps = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        addView(mWebView, lps);

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (mHangWebCallBack != null) {
                    mHangWebCallBack.onReceivedTitle(view, title);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);

                if (needClearHistory) {
                    needClearHistory = false;//清除历史记录的标识，在满足某个条件时，设置为true，删除历史记录
                    view.clearHistory();//清除历史记录
                }
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (TextUtils.equals(url, "http://c.hangxunc.com/index.php?route=account/logout")) {
                    LoginUtils.getInstance().loginOut();
                    EventBus.getDefault().post(MessageLogin.getInstance(MessageLogin.LOGIN_OUT));
                } else if (url.contains(ApiConstants.LANGUAGE_PATH)) {
//                    ChangeLanguageActivity.launch(getContext(), ChangeLanguageActivity.LANGUAGE_FLAG);

//                    return true;

                } else if (url.contains(ApiConstants.CURRENCY_PATH)) {
                    handleChangeCurrency(url);
                } else if (TextUtils.equals(url, "http://c.hangxunc.com/")) {
                    EventBus.getDefault().post(new MessageGoHome(MessageGoHome.GO_HOME));
                    return true;
                } else if (url.contains("/index.php?route=account/login")) {
                    EventBus.getDefault().post(new MessageGoLogin(MessageGoLogin.GO_LOGIN));
                    return true;
                } else if (TextUtils.equals(url, "http://c.hangxunc.com/index.php?route=checkout/cart")) {
                    if (!LoginUtils.getInstance().isLogin()) {
                        EventBus.getDefault().post(new MessageGoLogin(MessageGoLogin.GO_LOGIN));
                        return true;
                    }
                } else if (TextUtils.equals(url, "http://b.hangxunc.com/")) {
                    JumpUtils.goStreet(getContext());
                } else if (TextUtils.equals(url, "http://d.hangxunc.com:8081/scocenter/#/")) {
                    JumpUtils.goCenter(getContext());
                }

                return false;
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:bottomTabMenu()");
                if (!url.contains(ApiConstants.CART_PAGE_PATH)
                        && !url.contains(ApiConstants.ACCOUNT_PAGE_PATH)) {
                    view.loadUrl("javascript:navBar()");
                    view.loadUrl("javascript:pageBackHid()");

                }
            }
        });
    }

    private boolean needClearHistory = false;

    private void handleChangeCurrency(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Log.e("lulu", "url:" + url);
        String oldCode = CurrencySp.getInstance().getCode();
        CurrencyListBean list = CurrencySp.getInstance().getCurrencyList();

        String[] split = url.split("currency=");

        if (split != null && split.length > 1) {
            String s = split[1];
            String[] split1 = s.split("&");

            String currency = split1[0];

            if (!TextUtils.isEmpty(currency) && !currency.contains("=")) {

                if (TextUtils.equals(currency, CurrencyType.CHINESE.getCurrency())
                        | TextUtils.equals(currency, CurrencyType.ENGLISH.getCurrency())) {

                    if (!TextUtils.equals(oldCode, currency)) {
                        if (list == null) {
                            list = new CurrencyListBean();
                        }
                        list.setCode(currency);

                        CurrencySp.getInstance().saveCurrencyList(list);
                        EventBus.getDefault().post(MessageLocal.getInstance(MessageLocal.CURRENCY_CHANGE));
                    }
                }
            }
        }
    }

//    private void handleChangeLanguage(String url) {
//        if (TextUtils.isEmpty(url)) {
//            return;
//        }
//        Log.e("lulu", "url:" + url);
//
//        String oldCode = LanguageSp.getInstance().getCode();
//        LanguageListBean list = LanguageSp.getInstance().getLanguageList();
//
//        String[] split = url.split("language=");
//
//        if (split != null && split.length > 1) {
//            String s = split[1];
//            String[] split1 = s.split("&");
//
//            String language = split1[0];
//
//            if (!TextUtils.isEmpty(language) && !language.contains("=")) {
//                Log.e("lulu", "url:444444:language:" + language);
//
//                if (TextUtils.equals(language, LanguageType.CHINESE.getLanguage())
//                        | TextUtils.equals(language, LanguageType.ENGLISH.getLanguage())
//                        | TextUtils.equals(language, LanguageType.RU.getLanguage())) {
//                    Log.e("lulu", "url:55555:language:" + language);
//
//                    if (!TextUtils.equals(oldCode, language)) {
//                        Log.e("lulu", "url:66666:language:" + language);
//                        if (list == null) {
//                            list = new LanguageListBean();
//                        }
//                        list.setCode(language);
//
//                        LanguageSp.getInstance().saveLanguageList(list);
//                        LanguageUtil.changeAppLanguage(getContext(), language);
//                    }
//                }
//            }
//        }
//    }

    private HangWebCallBack mHangWebCallBack;

    public void setHangWebCallBack(HangWebCallBack mHangWebCallBack) {
        this.mHangWebCallBack = mHangWebCallBack;
    }

    public void clearHistory() {
        if (null != mWebView) {
            needClearHistory = true;
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearCache(true);
            mWebView.clearHistory();
            mWebView.freeMemory();
            mWebView.pauseTimers();
        }
    }

    public void destroyView() {
        clearHistory();
        mWebView = null;
    }


    public interface HangWebCallBack {
        void onReceivedTitle(WebView view, String title);

    }

    public void loadUrl(String url) {
        if (mWebView == null) {
            return;
        }
        mWebView.loadUrl(url);
    }

    public void reload() {
        if (mWebView == null) {
            return;
        }
        mWebView.reload();
    }

    public boolean canGoBack() {
        return null != mWebView ? mWebView.canGoBack() : false;
    }

    public boolean canGoForward() {
        return null != mWebView ? mWebView.canGoForward() : false;
    }

    public void goBack() {
        if (null != mWebView) {
            mWebView.goBack();
        }
    }

    public void goForward() {
        if (null != mWebView) {
            mWebView.goForward();
        }
    }

    public WebView getWebView() {
        return mWebView != null ? mWebView : null;
    }

    public void addJavascriptInterface() {
        if (null != mWebView) {
            mWebView.addJavascriptInterface(new WebLoginInterface(getContext()), "c_hangxun");
        }
    }

}
