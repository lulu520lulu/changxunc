package com.mall.hangxunc.pages.street.shop;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mall.hangxunc.R;
import com.mall.hangxunc.message.MessageGoHome;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.message.MessageLogin;
import com.mall.hangxunc.message.MessageShop;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.MainActivity;
import com.mall.hangxunc.pages.street.StreetHomeActivity;
import com.mall.hangxunc.pages.street.http.StreetApiConstants;
import com.mall.hangxunc.utils.CurrencySp;
import com.mall.hangxunc.utils.LanguageSp;
import com.mall.hangxunc.utils.LoginUtils;
import com.mall.hangxunc.web.HangXunWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StreetShopCartFragment extends BaseFragment<StreetShopCarPresenter> {


    private StreetShopCarPresenter mShopCarPresenter;
    private HangXunWebView mWebContainer;
    private CountDownTimer mTimer;

    @Override
    protected StreetShopCarPresenter onCreateTopPresenter() {
        mShopCarPresenter = new StreetShopCarPresenter(getActivity());
        return mShopCarPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.street_fragment_shop, null);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mWebContainer = view.findViewById(R.id.web_container);
        if (!LoginUtils.getInstance().isLogin()) {
            mWebContainer.setVisibility(View.GONE);
        } else {
            mWebContainer.setVisibility(View.VISIBLE);
            showWeb();
        }

    }

    private void showWeb() {
        String url = StreetApiConstants.BASE_URL + StreetApiConstants.CART_PAGE_PATH + LoginUtils.getInstance().getScoId()
                + StreetApiConstants.LANGUAGE_PATH + LanguageSp.getInstance().getCode()
                + StreetApiConstants.CURRENCY_PATH + CurrencySp.getInstance().getCode();
        mWebContainer.loadUrl(url);
    }

    @Override
    protected void onDestroyViewImpl() {
        super.onDestroyViewImpl();
        EventBus.getDefault().unregister(this);
    }

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
            }
        } else {
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
            }
        }
    }


    @Override
    public boolean onBackPressed() {
        if (mWebContainer != null && mWebContainer.canGoBack()) {
            mWebContainer.goBack();
            return true;
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLogin(MessageLogin message) {
        if (TextUtils.equals(message.message, MessageLogin.LOGIN_IN)) {
            if (mWebContainer.getVisibility() == View.GONE) {
                mWebContainer.setVisibility(View.VISIBLE);
            }
            showWeb();
        } else if (TextUtils.equals(message.message, MessageLogin.LOGIN_OUT)) {
            mWebContainer.clearHistory();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeCurrency(MessageLocal message) {
        if (TextUtils.equals(message.message, MessageLocal.CURRENCY_CHANGE)) {
            showWeb();
        } else if (TextUtils.equals(message.message, MessageLocal.LANGUAGE_CHANGE)) {
            showWeb();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addShopSuccess(MessageShop message) {
        if (TextUtils.equals(message.message, MessageShop.ADD_SHOP_SUCCESS)) {
            mWebContainer.reload();
        } else if (TextUtils.equals(message.message, MessageShop.CART_NEED_REFRESH)) {
            mWebContainer.reload();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goHome(MessageGoHome message) {
        if (TextUtils.equals(message.message, MessageGoHome.GO_HOME)) {
            ((StreetHomeActivity) getActivity()).setSelect(0);
        }
    }
}