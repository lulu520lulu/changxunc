package com.mall.hangxunc.pages.home.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.mall.hangxunc.bean.home.ItemsBean;
import com.mall.hangxunc.http.ApiConstants;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.List;

public class MallItemListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ItemsBean> mData;

    public MallItemListAdapter(Context mContext, List<ItemsBean> products) {
        this.mContext = mContext;
        this.mData = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mall_home_item_item_layout, parent, false);
        MallItemListAdapter.MyViewHolder holder = new MallItemListAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemsBean item = mData.get(position);
        MallItemListAdapter.MyViewHolder viewHolder = (MallItemListAdapter.MyViewHolder) holder;


        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int parentPadding = DimenUtils.dip2px(10);

        int width = (windowWidth - 2 * parentPadding) / 4;
        ViewGroup.LayoutParams params = viewHolder.container.getLayoutParams();
        params.width = width;

        int padding = DimenUtils.dip2px(16);

        if (position / 2 == 0) {
            viewHolder.container.setPadding(0, 0, 0, padding);
        }
        viewHolder.text.setText(item.getTitle());

        RequestOptions options = new RequestOptions()
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image)
                .bitmapTransform(new RoundedCorners(30));
        Glide.with(mContext)
                .load(item.getImage())
                .apply(options)
                .into(viewHolder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(ApiConstants.BASE_URL + "/" + item.getHref());
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

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView text;
        private LinearLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            container = itemView.findViewById(R.id.container);
        }
    }
}