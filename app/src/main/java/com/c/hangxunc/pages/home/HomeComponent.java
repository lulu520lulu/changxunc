package com.c.hangxunc.pages.home;

import android.content.Context;
import android.view.ViewGroup;

import com.c.hangxunc.mvp.BaseComponent;


public class HomeComponent extends BaseComponent<HomeFragment, HomePresenter> {

    @Override
    protected void bind(HomeFragment mView, HomePresenter mPresenter) {

    }

    @Override
    protected HomePresenter onCreatePresenter(Context context, ViewGroup container) {
        return new HomePresenter(context);
    }

    @Override
    protected HomeFragment onCreateView(Context context, ViewGroup container) {
        return new HomeFragment();
    }
}
