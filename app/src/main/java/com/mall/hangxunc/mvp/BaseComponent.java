package com.mall.hangxunc.mvp;

import android.content.Context;
import android.view.ViewGroup;

public abstract class BaseComponent<V extends IView, P extends IPresenter> implements IComponent {

    private V mView;
    private P mPresenter;

    @Override
    public void init(Context context, ViewGroup container) {
        mView = onCreateView(context, container);
        mPresenter = onCreatePresenter(context, container);
        bind(mView, mPresenter);
    }

    protected abstract void bind(V mView, P mPresenter);

    protected abstract P onCreatePresenter(Context context, ViewGroup container);

    protected abstract V onCreateView(Context context, ViewGroup container);

    @Override
    public IView getView() {
        return mView;
    }

    @Override
    public IPresenter getPresenter() {
        return mPresenter;
    }
}
