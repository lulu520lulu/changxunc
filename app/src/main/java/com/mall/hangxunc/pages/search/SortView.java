//package com.c.hangxunc.pages.search;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//
//import com.c.hangxunc.R;
//import com.c.hangxunc.bean.home.BreadCrumbsBean;
//import com.c.hangxunc.utils.DimenUtils;
//import com.c.hangxunc.utils.HangLog;
//
//import java.util.List;
//
//public class SortView extends LinearLayout {
//
//    //    private CheckBox mCheckBox;
//    private TextView mSortView;
//    private FrameLayout mContainer;
//    private SortOnClickListener mSortOnClickListener;
//
//    public SortView(Context context) {
//        super(context);
//        initView(context);
//    }
//
//    public SortView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        initView(context);
//    }
//
//
//    private void initView(Context context) {
//        inflate(context, R.layout.search_sort_view, this);
////        mCheckBox = findViewById(R.id.sort_view);
////        mCheckBox.setEnabled(false);
//        mSortView = findViewById(R.id.sort_view);
//        mContainer = findViewById(R.id.container);
//        mContainer.setClickable(true);
//        mSortView.setClickable(false);
//        mContainer.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean selected = mSortView.isSelected();
//                if (selected) {
//                    mSortData.setTop(true);
//                } else {
//                    mSortData.setTop(false);
//                    mSortView.setSelected(true);
//                }
//
//                if (mSortOnClickListener != null) {
//                    mSortOnClickListener.onSortSelectListener();
//                }
//            }
//        });
//    }
//
//
//    public void setSortClickListener(SortOnClickListener listener) {
//        this.mSortOnClickListener = listener;
//    }
//
//
//    private SortData mSortData;
//
//    public SortData getSort() {
//        return mSortData;
//    }
//
//    /**
//     * @param name
//     * @param sort
//     * @param bean
//     */
//    public void update(boolean isCheck, String name, String sort, List<BreadCrumbsBean.SortsBean> bean) {
//        if (bean == null) {
//            return;
//        }
//        mSortView.setText(name);
//        mSortView.setTextColor(getResources().getColor(R.color.mall_main_text));
//        mSortView.setSelected(false);
//        HangLog.e("sort:" + sort);
//        for (int i = 0; i < bean.size(); i++) {
//            BreadCrumbsBean.SortsBean sortsBean = bean.get(i);
//            String text = sortsBean.getText();
//            String[] split = text.split(" ");
//            String s = split[0];
//            HangLog.e("s:" + s + ",value:" + sortsBean.getValue());
//
//            if (TextUtils.equals(s, name)) {
//                if (TextUtils.equals(sort, sortsBean.getValue())) {
//                    mSortView.setTextColor(getResources().getColor(R.color.mall_main_blue_text));
//                    mSortView.setSelected(true);
//                }
//            }
//        }
//        Drawable drawable = null;
//        if (!TextUtils.equals(name, "默认")) {
//            if (isCheck) {
//                drawable = getResources().getDrawable(R.mipmap.bottom_arrow);
//                // 这一步必须要做,否则不会显示.
//                drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
//            } else {
//                drawable = getResources().getDrawable(R.mipmap.search_two_arrow);
//                // 这一步必须要做,否则不会显示.
//                drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
//            }
//
//        } else {
//            if (isCheck) {
//                if (mSortData.isTop()) {
//                    drawable = getResources().getDrawable(R.mipmap.top_arrow);
//                    // 这一步必须要做,否则不会显示.
//                    drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
//                } else {
//                    drawable = getResources().getDrawable(R.mipmap.bottom_arrow);
//                    // 这一步必须要做,否则不会显示.
//                    drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
//                }
//            } else {
//                drawable = getResources().getDrawable(R.mipmap.search_two_arrow);
//                // 这一步必须要做,否则不会显示.
//                drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
//            }
//        }
//
//    }
//}
