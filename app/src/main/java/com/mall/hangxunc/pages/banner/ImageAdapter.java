package com.mall.hangxunc.pages.banner;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.BannersBean;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;


/**
 * 自定义布局，图片
 */
public class ImageAdapter extends BannerAdapter<BannersBean, ImageHolder> {

    private Context mContext;

    public ImageAdapter(Context context, List<BannersBean> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
        mContext = context;
    }

    //更新数据
    public void updateData(List<BannersBean> data) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, BannersBean data, int position, int size) {

        RequestOptions options = new RequestOptions()
                .error(R.mipmap.new_banner)
                .placeholder(R.mipmap.new_banner)
                .bitmapTransform(new RoundedCorners(30));
        Glide.with(mContext)
                .load(data.getImage())
                .apply(options)
                .into(holder.imageView);
    }

}
