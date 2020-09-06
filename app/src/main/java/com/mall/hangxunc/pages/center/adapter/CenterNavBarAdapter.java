package com.mall.hangxunc.pages.center.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.center.NavBarBean;
import com.mall.hangxunc.pages.center.center.NavBarChildBean;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.ArrayList;
import java.util.List;

public class CenterNavBarAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private List<NavBarBean> mData = new ArrayList<>();

    public CenterNavBarAdapter(Context context, List<NavBarBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.center_home_nav_bar_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        NavBarBean item = mData.get(position);
        viewHolder.title.setText(item.getName());


        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int parentPadding = DimenUtils.dip2px(15);
        int margin = DimenUtils.dip2px(10);

        int width = (windowWidth - 2 * parentPadding - 6 * margin) / 4;

        List<NavBarChildBean> products = item.getProducts();

        if (products != null) {
            for (int i = 0; i < products.size(); i++) {
                NavBarChildBean bean = products.get(i);
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, (width / 9) * 11);
                if (i % 2 == 0) {
                    params.rightMargin = margin;
                }

                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(params);


                RequestOptions options = new RequestOptions()
                        .error(R.mipmap.center_cheng_place_icon)
                        .fallback(R.mipmap.center_cheng_place_icon)
                        .placeholder(R.mipmap.center_cheng_place_icon)
                        .bitmapTransform(new RoundedCorners(30));

                String thumb = bean.getImgPath();
                Glide.with(mContext)
                        .load(thumb)
                        .apply(options)
                        .into(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String href = bean.getUrlPath();
                        if (!TextUtils.isEmpty(href)) {
                            JumpUtils.goWeb(href);
                        }
                    }
                });
                viewHolder.image_container.addView(imageView, params);
            }

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
        private LinearLayout image_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image_container = itemView.findViewById(R.id.image_container);
        }
    }
}