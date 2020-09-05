package com.mall.hangxunc.pages.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.CategoryBean;
import com.mall.hangxunc.pages.home.listener.MallCategoryClickListener;

import java.util.ArrayList;
import java.util.List;

public class MallHomeCategoryAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private MallCategoryClickListener mListener;
    private List<CategoryBean> mData = new ArrayList<>();

    public MallHomeCategoryAdapter(Context mContext, MallCategoryClickListener listener) {
        this.mContext = mContext;
        this.mListener = listener;
    }

    public void setData(List<CategoryBean> data) {
        mData.clear();
        mData.addAll(data);
        CategoryBean bean = new CategoryBean();
        bean.setName(mContext.getResources().getString(R.string.tab_home));
        bean.setSelected(true);
        mData.add(0, bean);
        notifyDataSetChanged();
    }

    public List<CategoryBean> getData() {
        return mData;
    }

    public void updateData(List<CategoryBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mall_adapter_home_category, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        CategoryBean categoryChildBean = mData.get(position);
        vh.textView.setText(categoryChildBean.getName());
//        Drawable drawable = null;
        if (categoryChildBean.isSelected()) {
            vh.textView.setTextColor(mContext.getResources().getColor(R.color.mall_main_red_text));
//            drawable = mContext.getResources().getDrawable(R.drawable.mall_radio_line_checked);
//            drawable.setBounds(0, 0, DimenUtils.dip2px(24), DimenUtils.dip2px(2));
        } else {
            vh.textView.setTextColor(mContext.getResources().getColor(R.color.mall_main_text));
//            drawable = mContext.getResources().getDrawable(R.drawable.mall_radio_line_un_check);
//            drawable.setBounds(0, 0, DimenUtils.dip2px(24), DimenUtils.dip2px(2));
        }

//        vh.textView.setCompoundDrawables(null, null, null, drawable);
        vh.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(mData, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    private class VH extends RecyclerView.ViewHolder {

        private TextView textView;

        public VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }


}
