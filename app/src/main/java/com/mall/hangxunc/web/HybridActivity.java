package com.mall.hangxunc.web;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mall.hangxunc.message.MessageGoLogin;
import com.mall.hangxunc.R;
import com.mall.hangxunc.BaseActivity;
import com.mall.hangxunc.HangXunApplication;
import com.mall.hangxunc.http.ApiConstants;
import com.mall.hangxunc.message.MessageShop;
import com.mall.hangxunc.utils.Constants;
import com.mall.hangxunc.utils.DimenUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    public static void openWeb(String urlPath) {
        if (TextUtils.isEmpty(urlPath)) {
            return;
        }
        Intent intent = new Intent(HangXunApplication.getInstance(), HybridActivity.class);
        intent.putExtra(Constants.WEB_URL_KEY, urlPath);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        HangXunApplication.getInstance().startActivity(intent);
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

    private String mUrl;

    private void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constants.WEB_URL_KEY);
        if (TextUtils.isEmpty(mUrl)) {
            return;
        }
        HangXunApplication.getInstance().mHybridUrl = mUrl;
        mUrl = mUrl + "&app=app";
        mWebContainer.setHangWebCallBack(new HangXunWebView.HangWebCallBack() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (TextUtils.equals(ApiConstants.RULE_PATH, mUrl)) {
                    titleText.setText(R.string.register_rule);
                    return;
                }
                if (!TextUtils.isEmpty(title)) {
                    titleText.setText(title);
                }
            }
        });
        mWebContainer.loadUrl(mUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebContainer.canGoBack()) {
                mWebContainer.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebContainer != null) {
            mWebContainer.destroyView();
        }
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().post(MessageShop.getInstance(MessageShop.ADD_SHOP_SUCCESS));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goLogin(MessageGoLogin message) {
        if (TextUtils.equals(message.message, MessageGoLogin.GO_LOGIN)) {
            finish();
        }
    }

}
