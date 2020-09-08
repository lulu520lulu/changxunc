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
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.center.NavBean;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.List;

public class CenterNavAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<NavBean> mData;

    public CenterNavAdapter(Context mContext, List<NavBean> products) {
        this.mContext = mContext;
        this.mData = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.center_home_nav_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NavBean item = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;


        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int parentPadding = DimenUtils.dip2px(15);

        int width = (windowWidth - 2 * parentPadding) / 4;
        ViewGroup.LayoutParams params = viewHolder.container.getLayoutParams();
        params.width = width;

        viewHolder.text.setText(item.getName());

        RequestOptions options = new RequestOptions()
                .error(R.mipmap.center_cheng_place_icon)
                .fallback(R.mipmap.center_cheng_place_icon)
                .placeholder(R.mipmap.center_cheng_place_icon);

        Glide.with(mContext)
                .load(item.getImgPath())
                .apply(options)
                .into(viewHolder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(item.getUrlPath());
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