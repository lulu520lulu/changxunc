package com.c.hangxunc.pages.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.c.hangxunc.R;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.mvp.BaseFragment;
import com.just.agentweb.AgentWeb;

public class ForgetPassFragmentWeb extends BaseFragment<ForgetPassPresenterWeb> {
    //    b.hangxunc.com/index.php?route=account/forgotten
    private ForgetPassPresenterWeb mForgetPassPresenterWeb;
//    private WebView mWebView;


    @Override
    protected ForgetPassPresenterWeb onCreateTopPresenter() {
        mForgetPassPresenterWeb = new ForgetPassPresenterWeb(getActivity());
        return mForgetPassPresenterWeb;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password_web, null);
        initView(view);
        initData();
        return view;
    }

    private FrameLayout webContainer;
    private AgentWeb mAgentWeb;

    private void initView(View view) {
        webContainer = view.findViewById(R.id.container);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
//        mWebView = new WebView(HandXunApplication.getInstance());
//        mWebView.setLayoutParams(params);
//        container.addView(mWebView);
//        initWebView();
    }

    private void initData() {
        String url = ApiConstants.BASE_URL + ApiConstants.FORGOTTEN_PAGE_PATH;
        if (TextUtils.isEmpty(url)) {
            return;
        }


        mAgentWeb = AgentWeb.with(getActivity())//传入Activity
                .setAgentWebParent(webContainer, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .setWebViewClient(new com.just.agentweb.WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(url);
                        return true;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        view.loadUrl("javascript:pageBackHid()");
                        view.loadUrl("javascript:bottomTabMenu()");
                    }
                })
                .createAgentWeb()//
                .ready()
                .go(url);

//        mWebView.loadUrl(url);
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(url);
//                return true;
//
//            }
//        });
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//
//            }
//
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//            }
//        });
    }

//    private void initWebView() {
//        //声明WebSettings子类
//        WebSettings webSettings = mWebView.getSettings();
//        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
//        webSettings.setJavaScriptEnabled(true);
//        //设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        //缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
//        //其他细节操作
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
//        webSettings.setAllowFileAccess(true); //设置可以访问文件
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
//        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
//    }

    @Override
    protected void onResumeImpl() {
        super.onResumeImpl();
    }

    @Override
    protected void onPauseImpl() {
        super.onPauseImpl();
    }


    @Override
    protected void onDestroyViewImpl() {
        super.onDestroyViewImpl();
    }

}
