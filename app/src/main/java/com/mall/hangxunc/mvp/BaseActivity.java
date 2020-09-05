//package com.c.hangxunc.mvp;
//
//import android.app.Activity;
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
//
//import com.c.hangxunc.mvp.IPresenter;
//import com.c.hangxunc.mvp.IView;
//
//public abstract class BaseActivity<P extends IPresenter> extends Activity implements IView {
//
//    protected P mTopPresenter;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mTopPresenter = onCreateTopPresenter();
//        if (mTopPresenter != null) {
//            mTopPresenter.setView(this);
//            onCreateViewImpl(savedInstanceState);
//            mTopPresenter.onPageCreate();
//        }
//    }
//
//
//    protected void onCreateViewImpl(Bundle savedInstanceState) {
//
//    }
//
//    protected abstract P onCreateTopPresenter();
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        onResumeImpl();
//        if (mTopPresenter != null) {
//            mTopPresenter.onPageResume();
//        }
//    }
//
//    private void onResumeImpl() {
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        onPauseImpl();
//        if (mTopPresenter != null) {
//            mTopPresenter.onPagePause();
//        }
//    }
//
//    private void onPauseImpl() {
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        onDestroyImpl();
//        if (mTopPresenter != null) {
//            mTopPresenter.onPageDestroy();
//        }
//    }
//
//    private void onDestroyImpl() {
//
//    }
//}
