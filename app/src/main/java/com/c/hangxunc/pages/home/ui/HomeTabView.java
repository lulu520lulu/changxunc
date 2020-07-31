package com.c.hangxunc.pages.home.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.c.hangxunc.R;

public class HomeTabView extends LinearLayout {

    public HomeTabView(Context context) {
        super(context);
        initView(context);
    }

    public HomeTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }
    private void initView(Context context) {
        inflate(context, R.layout.home_tab_view_layout, this);
    }


}
