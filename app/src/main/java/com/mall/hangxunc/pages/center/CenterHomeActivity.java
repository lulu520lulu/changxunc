package com.mall.hangxunc.pages.center;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mall.hangxunc.BaseActivity;
import com.mall.hangxunc.HangXunApplication;
import com.mall.hangxunc.R;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.BackHandledInterface;
import com.mall.hangxunc.pages.MainActivity;
import com.mall.hangxunc.pages.center.adapter.CenterPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CenterHomeActivity extends BaseActivity implements BackHandledInterface {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.sliding_tabs)
    TabLayout mSlidingTabs;

    private CenterPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.center_activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        int[] footer = {R.drawable.center_footer_home_selector,
                R.drawable.center_footer_search_selector,
                R.drawable.center_footer_message_selector,
                R.drawable.center_footer_me_selector};
        mAdapter = new CenterPagerAdapter(this, getSupportFragmentManager());
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = View.inflate(this, R.layout.center_home_tab_view, null);
            ImageView img = (ImageView) view.findViewById(R.id.tab_icon);
            TextView text = (TextView) view.findViewById(R.id.tab_text);
            img.setImageResource(footer[i]);
            text.setText(mAdapter.getPageTitle(i));
            if (i == 0) {
                text.setTextColor(getResources().getColor(R.color.center_home_tab_text_selected));
            }
            mSlidingTabs.addTab(mSlidingTabs.newTab().setCustomView(view));
        }
        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mSlidingTabs));

        mSlidingTabs.setOnTabSelectedListener(new CenterHomeTabListener(mViewpager));
        mViewpager.setOffscreenPageLimit(4);

        setSelect(HangXunApplication.getInstance().MAIN_SELECT_ITEM);
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
    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void goLogin(MessageGoLogin message) {
//        if (TextUtils.equals(message.message, MessageGoLogin.GO_LOGIN)) {
//            setSelect(4);
//        }
//    }

}

//    private void init() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.container, new CenterHomeFragment())
//                .commit();
//    }
//}
