package com.c.hangxunc.pages.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c.hangxunc.R;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.web.HangXunWebView;

public class ForgetPassFragmentWeb extends BaseFragment<ForgetPassPresenterWeb> {

    private ForgetPassPresenterWeb mForgetPassPresenterWeb;
    private HangXunWebView webContainer;


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

    private void initView(View view) {
        webContainer = view.findViewById(R.id.container);
    }

    private void initData() {
        String url = ApiConstants.BASE_URL + ApiConstants.FORGOTTEN_PAGE_PATH;
        if (TextUtils.isEmpty(url)) {
            return;
        }
        webContainer.loadUrl(url);
    }

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
