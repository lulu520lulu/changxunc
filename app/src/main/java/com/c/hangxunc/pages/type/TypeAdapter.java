package com.c.hangxunc.pages.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CategoryChildBean;
import com.c.hangxunc.pages.widget.BottomView;
import com.c.hangxunc.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<CategoryChildBean> mData = new ArrayList<>();

    public TypeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<CategoryChildBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_type_type, parent, false);
        return new TypeAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TypeAdapter.VH vh = (TypeAdapter.VH) holder;
        CategoryChildBean item = mData.get(position);
        vh.textView.setText(item.getName());
        List<CategoryChildBean.ChildrenBean> childrenList = item.getChildren();

        TypeChildAdapter childAdapter = new TypeChildAdapter(mContext, childrenList);
        vh.gradview.setLayoutManager(new GridLayoutManager(mContext, 3));
        vh.gradview.setAdapter(childAdapter);

        vh.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(item.getHref());
            }
        });
        if ((position + 1) == getItemCount()) {
            vh.bottomView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
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
