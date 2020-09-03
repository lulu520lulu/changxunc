package com.c.hangxunc.pages.home;

import android.content.Context;
import android.view.ViewGroup;

import com.c.hangxunc.mvp.BaseComponent;


public class MallHomeComponent extends BaseComponent<MallHomeFragment, MallHomePresenter> {

    @Override
    protected void bind(MallHomeFragment mView, MallHomePresenter mPresenter) {

    }

    @Override
    protected MallHomePresenter onCreatePresenter(Context context, ViewGroup container) {
        return new MallHomePresenter(context);
    }

    @Override
    protected MallHomeFragment onCreateView(Context context, ViewGroup container) {
        return new MallHomeFragment();
    }
}
