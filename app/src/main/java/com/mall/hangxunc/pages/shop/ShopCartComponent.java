package com.mall.hangxunc.pages.shop;

import android.content.Context;
import android.view.ViewGroup;

import com.mall.hangxunc.mvp.BaseComponent;


public class ShopCartComponent extends BaseComponent<ShopCartFragment,ShopCarPresenter> {
    @Override
    protected void bind(ShopCartFragment mView, ShopCarPresenter mPresenter) {

    }

    @Override
    protected ShopCarPresenter onCreatePresenter(Context context, ViewGroup container) {
        return new ShopCarPresenter(context);
    }

    @Override
    protected ShopCartFragment onCreateView(Context context, ViewGroup container) {
        return new ShopCartFragment();
    }
}
