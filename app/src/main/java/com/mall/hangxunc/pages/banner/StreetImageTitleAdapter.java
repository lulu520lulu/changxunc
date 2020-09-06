package com.mall.hangxunc.pages.banner;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.BannersBean;
import com.youth.banner.adapter.BannerAdapter;

import org.w3c.dom.Text;

import java.util.List;


/**
 * 自定义布局，图片+标题
 */

public class StreetImageTitleAdapter extends BannerAdapter<BannersBean, ImageTitleHolder> {
    private Context mContext;

    public StreetImageTitleAdapter(Context context, List<BannersBean> mDatas) {
        super(mDatas);
        this.mContext = context;
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_image_title, parent, false));
    }

    @Override
    public void onBindView(ImageTitleHolder holder, BannersBean data, int position, int size) {
        if (TextUtils.isEmpty(data.getTitle())) {
            holder.title.setVisibility(View.GONE);
        } else {
            holder.title.setText(data.getTitle());
            holder.title.setVisibility(View.VISIBLE);
        }
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.new_banner)
                .placeholder(R.mipmap.new_banner);
        Glide.with(mContext)
                .load(data.getImage())
                .apply(options)
                .into(holder.imageView);
    }

}
