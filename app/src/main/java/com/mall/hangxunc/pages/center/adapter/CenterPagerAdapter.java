package com.mall.hangxunc.pages.center.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.CenterHomeFragment;
import com.mall.hangxunc.pages.center.web.CenterMeFragment;
import com.mall.hangxunc.pages.center.web.CenterMessageFragment;
import com.mall.hangxunc.pages.center.web.CenterSearchFragment;

public class CenterPagerAdapter extends FragmentPagerAdapter {

    private String[] mTabs;
    private Context mContext;

    public CenterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CenterPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        mTabs = context.getResources().getStringArray(R.array.center_main_tab);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CenterHomeFragment();
            case 1:
                return new CenterSearchFragment();
            case 2:
                return new CenterMessageFragment();
            case 3:
                return new CenterMeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}
