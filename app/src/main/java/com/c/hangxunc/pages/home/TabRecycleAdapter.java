package com.c.hangxunc.pages.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.TabsBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.JumpUtils;
import com.c.hangxunc.utils.ToastUtils;
import com.c.hangxunc.utils.WindowUtils;

import java.util.ArrayList;
import java.util.List;

class TabRecycleAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private List<TabsBean.ProductsBeanX> mData = new ArrayList<>();

    public TabRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<TabsBean.ProductsBeanX> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_tab_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        TabsBean.ProductsBeanX beanX = mData.get(position);
        viewHolder.text.setText(beanX.getName());
        viewHolder.price_text.setText(beanX.getPrice());
        ViewGroup.LayoutParams params = viewHolder.image.getLayoutParams();
        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int padding = DimenUtils.dip2px(6);
        int width = windowWidth / 2 - 2 * padding;
        params.height = width;
        params.width = width;

        Glide.with(mContext)
                .load(beanX.getThumb())
                .into(viewHolder.image);
        expandTouchArea(viewHolder.add_shop, DimenUtils.dip2px(20));
        viewHolder.add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HangXunBiz.getInstance().addShopCart(beanX.getProduct_id(), beanX.getQuantity(), new ResponseListener() {
                    @Override
                    public void onFail(int code, String message) {
                        ToastUtils.showToast(mContext, mContext.getString(R.string.shop_cart_add_fail));
                    }

                    @Override
                    public void onSuccess(Object o) {
                        ToastUtils.showToast(mContext, mContext.getString(R.string.shop_cart_add_success));
                    }
                });
            }
        });
        if (viewHolder.itemView != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.goWeb(beanX.getHref());
                }
            });
        }
        viewHolder.add_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/7/29 添加收藏
            }
        });

        if (getItemCount() == position + 1) {
            viewHolder.space.setVisibility(View.GONE);
        } else {
            viewHolder.space.setVisibility(View.VISIBLE);
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
        private TextView text;
        private TextView price_text;
        private ImageView add_shop;
        private ImageView add_love;
        private View space;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            price_text = itemView.findViewById(R.id.price_text);
            add_shop = itemView.findViewById(R.id.add_shop);
            add_love = itemView.findViewById(R.id.add_love);
            space = itemView.findViewById(R.id.space);
        }
    }
}