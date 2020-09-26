package com.mall.hangxunc.pages.type;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.BrandListBean;
import com.mall.hangxunc.bean.home.CategoryChildBean;
import com.mall.hangxunc.bean.home.ModulesBean;
import com.mall.hangxunc.bean.home.TypeChildrenBean;
import com.mall.hangxunc.pages.widget.BottomView;
import com.mall.hangxunc.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<BrandListBean.CategoriesBean.HBean> mData = new ArrayList<>();
    private BrandListBean.CategoriesBean data2;

    public BrandAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<BrandListBean.CategoriesBean.HBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mall_adapter_type_brand, parent, false);
        return new BrandAdapter.VH(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BrandAdapter.VH vh = (BrandAdapter.VH) holder;

        BrandListBean.CategoriesBean.HBean item = mData.get(position);
        List<TypeChildrenBean> manufacturer = item.getManufacturer();
        vh.gradview.setVisibility(View.VISIBLE);
        BrandChilAdapter childAdapter = new BrandChilAdapter(mContext, manufacturer);
        vh.gradview.setLayoutManager(new GridLayoutManager(mContext,3));
        vh.gradview.setAdapter(childAdapter);
        vh.textView.setText(item.getName());

        if ((position + 1) == getItemCount()) {
            vh.bottomView.setVisibility(View.VISIBLE);
        }

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
        private RecyclerView gradview;
        private BottomView bottomView;

        public VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            gradview = itemView.findViewById(R.id.gradview);
            bottomView = itemView.findViewById(R.id.bottom_view);
        }
    }


}
