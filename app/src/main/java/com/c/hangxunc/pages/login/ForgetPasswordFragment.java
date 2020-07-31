package com.c.hangxunc.pages.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c.hangxunc.R;
import com.c.hangxunc.mvp.BaseFragment;

public class ForgetPasswordFragment extends BaseFragment<ForgetPasswordPresenter> {

    private ForgetPasswordPresenter mForgetPasswordPresenter;

    @Override
    protected ForgetPasswordPresenter onCreateTopPresenter() {
        mForgetPasswordPresenter = new ForgetPasswordPresenter(getActivity());
        return mForgetPasswordPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {

    }

    private void initView(View view) {

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