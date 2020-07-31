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

import com.bumptech.glide.Glide;
import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.JumpUtils;
import com.c.hangxunc.utils.ToastUtils;

import java.util.List;

class ProductAllAdapter  extends RecyclerView.Adapter {
    private Context mContext;
    private List<ProductBean> mData;

    public ProductAllAdapter(Activity mContext, List<ProductBean> list) {
        this.mData = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_bottom_product_item, parent, false);
        ProductAllAdapter.MyViewHolder holder = new ProductAllAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductBean item = mData.get(position);
        ProductAllAdapter.MyViewHolder viewHolder = (ProductAllAdapter.MyViewHolder) holder;
        viewHolder.content.setText(item.getName());
        Glide.with(mContext)
                .load(item.getThumb())
                .error(R.mipmap.new_banner)
                .placeholder(R.mipmap.new_banner)
                .into(viewHolder.image);
        viewHolder.price_text.setText(item.getPrice());
        expandTouchArea(viewHolder.add_shop, DimenUtils.dip2px(20));
        viewHolder.add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HangXunBiz.getInstance().addShopCart(item.getProduct_id(), item.getQuantity(), new ResponseListener() {
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
                    JumpUtils.goWeb(item.getHref());
                }
            });
        }
        viewHolder.add_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        private ImageView add_shop;
        private TextView content;
        private TextView price_text;
        private ImageView add_love;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            content = itemView.findViewById(R.id.content);
            price_text = itemView.findViewById(R.id.price_text);
            add_shop = itemView.findViewById(R.id.add_shop);
            add_love = itemView.findViewById(R.id.add_love);
        }
    }
}
