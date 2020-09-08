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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.center.InstrumentChildBean;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.ArrayList;
import java.util.List;

public class CenterInstrumentAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<InstrumentChildBean.ListBean.ListChildBean> mData=new ArrayList<>();

    public CenterInstrumentAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void setData(List<InstrumentChildBean.ListBean.ListChildBean> list) {
        if (mData != null) {
            mData.clear();
        }
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.center_home_instrument_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InstrumentChildBean.ListBean.ListChildBean item = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;

        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int parentPadding = DimenUtils.dip2px(12);
        int margin = DimenUtils.dip2px(8);
        int marginSelf = DimenUtils.dip2px(10);


        int width = windowWidth / 2 - parentPadding - margin - 2 * marginSelf;

        LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams) viewHolder.image.getLayoutParams();
        imageParams.width = width;
        imageParams.height = (width / 3) * 4;

        List<String> imgUrlList = item.getImgUrlList();
        String imageUrl = "";
        if (imgUrlList != null && imgUrlList.size() > 0) {
            imageUrl = imgUrlList.get(0);

        }
        if (TextUtils.isEmpty(imageUrl)) {
            viewHolder.image.setImageResource(R.mipmap.center_cheng_place_icon);
        } else {
            RequestOptions options = new RequestOptions()
                    .error(R.mipmap.center_cheng_place_icon)
                    .placeholder(R.mipmap.center_cheng_place_icon)
                    .fallback(R.mipmap.center_cheng_place_icon)
                    .bitmapTransform(new RoundedCorners(30));

            Glide.with(mContext)
                    .load(imageUrl)
                    .apply(options)
                    .into(viewHolder.image);
        }


        viewHolder.content.setText(item.getZygn());
        viewHolder.title.setText(item.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(item.getDetailsLink());
            }
        });
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


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
