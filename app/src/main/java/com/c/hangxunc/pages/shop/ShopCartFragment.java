package com.c.hangxunc.pages.shop;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c.hangxunc.MessageGoHome;
import com.c.hangxunc.R;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.pages.MainActivity;
import com.c.hangxunc.pages.login.MessageLogin;
import com.c.hangxunc.utils.CurrencySp;
import com.c.hangxunc.utils.LanguageSp;
import com.c.hangxunc.utils.LoginUtils;
import com.c.hangxunc.web.HangXunWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ShopCartFragment extends BaseFragment<ShopCarPresenter> {
    private CountDownTimer mTimer;

    private ShopCarPresenter mShopCarPresenter;
    private HangXunWebView mWebContainer;

    @Override
    protected ShopCarPresenter onCreateTopPresenter() {
        mShopCarPresenter = new ShopCarPresenter(getActivity());
        return mShopCarPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
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
        String url = ApiConstants.BASE_URL + ApiConstants.CART_PAGE_PATH + LoginUtils.getInstance().getCustomerId()
                + ApiConstants.LANGUAGE_PATH + LanguageSp.getInstance().getCode()
                + ApiConstants.CURRENCY_PATH + CurrencySp.getInstance().getCode();
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
        if (TextUtils.equals(message.message, MessageLocal.CHANGE)) {
            showWeb();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addShopSuccess(MessageShop message) {
        if (TextUtils.equals(message.message, MessageShop.ADD_SHOP_SUCCESS)) {
            mWebContainer.reload();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goHome(MessageGoHome message) {
        if (TextUtils.equals(message.message, MessageGoHome.GO_HOME)) {
            ((MainActivity) getActivity()).setSelect(0);
        }
    }
}