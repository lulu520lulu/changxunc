package com.mall.hangxunc.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mall.hangxunc.pages.BackHandledInterface;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView {


    protected P mTopPresenter;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTopPresenter = onCreateTopPresenter();
        if (mTopPresenter != null) {
            mTopPresenter.setView(this);
            mRootView = onCreateViewImpl(inflater, container, savedInstanceState);
            mTopPresenter.onPageCreate();
            return mRootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    protected abstract P onCreateTopPresenter();

    @Override
    public void onResume() {
        super.onResume();
        onResumeImpl();
        if (mTopPresenter != null) {
            mTopPresenter.onPageResume();
        }
    }

    protected void onResumeImpl() {

    }

    @Override
    public void onPause() {
        super.onPause();
        onPauseImpl();
        if (mTopPresenter != null) {
            mTopPresenter.onPagePause();
        }
    }

    protected void onPauseImpl() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroyViewImpl();
        if (mTopPresenter != null) {
            mTopPresenter.onPageDestroy();
        }
        mRootView = null;
    }

    protected void onDestroyViewImpl() {

    }

    protected BackHandledInterface mBackHandledInterface;

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getActivity() instanceof BackHandledInterface)) {
            mBackHandledInterface = (BackHandledInterface) getActivity();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            setSelectedMe();
        }
    }

    protected void setSelectedMe() {
        if (mBackHandledInterface != null) {
            mBackHandledInterface.setSelectedFragment(this);
        }
    }

}
