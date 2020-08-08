package com.c.hangxunc.pages.home;

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

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CategoryBean;
import com.c.hangxunc.bean.home.ModulesBean;
import com.c.hangxunc.bean.home.ModulesListBean;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.bean.home.ProductListBean;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.loading.LoadingView;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.pages.MainActivity;
import com.c.hangxunc.pages.shop.MessageLocal;
import com.c.hangxunc.pages.home.ui.MessageHome;
import com.c.hangxunc.pages.widget.BottomView;
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.HangLog;
import com.c.hangxunc.utils.LoginUtils;
import com.c.hangxunc.web.HangXunWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment<HomePresenter> {

    private static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.search)
    FrameLayout search;
    @BindView(R.id.start_person)
    ImageView start_person;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.type_list)
    RecyclerView type_list;
    @BindView(R.id.bottom_view)
    BottomView bottomView;
    @BindView(R.id.empty_view)
    LinearLayout empty_view;
    @BindView(R.id.loading)
    LoadingView loadingView;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.webview)
    HangXunWebView webContainer;
    @BindView(R.id.go_type)
    ImageView go_type;

    private HomePresenter mHomePresenter;
    private HomeListAdapter mListAdapter;
    private HomeCategoryAdapter mTypeAdapter;

    @Override
    protected HomePresenter onCreateTopPresenter() {
        mHomePresenter = new HomePresenter(getActivity());
        return mHomePresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        setSelectedMe();
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init(view);
        return view;
    }

    private void init(View view) {
        Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.tabbar_search_un_select);
        drawable.setBounds(0, 0, DimenUtils.dip2px(16), DimenUtils.dip2px(16));
        tv_search.setCompoundDrawables(drawable, null, null, null);

        start_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPerson();
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
        go_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goType();
            }
        });
        initTypeList();
        initList();
        getData();
    }

    private void goSearch() {
        ((MainActivity) getActivity()).setSelect(1);
    }

    private void goPerson() {
        ((MainActivity) getActivity()).setSelect(4);
    }

    private void goType() {
        ((MainActivity) getActivity()).setSelect(2);
    }

    private void getData() {
        showLoading();
        HangXunBiz.getInstance().getCategory(new ResponseListener<List<CategoryBean>>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getCategory code: " + code + ",message:" + message);
                showEmpty();
            }

            @Override
            public void onSuccess(List<CategoryBean> list) {
                HangLog.d(TAG, "onSuccess getCategory ");
                if (list == null || list.size() == 0) {
                    showEmpty();
                    return;
                }
                mTypeAdapter.setData(list);
                getTopData();
            }
        });
    }

    private void getTopData() {
        HangXunBiz.getInstance().getHomeTop(new ResponseListener<ModulesListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getHomeTop code: " + code + ",message:" + message);
                showEmpty();
            }

            @Override
            public void onSuccess(ModulesListBean bean) {
                HangLog.d(TAG, "onSuccess getHomeTop ");
                if (bean == null) {
                    showEmpty();
                    return;
                }
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
        HangXunBiz.getInstance().getAllProduct(mProductCount, new ResponseListener<ProductListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getBottomData code: " + code + ",message:" + message);
                setData(modules);
            }

            @Override
            public void onSuccess(ProductListBean bean) {
                HangLog.d(TAG, "onSuccess getBottomData ");
                if (bean == null) {
                    setData(modules);
                    return;
                }
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
        HangXunBiz.getInstance().getAllProduct(mProductCount, new ResponseListener<ProductListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getMoreBottomData code: " + code + ",message:" + message);
//                hideLoading();
            }

            @Override
            public void onSuccess(ProductListBean bean) {
                HangLog.d(TAG, "onSuccess getMoreBottomData ");
//                hideLoading();
                if (bean == null) {
                    return;
                }
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
        type_list.setVisibility(View.GONE);
        recycleView.setVisibility(View.GONE);
        empty_view.setVisibility(View.GONE);
        bottomView.setVisibility(View.GONE);
        go_type.setVisibility(View.GONE);
    }

    private void hideLoading() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.GONE);
        bottomView.setVisibility(View.VISIBLE);
        type_list.setVisibility(View.VISIBLE);
        recycleView.setVisibility(View.VISIBLE);
        go_type.setVisibility(View.VISIBLE);

    }

    private void showEmpty() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.GONE);
        type_list.setVisibility(View.GONE);
        recycleView.setVisibility(View.GONE);
        empty_view.setVisibility(View.VISIBLE);
        bottomView.setVisibility(View.VISIBLE);
        go_type.setVisibility(View.GONE);
    }


    private void initList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recycleView.setLayoutManager(manager);
        mListAdapter = new HomeListAdapter(getActivity());
        recycleView.setAdapter(mListAdapter);
        mListAdapter.setLoadMoreListener(new HomeListAdapter.LoadMoreListener() {
            @Override
            public void handleLoadMore() {
                getMoreBottomData();
            }
        });
    }

    private void initTypeList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        type_list.setLayoutManager(manager);
        mTypeAdapter = new HomeCategoryAdapter(getActivity(), mHomeCategoryClickListener);
        type_list.setAdapter(mTypeAdapter);
    }


    private HomeCategoryClickListener mHomeCategoryClickListener = new HomeCategoryClickListener() {

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
                String url = ApiConstants.BASE_URL + ApiConstants.CATEGORY_DETAIL_PATH + categoryBean.getCategory_id() + ApiConstants.CUSTOMER_ID_PATH + LoginUtils.getInstance().getCustomerId();
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
        if (TextUtils.equals(message.message, MessageLocal.CHANGE)) {
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
}
