package com.mall.hangxunc.pages.center.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.center.NewsChildBean;
import com.mall.hangxunc.utils.JumpUtils;

import java.util.List;

public class CenterNewsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<NewsChildBean.ListBean> mData;

    public CenterNewsAdapter(Activity mContext, List<NewsChildBean.ListBean> list) {
        this.mData = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.center_list_hot_item, parent, false);
        CenterNewsAdapter.MyViewHolder holder = new CenterNewsAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsChildBean.ListBean item = mData.get(position);
        CenterNewsAdapter.MyViewHolder viewHolder = (CenterNewsAdapter.MyViewHolder) holder;

        if (TextUtils.isEmpty(item.getImgUuid())) {
            viewHolder.image.setImageResource(R.mipmap.center_cheng_place_icon);
        } else {
            RequestOptions options = new RequestOptions()
                    .error(R.mipmap.center_cheng_place_icon)
                    .fallback(R.mipmap.center_cheng_place_icon)
                    .placeholder(R.mipmap.center_cheng_place_icon)
                    .bitmapTransform(new RoundedCorners(30));

            Glide.with(mContext)
                    .load(item.getImgUuid())
                    .apply(options)
                    .into(viewHolder.image);

        }

        viewHolder.title.setText(item.getNewsName());
        viewHolder.area_new.setText(item.getNewsTypeName());
        viewHolder.date.setText(item.getNewstime());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(item.getDetailsLink());
            }
        });

//        if ((position + 1) == mData.size()) {
//            viewHolder.line.setVisibility(View.GONE);
//        } else {
//            viewHolder.line.setVisibility(View.VISIBLE);
//        }
    }

    private void expandTouchArea(View view, int size) {
        View parentView = (View) view.getParent();
        parentView.post(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                view.getHitRect(rect);

                rect.top -= size;
                rect.bottom += size;
                rect.left -= size;
                rect.right += size;

                parentView.setTouchDelegate(new TouchDelegate(rect, view));
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
        private TextView title;
        private TextView area_new;
        private TextView date;
        private View line;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            area_new = itemView.findViewById(R.id.area_new);
            date = itemView.findViewById(R.id.date);
            line=itemView.findViewById(R.id.line);
        }
    }
}
