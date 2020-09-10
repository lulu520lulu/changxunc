package com.mall.hangxunc.pages.center.web;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mall.hangxunc.R;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.message.MessageLogin;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.center.http.CenterApiConstants;
import com.mall.hangxunc.utils.HangLog;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.LanguageSp;
import com.mall.hangxunc.utils.LoginUtils;
import com.mall.hangxunc.web.HangXunWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class CenterSearchFragment extends BaseFragment<CenterSearchPresenter> {

    private static final String TAG = CenterSearchFragment.class.getSimpleName();

    private CenterSearchPresenter mShopCarPresenter;
    private HangXunWebView mWebContainer;

    @Override
    protected CenterSearchPresenter onCreateTopPresenter() {
        mShopCarPresenter = new CenterSearchPresenter(getActivity());
        return mShopCarPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cneter_fragment_web, null);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mWebContainer = view.findViewById(R.id.web_container);
        mWebContainer.setVisibility(View.VISIBLE);
        showWeb();

    }

    private void showWeb() {
        String url = CenterApiConstants.BASE_URL + CenterApiConstants.HOME_SEARCH + LoginUtils.getInstance().getScoId()
                + CenterApiConstants.LANGUAGE_PATH + LanguageSp.getInstance().getCode()
                + CenterApiConstants.APP;
        HangLog.d(TAG, "url : " + url);
        mWebContainer.loadUrl(url);
    }

    @Override
    protected void onDestroyViewImpl() {
        super.onDestroyViewImpl();
        EventBus.getDefault().unregister(this);
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
    public void changeCurrency(MessageLocal message) {
        if (TextUtils.equals(message.message, MessageLocal.CURRENCY_CHANGE)) {
            showWeb();
        } else if (TextUtils.equals(message.message, MessageLocal.LANGUAGE_CHANGE)) {
            showWeb();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLogin(MessageLogin message) {
//        if (TextUtils.equals(message.message, MessageLogin.LOGIN_IN)) {
//            loginChangeView(true);
//        } else if (TextUtils.equals(message.message, MessageLogin.LOGIN_OUT)) {
//            loginChangeView(false);
//        }
    }

//    private void loginChangeView(boolean b) {
//        if (!b) {
//            JumpUtils.goMallLogin(getActivity());
//        }
//    }
}