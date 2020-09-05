package com.mall.hangxunc.pages.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class RecycleScrollView extends ScrollView {

    private int slop;
    private int touch;

    public RecycleScrollView(Context context) {
        super(context);
        setSlop(context);
    }

    public RecycleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSlop(context);

    }

    public RecycleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSlop(context);

    }

    private void setSlop(Context context) {
        slop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs((int) ev.getRawY() - touch) > slop) {
                    return true;
                }
        }

        return super.onInterceptTouchEvent(ev);

    }
}
