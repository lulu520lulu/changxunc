package com.mall.hangxunc.pages.guide.interest;

import android.app.Activity;
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
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.event.GuideBackEvent;
import com.mall.hangxunc.bean.event.GuideJumpEvent;
import com.mall.hangxunc.bean.guide.CustomStyleBean;
import com.mall.hangxunc.bean.guide.InterestBean;
import com.mall.hangxunc.bean.home.BaseBean;
import com.mall.hangxunc.http.CustomStyleBody;
import com.mall.hangxunc.http.HangXunBiz;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.loading.LoadingView;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.guide.GuideActivity;
import com.mall.hangxunc.utils.CommSharedUtil;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.HangLog;
import com.mall.hangxunc.utils.LoginUtils;
import com.mall.hangxunc.utils.WindowUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

/**
 * 选择兴趣
 * Created by 王新超 on 2020/9/5.
 */
public class SelectInterestFragment extends BaseFragment<SelectInterestPresenter> {

    private static final String TAG = SelectInterestFragment.class.getSimpleName();
    @BindView(R.id.selectInterest_interest)
    RecyclerView interestRecyclerView;
    @BindView(R.id.selectInterest_back)
    ImageView backView;
    @BindView(R.id.selectInterest_jump)
    TextView jumpView;
    @BindView(R.id.selectInterest_start)
    ImageView startView;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.loading)
    LoadingView loading;

    private SelectInterestPresenter mInterestPresenter;
    private MyAdapter mAdapter;

    @Override
    protected SelectInterestPresenter onCreateTopPresenter() {
        mInterestPresenter = new SelectInterestPresenter(getActivity());
        return mInterestPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mall_fragment_guide_select_interest, null);
        ButterKnife.bind(this, view);
        initView();
        getData();
        return view;
    }

    private void getData() {
        Bundle bundle = getArguments();
        Serializable styleBean = bundle.getSerializable("CustomStyleBean");
        if (styleBean instanceof CustomStyleBean) {
            CustomStyleBean bean = (CustomStyleBean) styleBean;
            mAdapter = new MyAdapter(getContext());
            interestRecyclerView.setAdapter(mAdapter);
            List<InterestBean> interest = bean.getInterest();
            mAdapter.setNewData(interest);
            initListener();
        }
    }

    private void initView() {
        interestRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        expandTouchArea(backView, DimenUtils.dip2px(20));
        expandTouchArea(jumpView, DimenUtils.dip2px(20));
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

    private void initListener() {
        //返回
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GuideBackEvent());
            }
        });

        //跳过
        jumpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GuideJumpEvent());
            }
        });

        //开启品质生活
        startView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEvent();
            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                List<InterestBean> data = mAdapter.getData();

                InterestBean bean = data.get(position);
                boolean select = bean.isSelect();
                bean.setSelect(!select);
                mAdapter.replaceData(data);
            }
        });
    }

    private void startEvent() {
        loading.setVisibility(View.VISIBLE);

        List<InterestBean> data = mAdapter.getData();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < data.size(); i++) {
            InterestBean bean = data.get(i);
            if (bean != null && bean.isSelect()) {
                buffer.append(bean.getName()).append(",");
            }
        }
        GuideActivity activity = (GuideActivity) getActivity();
        if (LoginUtils.getInstance().isLogin()) {
            HangXunBiz.getInstance().setCustomerStyle(activity.mSelectSex, activity.mSelectAge, buffer.toString(), new ResponseListener<BaseBean>() {
                @Override
                public void onFail(int code, String message) {
                    handleResult();
                }

                @Override
                public void onSuccess(BaseBean baseBean) {
                    handleResult();
                }
            });
        } else {
            CustomStyleBody body = new CustomStyleBody(activity.mSelectSex, activity.mSelectAge, buffer.toString());
            CommSharedUtil.getInstance(getActivity()).saveBean2Sp("CustomStyleBody", body);
            handleResult();
        }

    }


    private void showLoading() {
        HangLog.d(TAG, "showLoading ");
        loading.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    private void hideLoading() {
        HangLog.d(TAG, "showLoading ");
        loading.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    private void handleResult() {
        loading.setVisibility(View.GONE);

        EventBus.getDefault().post(new GuideJumpEvent());
    }

    private class MyAdapter extends BaseQuickAdapter<InterestBean, BaseViewHolder> {

        private Context mContext;

        public MyAdapter(Context context) {
            super(R.layout.mall_adapter_guide_interest);
            mContext = context;
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, InterestBean item) {
            baseViewHolder.setText(R.id.interestItem_name, item.getName())
                    .setVisible(R.id.interestItem_select, item.isSelect());
            ImageView imageView = baseViewHolder.getView(R.id.interestItem_img);

            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
            int padding = DimenUtils.dip2px(10);
            int width = (windowWidth - 2 * padding - 6 * padding) / 3;
            params.height = width;
            params.width = width;

            Transformation transformation;
            if (item.isSelect()) {
                transformation = new ColorFilterTransformation(mContext.getResources().getColor(R.color.black_60));
            } else {
                transformation = new RoundedCorners(1);
            }
            RequestOptions options = new RequestOptions()
                    .error(R.mipmap.place_image)
                    .placeholder(R.mipmap.place_image)
                    .bitmapTransform(transformation);
            Glide.with(mContext)
                    .load(item.getImage())
                    .apply(options)
                    .into(imageView);

        }
    }
}
