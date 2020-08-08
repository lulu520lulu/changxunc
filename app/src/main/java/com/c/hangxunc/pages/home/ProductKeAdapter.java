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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.pages.MainActivity;
import com.c.hangxunc.pages.shop.MessageShop;
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.JumpUtils;
import com.c.hangxunc.utils.LoginUtils;
import com.c.hangxunc.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ProductKeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ProductBean> mData;

    public ProductKeAdapter(Activity mContext, List<ProductBean> list) {
        this.mData = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_product_keji_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductBean item = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.content.setText(item.getName());


        RequestOptions options = new RequestOptions()
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image)
                .bitmapTransform(new RoundedCorners(30));
        Glide.with(mContext)
                .load(item.getThumb())
                .apply(options)
                .into(viewHolder.image);

        viewHolder.price_text.setText(item.getPrice());
        if (item.getSales() == 0) {
            viewHolder.sale_num.setVisibility(View.GONE);
        } else {
            viewHolder.sale_num.setVisibility(View.VISIBLE);
            String format = String.format(mContext.getString(R.string.saled), item.getSales() + "");
            viewHolder.sale_num.setText(format);
        }
        if (viewHolder.itemView != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.goWeb(item.getHref());
                }
            });
        }
        expandTouchArea(viewHolder.add_shop, DimenUtils.dip2px(20));

        if (LoginUtils.getInstance().isLogin()) {
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
                            EventBus.getDefault().post(MessageShop.getInstance(MessageShop.ADD_SHOP_SUCCESS));
                            ToastUtils.showToast(mContext, mContext.getString(R.string.shop_cart_add_success));
                        }
                    });
                }
            });
        }else {
            viewHolder.add_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) mContext).setSelect(4);
                    ToastUtils.showToast(mContext, mContext.getString(R.string.show_login));
                }
            });
        }

        if (position == mData.size() - 1) {
            viewHolder.line.setVisibility(View.GONE);
            viewHolder.space.setVisibility(View.VISIBLE);
        } else {
            viewHolder.line.setVisibility(View.VISIBLE);
            viewHolder.space.setVisibility(View.GONE);
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
        private TextView content;
        private TextView price_text;
        private TextView sale_num;
        private ImageView add_shop;
        private View line;
        private View space;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            content = itemView.findViewById(R.id.content);
            price_text = itemView.findViewById(R.id.price_text);
            sale_num = itemView.findViewById(R.id.sale_num);
            add_shop = itemView.findViewById(R.id.add_shop);
            line = itemView.findViewById(R.id.line);
            space = itemView.findViewById(R.id.space);

        }
    }
}
