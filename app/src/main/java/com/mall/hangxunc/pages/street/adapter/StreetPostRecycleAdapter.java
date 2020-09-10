package com.mall.hangxunc.pages.street.adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
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
import com.mall.hangxunc.bean.home.PostsBean;
import com.mall.hangxunc.pages.street.http.StreetApiConstants;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.List;

class StreetPostRecycleAdapter extends RecyclerView.Adapter {
    private Activity mContext;
    private List<PostsBean> mData;

    public StreetPostRecycleAdapter(Activity mContext, List<PostsBean> posts) {
        this.mContext = mContext;
        this.mData = posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.street_list_post_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostsBean bean = mData.get(position);

        MyViewHolder viewHolder = (MyViewHolder) holder;
        String date_added = bean.getDate_modified();
//        String[] split = date_added.split(" ");
        viewHolder.date.setText(date_added);
        viewHolder.content.setText(bean.getName());
        int windowWidth = WindowUtils.getWindowWidth(mContext);
        int padding = DimenUtils.dip2px(5);
        int width = windowWidth / 2 - 2 * padding;
        ViewGroup.LayoutParams params = viewHolder.image.getLayoutParams();
        params.width = width;
        params.height = (int) (3.3f * width / 5);
        viewHolder.image.setLayoutParams(params);

        RequestOptions options = new RequestOptions()
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image);
        Glide.with(mContext)
                .load(StreetApiConstants.IMAGE_BASE_URL + "/" + bean.getImage())
                .apply(options)
                .into(viewHolder.image);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(StreetApiConstants.BASE_URL + bean.getHref());
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
        private TextView date;
        private TextView content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);
            content = itemView.findViewById(R.id.content);
        }
    }
}
