package com.c.hangxunc.pages;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.c.hangxunc.R;
import com.c.hangxunc.pages.home.MallHomeFragment;
import com.c.hangxunc.pages.person.PersonalFragment;
import com.c.hangxunc.pages.search.SearchFragment;
import com.c.hangxunc.pages.shop.ShopCartFragment;
import com.c.hangxunc.pages.type.TypeFragment;


public class HomePagerAdapter extends FragmentPagerAdapter {

    private String[] mTabs;
    private Context mContext;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        mTabs = context.getResources().getStringArray(R.array.main_tab);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MallHomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new TypeFragment();
            case 3:
                return new ShopCartFragment();
            case 4:
                return new PersonalFragment();
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
