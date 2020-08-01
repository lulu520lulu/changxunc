package com.c.hangxunc.web;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.c.hangxunc.R;
import com.c.hangxunc.BaseActivity;
import com.c.hangxunc.HandXunApplication;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.utils.Constants;
import com.c.hangxunc.utils.DimenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class HybridActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.container)
    HangXunWebView mWebContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public static void openWeb(String urlPath) {
        if (TextUtils.isEmpty(urlPath)) {
            return;
        }
        Intent intent = new Intent(HandXunApplication.getInstance(), HybridActivity.class);
        intent.putExtra(Constants.WEB_URL_KEY, urlPath);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        HandXunApplication.getInstance().startActivity(intent);
    }

    private void initView() {
        expandTouchArea(back, DimenUtils.dip2px(20));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void expandTouchArea(View view, int size) {
        View parentView = (View) view.getParent();
        parentView.post(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                view.getHitRect(rect);

                rect.top -= size;
                rect.bottom += size;
                rect.left -= size;
                rect.right += size;

                parentView.setTouchDelegate(new TouchDelegate(rect, view));
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra(Constants.WEB_URL_KEY);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mWebContainer.setHangWebCallBack(new HangXunWebView.HangWebCallBack() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (TextUtils.equals(ApiConstants.RULE_PATH, url)) {
                    titleText.setText(R.string.register_rule);
                    return;
                }
                if (!TextUtils.isEmpty(title)) {
                    titleText.setText(title);
                }
            }
        });
        mWebContainer.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebContainer.canGoBack()) {
            mWebContainer.goBack();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

}
