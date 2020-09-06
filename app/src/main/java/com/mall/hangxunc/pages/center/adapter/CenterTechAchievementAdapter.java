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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.center.TechAchievementChildBean;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.List;

public class CenterTechAchievementAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<TechAchievementChildBean.ListBean> mData;

    public CenterTechAchievementAdapter(Activity mContext, List<TechAchievementChildBean.ListBean> list) {
        this.mData = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.center_home_techachievement_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TechAchievementChildBean.ListBean item = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;

        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int parentPadding = DimenUtils.dip2px(15);
        int margin = DimenUtils.dip2px(10);

        int width = (windowWidth - 2 * parentPadding - margin) / 2;

        RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) viewHolder.image.getLayoutParams();
        imageParams.width = width;
        imageParams.height = (width / 3) * 4;
        if (position % 2 == 0) {
            imageParams.rightMargin = margin;
        }

        if (TextUtils.isEmpty(item.getUrl())) {
            viewHolder.image.setImageResource(R.mipmap.center_cheng_place_icon);
        } else {
            RequestOptions options = new RequestOptions()
                    .error(R.mipmap.center_cheng_place_icon)
                    .placeholder(R.mipmap.center_cheng_place_icon)
                    .fallback(R.mipmap.center_cheng_place_icon)
                    .bitmapTransform(new RoundedCorners(30));
            Glide.with(mContext)
                    .load(item.getUrl())
                    .apply(options)
                    .into(viewHolder.image);
        }

        viewHolder.title.setText(item.getName());
        viewHolder.content.setText(item.getIntroduce());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        String linkman = item.getLinkman();
        if (!TextUtils.isEmpty(linkman)) {
            TextView textView = new TextView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.rightMargin = DimenUtils.dip2px(6);
            textView.setPadding(DimenUtils.dip2px(6), DimenUtils.dip2px(2), DimenUtils.dip2px(6), DimenUtils.dip2px(2));
            textView.setText(linkman);
            textView.setTextColor(mContext.getResources().getColor(R.color.white));
            textView.setBackgroundResource(R.drawable.center_blue_tag_bg);
            viewHolder.tag_container.addView(textView, layoutParams);
            viewHolder.tag_container.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tag_container.setVisibility(View.GONE);
        }
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
        private TextView content;
        private LinearLayout tag_container;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            tag_container = itemView.findViewById(R.id.tag_container);
        }
    }
}
