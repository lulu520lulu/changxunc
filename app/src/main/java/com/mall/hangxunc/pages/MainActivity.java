package com.mall.hangxunc.pages;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.mall.hangxunc.HangXunApplication;
import com.mall.hangxunc.message.MessageGoLogin;
import com.mall.hangxunc.R;
import com.mall.hangxunc.BaseActivity;
import com.mall.hangxunc.bean.home.IsLoginBean;
import com.mall.hangxunc.http.HangXunBiz;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.utils.LoginUtils;
import com.google.android.material.tabs.TabLayout;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BackHandledInterface {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.sliding_tabs)
    TabLayout mSlidingTabs;

    private HomePagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main_layout);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initData() {
        if (LoginUtils.getInstance().isLogin()) {
            getLoginState();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int show_page = intent.getIntExtra("login_out_show_page", 0);
        if (show_page == 4) {
            setSelect(4);
        }
    }

    private void getLoginState() {
        HangXunBiz.getInstance().isCustomerLogin(new ResponseListener<IsLoginBean>() {
            @Override
            public void onFail(int code, String message) {

            }

            @Override
            public void onSuccess(IsLoginBean bean) {
                if (bean == null) {
                    return;
                }
                if (bean.getCode() == 0) {
                    //0代表登录
                } else {
                    LoginUtils.getInstance().loginOut();
                }
            }
        });
    }


    private void initView() {
        int[] footer = {R.drawable.mall_footer_home_selector, R.drawable.mall_footer_search_selector,
                R.drawable.mall_footer_type_selector,
                R.drawable.mall_footer_shopingcart_selector,
                R.drawable.mall_footer_personcenter_selector};
        mAdapter = new HomePagerAdapter(this, getSupportFragmentManager());
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = View.inflate(this, R.layout.mall_home_tab_view, null);
            ImageView img = (ImageView) view.findViewById(R.id.tab_icon);
            TextView text = (TextView) view.findViewById(R.id.tab_text);
            img.setImageResource(footer[i]);
            text.setText(mAdapter.getPageTitle(i));
            if (i == 0) {
                text.setTextColor(getResources().getColor(R.color.mall_main_red_text));
            }
            mSlidingTabs.addTab(mSlidingTabs.newTab().setCustomView(view));
        }
        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mSlidingTabs));

        mSlidingTabs.setOnTabSelectedListener(new HomeTabListener(mViewpager));
        mViewpager.setOffscreenPageLimit(5);

        Intent intent = getIntent();
        int show_page = intent.getIntExtra("login_out_show_page", 0);
        if (show_page == 4) {
            setSelect(4);
        } else {
            setSelect(HangXunApplication.getInstance().MAIN_SELECT_ITEM);
        }
    }

    public void setSelect(int position) {
        if (mViewpager != null) {
            mViewpager.setCurrentItem(position);
        }
    }

    private BaseFragment mBackHandedFragment;

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBackHandedFragment != null) {
                if (mBackHandedFragment.onBackPressed()) {
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mViewpager != null) {
            HangXunApplication.getInstance().MAIN_SELECT_ITEM = mViewpager.getCurrentItem();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goLogin(MessageGoLogin message) {
        if (TextUtils.equals(message.message, MessageGoLogin.GO_LOGIN)) {
            setSelect(4);
        }
    }

}