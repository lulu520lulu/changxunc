package com.c.hangxunc.pages.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.c.hangxunc.R;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.pages.login.ForgetPassFragmentWeb;
import com.c.hangxunc.pages.login.LoginFragment;
import com.c.hangxunc.pages.login.RegisterFragment;
import com.c.hangxunc.utils.LanguageUtils;
import com.c.hangxunc.utils.LoginUtils;
import com.just.agentweb.AgentWeb;

public class PersonalFragment extends BaseFragment<PersonalPresenter> {

    private PersonalPresenter mPersonalPresenter;
    private FrameLayout mWebContainer;
    private FrameLayout mFragmentContainer;
    private LoginFragment mLoginFragment;
    private RegisterFragment mRegisterFragment;
    private AgentWeb mAgentWeb;

    @Override
    protected PersonalPresenter onCreateTopPresenter() {
        mPersonalPresenter = new PersonalPresenter(getActivity());
        return mPersonalPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, null);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mWebContainer = view.findViewById(R.id.web_container);
        mFragmentContainer = view.findViewById(R.id.fragment_container);

        initFragment();

        if (!LoginUtils.getInstance().isLogin()) {
            showLoginFragment();
        } else {
            initWebData(LoginUtils.getInstance().getCustomerId());
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
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
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
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mFragmentContainer.getVisibility() == View.GONE) {
            mFragmentContainer.setVisibility(View.VISIBLE);
        }
        if (mWebContainer.getVisibility() == View.VISIBLE) {
            mWebContainer.setVisibility(View.GONE);
        }
        transaction.replace(R.id.fragment_container, mRegisterFragment);
        transaction.commit();
    }

    private void showForgetFragment() {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mFragmentContainer.getVisibility() == View.GONE) {
            mFragmentContainer.setVisibility(View.VISIBLE);
        }
        if (mWebContainer.getVisibility() == View.VISIBLE) {
            mWebContainer.setVisibility(View.GONE);
        }
        ForgetPassFragmentWeb fragment = new ForgetPassFragmentWeb();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void initWebData(String customId) {
        if (mFragmentContainer.getVisibility() == View.VISIBLE) {
            mFragmentContainer.setVisibility(View.GONE);
        }
        if (mWebContainer.getVisibility() == View.GONE) {
            mWebContainer.setVisibility(View.VISIBLE);
        }
        String url = ApiConstants.BASE_URL + ApiConstants.ACCOUNT_PAGE_PATH + customId;
//                ApiConstants.LANGUAGE_PATH + LanguageUtils.getInstance().getCode();
        if (TextUtils.isEmpty(url)) {
            return;
        }

        mAgentWeb = AgentWeb.with(getActivity())//传入Activity
                .setAgentWebParent(mWebContainer, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()// 使用默认进度条
                .setWebViewClient(new com.just.agentweb.WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(url);
                        return true;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        view.loadUrl("javascript:pageBackHid()");
                        view.loadUrl("javascript:bottomTabMenu()");
                    }
                })
                .createAgentWeb()
                .ready()
                .go(url);

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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (!LoginUtils.getInstance().isLogin()) {
                showLoginFragment();
            } else {
                initWebData(LoginUtils.getInstance().getCustomerId());
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!LoginUtils.getInstance().isLogin()) {
            showLoginFragment();
        } else {
            initWebData(LoginUtils.getInstance().getCustomerId());
        }
    }

}