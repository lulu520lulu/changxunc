package com.c.hangxunc.pages.home.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.c.hangxunc.R;
import com.c.hangxunc.pages.street.StreetHomeActivity;

public class MallChangeIdentityWindow extends PopupWindow implements View.OnClickListener {

    private View mPopView;
    private TextView mName;
    private TextView mGoCenter;
    private Activity mActivity;
    private View mContainer;

    public MallChangeIdentityWindow(Activity context) {
        super(context);
        this.mActivity = context;
        init(context);
        setPopupWindow();
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPopView = inflater.inflate(R.layout.mall_change_identity_popup_layout, null);
        mContainer = mPopView.findViewById(R.id.container);

        mName = (TextView) mPopView.findViewById(R.id.name);
        mGoCenter = (TextView) mPopView.findViewById(R.id.go_center);
        mName.setOnClickListener(this);
        mGoCenter.setOnClickListener(this);
    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.DetailAnimation);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        this.setOutsideTouchable(true);
        this.setOnDismissListener(new OnDismissListener() { //退出popupwidon时显示父控件原来的颜色
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                lp.alpha = 1.0f;
                mActivity.getWindow().setAttributes(lp);
            }
        });
        mPopView.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int bottom = mContainer.getBottom();
                int left = mContainer.getLeft();

                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > bottom && x < left) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name:
                goPerson();
                break;
            case R.id.go_center:
                goCenter();
                break;
        }
        dismiss();
    }

    private void goPerson() {

    }

    private void goCenter() {
        if (mActivity == null) {
            return;
        }
        Intent intent = new Intent(mActivity, StreetHomeActivity.class);
        mActivity.startActivity(intent);
    }


}