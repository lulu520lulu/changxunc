package com.c.hangxunc.pages;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.c.hangxunc.HangXunApplication;
import com.c.hangxunc.MessageGoLogin;
import com.c.hangxunc.R;
import com.c.hangxunc.BaseActivity;
import com.c.hangxunc.bean.home.IsLoginBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.utils.LoginUtils;
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
        getLoginState();
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
        int[] footer = {R.drawable.footer_home_selector, R.drawable.footer_search_selector,
                R.drawable.footer_type_selector,
                R.drawable.footer_shopingcart_selector,
                R.drawable.footer_personcenter_selector};
        mAdapter = new HomePagerAdapter(this, getSupportFragmentManager());
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = View.inflate(this, R.layout.home_tab_view, null);
            ImageView img = (ImageView) view.findViewById(R.id.tab_icon);
            TextView text = (TextView) view.findViewById(R.id.tab_text);
            img.setImageResource(footer[i]);
            text.setText(mAdapter.getPageTitle(i));
            if (i == 0) {
                text.setTextColor(getResources().getColor(R.color.main_red_text));
            }
            mSlidingTabs.addTab(mSlidingTabs.newTab().setCustomView(view));
        }
        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mSlidingTabs));

        mSlidingTabs.setOnTabSelectedListener(new HomeTabListener(mViewpager));
        mViewpager.setOffscreenPageLimit(5);

        setSelect( HangXunApplication.getInstance().MAIN_SELECT_ITEM);
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