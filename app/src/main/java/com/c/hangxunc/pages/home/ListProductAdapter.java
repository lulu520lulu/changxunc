package com.c.hangxunc.pages.home;

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
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.utils.JumpUtils;

import java.util.List;

class ListProductAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ProductBean> mData;

    public ListProductAdapter(Context mContext, List<ProductBean> products) {
        this.mContext = mContext;
        this.mData = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_product_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductBean productBean = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.text.setText(productBean.getPrice());
        Glide.with(mContext)
                .load(productBean.getThumb())
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image)
                .into(viewHolder.image);

        if (viewHolder.itemView != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.goWeb(productBean.getHref());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }
}
