package com.c.hangxunc.pages.shop;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c.hangxunc.R;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.pages.MainActivity;
import com.c.hangxunc.utils.LanguageUtils;
import com.c.hangxunc.utils.LoginUtils;
import com.c.hangxunc.web.HangXunWebView;

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
        initView(view);
        return view;
    }

    private void initView(View view) {
        mWebContainer = view.findViewById(R.id.web_container);
        showWeb();
    }


    private void showWeb() {
        String url = ApiConstants.BASE_URL + ApiConstants.CART_PAGE_PATH + LoginUtils.getInstance().getCustomerId()
                + ApiConstants.LANGUAGE_PATH + LanguageUtils.getInstance().getCode();
        if (TextUtils.isEmpty(url)) {
            return;
        }

        mWebContainer.loadUrl(url);
    }


    @Override
    protected void onDestroyViewImpl() {
        super.onDestroyViewImpl();
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
            } else {
                showWeb();
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        }
    }
}