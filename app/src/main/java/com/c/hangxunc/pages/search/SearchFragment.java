package com.c.hangxunc.pages.search;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.bean.home.SearchResultBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.loading.LoadingView;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.pages.shop.MessageLocal;
import com.c.hangxunc.pages.widget.BottomView;
import com.c.hangxunc.utils.HangLog;
import com.c.hangxunc.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends BaseFragment<SearchPresenter> {

    private static final String TAG = SearchFragment.class.getSimpleName();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    TextView titleText;
    EditText search;
    TextView startSearch;
    RecyclerView sortList;
    RecyclerView resultList;
    LinearLayout emptyContainer;
    ScrollView resultContainer;

    BottomView bottomView;
    LoadingView loadingView;


    private SearchPresenter mSearchPresenter;
    private SearchAdapter mSearchAdapter;

    private SearchTabAdapter mSortAdapter;

    @Override
    protected SearchPresenter onCreateTopPresenter() {
        mSearchPresenter = new SearchPresenter(getActivity());
        return mSearchPresenter;
    }

    @Nullable
    @Override
    public View onCreateViewImpl(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);
        EventBus.getDefault().register(this);

        init(view);
        return view;
    }


    private void init(View view) {
        titleText = view.findViewById(R.id.title_text);
        search = view.findViewById(R.id.search);
        startSearch = view.findViewById(R.id.start_search);
        sortList = view.findViewById(R.id.search_result_sort);
        resultList = view.findViewById(R.id.search_result);
        emptyContainer = view.findViewById(R.id.empty_view);
        resultContainer = view.findViewById(R.id.container);
        bottomView = view.findViewById(R.id.bottom_view);
        loadingView = view.findViewById(R.id.loading);

        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canSearch("", "");
            }
        });
        showEmpty();

        resultList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        resultList.setFocusable(false);
        mSearchAdapter = new SearchAdapter(getActivity());
        resultList.setAdapter(mSearchAdapter);


        mSortAdapter = new SearchTabAdapter(getActivity());
        mSortAdapter.setSortOnClickListener(new SortOnClickListener() {

            @Override
            public void onSortSelectListener(String sort, String order) {
                canSearch(sort, order);
            }
        });
        sortList.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        sortList.setAdapter(mSortAdapter);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    canSearch("", "");
                    return false;
                }
                return false;
            }
        });
    }

    private void canSearch(String sorts, String order) {
        String keyword = search.getText().toString();
        if (TextUtils.isEmpty(keyword)) {
            ToastUtils.showToast(getActivity(), getActivity().getString(R.string.please_edit_content));
        } else {
            startSearch(keyword, sorts, order);
        }
    }

    private void showLoading() {
        HangLog.d(TAG, "showLoading ");
        loadingView.setVisibility(View.VISIBLE);
        resultContainer.setVisibility(View.GONE);
        sortList.setVisibility(View.GONE);
        bottomView.setVisibility(View.GONE);
        startSearch.setEnabled(false);
        search.setEnabled(false);
    }

    private void showFail() {
        HangLog.d(TAG, "showFail ");
        loadingView.setVisibility(View.GONE);
        resultContainer.setVisibility(View.VISIBLE);
        emptyContainer.setVisibility(View.VISIBLE);
        resultList.setVisibility(View.GONE);
        sortList.setVisibility(View.GONE);
        bottomView.setVisibility(View.VISIBLE);
        startSearch.setEnabled(true);
        search.setEnabled(true);
    }

    private void showEmpty() {
        HangLog.d(TAG, "showEmpty ");
        loadingView.setVisibility(View.GONE);
        resultContainer.setVisibility(View.VISIBLE);
        emptyContainer.setVisibility(View.INVISIBLE);
        resultList.setVisibility(View.GONE);
        sortList.setVisibility(View.GONE);
        bottomView.setVisibility(View.VISIBLE);
        startSearch.setEnabled(true);
        search.setEnabled(true);
    }

    private SortData initSort(String name, String sort, String order) {
        SortData sortData = new SortData();
        String key = "";
        int checkFlag = 0;

        if (TextUtils.equals(name, "默认")) {
            key = "p.sort_order";
        } else if (TextUtils.equals(name, "名称")) {
            key = "pd.name";
        } else if (TextUtils.equals(name, "价格")) {
            key = "p.price";
        } else if (TextUtils.equals(name, "评级")) {
            key = "rating";
        } else if (TextUtils.equals(name, "销量")) {
            key = "sales";
        }
        if (TextUtils.isEmpty(sort) && TextUtils.equals(name, "默认")) {
            checkFlag = SortData.ASC_FLAG;
        }
        if (TextUtils.equals(key, sort)) {
            if (TextUtils.equals(order, SortData.ASC)) {
                checkFlag = SortData.ASC_FLAG;
            } else {
                checkFlag = SortData.DESC_FLAG;
            }
        }

        sortData.key = key;
        sortData.name = name;
        sortData.checkFlag = checkFlag;
        return sortData;
    }

    private void showSuccess(SearchResultBean bean) {
        HangLog.d(TAG, "showSuccess ");
        if (bean == null || bean.getProducts() == null || bean.getProducts().size() == 0) {
            handleFail();
            return;
        }
        List<ProductBean> products = bean.getProducts();
        HangLog.d(TAG, "showSuccess " + products.size());
        mSearchAdapter.setData(products);

        String[] sorts = getActivity().getResources().getStringArray(R.array.search_sort);
        ArrayList<SortData> list = new ArrayList<>();
        for (int i = 0; i < sorts.length; i++) {
            String name = sorts[i];
            SortData data = initSort(name, bean.getSort(), bean.getOrder());
            list.add(data);
        }

        mSortAdapter.setData(list);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingView.setVisibility(View.GONE);
                emptyContainer.setVisibility(View.GONE);
                sortList.setVisibility(View.VISIBLE);

                resultContainer.setVisibility(View.VISIBLE);
                resultList.setVisibility(View.VISIBLE);

                bottomView.setVisibility(View.VISIBLE);

                startSearch.setEnabled(true);
                search.setEnabled(true);
            }
        }, 300);
    }


    private void startSearch(String keyword, String sorts, String order) {
        showLoading();
        HangXunBiz.getInstance().searchPro(keyword, sorts, order, new ResponseListener<SearchResultBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail startSearch code: " + code + ",message:" + message);
                handleFail();
            }

            @Override
            public void onSuccess(SearchResultBean bean) {
                if (bean == null) {
                    handleFail();
                    return;
                }
                List<ProductBean> products = bean.getProducts();
                if (products == null || products.size() == 0) {
                    handleFail();
                    return;
                }
                HangLog.d(TAG, "onSuccess startSearch bean: " + bean.toString());
                showSuccess(bean);
            }
        });
    }

    private void handleFail() {
        HangLog.d(TAG, "handleFail");
        showFail();
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
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeCurrency(MessageLocal message) {
        if (TextUtils.equals(message.message, MessageLocal.LANGUAGE_CHANGE)
                || TextUtils.equals(message.message, MessageLocal.CURRENCY_CHANGE)) {
            String keyword = search.getText().toString();
            if (!TextUtils.isEmpty(keyword)) {
                startSearch(keyword, "", "");
            }
        }
    }

}