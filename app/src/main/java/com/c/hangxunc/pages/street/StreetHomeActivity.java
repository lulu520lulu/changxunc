package com.c.hangxunc.pages.street;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.c.hangxunc.BaseActivity;
import com.c.hangxunc.R;

public class StreetHomeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.street_activity_home);
        init();

    }

    private void init() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new StreetHomeFragment())
                .commit();
    }
}
