package com.mall.hangxunc.pages.guide.sex;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.event.GuideJumpEvent;
import com.mall.hangxunc.bean.event.GuideSexNextEvent;
import com.mall.hangxunc.bean.guide.CustomStyleBean;
import com.mall.hangxunc.bean.guide.SexBean;
import com.mall.hangxunc.bean.guide.TimeBean;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.utils.DimenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择性别 年代
 * Created by 王新超 on 2020/9/5.
 */
public class SelectSexFragment extends BaseFragment<SelectSexPresenter> {

    @BindView(R.id.selectSex_jump)
    TextView jumpView;
    @BindView(R.id.selectSex_next)
    ImageView nextView;
    @BindView(R.id.selectSex_man)
    LinearLayout manLayout;
    @BindView(R.id.selectSex_woman)
    LinearLayout womanLayout;
    @BindView(R.id.selectSex_year)
    RecyclerView yearRecycleView;
    @BindView(R.id.selectSex_woman_text)
    TextView womanTextView;
    @BindView(R.id.selectSex_man_text)
    TextView manTextView;
    @BindView(R.id.selectSex_man_img)
    ImageView manImageVIew;
    @BindView(R.id.selectSex_woman_img)
    ImageView womanImageView;

    private SelectSexPresenter mSelectSexPresenter;
    private MyAdapter mAdapter;
    private SexBean mSex;

    @Override
    protected SelectSexPresenter onCreateTopPresenter() {
        mSelectSexPresenter = new SelectSexPresenter(getActivity());
        return mSelectSexPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mall_fragment_guide_select_sex, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        yearRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        Bundle bundle = getArguments();
        Serializable styleBean = bundle.getSerializable("CustomStyleBean");
        if (styleBean instanceof CustomStyleBean) {
            CustomStyleBean bean = (CustomStyleBean) styleBean;
            List<TimeBean> times = bean.getTime();
            mAdapter = new MyAdapter(getContext());
            yearRecycleView.setAdapter(mAdapter);
            if (times != null) {
                mSelectAge = times.get(0).getStage();
                mAdapter.setNewData(times);
            }

            initListener();
            SexBean sex = bean.getSex();
            mSex = sex;
            if (sex == null) {
                return;
            }
            SexBean.ManBean man = sex.getMan();
            SexBean.WomanBean woman = sex.getWoman();
            if (man != null) {
                manTextView.setText(man.getMan());
                mSelectSex = man.getMan();
            }
            if (woman != null) {
                womanTextView.setText(woman.getWoman());
            }

        }
    }


    private void initListener() {
        //跳过
        jumpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GuideJumpEvent());
            }
        });
        //下一步
        nextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GuideSexNextEvent(mSelectSex, mSelectAge));
            }
        });
        //选择性别男
        manLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelectMan(true);
            }
        });
        //选择性别女
        womanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelectMan(false);
            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                List<TimeBean> data = mAdapter.getData();

                for (int i = 0; i < data.size(); i++) {
                    TimeBean bean = data.get(i);
                    bean.setSelect(i == position);
                    if (i == position) {
                        mSelectAge = bean.getStage();
                    }
                }
                mAdapter.replaceData(data);
            }
        });
        expandTouchArea(jumpView, DimenUtils.dip2px(20));
    }

    private String mSelectSex = "";
    private String mSelectAge = "";


    private void updateSelectMan(boolean isSelectMan) {
        if (mSex == null || mSex.getMan() == null || mSex.getWoman() == null) {
            return;
        }
        if (isSelectMan) {
            Glide.with(getContext()).load(mSex.getMan().getImagechecked())
                    .placeholder(R.mipmap.guide_man_true)
                    .into(manImageVIew);

            Glide.with(getContext()).load(mSex.getWoman().getImage())
                    .placeholder(R.mipmap.guide_woman_false)
                    .into(womanImageView);

            manTextView.setTextColor(getActivity().getResources().getColor(R.color.mall_main_red_text));
            womanTextView.setTextColor(getActivity().getResources().getColor(R.color.black));
        } else {
            Glide.with(getContext()).load(mSex.getMan().getImage())
                    .placeholder(R.mipmap.guide_man_false)
                    .into(manImageVIew);
            Glide.with(getContext()).load(mSex.getWoman().getImagechecked())
                    .placeholder(R.mipmap.guide_woman_true)
                    .into(womanImageView);

            womanTextView.setTextColor(getActivity().getResources().getColor(R.color.mall_main_red_text));
            manTextView.setTextColor(getActivity().getResources().getColor(R.color.black));
        }
        mSelectSex = isSelectMan ? mSex.getMan().getMan() : mSex.getWoman().getWoman();
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

    private class MyAdapter extends BaseQuickAdapter<TimeBean, BaseViewHolder> {

        private Context mContext;

        public MyAdapter(Context context) {
            super(R.layout.mall_adapter_guide_year);
            mContext = context;
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, TimeBean bean) {
            int white = mContext.getResources().getColor(R.color.white);
            int black = mContext.getResources().getColor(R.color.black);

            baseViewHolder.setText(R.id.yearItem_content, bean.getStage())
                    .setTextColor(R.id.yearItem_content, bean.isSelect() ? white : black)
                    .setBackgroundResource(R.id.yearItem_content, bean.isSelect() ? R.mipmap.guide_year_bg_true : R.drawable.mall_guide_year_bg_false);
        }
    }
}
