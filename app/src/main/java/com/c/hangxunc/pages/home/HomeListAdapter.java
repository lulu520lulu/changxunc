package com.c.hangxunc.pages.home;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.BannersBean;
import com.c.hangxunc.bean.home.ItemsBean;
import com.c.hangxunc.bean.home.ModulesBean;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.utils.JumpUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter {

    private Activity mContext;
    private List<ModulesBean> mData = new ArrayList<>();

    private static final int ITEM_TYPE_BANNER = 1;
    private static final int ITEM_TYPE_ITEM = 2;
    private static final int ITEM_TYPE_PRODUCT_KEJI = 3;
    private static final int ITEM_TYPE_PRODUCT_XIANSHI = 4;
    private static final int ITEM_TYPE_BOTTOM_LIST = 5;
    private static final int ITEM_TYPE_BOTTOM = 6;
    private static final int ITEM_TYPE_PRODUCT_GONGCHANG = 7;

    private static final int ITEM_TYPE_PRODUCT_ALL = 8;


    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        ModulesBean bean = mData.get(position);
        if (bean != null) {
            if (bean.getBanners() != null && bean.getBanners().size() > 0) {
                return ITEM_TYPE_BANNER;
            } else if (bean.getItems() != null && bean.getItems().size() > 0) {
                return ITEM_TYPE_ITEM;
            } else if (bean.getProducts() != null && bean.getProducts().size() > 0) {
                if (TextUtils.equals(bean.getTitle(), "科技成果")) {
                    return ITEM_TYPE_PRODUCT_KEJI;
                } else if (TextUtils.equals(bean.getTitle(), "限时优惠")) {
                    return ITEM_TYPE_PRODUCT_XIANSHI;
                } else if (TextUtils.equals(bean.getTitle(), "工厂尾单")) {
                    if (TextUtils.isEmpty(bean.getHref())) {
                        return ITEM_TYPE_PRODUCT_GONGCHANG;
                    } else {
                        return ITEM_TYPE_PRODUCT_ALL;
                    }
                }
            } else if (TextUtils.equals(bean.getTitle(), "bottom")) {
                return ITEM_TYPE_BOTTOM;
            } else if (TextUtils.equals(bean.getTitle(), "product")) {
                return ITEM_TYPE_BOTTOM_LIST;
            }
        }
        return super.getItemViewType(position);
    }


    public HomeListAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void setTopData(List<ModulesBean> data) {
        mData.clear();
        mData.addAll(data);
        ModulesBean bean = new ModulesBean();
        bean.setTitle("bottom");
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setAllData(List<ModulesBean> topData, List<ProductBean> data) {
        mData.clear();
        mData.addAll(topData);

        ModulesBean product = new ModulesBean();
        product.setTitle("product");
        product.setBottomProducts(data);
        mData.add(product);

        ModulesBean bean = new ModulesBean();
        bean.setTitle("bottom");
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setMoreData(List<ProductBean> data) {
        ModulesBean product = new ModulesBean();
        product.setTitle("product");
        product.setSubtitle("more");
        product.setBottomProducts(data);
        mData.add(mData.size() - 1, product);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ITEM_TYPE_BANNER:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_banner_top, parent, false);
                return new HomeListAdapter.BannerTopViewHolder(view);
            case ITEM_TYPE_ITEM:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_item, parent, false);
                return new HomeListAdapter.ItemViewHolder(view);
            case ITEM_TYPE_PRODUCT_KEJI:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_product_keji, parent, false);
                return new HomeListAdapter.ProductKeJiViewHolder(view);
            case ITEM_TYPE_PRODUCT_XIANSHI:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_product_xianshi, parent, false);
                return new HomeListAdapter.ProductXianViewHolder(view);
            case ITEM_TYPE_PRODUCT_GONGCHANG:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_product_gongchang, parent, false);
                return new HomeListAdapter.ProductGongViewHolder(view);
            case ITEM_TYPE_PRODUCT_ALL:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_product_all, parent, false);
                return new HomeListAdapter.ProductAllViewHolder(view);
            case ITEM_TYPE_BOTTOM_LIST:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_bottom_list, parent, false);
                return new HomeListAdapter.BottomListViewHolder(view);
            case ITEM_TYPE_BOTTOM:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_bottom, parent, false);
                return new HomeListAdapter.BottomViewHolder(view);

        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ModulesBean bean = mData.get(position);

        int type = getItemViewType(position);
        switch (type) {
            case ITEM_TYPE_BANNER:
                handleBannerTop(holder, bean);
                break;
            case ITEM_TYPE_ITEM:
                handleItem(holder, bean);
                break;
            case ITEM_TYPE_PRODUCT_KEJI:
                handleProductKeji(holder, bean);
                break;
            case ITEM_TYPE_PRODUCT_XIANSHI:
                handleProductXian(holder, bean);
                break;
            case ITEM_TYPE_PRODUCT_GONGCHANG:
                handleProductGong(holder, bean);
                break;
            case ITEM_TYPE_PRODUCT_ALL:
                handleProductAll(holder, bean);
                break;
            case ITEM_TYPE_BOTTOM_LIST:
                handleBottomList(holder, bean);
                break;
            case ITEM_TYPE_BOTTOM:
                break;


        }
    }

    private void handleProductAll(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<ProductBean> list = bean.getBottomProducts();
        if (list == null || list.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof ProductAllViewHolder)) {
            return;
        }
        ProductAllViewHolder holder = (ProductAllViewHolder) viewHolder;

        if (TextUtils.isEmpty(bean.getSubtitle())) {
            holder.products_container.setVisibility(View.VISIBLE);
        } else {
            holder.products_container.setVisibility(View.GONE);
        }
        holder.product_recycle.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        ProductAllAdapter adapter = new ProductAllAdapter(mContext, list);
        holder.product_recycle.setAdapter(adapter);
    }

    private void handleProductGong(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<ProductBean> posts = bean.getProducts();
        if (posts == null || posts.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof ProductGongViewHolder)) {
            return;
        }
        ProductGongViewHolder holder = (ProductGongViewHolder) viewHolder;
        if (TextUtils.isEmpty(bean.getHref())) {
            holder.see_more.setVisibility(View.GONE);
        } else {
            holder.see_more.setVisibility(View.VISIBLE);
            holder.see_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.goWeb(ApiConstants.BASE_URL + bean.getHref());
                }
            });
        }

        holder.product_text.setText(bean.getTitle());
        holder.product_recycle.setLayoutManager(new LinearLayoutManager(mContext,
                RecyclerView.VERTICAL, false));
        ProductGongAdapter recycleAdapter = new ProductGongAdapter(mContext, posts);
        holder.product_recycle.setAdapter(recycleAdapter);
    }


    private void handleProductXian(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<ProductBean> posts = bean.getProducts();
        if (posts == null || posts.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof ProductXianViewHolder)) {
            return;
        }
        ProductXianViewHolder holder = (ProductXianViewHolder) viewHolder;
        holder.see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(ApiConstants.BASE_URL + bean.getHref());
            }
        });
        holder.product_text.setText(bean.getTitle());
        holder.product_recycle.setLayoutManager(new GridLayoutManager(mContext, 4));
        ProductXianAdapter recycleAdapter = new ProductXianAdapter(mContext, posts);
        holder.product_recycle.setAdapter(recycleAdapter);
    }

    private void handleProductKeji(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<ProductBean> posts = bean.getProducts();
        if (posts == null || posts.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof ProductKeJiViewHolder)) {
            return;
        }
        ProductKeJiViewHolder holder = (ProductKeJiViewHolder) viewHolder;
        holder.see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goWeb(ApiConstants.BASE_URL + bean.getHref());
            }
        });
        holder.product_text.setText(bean.getTitle());
        holder.product_recycle.setLayoutManager(new LinearLayoutManager(mContext,
                RecyclerView.VERTICAL, false));
        ProductKeAdapter recycleAdapter = new ProductKeAdapter(mContext, posts);
        holder.product_recycle.setAdapter(recycleAdapter);
    }

    private void handleItem(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<ItemsBean> items = bean.getItems();
        if (items == null || items.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof ItemViewHolder)) {
            return;
        }
        ItemViewHolder holder = (ItemViewHolder) viewHolder;

        holder.container.removeAllViews();
        for (int i = 0; i < items.size(); i++) {
            ItemsBean item = items.get(i);
            if (item == null) {
                continue;
            }
            View view = View.inflate(mContext, R.layout.home_item_item_layout, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);

            ImageView image = view.findViewById(R.id.image);
            TextView textView = view.findViewById(R.id.text);
            textView.setText(item.getTitle());
            Glide.with(mContext)
                    .load(item.getImage())
                    .into(image);

            holder.container.addView(view, layoutParams);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.goWeb(ApiConstants.BASE_URL + "/" + item.getHref());
                }
            });
        }
    }


    private void handleBottomList(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<ProductBean> list = bean.getBottomProducts();
        if (list == null || list.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof BottomListViewHolder)) {
            return;
        }
        BottomListViewHolder holder = (BottomListViewHolder) viewHolder;

        holder.product_recycle.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        BottomListAdapter adapter = new BottomListAdapter(mContext, list);
        holder.product_recycle.setAdapter(adapter);
        holder.product_recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                loadMore(recyclerView);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                loadMore(recyclerView);
            }
        });
    }

    public interface LoadMoreListener {
        void handleLoadMore();
    }

    private LoadMoreListener mLoadMoreListener;

    public void setLoadMoreListener(LoadMoreListener mLoadMoreListener) {
        this.mLoadMoreListener = mLoadMoreListener;
    }

    private void loadMore(RecyclerView recyclerView) {
        boolean isLoadMore = recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange();
        if (isLoadMore) {
            if (mLoadMoreListener != null) {
                mLoadMoreListener.handleLoadMore();
            }
        }

    }

    private void handleBannerTop(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<BannersBean> banners = bean.getBanners();
        if (banners == null || banners.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof BannerTopViewHolder)) {
            return;
        }
        ArrayList<String> imageTitle = new ArrayList<>();
        ArrayList<String> imagePath = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            BannersBean bannersBean = banners.get(i);
            imageTitle.add(bannersBean.getTitle());
            imagePath.add(bannersBean.getImage());
        }
        BannerTopViewHolder holder = (BannerTopViewHolder) viewHolder;

        LocalImageLoader imageLoader = new LocalImageLoader();
        //样式
        holder.banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //加载器
        holder.banner.setImageLoader(imageLoader);
        //动画效果
        holder.banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //图片标题
        holder.banner.setBannerTitles(imageTitle);
        //间隔时间
        holder.banner.setDelayTime(3000);
        //是否为自动轮播
        holder.banner.isAutoPlay(true);
        //图片小点显示所在位置
        holder.banner.setIndicatorGravity(BannerConfig.CENTER);
        //图片加载地址
        holder.banner.setImages(imagePath);
        //启动轮播图。
        holder.banner.start();
        //监听轮播图
        holder.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if ((position + 1) > banners.size() || position < 0) {
                    return;
                }
                BannersBean bannersBean = banners.get(position);
                JumpUtils.goWeb(ApiConstants.BASE_URL + bannersBean.getLink());
            }
        });

    }

    private class LocalImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .placeholder(R.mipmap.new_banner)
                    .error(R.mipmap.new_banner)
                    .into(imageView);
        }
    }

    private class ProductKeJiViewHolder extends RecyclerView.ViewHolder {
        private TextView see_more;
        private TextView product_text;
        private RecyclerView product_recycle;

        public ProductKeJiViewHolder(@NonNull View itemView) {
            super(itemView);
            see_more = itemView.findViewById(R.id.see_more);
            product_text = itemView.findViewById(R.id.product_text);
            product_recycle = itemView.findViewById(R.id.product_recycle);
        }
    }

    private class ProductGongViewHolder extends RecyclerView.ViewHolder {
        private TextView see_more;
        private TextView product_text;
        private RecyclerView product_recycle;

        public ProductGongViewHolder(@NonNull View itemView) {
            super(itemView);
            see_more = itemView.findViewById(R.id.see_more);
            product_text = itemView.findViewById(R.id.product_text);
            product_recycle = itemView.findViewById(R.id.product_recycle);
        }
    }

    private class ProductXianViewHolder extends RecyclerView.ViewHolder {
        private TextView see_more;
        private TextView product_text;
        private RecyclerView product_recycle;

        public ProductXianViewHolder(@NonNull View itemView) {
            super(itemView);
            see_more = itemView.findViewById(R.id.see_more);
            product_text = itemView.findViewById(R.id.product_text);
            product_recycle = itemView.findViewById(R.id.product_recycle);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout container;

        public ItemViewHolder(View view) {
            super(view);
            container = view.findViewById(R.id.container);
        }
    }

    private class BottomViewHolder extends RecyclerView.ViewHolder {

        public BottomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class BottomListViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView product_recycle;


        public BottomListViewHolder(@NonNull View itemView) {
            super(itemView);
            product_recycle = itemView.findViewById(R.id.product_recycle);
        }
    }

    private class ProductAllViewHolder extends RecyclerView.ViewHolder {
        private TextView product_text;
        private RecyclerView product_recycle;
        private RelativeLayout products_container;


        public ProductAllViewHolder(@NonNull View itemView) {
            super(itemView);
            product_text = itemView.findViewById(R.id.product_text);
            product_recycle = itemView.findViewById(R.id.product_recycle);
            products_container = itemView.findViewById(R.id.products_container);
        }
    }

    private class BannerTopViewHolder extends RecyclerView.ViewHolder {

        private Banner banner;

        public BannerTopViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

}
