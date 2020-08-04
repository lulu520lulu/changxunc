package com.c.hangxunc.pages.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.BreadCrumbsBean;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.utils.JumpUtils;
import com.c.hangxunc.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter {

    private static final String TAG = SearchAdapter.class.getSimpleName();

    private Context mContext;
    private List<ProductBean> mDatas = new ArrayList<>();

    public SearchAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<ProductBean> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_result_item, parent, false);
        SearchViewHolder viewHolder = new SearchViewHolder(view);
        viewHolder.product_image = view.findViewById(R.id.product_image);
        viewHolder.product_text = view.findViewById(R.id.product_text);
        viewHolder.price_text = view.findViewById(R.id.price_text);
        viewHolder.icon_shop_cart=view.findViewById(R.id.icon_shop_cart);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder viewHolder = (SearchViewHolder) holder;

        ProductBean item = mDatas.get(position);
        viewHolder.price_text.setText(item.getPrice());
        viewHolder.product_text.setText(item.getName());
        Glide.with(mContext)
                .load(item.getThumb())
                .error(R.mipmap.place_image)
                .placeholder(R.mipmap.place_image)
                .into(viewHolder.product_image);
        viewHolder.icon_shop_cart.setOnClickListener(new View.OnClickListener() {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(item.getHref());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    private class SearchViewHolder extends RecyclerView.ViewHolder {

        private ImageView product_image;
        private TextView product_text;
        private TextView price_text;
        private ImageView icon_shop_cart;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
