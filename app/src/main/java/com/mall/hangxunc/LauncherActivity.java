package com.mall.hangxunc;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mall.hangxunc.utils.CommSharedUtil;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.LoginUtils;

public class LauncherActivity extends AppCompatActivity {

    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        setContentView(R.layout.activity_launcher);

        mTimer = new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                boolean isFirst = CommSharedUtil.getInstance(LauncherActivity.this).getBoolean(CommSharedUtil.FIRST_ENTER_KEY, true);
                if (isFirst && LoginUtils.getInstance().isLogin()) {
                    JumpUtils.goGuideActivity(LauncherActivity.this);
                    CommSharedUtil.getInstance(LauncherActivity.this).putBoolean(CommSharedUtil.FIRST_ENTER_KEY, false);
                } else {
                    JumpUtils.goMain(LauncherActivity.this);
                }
                finish();
            }
        };
        mTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        mTimer = null;
    }
}
