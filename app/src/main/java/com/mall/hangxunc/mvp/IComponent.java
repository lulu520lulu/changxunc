package com.mall.hangxunc.mvp;

import android.content.Context;
import android.view.ViewGroup;

public interface IComponent<V extends IView, P extends IPresenter> {

    void init(Context context, ViewGroup container);

    V getView();

    P getPresenter();
}
