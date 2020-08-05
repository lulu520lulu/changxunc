package com.c.hangxunc.pages.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.bean.home.TabsBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.JumpUtils;
import com.c.hangxunc.utils.ToastUtils;
import com.c.hangxunc.utils.WindowUtils;

import java.util.ArrayList;
import java.util.List;

class TabRecycleAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private List<TabsBean> mData = new ArrayList<>();

    public TabRecycleAdapter(Context context, List<TabsBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_tab_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        TabsBean item = mData.get(position);
        viewHolder.title.setText(item.getName());
        viewHolder.content.setText(item.getNameSub());

        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int parentPadding = DimenUtils.dip2px(6);
        int margin = DimenUtils.dip2px(8);

        int width = (windowWidth / 2 - parentPadding - 2 * margin - margin) / 2;

        List<ProductBean> products = item.getProducts();

        if (products != null) {
            for (int i = 0; i < products.size(); i++) {
                ProductBean bean = products.get(i);
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
                if (i % 2 == 0) {
                    params.rightMargin = margin;
                }
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(params);
                Glide.with(mContext)
                        .load(bean.getThumb())
                        .error(R.mipmap.place_image)
                        .placeholder(R.mipmap.place_image)
                        .into(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpUtils.goWeb(bean.getHref());
                    }
                });
                viewHolder.image_container.addView(imageView, params);
            }

        }
        if (position % 2 == 0) {
            viewHolder.line.setVisibility(View.VISIBLE);
        } else {
            viewHolder.line.setVisibility(View.GONE);
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

        private TextView title;
        private TextView content;
        private LinearLayout image_container;
        private View line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            image_container = itemView.findViewById(R.id.image_container);
            line = itemView.findViewById(R.id.line);
        }
    }
}