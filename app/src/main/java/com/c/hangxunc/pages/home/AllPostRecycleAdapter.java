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
import com.c.hangxunc.bean.home.PostsBean;
import com.c.hangxunc.http.ApiConstants;

import java.util.ArrayList;

public class AllPostRecycleAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<PostsBean> mData;

    public AllPostRecycleAdapter(Context context, ArrayList<PostsBean> list) {
        this.mContext = context;
        this.mData = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.all_post_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostsBean postsBean = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.title.setText(postsBean.getName());
        viewHolder.content.setText(postsBean.getContent());
        Glide.with(mContext)
                .load(ApiConstants.IMAGE_BASE_URL + "/" + postsBean.getImage())
                .error(R.mipmap.new_banner)
                .placeholder(R.mipmap.new_banner)
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