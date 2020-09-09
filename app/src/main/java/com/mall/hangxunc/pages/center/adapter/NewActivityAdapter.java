package com.mall.hangxunc.pages.center.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.center.ActivityChildBean;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.List;

public class NewActivityAdapter extends RecyclerView.Adapter {
    private Activity mContext;
    private List<ActivityChildBean.ListBean> mData;

    public NewActivityAdapter(Activity mContext, List<ActivityChildBean.ListBean> posts) {
        this.mContext = mContext;
        this.mData = posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.center_home_new_active_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ActivityChildBean.ListBean bean = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;

        List<String> picUrlList = bean.getPicUrlList();
        String imageUrl = "";
        if (picUrlList != null && picUrlList.size() > 0) {
            imageUrl = picUrlList.get(0);
        }

        viewHolder.itemView.setPadding(0, 0, DimenUtils.dip2px(15), 0);

        viewHolder.title.setText(bean.getName());
        viewHolder.date.setText(bean.getStartTime());
        viewHolder.position.setText(bean.getCountry());

        int windowWidth = WindowUtils.getWindowWidth(mContext);
        int padding = DimenUtils.dip2px(5);
        int width = windowWidth / 3;
        ViewGroup.LayoutParams params = viewHolder.image.getLayoutParams();
        params.width = width;
        params.height = (int) (3 * width / 4);
        viewHolder.image.setLayoutParams(params);


        RequestOptions options = new RequestOptions()
                .error(R.mipmap.center_cheng_place_icon)
                .fallback(R.mipmap.center_cheng_place_icon)
                .placeholder(R.mipmap.center_cheng_place_icon);
        Glide.with(mContext)
                .load(imageUrl)
                .apply(options)
                .into(viewHolder.image);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(bean.getDetailsLink());
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
        private TextView date;
        private TextView position;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);
            position = itemView.findViewById(R.id.position);
            title = itemView.findViewById(R.id.title);
        }
    }
}
