package com.mall.hangxunc.pages.center;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mall.hangxunc.BaseActivity;
import com.mall.hangxunc.R;


public class CenterHomeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_activity_home);
        init();

    }

    private void init() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new CenterHomeFragment())
                .commit();
    }
}
