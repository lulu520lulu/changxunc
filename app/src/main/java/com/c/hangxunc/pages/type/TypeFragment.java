package com.c.hangxunc.pages.type;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CategoryChildBean;
import com.c.hangxunc.bean.home.CategoryListBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.loading.LoadingView;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.pages.widget.BottomView;
import com.c.hangxunc.utils.HangLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeFragment extends BaseFragment<TypePresenter> {


    private static final String TAG = TypeFragment.class.getSimpleName();
    @BindView(R.id.type_button)
    RadioButton typeButton;
    @BindView(R.id.brand_button)
    RadioButton brandButton;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.type_list)
    RecyclerView typeList;
    @BindView(R.id.brand_list)
    RecyclerView brandList;
    @BindView(R.id.loading)
    LoadingView loadingView;
    @BindView(R.id.empty_view)
    ViewGroup empty_view;
    @BindView(R.id.bottom_view)
    BottomView bottomView;
    @BindView(R.id.empty_tv)
    TextView empty_tv;
    private TypePresenter mTypePresenter;

    private TypeAdapter mTypeAdapter;
    private BrandAdapter mBrandAdapter;

    @Override
    protected TypePresenter onCreateTopPresenter() {
        mTypePresenter = new TypePresenter(getActivity());
        return mTypePresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.type_button) {
                    typeList.setVisibility(View.VISIBLE);
                    brandList.setVisibility(View.GONE);
                    if (mTypeAdapter == null || mTypeAdapter.getItemCount() == 0) {
                        getData();
                    } else {
                        empty_view.setVisibility(View.GONE);

                    }
                } else if (checkedId == R.id.brand_button) {
                    typeList.setVisibility(View.GONE);
                    brandList.setVisibility(View.VISIBLE);
                    if (mBrandAdapter == null || mBrandAdapter.getItemCount() == 0) {
                        getBrandData();
                    } else {
                        empty_view.setVisibility(View.GONE);

                    }
                }
            }
        });
        radioGroup.setVisibility(View.VISIBLE);

        initTypeList();
        initBrandList();
        getData();

    }

    private void showLoading() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.VISIBLE);
        typeList.setVisibility(View.GONE);
        brandList.setVisibility(View.GONE);
        empty_view.setVisibility(View.GONE);
        bottomView.setVisibility(View.GONE);
    }

    private void hideLoading() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.GONE);
        bottomView.setVisibility(View.VISIBLE);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        if (radioButtonId == R.id.type_button) {
            typeList.setVisibility(View.VISIBLE);
            brandList.setVisibility(View.GONE);
        } else if (radioButtonId == R.id.brand_button) {
            typeList.setVisibility(View.GONE);
            brandList.setVisibility(View.VISIBLE);
        }
    }

    private void showEmpty() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.GONE);
        typeList.setVisibility(View.GONE);
        brandList.setVisibility(View.GONE);
        empty_view.setVisibility(View.VISIBLE);
        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.type_button) {
            empty_tv.setText(R.string.type_empty_text);
        } else {
            empty_tv.setText(R.string.brand_empty_text);
        }
        bottomView.setVisibility(View.VISIBLE);
    }


    private void getData() {
        showLoading();
        HangXunBiz.getInstance().getCategoryPage(new ResponseListener<CategoryListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getCategoryPage code: " + code + ",message:" + message);
                showEmpty();
            }

            @Override
            public void onSuccess(CategoryListBean bean) {
                if (bean == null) {
                    showEmpty();
                    return;
                }
                List<CategoryChildBean> categories = bean.getCategories();
                if (categories == null || categories.size() == 0) {
                    showEmpty();
                    return;
                }
                HangLog.d(TAG, "onSuccess getCategoryPage bean: " + bean.toString());
                showTypeSuccess(categories);
            }
        });
    }

    private void getBrandData() {
        showLoading();
        HangXunBiz.getInstance().getManufacturer(new ResponseListener<CategoryListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getManufacturer code: " + code + ",message:" + message);
                showEmpty();
            }

            @Override
            public void onSuccess(CategoryListBean bean) {
                if (bean == null) {
                    showEmpty();
                    return;
                }
                List<CategoryChildBean> categories = bean.getCategories();
                if (categories == null || categories.size() == 0) {
                    showEmpty();
                    return;
                }
                HangLog.d(TAG, "onSuccess getManufacturer bean: " + bean.toString());
                showBrandSuccess(categories);
            }
        });

    }

    private void showBrandSuccess(List<CategoryChildBean> list) {
        hideLoading();
        mBrandAdapter.setData(list);
    }

    private void showTypeSuccess(List<CategoryChildBean> list) {
        hideLoading();
        mTypeAdapter.setData(list);
    }

    private void handleFail() {
        hideLoading();

    }


    private void initBrandList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        brandList.setLayoutManager(manager);
        mBrandAdapter = new BrandAdapter(getActivity());
        brandList.setAdapter(mBrandAdapter);
    }

    private void initTypeList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        typeList.setLayoutManager(manager);
        mTypeAdapter = new TypeAdapter(getActivity());
        typeList.setAdapter(mTypeAdapter);
    }

    @Override
    protected void onResumeImpl() {
        super.onResumeImpl();
    }

    @Override
    protected void onPauseImpl() {
        super.onPauseImpl();
    }


    @Override
    protected void onDestroyViewImpl() {
        super.onDestroyViewImpl();
    }

}