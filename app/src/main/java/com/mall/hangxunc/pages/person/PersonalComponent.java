package com.mall.hangxunc.pages.person;

import android.content.Context;
import android.view.ViewGroup;

import com.mall.hangxunc.mvp.BaseComponent;


public class PersonalComponent extends BaseComponent<PersonalFragment,PersonalPresenter> {
    @Override
    protected void bind(PersonalFragment mView, PersonalPresenter mPresenter) {

    }

    @Override
    protected PersonalPresenter onCreatePresenter(Context context, ViewGroup container) {
        return new PersonalPresenter(context);
    }

    @Override
    protected PersonalFragment onCreateView(Context context, ViewGroup container) {
        return new PersonalFragment();
    }
}
