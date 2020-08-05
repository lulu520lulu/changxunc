package com.c.hangxunc.pages.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.JumpUtils;
import com.c.hangxunc.utils.LoginUtils;
import com.c.hangxunc.utils.ToastUtils;
import com.c.hangxunc.utils.WindowUtils;

import java.util.List;

class BottomListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ProductBean> mData;

    public BottomListAdapter(Activity mContext, List<ProductBean> list) {
        this.mData = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_bottom_product_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductBean item = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;

        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int parentPadding = DimenUtils.dip2px(12);
        int margin = DimenUtils.dip2px(8);

        int width = windowWidth / 2 - parentPadding - margin;

        LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams) viewHolder.image.getLayoutParams();
        imageParams.width = width;
        imageParams.height = (int) (width * 1.186);


        Glide.with(mContext)
                .load(item.getThumb())
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image)
                .into(viewHolder.image);

        if (TextUtils.equals(item.getSpecial(), "false")) {
            viewHolder.price_text.setText(item.getPrice());
        } else {
            viewHolder.price_text.setText(item.getSpecial());
        }
        if (item.getSales() == 0) {
            viewHolder.pinNum.setVisibility(View.GONE);
        } else {
            viewHolder.pinNum.setVisibility(View.VISIBLE);
            String format = String.format(mContext.getString(R.string.pin), item.getGroupbuy_list_len() + "");
            viewHolder.pinNum.setText(format);
        }

        viewHolder.sale_num.setText(String.format(mContext.getResources().getString(R.string.sales), item.getSales() + ""));
        viewHolder.title.setText(item.getName());

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
                            ToastUtils.showToast(mContext, mContext.getString(R.string.shop_cart_add_success));
                        }
                    });
                }
            });
        }else {
            ToastUtils.showToast(mContext,mContext.getString(R.string.show_login));
        }

        if (viewHolder.itemView != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.goWeb(item.getHref());
                }
            });
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewHolder.container.getLayoutParams();
        if (position % 2 == 0) {
            layoutParams.rightMargin = DimenUtils.dip2px(8);
        } else {
            layoutParams.leftMargin = DimenUtils.dip2px(0);
        }
        if (mData.size() % 2 == 0 && (position + 2) == mData.size()) {
            layoutParams.bottomMargin = 0;
        }
        if ((position + 1) == mData.size()) {
            layoutParams.bottomMargin = 0;
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
        private TextView price_text;
        private TextView title;
        private TextView pinNum;
        private TextView sale_num;
        private ImageView add_shop;
        private LinearLayout container;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price_text = itemView.findViewById(R.id.price_text);
            pinNum = itemView.findViewById(R.id.num);
            sale_num = itemView.findViewById(R.id.sale_num);
            add_shop = itemView.findViewById(R.id.add_shop);
            container = itemView.findViewById(R.id.container);

        }
    }
}
