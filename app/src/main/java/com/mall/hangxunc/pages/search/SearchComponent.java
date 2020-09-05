package com.mall.hangxunc.pages.search;

import android.content.Context;
import android.view.ViewGroup;

import com.mall.hangxunc.mvp.BaseComponent;


public class SearchComponent extends BaseComponent<SearchFragment,SearchPresenter> {
    @Override
    protected void bind(SearchFragment mView, SearchPresenter mPresenter) {

    }

    @Override
    protected SearchPresenter onCreatePresenter(Context context, ViewGroup container) {
        return new SearchPresenter(context);
    }

    @Override
    protected SearchFragment onCreateView(Context context, ViewGroup container) {
        return new SearchFragment();
    }
}
