package com.c.hangxunc.pages.guide;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.c.hangxunc.BaseActivity;
import com.c.hangxunc.R;
import com.c.hangxunc.bean.event.GuideBackEvent;
import com.c.hangxunc.bean.event.GuideJumpEvent;
import com.c.hangxunc.bean.event.GuideNextEvent;
import com.c.hangxunc.bean.guide.CustomStyleBean;
import com.c.hangxunc.bean.guide.CustomStyleData;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.loading.LoadingView;
import com.c.hangxunc.pages.guide.interest.SelectInterestFragment;
import com.c.hangxunc.pages.guide.sex.SelectSexFragment;
import com.c.hangxunc.utils.HangLog;
import com.c.hangxunc.utils.JumpUtils;
import com.c.hangxunc.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 王新超 on 2020/9/5.
 */
public class GuideActivity extends BaseActivity {
    private static final String TAG = GuideActivity.class.getSimpleName();
    @BindView(R.id.guide_viewpager)
    NoScrollViewPager mViewpager;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.loading)
    LoadingView loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mall_activity_guide);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getData();
    }

    private void showLoading() {
        HangLog.d(TAG, "showLoading ");
        loading.setVisibility(View.VISIBLE);
        mViewpager.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    private void showSuccess() {
        HangLog.d(TAG, "showLoading ");
        loading.setVisibility(View.GONE);
        mViewpager.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    private void showEmpty() {
        HangLog.d(TAG, "showLoading ");
        loading.setVisibility(View.GONE);
        mViewpager.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    private void getData() {
        showLoading();
        HangXunBiz.getInstance().getCustomerStyle(new ResponseListener<CustomStyleData>() {
            @Override
            public void onFail(int code, String message) {
                handleFail();
            }

            @Override
            public void onSuccess(CustomStyleData data) {
                if (data == null || data.getCode() != 0 || data.getData() == null) {
                    handleFail();
                    return;
                }
                showSuccess();
                CustomStyleBean bean = data.getData();
                showView(bean);
            }
        });
    }

    private void handleFail() {
        showEmpty();
    }

    private void showView(CustomStyleBean bean) {
        if (bean == null) {
            return;
        }
        List<Fragment> fragments = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("CustomStyleBean", bean);

        SelectSexFragment sexFragment = new SelectSexFragment();
        sexFragment.setArguments(bundle);

        SelectInterestFragment fragment = new SelectInterestFragment();
        fragment.setArguments(bundle);

        fragments.add(sexFragment);
        fragments.add(fragment);

        mViewpager.setAdapter(new GuidePageAdapter(getSupportFragmentManager(), fragments));
    }

    public String mSelectAge;
    public String mSelectSex;

    @Subscribe
    public void onEvent(Object object) {
        //选择性别页面 点击下一步
        if (object instanceof GuideNextEvent) {
            GuideNextEvent nextEvent = (GuideNextEvent) object;
            mSelectAge = nextEvent.getSelectAge();
            mSelectSex = nextEvent.getSelectSex();
            mViewpager.setCurrentItem(1);
            //点击跳过
        } else if (object instanceof GuideJumpEvent) {
            JumpUtils.goMain(this);
            finish();
        } else if (object instanceof GuideBackEvent) {
            mViewpager.setCurrentItem(0);
        }
    }

    static class GuidePageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public GuidePageAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}


