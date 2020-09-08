package com.mall.hangxunc.pages.center;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mall.hangxunc.R;
import com.mall.hangxunc.message.MessageHome;

import org.greenrobot.eventbus.EventBus;

public class CenterHomeTabListener extends TabLayout.ViewPagerOnTabSelectedListener {
    ViewPager viewPager;

    public CenterHomeTabListener(ViewPager viewPager) {
        super(viewPager);
        this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        super.onTabSelected(tab);
        View view = tab.getCustomView();
        ImageView img = (ImageView) view.findViewById(R.id.tab_icon);
        TextView text = (TextView) view.findViewById(R.id.tab_text);
        if (img != null) {
            img.setEnabled(true);
        }
        if (text != null) {
            text.setTextColor(view.getContext().getResources().getColor(R.color.center_main_blue_text));
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        super.onTabUnselected(tab);
        View view = tab.getCustomView();
        ImageView img = (ImageView) view.findViewById(R.id.tab_icon);
        TextView text = (TextView) view.findViewById(R.id.tab_text);
        if (img != null) {
            img.setEnabled(false);
        }
        if (text != null) {
            text.setTextColor(view.getContext().getResources().getColor(R.color.center_home_tab_text_un_select));
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        super.onTabReselected(tab);
        int position = tab.getPosition();
        if (position == 0) {
            publishContent();
        }
    }

    private void publishContent() {
        String msg = "show home";
        EventBus.getDefault().post(MessageHome.getInstance(msg));
    }
}
