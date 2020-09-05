package com.mall.hangxunc.pages.type;

import android.content.Context;
import android.view.ViewGroup;

import com.mall.hangxunc.mvp.BaseComponent;


public class TypeComponent extends BaseComponent<TypeFragment, TypePresenter> {
    @Override
    protected void bind(TypeFragment mView, TypePresenter mPresenter) {

    }

    @Override
    protected TypePresenter onCreatePresenter(Context context, ViewGroup container) {
        return new TypePresenter(context);
    }

    @Override
    protected TypeFragment onCreateView(Context context, ViewGroup container) {
        return new TypeFragment();
    }
}
