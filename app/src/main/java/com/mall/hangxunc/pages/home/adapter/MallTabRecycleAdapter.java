package com.mall.hangxunc.pages.home.adapter;

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

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.bumptech.glide.Glide;
import com.mall.hangxunc.bean.home.ProductBean;
import com.mall.hangxunc.bean.home.TabsBean;
import com.mall.hangxunc.http.ApiConstants;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.ArrayList;
import java.util.List;

class MallTabRecycleAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private List<TabsBean> mData = new ArrayList<>();

    public MallTabRecycleAdapter(Context context, List<TabsBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mall_list_tab_item, parent, false);
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


                RequestOptions options = new RequestOptions()
                        .error(R.mipmap.place_image)
                        .placeholder(R.mipmap.place_image)
                        .bitmapTransform(new RoundedCorners(30));
                String thumb = bean.getThumb();
                if (!TextUtils.isEmpty(thumb) && !thumb.contains(ApiConstants.IMAGE_BASE_URL)) {
                    thumb = ApiConstants.IMAGE_BASE_URL + "/" + thumb;
                }
                Glide.with(mContext)
                        .load(thumb)
                        .apply(options)
                        .into(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String href = bean.getHref();
                        if (!href.contains(ApiConstants.BASE_URL)) {
                            href = ApiConstants.BASE_URL + "/" + href;
                        }
                        JumpUtils.goWeb(href);
                    }
                });
                viewHolder.image_container.addView(imageView, params);
            }

        }
        if (position % 2 == 0) {
            viewHolder.mall_line.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mall_line.setVisibility(View.GONE);
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
        private View mall_line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            image_container = itemView.findViewById(R.id.image_container);
            mall_line = itemView.findViewById(R.id.mall_line);
        }
    }
}