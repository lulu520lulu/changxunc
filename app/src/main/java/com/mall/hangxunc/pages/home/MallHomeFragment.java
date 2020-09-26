package com.mall.hangxunc.pages.home;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.CategoryBean;
import com.mall.hangxunc.bean.home.HomeCategoryData;
import com.mall.hangxunc.bean.home.ModulesListData;
import com.mall.hangxunc.bean.home.ModulesListBean;
import com.mall.hangxunc.bean.home.ModulesBean;
import com.mall.hangxunc.bean.home.ProductBean;
import com.mall.hangxunc.bean.home.ProductListBean;
import com.mall.hangxunc.http.ApiConstants;
import com.mall.hangxunc.http.HangXunBiz;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.loading.LoadingView;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.MainActivity;
import com.mall.hangxunc.pages.home.adapter.MallHomeCategoryAdapter;
import com.mall.hangxunc.pages.home.adapter.MallHomeListAdapter;
import com.mall.hangxunc.pages.home.listener.MallCategoryClickListener;
import com.mall.hangxunc.pages.home.widget.MallChangeIdentityDialog;
import com.mall.hangxunc.pages.home.widget.MallChangeIdentityWindow;
import com.mall.hangxunc.message.MessageLogin;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.message.MessageHome;
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

public class MallHomeFragment extends BaseFragment<MallHomePresenter> {

    private static final String TAG = MallHomeFragment.class.getSimpleName();
    @BindView(R.id.search)
    FrameLayout search;
    @BindView(R.id.start_change)
    LinearLayout start_change;
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
    @BindView(R.id.login_person)
    RelativeLayout login_person;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.person)
    TextView changeIdentityPerson;
    @BindView(R.id.company)
    TextView changeIdentityCompany;
    @BindView(R.id.top_view)
    LinearLayout top_view;

    private MallHomePresenter mMallHomePresenter;
    private MallHomeListAdapter mListAdapter;
    private MallHomeCategoryAdapter mTypeAdapter;

    @Override
    protected MallHomePresenter onCreateTopPresenter() {
        mMallHomePresenter = new MallHomePresenter(getActivity());
        return mMallHomePresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        setSelectedMe();
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

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mall_fragment_home, null);
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
        expandTouchArea(start_person, DimenUtils.dip2px(20));
        changeIdentityCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goCenter(getActivity());
            }
        });
        expandTouchArea(changeIdentityCompany, DimenUtils.dip2px(20));
        login_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
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

        loginChangeView(LoginUtils.getInstance().isLogin());
        initTypeList();
        initList();
        getData();
    }


    private void loginChangeView(boolean isLogin) {
        if (isLogin) {
            start_person.setVisibility(View.GONE);
            login_person.setVisibility(View.GONE);
            start_change.setVisibility(View.VISIBLE);
            // TODO: 2020/9/2
            name.setText("a*");
        } else {
            start_person.setVisibility(View.VISIBLE);
            login_person.setVisibility(View.GONE);
            start_change.setVisibility(View.GONE);
        }
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
        HangXunBiz.getInstance().getCategory(new ResponseListener<HomeCategoryData>() {
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
        HangXunBiz.getInstance().getHomeTop(new ResponseListener<ModulesListData>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getHomeTop code: " + code + ",message:" + message);
                showEmpty();
            }

            @Override
            public void onSuccess(ModulesListData bean) {
                HangLog.d(TAG, "onSuccess getHomeTop ");
                if (bean == null || bean.getData() == null) {
                    showEmpty();
                    return;
                }
                ModulesListBean data = bean.getData();
                List<ModulesBean> modules = data.getModules();
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
//        loadingView.setVisibility(View.VISIBLE);
        HangXunBiz.getInstance().getAllProduct(mProductCount, new ResponseListener<ProductListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getMoreBottomData code: " + code + ",message:" + message);
//                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(ProductListBean bean) {
                HangLog.d(TAG, "onSuccess getMoreBottomData ");
//                loadingView.setVisibility(View.GONE);
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
        showSuccess();
        mListAdapter.setTopData(modules);
    }

    private void setData(List<ModulesBean> modules, List<ProductBean> list) {
        showSuccess();
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
        top_view.setVisibility(View.GONE);
    }

    private void showSuccess() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.GONE);
        bottomView.setVisibility(View.VISIBLE);
        type_list.setVisibility(View.VISIBLE);
        recycleView.setVisibility(View.VISIBLE);
        go_type.setVisibility(View.VISIBLE);
        top_view.setVisibility(View.VISIBLE);

    }

    private void showEmpty() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.GONE);
        type_list.setVisibility(View.GONE);
        recycleView.setVisibility(View.GONE);
        empty_view.setVisibility(View.VISIBLE);
        bottomView.setVisibility(View.VISIBLE);
        go_type.setVisibility(View.GONE);
        top_view.setVisibility(View.GONE);
    }


    private void initList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recycleView.setLayoutManager(manager);
        mListAdapter = new MallHomeListAdapter(getActivity());
        recycleView.setAdapter(mListAdapter);
        mListAdapter.setLoadMoreListener(new MallHomeListAdapter.LoadMoreListener() {
            @Override
            public void handleLoadMore() {
                getMoreBottomData();
            }
        });
    }

    private void initTypeList() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        type_list.setLayoutManager(manager);
        mTypeAdapter = new MallHomeCategoryAdapter(getActivity(), mMallCategoryClickListener);
        type_list.setAdapter(mTypeAdapter);
    }


    private MallCategoryClickListener mMallCategoryClickListener = new MallCategoryClickListener() {

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
                String url = ApiConstants.BASE_URL + ApiConstants.CATEGORY_DETAIL_PATH + categoryBean.getCategory_id() + ApiConstants.CUSTOMER_ID_PATH + LoginUtils.getInstance().getScoId();
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
        if (mMallChangeIdentityWindow != null) {
            mMallChangeIdentityWindow.dismiss();
        }
        if (mMallChangeIdentityDialog != null) {
            mMallChangeIdentityDialog.cancel();
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLogin(MessageLogin message) {
        if (TextUtils.equals(message.message, MessageLogin.LOGIN_IN)) {
            loginChangeView(true);
        } else if (TextUtils.equals(message.message, MessageLogin.LOGIN_OUT)) {
            loginChangeView(false);
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

    private MallChangeIdentityWindow mMallChangeIdentityWindow;

    private void showPopupWindow() {
        if (mMallChangeIdentityWindow == null) {
            mMallChangeIdentityWindow = new MallChangeIdentityWindow(getActivity());
        }
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.3f;
        getActivity().getWindow().setAttributes(lp);
        mMallChangeIdentityWindow.showAsDropDown(login_person, -20, 0, Gravity.BOTTOM | Gravity.RIGHT);
    }

    private MallChangeIdentityDialog mMallChangeIdentityDialog;

    private void showChangeDialog() {
        if (mMallChangeIdentityDialog == null) {
            mMallChangeIdentityDialog = new MallChangeIdentityDialog(getActivity());
        }
        mMallChangeIdentityDialog.show();
    }

}
