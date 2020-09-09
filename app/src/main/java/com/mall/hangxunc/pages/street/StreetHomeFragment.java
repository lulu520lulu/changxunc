package com.mall.hangxunc.pages.street;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.CategoryBean;
import com.mall.hangxunc.bean.home.HomeCategoryData;
import com.mall.hangxunc.bean.home.IsLoginBean;
import com.mall.hangxunc.bean.home.IsLoginData;
import com.mall.hangxunc.bean.home.ModulesBean;
import com.mall.hangxunc.bean.home.ModulesListBean;
import com.mall.hangxunc.bean.home.ModulesListData;
import com.mall.hangxunc.bean.home.ProductBean;
import com.mall.hangxunc.bean.home.ProductListBean;
import com.mall.hangxunc.bean.home.StreetAllProductBean;
import com.mall.hangxunc.bean.home.StreetAllProductData;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.message.MessageHome;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.street.adapter.StreetHomeCategoryAdapter;
import com.mall.hangxunc.pages.street.adapter.StreetHomeListAdapter;
import com.mall.hangxunc.pages.street.http.StreetApiConstants;
import com.mall.hangxunc.pages.street.http.StreetHangXunBiz;
import com.mall.hangxunc.pages.street.http.StreetIsLoginBean;
import com.mall.hangxunc.pages.street.http.StreetIsLoginData;
import com.mall.hangxunc.pages.street.listener.StreetHomeCategoryClickListener;
import com.mall.hangxunc.pages.street.loading.StreetLoadingView;
import com.mall.hangxunc.pages.street.widget.StreetChangeIdentityDialog;
import com.mall.hangxunc.pages.widget.BottomView;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.HangLog;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.LoginUtils;
import com.mall.hangxunc.web.HangXunWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StreetHomeFragment extends BaseFragment<StreetHomePresenter> {

    private static final String TAG = StreetHomeFragment.class.getSimpleName();
    @BindView(R.id.search)
    FrameLayout search;
    @BindView(R.id.change_identity)
    ImageView changeIdentity;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.type_list)
    RecyclerView type_list;
    @BindView(R.id.bottom_view)
    BottomView bottomView;
    @BindView(R.id.empty_view)
    LinearLayout empty_view;
    @BindView(R.id.loading)
    StreetLoadingView loadingView;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.webview)
    HangXunWebView webContainer;
    @BindView(R.id.container)
    LinearLayout container;

    private StreetHomePresenter mStreetHomePresenter;
    private StreetHomeListAdapter mListAdapter;
    private StreetHomeCategoryAdapter mTypeAdapter;

    @Override
    protected StreetHomePresenter onCreateTopPresenter() {
        mStreetHomePresenter = new StreetHomePresenter(getActivity());
        return mStreetHomePresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        setSelectedMe();
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.street_fragment_home, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init(view);
        return view;
    }

    private void init(View view) {
        Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.tabbar_search_un_select);
        drawable.setBounds(0, 0, DimenUtils.dip2px(16), DimenUtils.dip2px(16));
        tv_search.setCompoundDrawables(drawable, null, null, null);

        changeIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoginState();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch();
            }
        });
        empty_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        initTypeList();
        initList();
        getData();
    }

    private void goSearch() {
        JumpUtils.goWeb(StreetApiConstants.BASE_URL + StreetApiConstants.HOME_SEARCH);
//        ((MainActivity) getActivity()).setSelect(1);
    }


    private void getData() {
        showLoading();
        StreetHangXunBiz.getInstance().getCategory(new ResponseListener<HomeCategoryData>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getCategory code: " + code + ",message:" + message);
                showEmpty();
            }

            @Override
            public void onSuccess(HomeCategoryData data) {
                HangLog.d(TAG, "onSuccess getCategory ");

                if (data == null || data.getData() == null || data.getData().size() == 0) {
                    showEmpty();
                    return;
                }
                List<CategoryBean> list = data.getData();
                mTypeAdapter.setData(list);
                getTopData();
            }
        });
    }

    private void getTopData() {
        StreetHangXunBiz.getInstance().getHomeTop(new ResponseListener<ModulesListData>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getHomeTop code: " + code + ",message:" + message);
                showEmpty();
            }

            @Override
            public void onSuccess(ModulesListData data) {
                HangLog.d(TAG, "onSuccess getHomeTop ");
                if (data == null || data.getData() == null) {
                    showEmpty();
                    return;
                }
                ModulesListBean bean = data.getData();
                List<ModulesBean> modules = bean.getModules();
                if (modules == null || modules.size() == 0) {
                    showEmpty();
                    return;
                }
                mProductCount = 0;
                getBottomData(modules);
            }
        });
    }

    private int mProductCount = 0;

    private void getBottomData(List<ModulesBean> modules) {
        StreetHangXunBiz.getInstance().getAllProduct(mProductCount, new ResponseListener<StreetAllProductBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getBottomData code: " + code + ",message:" + message);
                setData(modules);
            }

            @Override
            public void onSuccess(StreetAllProductBean data) {
                HangLog.d(TAG, "onSuccess getBottomData ");
                if (data == null || data.getData() == null) {
                    setData(modules);
                    return;
                }
                StreetAllProductData bean = data.getData();
                List<ProductBean> list = bean.getProducts();
                if (list == null || list.size() == 0) {
                    setData(modules);
                    return;
                }
                setData(modules, list);

            }
        });
    }

    private void getMoreBottomData() {
        mProductCount++;
//        showLoading();
        StreetHangXunBiz.getInstance().getAllProduct(mProductCount, new ResponseListener<StreetAllProductBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getMoreBottomData code: " + code + ",message:" + message);
//                hideLoading();
            }

            @Override
            public void onSuccess(StreetAllProductBean data) {
                HangLog.d(TAG, "onSuccess getMoreBottomData ");
//                hideLoading();
                if (data == null || data.getData() == null) {
                    return;
                }
                StreetAllProductData bean = data.getData();
                List<ProductBean> list = bean.getProducts();
                if (list == null || list.size() == 0) {
                    return;
                }
                mListAdapter.setMoreData(list);
            }
        });
    }

    private void setData(List<ModulesBean> modules) {
        hideLoading();
        mListAdapter.setTopData(modules);
    }

    private void setData(List<ModulesBean> modules, List<ProductBean> list) {
        hideLoading();
        mListAdapter.setAllData(modules, list);
    }

    private void showLoading() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);
        empty_view.setVisibility(View.GONE);
    }

    private void hideLoading() {
        HangLog.d(TAG, "hideLoading ");
        loadingView.setVisibility(View.GONE);
        empty_view.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
        bottomView.setVisibility(View.VISIBLE);
        type_list.setVisibility(View.VISIBLE);
        recycleView.setVisibility(View.VISIBLE);
    }

    private void showEmpty() {
        HangLog.d(TAG, "showEmpty ");
        loadingView.setVisibility(View.GONE);
        container.setVisibility(View.GONE);
        empty_view.setVisibility(View.VISIBLE);
        bottomView.setVisibility(View.VISIBLE);
    }


    private void initList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recycleView.setLayoutManager(manager);
        mListAdapter = new StreetHomeListAdapter(getActivity());
        recycleView.setAdapter(mListAdapter);
        mListAdapter.setLoadMoreListener(new StreetHomeListAdapter.LoadMoreListener() {
            @Override
            public void handleLoadMore() {
                getMoreBottomData();
            }
        });
    }

    private void initTypeList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        type_list.setLayoutManager(manager);
        mTypeAdapter = new StreetHomeCategoryAdapter(getActivity(), mStreetHomeCategoryClickListener);
        type_list.setAdapter(mTypeAdapter);
    }


    private StreetHomeCategoryClickListener mStreetHomeCategoryClickListener = new StreetHomeCategoryClickListener() {

        @Override
        public void onItemClick(List<CategoryBean> list, int position) {
            ArrayList<CategoryBean> data = new ArrayList<>();
            data.addAll(list);
            for (int i = 0; i < data.size(); i++) {
                CategoryBean categoryBean = data.get(i);
                if (position == i) {
                    categoryBean.setSelected(true);
                } else {
                    categoryBean.setSelected(false);
                }
            }
            CategoryBean categoryBean = data.get(position);
            if (position == 0) {
                webContainer.setVisibility(View.GONE);
                recycleView.setVisibility(View.VISIBLE);
            } else {
                String url = StreetApiConstants.BASE_URL + StreetApiConstants.CATEGORY_DETAIL_PATH + categoryBean.getCategory_id() + StreetApiConstants.CUSTOMER_ID_PATH + LoginUtils.getInstance().getScoId();
                showWebView(url);
            }
            mTypeAdapter.updateData(data);
        }
    };


    private void showWebView(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        webContainer.setVisibility(View.VISIBLE);
        recycleView.setVisibility(View.GONE);
        webContainer.loadUrl(url);
    }

    private void startShowHome() {
        if (mTypeAdapter != null) {
            List<CategoryBean> list = mTypeAdapter.getData();
            if (list != null && list.size() > 0) {

                ArrayList<CategoryBean> data = new ArrayList<>();
                data.addAll(list);
                for (int i = 0; i < data.size(); i++) {
                    CategoryBean categoryBean = data.get(i);
                    if (0 == i) {
                        categoryBean.setSelected(true);
                    } else {
                        categoryBean.setSelected(false);
                    }
                }
                webContainer.setVisibility(View.GONE);
                recycleView.setVisibility(View.VISIBLE);
                mTypeAdapter.updateData(data);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageHome message) {
        if (TextUtils.equals(message.message, "show home")) {
            startShowHome();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeCurrency(MessageLocal message) {
        if (TextUtils.equals(message.message, MessageLocal.CURRENCY_CHANGE)) {
            getData();
        } else if (TextUtils.equals(message.message, MessageLocal.LANGUAGE_CHANGE)) {
            getData();
        }
    }

    @Override
    public boolean onBackPressed() {
        if (webContainer != null && webContainer.canGoBack()) {
            webContainer.goBack();
            return true;
        }
        return false;
    }

    private void getLoginState() {
        loadingView.setVisibility(View.VISIBLE);

        StreetHangXunBiz.getInstance().isCustomerLogin(new ResponseListener<StreetIsLoginBean>() {
            @Override
            public void onFail(int code, String message) {
                loadingView.setVisibility(View.GONE);

            }

            @Override
            public void onSuccess(StreetIsLoginBean bean) {
                loadingView.setVisibility(View.GONE);

                if (bean == null && bean.getData() == null) {
                    return;
                }
                if (bean.getCode() == 0) {
                    StreetIsLoginData data = bean.getData();
                    showChangeDialog(data);
                }
            }
        });
    }

    private StreetChangeIdentityDialog mStreetChangeIdentityDialog;

    private void showChangeDialog(StreetIsLoginData data) {
        if (data == null) {
            return;
        }
        if (mStreetChangeIdentityDialog == null) {
            mStreetChangeIdentityDialog = new StreetChangeIdentityDialog(getActivity(), data);
        }
        mStreetChangeIdentityDialog.show();
    }

}
