package com.c.hangxunc.pages.shop;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.c.hangxunc.pages.MainActivity;
import com.c.hangxunc.utils.LanguageUtils;
import com.c.hangxunc.utils.LoginUtils;
import com.just.agentweb.AgentWeb;

public class ShopCartFragment extends BaseFragment<ShopCarPresenter> {

    private ShopCarPresenter mShopCarPresenter;
    private FrameLayout mWebContainer;
    private AgentWeb mAgentWeb;


    @Override
    protected ShopCarPresenter onCreateTopPresenter() {
        mShopCarPresenter = new ShopCarPresenter(getActivity());
        return mShopCarPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mWebContainer = view.findViewById(R.id.container);
        showWeb(mWebContainer);
    }


    private void showWeb(FrameLayout webContainer) {
        if (webContainer == null) {
            return;
        }
        String url = ApiConstants.BASE_URL + ApiConstants.CART_PAGE_PATH + LoginUtils.getInstance().getCustomerId()
                + ApiConstants.LANGUAGE_PATH + LanguageUtils.getInstance().getCode();
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mAgentWeb = AgentWeb.with(getActivity())
                .setAgentWebParent(webContainer, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT))
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
                .createAgentWeb()
                .ready()
                .go(url);

    }


    @Override
    protected void onDestroyViewImpl() {
        super.onDestroyViewImpl();
    }

    private CountDownTimer mTimer;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (!LoginUtils.getInstance().isLogin()) {

                mTimer = new CountDownTimer(300, 100) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        ((MainActivity) getActivity()).setSelect(4);
                    }
                };
                mTimer.start();
            } else {
                showWeb(mWebContainer);
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        }
    }
}