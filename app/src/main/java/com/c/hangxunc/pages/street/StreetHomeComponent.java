package com.c.hangxunc.pages.street;

import android.content.Context;
import android.view.ViewGroup;

import com.c.hangxunc.mvp.BaseComponent;


public class StreetHomeComponent extends BaseComponent<StreetHomeFragment, StreetHomePresenter> {

    @Override
    protected void bind(StreetHomeFragment mView, StreetHomePresenter mPresenter) {

    }

    @Override
    protected StreetHomePresenter onCreatePresenter(Context context, ViewGroup container) {
        return new StreetHomePresenter(context);
    }

    @Override
    protected StreetHomeFragment onCreateView(Context context, ViewGroup container) {
        return new StreetHomeFragment();
    }
}
