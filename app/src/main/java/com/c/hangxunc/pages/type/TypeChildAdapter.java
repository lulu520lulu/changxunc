package com.c.hangxunc.pages.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.CategoryChildBean;
import com.c.hangxunc.utils.JumpUtils;

import java.util.List;

public class TypeChildAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<CategoryChildBean.ChildrenBean> mData;

    public TypeChildAdapter(Context mContext, List<CategoryChildBean.ChildrenBean> data) {
        this.mContext = mContext;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_type_type_item, parent, false);
        return new TypeChildAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TypeChildAdapter.VH vh = (TypeChildAdapter.VH) holder;
        CategoryChildBean.ChildrenBean categoryChildBean = mData.get(position);

        vh.textView.setText(categoryChildBean.getName());

        Glide.with(mContext)
                .load(categoryChildBean.getThumb())
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image)
                .into(vh.imageView);
        vh.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(categoryChildBean.getHref());
            }
        });
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(categoryChildBean.getHref());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class VH extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
        }
    }


}
