package com.mall.hangxunc.pages.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mall.hangxunc.LauncherActivity;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.BaseBean;
import com.mall.hangxunc.http.ApiConstants;
import com.mall.hangxunc.http.CustomStyleBody;
import com.mall.hangxunc.http.HangXunBiz;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.mvp.BaseFragment;
import com.mall.hangxunc.pages.login.ForgetPassFragmentWeb;
import com.mall.hangxunc.pages.login.LoginFragment;
import com.mall.hangxunc.message.MessageLogin;
import com.mall.hangxunc.pages.login.RegisterFragment;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.utils.CommSharedUtil;
import com.mall.hangxunc.utils.CurrencySp;
import com.mall.hangxunc.utils.LanguageSp;
import com.mall.hangxunc.utils.LoginUtils;
import com.mall.hangxunc.web.HangXunWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PersonalFragment extends BaseFragment<PersonalPresenter> {

    private PersonalPresenter mPersonalPresenter;
    private HangXunWebView mWebContainer;
    private FrameLayout mFragmentContainer;
    private LoginFragment mLoginFragment;
    private RegisterFragment mRegisterFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected PersonalPresenter onCreateTopPresenter() {
        mPersonalPresenter = new PersonalPresenter(getActivity());
        return mPersonalPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mall_fragment_person, null);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mFragmentManager = getChildFragmentManager();
        mWebContainer = view.findViewById(R.id.web_container);
        mFragmentContainer = view.findViewById(R.id.fragment_container);

        initFragment();

        if (!LoginUtils.getInstance().isLogin()) {
            showLoginFragment();
        } else {
            initWebData(LoginUtils.getInstance().getScoId());
        }
    }


    private void initFragment() {
        mLoginFragment = new LoginFragment();

        mLoginFragment.setLoginChangeListener(new LoginFragment.LoginChangeListener() {
            @Override
            public void showRegister() {
                showRegisterFragment();
            }

            @Override
            public void showForget() {
                showForgetFragment();
            }

            @Override
            public void showPersion(String customId) {
                initWebData(customId);
            }
        });
        mRegisterFragment = new RegisterFragment();
        mRegisterFragment.setRegisterChangeListener(new RegisterFragment.RegisterChangeListener() {
            @Override
            public void showLogin() {
                showLoginFragment();
            }
        });
    }


    private void showLoginFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mFragmentContainer.getVisibility() == View.GONE) {
            mFragmentContainer.setVisibility(View.VISIBLE);
        }
        if (mWebContainer.getVisibility() == View.VISIBLE) {
            mWebContainer.setVisibility(View.GONE);
        }
        transaction.replace(R.id.fragment_container, mLoginFragment);
        transaction.commit();
    }

    private void showRegisterFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mFragmentContainer.getVisibility() == View.GONE) {
            mFragmentContainer.setVisibility(View.VISIBLE);
        }
        if (mWebContainer.getVisibility() == View.VISIBLE) {
            mWebContainer.setVisibility(View.GONE);
        }
        transaction.add(R.id.fragment_container, mRegisterFragment, REGISTER_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    private static final String FORGET_PASSWORD_FRAGMENT = "forget_password_fragmetn";
    private static final String REGISTER_FRAGMENT = "register_fragment";

    private void showForgetFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mFragmentContainer.getVisibility() == View.GONE) {
            mFragmentContainer.setVisibility(View.VISIBLE);
        }
        if (mWebContainer.getVisibility() == View.VISIBLE) {
            mWebContainer.setVisibility(View.GONE);
        }
        ForgetPassFragmentWeb fragment = new ForgetPassFragmentWeb();
        transaction.add(R.id.fragment_container, fragment, FORGET_PASSWORD_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    private void initWebData(String customId) {
        if (mFragmentContainer.getVisibility() == View.VISIBLE) {
            mFragmentContainer.setVisibility(View.GONE);
        }
        if (mWebContainer.getVisibility() == View.GONE) {
            mWebContainer.setVisibility(View.VISIBLE);
        }
        showWeb(customId);

    }

    private void ss() {
        Object body = CommSharedUtil.getInstance(getActivity()).getBeanFromSp("CustomStyleBody");
        if (body != null && (body instanceof CustomStyleBody)) {
            CustomStyleBody styleBody = (CustomStyleBody) body;
            if (styleBody != null) {
                HangXunBiz.getInstance().setCustomerStyle(styleBody.getSex(), styleBody.getAge(), styleBody.getInterest(),
                        styleBody.getIndustryId(), styleBody.getCompanyName(), new ResponseListener<BaseBean>() {
                            @Override
                            public void onFail(int code, String message) {

                            }

                            @Override
                            public void onSuccess(BaseBean baseBean) {
                                CommSharedUtil.getInstance(getActivity()).saveBean2Sp("CustomStyleBody", null);

                            }
                        });
            }
        }
    }

    private void showWeb(String customId) {
        String url = ApiConstants.BASE_URL + ApiConstants.ACCOUNT_PAGE_PATH + customId
                + ApiConstants.LANGUAGE_PATH + LanguageSp.getInstance().getCode()
                + ApiConstants.CURRENCY_PATH + CurrencySp.getInstance().getCode();
        if (mWebContainer == null) {
            return;
        }
        mWebContainer.loadUrl(url);
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (LoginUtils.getInstance().isLogin()) {
            }
        } else {
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!LoginUtils.getInstance().isLogin()) {
            showLoginFragment();
        } else {
            initWebData(LoginUtils.getInstance().getScoId());
        }
    }

    @Override
    public boolean onBackPressed() {
        if (mFragmentManager != null) {
            Fragment fragment1 = mFragmentManager.findFragmentByTag(FORGET_PASSWORD_FRAGMENT);
            if (fragment1 instanceof ForgetPassFragmentWeb) {
                ForgetPassFragmentWeb fragmentWeb = (ForgetPassFragmentWeb) fragment1;
                if (fragmentWeb.onBackPressed()) {
                    return true;
                }
                int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
                if (backStackEntryCount > 0) {
                    mFragmentManager.popBackStack();
                    return true;
                }
            }
            Fragment fragment2 = mFragmentManager.findFragmentByTag(REGISTER_FRAGMENT);
            if (fragment2 instanceof RegisterFragment) {
                RegisterFragment fragmentWeb = (RegisterFragment) fragment2;

                int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
                if (backStackEntryCount > 0) {
                    mFragmentManager.popBackStack();
                    return true;
                }
            }
        }
        if (mWebContainer != null && mWebContainer.canGoBack()) {
            mWebContainer.goBack();
            return true;
        }
        return false;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLogin(MessageLogin message) {
        if (TextUtils.equals(message.message, MessageLogin.LOGIN_OUT)) {
            if (mWebContainer.getVisibility() == View.VISIBLE) {
                mWebContainer.setVisibility(View.GONE);
                mWebContainer.clearHistory();
            }
            showLoginFragment();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeCurrency(MessageLocal message) {
        if (TextUtils.equals(message.message, MessageLocal.CURRENCY_CHANGE)) {

        } else if (TextUtils.equals(message.message, MessageLocal.LANGUAGE_CHANGE)) {
            if (LoginUtils.getInstance().isLogin()) {
                showWeb(LoginUtils.getInstance().getScoId());
            }
        }
    }
}