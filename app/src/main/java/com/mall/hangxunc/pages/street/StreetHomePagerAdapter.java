package com.mall.hangxunc.pages.street;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.street.person.StreetPersonalFragment;
import com.mall.hangxunc.pages.street.search.StreetSearchFragment;
import com.mall.hangxunc.pages.street.shop.StreetShopCartFragment;
import com.mall.hangxunc.pages.street.type.StreetTypeFragment;

public class StreetHomePagerAdapter extends FragmentPagerAdapter {

    private String[] mTabs;
    private Context mContext;

    public StreetHomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public StreetHomePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        mTabs = context.getResources().getStringArray(R.array.main_tab);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StreetHomeFragment();
            case 1:
                return new StreetSearchFragment();
            case 2:
                return new StreetTypeFragment();
            case 3:
                return new StreetShopCartFragment();
            case 4:
                return new StreetPersonalFragment();
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
