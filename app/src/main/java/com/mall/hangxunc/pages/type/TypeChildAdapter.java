package com.mall.hangxunc.pages.type;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.bumptech.glide.Glide;
import com.mall.hangxunc.bean.home.CategoryChildBean;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.WindowUtils;

import java.util.List;

public class TypeChildAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<CategoryChildBean.ChildrenBean> mData;

    public TypeChildAdapter(Context mContext, List<CategoryChildBean.ChildrenBean> data) {
        this.mContext = mContext;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mall_adapter_type_type_item, parent, false);
        return new TypeChildAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TypeChildAdapter.VH vh = (TypeChildAdapter.VH) holder;
        CategoryChildBean.ChildrenBean categoryChildBean = mData.get(position);
        if (TextUtils.isEmpty(categoryChildBean.getName())) {
            vh.textView.setVisibility(View.GONE);
        } else {
            vh.textView.setText(categoryChildBean.getName());
            vh.textView.setVisibility(View.VISIBLE);
        }

        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int padding = DimenUtils.dip2px(6);
        int width = (windowWidth - 6 * padding) / 3;
        ViewGroup.LayoutParams params = vh.imageView.getLayoutParams();
        params.height = width;
        params.width = width;


        RequestOptions options = new RequestOptions()
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image)
                .bitmapTransform(new RoundedCorners(30));
        Glide.with(mContext)
                .load(categoryChildBean.getThumb())
                .apply(options)
                .into(vh.imageView);

        if (!TextUtils.isEmpty(categoryChildBean.getHref())) {
            vh.itemView.setClickable(true);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.goWeb(categoryChildBean.getHref());
                }
            });
        } else {
            vh.itemView.setClickable(false);
            vh.imageView.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class VH extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
        }
    }


}
