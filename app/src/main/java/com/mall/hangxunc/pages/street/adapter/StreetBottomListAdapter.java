package com.mall.hangxunc.pages.street.adapter;

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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.ProductBean;
import com.mall.hangxunc.message.MessageShop;
import com.mall.hangxunc.pages.MainActivity;
import com.mall.hangxunc.pages.street.http.StreetHangXunBiz;
import com.mall.hangxunc.pages.street.http.StreetResponseListener;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.LoginUtils;
import com.mall.hangxunc.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

class StreetBottomListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ProductBean> mData;

    public StreetBottomListAdapter(Activity mContext, List<ProductBean> list) {
        this.mData = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.street_list_bottom_product_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductBean item = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.content.setText(item.getName());

        RequestOptions options = new RequestOptions()
                .error(R.mipmap.new_banner)
                .placeholder(R.mipmap.new_banner)
                .bitmapTransform(new RoundedCorners(2));
        Glide.with(mContext)
                .load(item.getThumb())
                .apply(options)
                .into(viewHolder.image);

        viewHolder.price_text.setText(item.getPrice());
        expandTouchArea(viewHolder.add_shop, DimenUtils.dip2px(20));

        viewHolder.add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LoginUtils.getInstance().isLogin()) {
                    StreetHangXunBiz.getInstance().addShopCart(item.getProduct_id(), item.getQuantity(), new StreetResponseListener() {
                        @Override
                        public void onFail(int code, String message) {
                            ToastUtils.showToast(mContext, mContext.getString(R.string.shop_cart_add_fail));
                        }

                        @Override
                        public void onSuccess(Object o) {
                            EventBus.getDefault().post(MessageShop.getInstance(MessageShop.ADD_SHOP_SUCCESS));
                            ToastUtils.showToast(mContext, mContext.getString(R.string.shop_cart_add_success));
                        }
                    });
                } else {
                    ToastUtils.showToast(mContext, mContext.getString(R.string.show_login));
                    ((MainActivity) mContext).setSelect(4);
                }
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
