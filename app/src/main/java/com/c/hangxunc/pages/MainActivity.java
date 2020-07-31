package com.c.hangxunc.pages;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.c.hangxunc.R;
import com.c.hangxunc.BaseActivity;
import com.c.hangxunc.bean.home.IsLoginBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.utils.LoginUtils;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.sliding_tabs)
    TabLayout mSlidingTabs;

    private long exitTime = 0;
    private HomePagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
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
                text.setTextColor(getResources().getColor(R.color.home_tab_text_selected));
            }
            mSlidingTabs.addTab(mSlidingTabs.newTab().setCustomView(view));
        }
        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mSlidingTabs));

        mSlidingTabs.setOnTabSelectedListener(new HomeTabListener(mViewpager));
        mViewpager.setOffscreenPageLimit(5);
        mSlidingTabs.getTabAt(0).select();

    }

    public void setSelect(int position) {
        if (mViewpager != null) {
            mViewpager.setCurrentItem(position);
        }
    }

}
