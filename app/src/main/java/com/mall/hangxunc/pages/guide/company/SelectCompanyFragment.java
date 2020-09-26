package com.mall.hangxunc.pages.guide.company;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.event.GuideBackEvent;
import com.mall.hangxunc.bean.event.GuideJumpEvent;
import com.mall.hangxunc.bean.guide.IndustryBean;
import com.mall.hangxunc.bean.guide.IndustyData;
import com.mall.hangxunc.bean.guide.IndustyModule;
import com.mall.hangxunc.bean.guide.InterestBean;
import com.mall.hangxunc.bean.home.BaseBean;
import com.mall.hangxunc.http.CustomStyleBody;
import com.mall.hangxunc.http.HangXunBiz;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.loading.LoadingView;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.center.http.CenterBiz;
import com.mall.hangxunc.pages.guide.GuideActivity;
import com.mall.hangxunc.utils.CommSharedUtil;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.HangLog;
import com.mall.hangxunc.utils.LoginUtils;
import com.mall.hangxunc.utils.WindowUtils;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

public class SelectCompanyFragment extends BaseFragment<SelectCompanyPresenter> {

    private static final String TAG = SelectCompanyFragment.class.getSimpleName();
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
    @BindView(R.id.edit_company)
    EditText edit_company;
//    @BindView(R.id.select_industry)
//    TextView selectIndustry;

    private SelectCompanyPresenter mCompanyPresenter;
    private MyAdapter mAdapter;

    @Override
    protected SelectCompanyPresenter onCreateTopPresenter() {
        mCompanyPresenter = new SelectCompanyPresenter(getActivity());
        return mCompanyPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mall_fragment_guide_select_company, null);
        ButterKnife.bind(this, view);
        initView();
        getData();
        return view;
    }

    private void getData() {
        showLoading();

        CenterBiz.getInstance().getAllIndusty(new ResponseListener<IndustyModule>() {
            @Override
            public void onFail(int code, String message) {
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(IndustyModule module) {
                loading.setVisibility(View.GONE);
                if (module == null || module.getCode() != 0) {
                    return;
                }
                IndustyData data = module.getData();
                if (data == null || data.getIndustry() == null || data.getIndustry().size() == 0) {
                    return;
                }
                hideLoading();
                List<IndustryBean> industry = data.getIndustry();
                mAdapter = new MyAdapter(getContext());
                interestRecyclerView.setAdapter(mAdapter);
                mAdapter.setNewData(industry);
                initListener();
            }
        });
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
                EventBus.getDefault().post(new GuideBackEvent(2));
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
                List<IndustryBean> data = mAdapter.getData();

                IndustryBean bean = data.get(position);
                boolean select = bean.isSelect();
                bean.setSelect(!select);
                mAdapter.replaceData(data);
            }
        });
    }

    private void startEvent() {
        loading.setVisibility(View.VISIBLE);

        String companyStr = edit_company.getText().toString();
        if (TextUtils.isEmpty(companyStr)) {
            Toast.makeText(getActivity(), "请填写公司名称", Toast.LENGTH_SHORT).show();
            return;
        }


        List<IndustryBean> data = mAdapter.getData();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < data.size(); i++) {
            IndustryBean bean = data.get(i);
            if (bean != null && bean.isSelect()) {
                buffer.append(bean.getName()).append(",");
            }
        }


        GuideActivity activity = (GuideActivity) getActivity();
        if (LoginUtils.getInstance().isLogin()) {
            HangXunBiz.getInstance().setCustomerStyle(activity.mSelectSex, activity.mSelectAge, activity.mInterest,
                    buffer.toString(), companyStr, new ResponseListener<BaseBean>() {
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
            CustomStyleBody body = new CustomStyleBody(activity.mSelectSex, activity.mSelectAge, activity.mInterest, buffer.toString(), companyStr);
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

    private class MyAdapter extends BaseQuickAdapter<IndustryBean, BaseViewHolder> {

        private Context mContext;

        public MyAdapter(Context context) {
            super(R.layout.mall_adapter_guide_interest);
            mContext = context;
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, IndustryBean item) {
            baseViewHolder.setText(R.id.interestItem_name, item.getName())
                    .setVisible(R.id.interestItem_select, item.isSelect());
            ImageView imageView = baseViewHolder.getView(R.id.interestItem_img);

            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
            int padding = DimenUtils.dip2px(10);
            int width = (windowWidth - 2 * padding - 6 * padding) / 3;
            params.height = 2 * width / 3;
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
                    .load(item.getImgPath())
                    .apply(options)
                    .into(imageView);

        }
    }
}
