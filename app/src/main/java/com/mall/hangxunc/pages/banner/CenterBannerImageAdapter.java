package com.mall.hangxunc.pages.banner;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.center.BannerBean;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;


public class CenterBannerImageAdapter extends BannerAdapter<BannerBean, ImageHolder> {

    private Context mContext;

    public CenterBannerImageAdapter(Context context, List<BannerBean> mDatas) {
        //MultipleTypesAdapter，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
        mContext = context;
    }

    //更新数据
    public void updateData(List<BannerBean> data) {
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
    public void onBindView(ImageHolder holder, BannerBean data, int position, int size) {
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.center_banner)
                .placeholder(R.mipmap.center_banner);
        Glide.with(mContext)
                .load(data.getImgPath())
                .apply(options)
                .into(holder.imageView);
    }
}
   