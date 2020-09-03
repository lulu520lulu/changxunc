package com.c.hangxunc.pages.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.PostsBean;
import com.c.hangxunc.http.ApiConstants;

import java.util.ArrayList;

public class MallAllPostRecycleAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<PostsBean> mData;

    public MallAllPostRecycleAdapter(Context context, ArrayList<PostsBean> list) {
        this.mContext = context;
        this.mData = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mall_all_post_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostsBean postsBean = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.title.setText(postsBean.getName());
        viewHolder.content.setText(postsBean.getContent());

        RequestOptions options = new RequestOptions()
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image)
                .bitmapTransform(new RoundedCorners(30));
        Glide.with(mContext)
                .load(ApiConstants.IMAGE_BASE_URL + "/" + postsBean.getImage())
                .apply(options)
                .into(viewHolder.image);


        viewHolder.read_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        private TextView content;
        private TextView read_all;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            read_all = itemView.findViewById(R.id.read_all);
        }
    }
}