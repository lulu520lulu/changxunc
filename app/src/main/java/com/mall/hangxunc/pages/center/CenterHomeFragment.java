package com.mall.hangxunc.pages.center;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.IsLoginBean;
import com.mall.hangxunc.bean.home.IsLoginData;
import com.mall.hangxunc.change.ChangeLanguageActivity;
import com.mall.hangxunc.http.HangXunBiz;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.message.MessageLogin;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.banner.CenterBannerImageAdapter;
import com.mall.hangxunc.pages.center.adapter.CenterInstrumentAdapter;
import com.mall.hangxunc.pages.center.adapter.CenterNavAdapter;
import com.mall.hangxunc.pages.center.adapter.CenterNavBarAdapter;
import com.mall.hangxunc.pages.center.adapter.CenterNewsAdapter;
import com.mall.hangxunc.pages.center.adapter.CenterTechAchievementAdapter;
import com.mall.hangxunc.pages.center.adapter.NewActivityAdapter;
import com.mall.hangxunc.pages.center.adapter.NewXqkAdapter;
import com.mall.hangxunc.pages.center.center.ActivityBean;
import com.mall.hangxunc.pages.center.center.ActivityChildBean;
import com.mall.hangxunc.pages.center.center.AdvertisementBean;
import com.mall.hangxunc.pages.center.center.BannerBean;
import com.mall.hangxunc.pages.center.center.CenterHomeBean;
import com.mall.hangxunc.pages.center.center.CenterHomeData;
import com.mall.hangxunc.pages.center.center.CenterIsLoginBean;
import com.mall.hangxunc.pages.center.center.CenterIsLoginData;
import com.mall.hangxunc.pages.center.center.InstrumentBean;
import com.mall.hangxunc.pages.center.center.InstrumentChildBean;
import com.mall.hangxunc.pages.center.center.MessageBean;
import com.mall.hangxunc.pages.center.center.NavBarBean;
import com.mall.hangxunc.pages.center.center.NavBean;
import com.mall.hangxunc.pages.center.center.NewsBean;
import com.mall.hangxunc.pages.center.center.NewsChildBean;
import com.mall.hangxunc.pages.center.center.TechAchievementBean;
import com.mall.hangxunc.pages.center.center.TechAchievementChildBean;
import com.mall.hangxunc.pages.center.center.XqkBean;
import com.mall.hangxunc.pages.center.center.XqkChildBean;
import com.mall.hangxunc.pages.center.http.CenterApiConstants;
import com.mall.hangxunc.pages.center.http.CenterBiz;
import com.mall.hangxunc.pages.center.widget.CenterBottomView;
import com.mall.hangxunc.pages.center.widget.CenterChangeIdentityDialog;
import com.mall.hangxunc.pages.center.widget.CenterLoadingView;
import com.mall.hangxunc.pages.street.http.StreetHangXunBiz;
import com.mall.hangxunc.utils.HangLog;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CenterHomeFragment extends BaseFragment<CenterHomePresenter> {

    private static final String TAG = CenterHomeFragment.class.getSimpleName();

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_city)
    TextView homeCity;
    @BindView(R.id.start_change)
    RadioGroup startChange;
    @BindView(R.id.show_language)
    ImageView showLanguage;
    @BindView(R.id.start_search)
    ImageView startSearch;
    @BindView(R.id.data_container)
    LinearLayout dataContainer;
    @BindView(R.id.container)
    ScrollView container;
    @BindView(R.id.loading)
    CenterLoadingView loading;
    @BindView(R.id.bottom_view)
    CenterBottomView bottomView;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.indicator)
    RectangleIndicator indicator;


    private CenterHomePresenter mCenterHomePresenter;
    private int endX;

    @Override
    protected CenterHomePresenter onCreateTopPresenter() {
        mCenterHomePresenter = new CenterHomePresenter(getActivity());
        return mCenterHomePresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        setSelectedMe();
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.center_fragment_home, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init(view);
        getData();
        getLoginState();
        return view;
    }

    private void init(View view) {
        homeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCity();
            }
        });
        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch();
            }
        });
        showLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageDialog();
            }
        });
        startChange.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.person:
                        JumpUtils.goMall(getContext());
                        break;
                    case R.id.company:
                        CenterIsLoginData.UserBean user = mData.getUser();
                        if (user.getEnterpriseStatus() == 1) {
                            //企业
                        } else {
                            //个人
                            JumpUtils.goWeb(mData.getEnterpriseLink());
                        }
                        break;
                }
            }
        });
    }

    private void showLanguageDialog() {
        ChangeLanguageActivity.launch(getContext(), ChangeLanguageActivity.LANGUAGE_FLAG);
    }

    private void goSearch() {
        ((CenterHomeActivity) getActivity()).setSelect(1);
    }

    private void showCity() {

    }


    private void getData() {
        showLoading();
        CenterBiz.getInstance().getIndex(new ResponseListener<CenterHomeBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getHomeTop code: " + code + ",message:" + message);
                showEmpty();
            }

            @Override
            public void onSuccess(CenterHomeBean bean) {
                HangLog.d(TAG, "onSuccess getBottomData ");
                if (bean == null || bean.getData() == null) {
                    showEmpty();
                    return;
                }
                setData(bean.getData());
            }
        });
    }

    private void setData(CenterHomeData modules) {
        if (modules == null) {
            return;
        }
        showSuccess();
        dataContainer.removeAllViews();
        showBanner(modules.getBanner());
        addNav(modules.getNav());
        addMessage(modules.getMessage());
        addNavBar(modules.getNavBar());
        //成功推广
        addTechAchievement(modules.getTechAchievement());
        //大型仪器
        addInstrument(modules.getInstrument());
        //最新技术
        addXqk(modules.getXqk());
        //最新活动
        addActivity(modules.getActivity());
        addCarBanner(modules.getAdvertisement());
        //上合新闻
        addNews(modules.getNews());
        addBottom();

    }

    private void addBottom() {
        View view = getLayoutInflater().inflate(R.layout.center_home_list_bottom, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dataContainer.addView(view, params);
    }

    //上合新闻
    private void addNews(NewsBean bean) {
        if (bean == null) {
            return;
        }
        NewsChildBean dataList = bean.getList();
        if (dataList == null) {
            return;
        }
        List<NewsChildBean.ListBean> list = dataList.getList();
        View view = getLayoutInflater().inflate(R.layout.center_home_news, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView see_more = view.findViewById(R.id.see_more);

        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(bean.getLink());
            }
        });
        TextView product_text = view.findViewById(R.id.product_text);
        product_text.setText(bean.getTitle());

        RecyclerView recyclerView = view.findViewById(R.id.product_recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        CenterNewsAdapter recycleAdapter = new CenterNewsAdapter(getActivity(), list);
        recyclerView.setAdapter(recycleAdapter);

        dataContainer.addView(view, params);
    }

    private void addCarBanner(AdvertisementBean bean) {
        if (bean == null) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.center_home_list_banner, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView image = view.findViewById(R.id.banner);
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.center_home_list_banner)
                .placeholder(R.mipmap.center_home_list_banner)
                .bitmapTransform(new RoundedCorners(4));
        Glide.with(getActivity())
                .load(bean.getImgPath())
                .apply(options)
                .into(image);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(bean.getUrlPath());
            }
        });
        dataContainer.addView(view, params);

    }

    //最新活动
    private void addActivity(ActivityBean bean) {
        if (bean == null) {
            return;
        }
        ActivityChildBean list = bean.getList();
        if (list == null) {
            return;
        }
        List<ActivityChildBean.ListBean> listList = list.getList();
        if (listList == null || listList.size() == 0) {
            return;
        }

        View view = getLayoutInflater().inflate(R.layout.center_home_new_active, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView see_more = view.findViewById(R.id.see_more);

        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(bean.getLink());
            }
        });
        TextView product_text = view.findViewById(R.id.product_text);
        product_text.setText(bean.getTitle());

        RecyclerView recyclerView = view.findViewById(R.id.product_recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        NewActivityAdapter recycleAdapter = new NewActivityAdapter(getActivity(), listList);
        recyclerView.setAdapter(recycleAdapter);
        dataContainer.addView(view, params);
    }

    //最新技术需求
    private void addXqk(XqkBean bean) {
        if (bean == null) {
            return;
        }
        XqkChildBean beanList = bean.getList();
        if (beanList == null) {
            return;
        }
        List<XqkChildBean.ListBean> list = beanList.getList();
        if (list == null || list.size() == 0) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.center_home_xqk, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView see_more = view.findViewById(R.id.see_more);

        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(bean.getLink());
            }
        });
        TextView product_text = view.findViewById(R.id.product_text);
        product_text.setText(bean.getTitle());

        RecyclerView recyclerView = view.findViewById(R.id.product_recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        NewXqkAdapter recycleAdapter = new NewXqkAdapter(getActivity(), list);
        recyclerView.setAdapter(recycleAdapter);
        dataContainer.addView(view, params);
    }

    private List<InstrumentChildBean.ListBean.ListChildBean> listList1 = null;
    private List<InstrumentChildBean.ListBean.ListChildBean> listList2 = null;

    //大型仪器共享
    private void addInstrument(InstrumentBean bean) {
        if (bean == null) {
            return;
        }
        List<InstrumentChildBean> list = bean.getProducts();
        if (list == null || list.size() == 0) {
            return;
        }


        InstrumentChildBean instrumentChildBean = list.get(0);
        if (instrumentChildBean != null) {
            InstrumentChildBean.ListBean beanList = instrumentChildBean.getList();
            if (beanList != null) {
                listList1 = beanList.getList();
            }
        }

        if (list.size() == 2) {
            InstrumentChildBean instrumentChildBean1 = list.get(1);
            if (instrumentChildBean1 != null) {
                InstrumentChildBean.ListBean beanList = instrumentChildBean1.getList();
                if (beanList != null) {
                    listList2 = beanList.getList();
                }
            }
        }

        View view = getLayoutInflater().inflate(R.layout.center_home_instrument, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        RadioGroup radio_group = view.findViewById(R.id.radio_group);

        TextView see_more = view.findViewById(R.id.see_more);

        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(bean.getLink());
            }
        });
        TextView product_text = view.findViewById(R.id.product_text);
        product_text.setText(bean.getTitle());

        RecyclerView recyclerView = view.findViewById(R.id.product_recycle);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        CenterInstrumentAdapter recycleAdapter = new CenterInstrumentAdapter(getActivity());
        recyclerView.setAdapter(recycleAdapter);


        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.position_1:
                        recycleAdapter.setData(listList1);
                        break;
                    case R.id.position_2:
                        recycleAdapter.setData(listList2);
                        break;

                }
            }
        });

        ((RadioButton) (radio_group.getChildAt(0))).setChecked(true);
        recycleAdapter.setData(listList1);
        dataContainer.addView(view, params);

    }

    //科技成果
    private void addTechAchievement(TechAchievementBean bean) {
        if (bean == null) {
            return;
        }
        TechAchievementChildBean beanList = bean.getList();
        if (beanList == null) {
            return;
        }
        List<TechAchievementChildBean.ListBean> list = beanList.getList();
        if (list == null || list.size() == 0) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.center_home_techachievement, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView see_more = view.findViewById(R.id.see_more);

        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(bean.getLink());
            }
        });
        TextView product_text = view.findViewById(R.id.product_text);
        product_text.setText(bean.getTitle());

        RecyclerView recyclerView = view.findViewById(R.id.product_recycle);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        CenterTechAchievementAdapter recycleAdapter = new CenterTechAchievementAdapter(getActivity(), list);
        recyclerView.setAdapter(recycleAdapter);
        dataContainer.addView(view, params);
    }

    private void addNavBar(List<NavBarBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.center_home_nav_bar, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recycleView = view.findViewById(R.id.recycleView);


        recycleView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        CenterNavBarAdapter adapter = new CenterNavBarAdapter(getActivity(), list);
        recycleView.setAdapter(adapter);
        dataContainer.addView(view, params);
    }


    private void addMessage(MessageBean bean) {
        if (bean == null) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.center_home_list_message, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView text = view.findViewById(R.id.text);
        text.setText(bean.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bean.getUrlPath())) {
                    JumpUtils.goWeb(bean.getUrlPath());
                }
            }
        });
        dataContainer.addView(view, params);

    }

    private void addNav(List<NavBean> nav) {
        if (nav == null || nav.size() == 0) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.center_home_nav, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recycleView = view.findViewById(R.id.recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.HORIZONTAL, false);//2代表是两列,也可以改成你需要的列数 ,LinearLayoutManager.HORIZONTAL代表横向滑动
        recycleView.setLayoutManager(gridLayoutManager);
        View main_line = view.findViewById(R.id.main_line);

        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //整体的总宽度，注意是整体，包括在显示区域之外的。
                int range = recycleView.computeHorizontalScrollRange();
                float density = getScreenDensity();
                //计算出溢出部分的宽度，即屏幕外剩下的宽度
                float maxEndX = range + (10 * density) + 5 - WindowUtils.getWindowWidth(getActivity());
                //滑动的距离
                endX += dx;
                //计算比例
                float proportion = endX / maxEndX;

                //计算滚动条宽度
                int transMaxRange = ((ViewGroup) main_line.getParent()).getWidth() - main_line.getWidth();
                //设置滚动条移动
                main_line.setTranslationX(transMaxRange * proportion);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

        });


        CenterNavAdapter adapter = new CenterNavAdapter(getActivity(), nav);
        recycleView.setAdapter(adapter);
        dataContainer.addView(view, params);
    }

    public float getScreenDensity() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
        }
        int width = dm.widthPixels;// 屏幕宽度（像素）
        int height = dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        return density;
    }

    private void showBanner(List<BannerBean> data) {
        if (data == null || data.size() == 0) {
            return;
        }

        indicator.setVisibility(View.VISIBLE);
        //在布局文件中使用指示器，这样更灵活
        banner.setIndicator(indicator, false);

        banner.isAutoLoop(true);
        banner.setIndicatorGravity(IndicatorConfig.Direction.LEFT);
        banner.setAdapter(new CenterBannerImageAdapter(getActivity(), data));

        banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(12));
        banner.setIndicatorNormalWidth((int) BannerUtils.dp2px(6));

        banner.setIndicatorSpace((int) BannerUtils.dp2px(4));
        banner.setIndicatorHeight((int) BannerUtils.dp2px(6));
        banner.setIndicatorSelectedColorRes(R.color.white);
        banner.setIndicatorNormalColorRes(R.color.white_40);
        banner.setIndicatorRadius(30);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object object, int position) {
                if ((position + 1) > data.size() || position < 0) {
                    return;
                }
                BannerBean bannersBean = data.get(position);
                if (bannersBean != null && !TextUtils.isEmpty(bannersBean.getUrlPath())) {
                    JumpUtils.goWeb(bannersBean.getUrlPath());
                }
            }
        });
    }

    private void showLoading() {
        HangLog.d(TAG, "showLoading ");
        container.setVisibility(View.GONE);
        bottomView.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    private void showSuccess() {
        HangLog.d(TAG, "showSuccess ");
        bottomView.setVisibility(View.VISIBLE);
        container.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    private void showEmpty() {
        HangLog.d(TAG, "showLoading ");
        container.setVisibility(View.GONE);
        bottomView.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeCurrency(MessageLocal message) {
        String code = message.getCode();
        String name = message.getName();
        if (TextUtils.equals(message.message, MessageLocal.CURRENCY_CHANGE)) {
            getData();
        } else if (TextUtils.equals(message.message, MessageLocal.LANGUAGE_CHANGE)) {
            getData();
            languageUpdate(code, name);
        }
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    private void languageUpdate(String code, String name) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mData == null) {
            return;
        }
        CenterIsLoginData.UserBean user = mData.getUser();
        if (user == null) {
            return;
        }
        if (user.getEnterpriseStatus() == 1) {
            ((RadioButton) (startChange.getChildAt(1))).setChecked(true);
        } else {
            ((RadioButton) (startChange.getChildAt(0))).setChecked(true);
        }

    }

    private void getLoginState() {
        loading.setVisibility(View.VISIBLE);

        CenterBiz.getInstance().isCustomerLogin(new ResponseListener<CenterIsLoginBean>() {
            @Override
            public void onFail(int code, String message) {
                loading.setVisibility(View.GONE);

            }

            @Override
            public void onSuccess(CenterIsLoginBean bean) {
                loading.setVisibility(View.GONE);

                if (bean == null && bean.getData() == null) {
                    return;
                }
                if (bean.getCode() == 0) {
                    mData = bean.getData();
                    CenterIsLoginData.UserBean user = mData.getUser();
                    if (user == null) {
                        return;
                    }
                    if (user.getEnterpriseStatus() == 1) {
                        ((RadioButton) (startChange.getChildAt(1))).setChecked(true);
                    } else {
                        ((RadioButton) (startChange.getChildAt(1))).setChecked(false);
                    }

                }
            }
        });
    }

    private CenterIsLoginData mData;

//
//    private CenterChangeIdentityDialog mCenterChangeIdentityDialog;
//
//    private void showChangeDialog(CenterIsLoginData data) {
//        if (mCenterChangeIdentityDialog == null) {
//            mCenterChangeIdentityDialog = new CenterChangeIdentityDialog(getActivity(), data);
//        }
//        mCenterChangeIdentityDialog.show();
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLogin(MessageLogin message) {
//        if (TextUtils.equals(message.message, MessageLogin.LOGIN_IN)) {
//            loginChangeView(true);
//        } else if (TextUtils.equals(message.message, MessageLogin.LOGIN_OUT)) {
//            loginChangeView(false);
//        }
    }

//    private void loginChangeView(boolean b) {
//        if (!b) {
//            JumpUtils.goMallLogin(getActivity());
//        }
//    }
}
