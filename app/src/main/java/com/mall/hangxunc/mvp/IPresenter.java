package com.mall.hangxunc.mvp;

import android.content.Context;

public abstract class IPresenter<V extends IView> {

    protected Context mContext;
    public V mView;

    public IPresenter(Context context) {
        this.mContext = context;
    }

    public void setView(V view) {
        this.mView = view;
    }

    public void onPageCreate() {

    }
    public void onPageStart() {

    }
    public void onPageResume() {

    }
    public void onPagePause() {

    }

    public void onPageStop() {

    }
    public void onPageDestroy() {

    }
}
