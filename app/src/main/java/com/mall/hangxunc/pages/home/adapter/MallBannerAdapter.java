//package com.c.hangxunc.pages.home.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.bumptech.glide.request.RequestOptions;
//import com.c.hangxunc.R;
//import com.bumptech.glide.Glide;
//import com.c.hangxunc.bean.home.BannersBean;
//import com.c.hangxunc.http.ApiConstants;
//import com.c.hangxunc.utils.DimenUtils;
//import com.c.hangxunc.utils.JumpUtils;
//import com.c.hangxunc.utils.WindowUtils;
//
//import java.util.List;
//
//class MallBannerAdapter extends RecyclerView.Adapter {
//    private Context mContext;
//    private List<BannersBean> mData;
//
//    public MallBannerAdapter(Context mContext, List<BannersBean> products) {
//        this.mContext = mContext;
//        this.mData = products;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.mall_list_banner_item, parent, false);
//        MyViewHolder holder = new MyViewHolder(view);
//        return holder;
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        BannersBean bean = mData.get(position);
//
//        MyViewHolder viewHolder = (MyViewHolder) holder;
//        viewHolder.text.setText(bean.getTitle());
//
//        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
//        int padding = DimenUtils.dip2px(5);
//        int width = windowWidth / 2 - 2 * padding;
//        ViewGroup.LayoutParams params = viewHolder.image.getLayoutParams();
//        params.width = width;
//        params.height = width;
//        viewHolder.image.setLayoutParams(params);
//
//        RequestOptions options = new RequestOptions()
//                .error(R.mipmap.new_banner)
//                .placeholder(R.mipmap.new_banner)
//                .bitmapTransform(new RoundedCorners(30));
//        Glide.with(mContext)
//                .load(bean.getImage())
//                .apply(options)
//                .into(viewHolder.image);
//
//        if (viewHolder.itemView != null) {
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    JumpUtils.goWeb(ApiConstants.BASE_URL + bean.getLink());
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mData == null) {
//            return 0;
//        }
//        return mData.size();
//    }
//
//    private class MyViewHolder extends RecyclerView.ViewHolder {
//        private ImageView image;
//        private TextView text;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            image = itemView.findViewById(R.id.image);
//            text = itemView.findViewById(R.id.text);
//        }
//    }
//}
