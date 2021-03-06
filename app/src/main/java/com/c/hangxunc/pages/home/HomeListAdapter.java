package com.c.hangxunc.pages.home;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.BannersBean;
import com.c.hangxunc.bean.home.ItemsBean;
import com.c.hangxunc.bean.home.ModulesBean;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.bean.home.TabsBean;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.pages.MainActivity;
import com.c.hangxunc.utils.DimenUtils;
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
    private static final String BANNER_MODULE_TYPE = "slideshow";
    private static final String ITEM_MODULE_TYPE = "category_module";
    private static final String TABS_MODULE_TYPE = "tab_product_advertising";
    private static final String SPECIAL_MODULE_TYPE = "special";
    private static final String BOTTOM_MODULE_TYPE = "bottom";
    private static final String PRODUCT_MODULE_TYPE = "product";

    private static final int ITEM_TYPE_DEFAULT = 0;
    private static final int ITEM_TYPE_BANNER = 1;
    private static final int ITEM_TYPE_ITEM = 2;
    private static final int ITEM_TYPE_SPECIAL = 3;
    private static final int ITEM_TYPE_PRODUCT = 5;
    private static final int ITEM_TYPE_BOTTOM = 6;
    private static final int ITEM_TYPE_TAB = 9;

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
            if (TextUtils.equals(bean.getModule_type(), BANNER_MODULE_TYPE)) {
                return ITEM_TYPE_BANNER;
            } else if (TextUtils.equals(bean.getModule_type(), ITEM_MODULE_TYPE)) {
                return ITEM_TYPE_ITEM;
            } else if (TextUtils.equals(bean.getModule_type(), TABS_MODULE_TYPE)) {
                return ITEM_TYPE_TAB;
            } else if (TextUtils.equals(bean.getModule_type(), SPECIAL_MODULE_TYPE)) {
                return ITEM_TYPE_SPECIAL;
            } else if (TextUtils.equals(bean.getModule_type(), BOTTOM_MODULE_TYPE)) {
                return ITEM_TYPE_BOTTOM;
            } else if (TextUtils.equals(bean.getModule_type(), PRODUCT_MODULE_TYPE)) {
                return ITEM_TYPE_PRODUCT;
            }
        }
        return ITEM_TYPE_DEFAULT;
    }

    public HomeListAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void setTopData(List<ModulesBean> data) {
        mData.clear();
        mData.addAll(data);
        ModulesBean bean = new ModulesBean();
        bean.setModule_type(BOTTOM_MODULE_TYPE);
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setAllData(List<ModulesBean> topData, List<ProductBean> data) {
        mData.clear();
        mData.addAll(topData);

        ModulesBean product = new ModulesBean();
        product.setModule_type(PRODUCT_MODULE_TYPE);

        product.setBottomProducts(data);
        mData.add(product);

        ModulesBean bean = new ModulesBean();
        bean.setModule_type(BOTTOM_MODULE_TYPE);

        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setMoreData(List<ProductBean> data) {
        ModulesBean product = new ModulesBean();
        product.setModule_type(PRODUCT_MODULE_TYPE);
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
            case ITEM_TYPE_SPECIAL:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_product_keji, parent, false);
                return new HomeListAdapter.ProductKeJiViewHolder(view);
            case ITEM_TYPE_PRODUCT:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_product_all, parent, false);
                return new HomeListAdapter.BottomListViewHolder(view);
            case ITEM_TYPE_BOTTOM:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_bottom, parent, false);
                return new HomeListAdapter.BottomViewHolder(view);
            case ITEM_TYPE_TAB:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_tabs, parent, false);
                return new HomeListAdapter.TabsViewHolder(view);
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
            case ITEM_TYPE_SPECIAL:
                handleSpecial(holder, bean);
                break;
            case ITEM_TYPE_PRODUCT:
                handleProduct(holder, bean);
                break;
            case ITEM_TYPE_BOTTOM:
                break;
            case ITEM_TYPE_TAB:
                handleTabs(holder, bean);
                break;
        }
    }

    private void handleTabs(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<TabsBean> list = bean.getTabs();
        if (list == null || list.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof TabsViewHolder)) {
            return;
        }
        TabsViewHolder holder = (TabsViewHolder) viewHolder;

        holder.recycleView.setLayoutManager(new GridLayoutManager(mContext, 2));
        TabRecycleAdapter adapter = new TabRecycleAdapter(mContext, list);
        holder.recycleView.setAdapter(adapter);
    }

    private void handleSpecial(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
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
        holder.product_recycle.setLayoutManager(new GridLayoutManager(mContext,2,
                RecyclerView.VERTICAL, false));
        SpecialAdapter recycleAdapter = new SpecialAdapter(mContext, posts);
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.HORIZONTAL, false);//2代表是两列,也可以改成你需要的列数 ,LinearLayoutManager.HORIZONTAL代表横向滑动
        holder.recyclerView.setLayoutManager(gridLayoutManager);

        ItemListAdapter adapter = new ItemListAdapter(mContext, items);
        holder.recyclerView.setAdapter(adapter);
    }


    private void handleProduct(RecyclerView.ViewHolder viewHolder, ModulesBean bean) {
        List<ProductBean> list = bean.getBottomProducts();
        if (list == null || list.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof BottomListViewHolder)) {
            return;
        }
        BottomListViewHolder holder = (BottomListViewHolder) viewHolder;

        if (TextUtils.isEmpty(bean.getSubtitle())) {
            holder.products_container.setVisibility(View.VISIBLE);
        } else {
            holder.products_container.setVisibility(View.GONE);
        }
        holder.product_recycle.setLayoutManager(new GridLayoutManager(mContext, 2));
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
        ViewGroup.LayoutParams params = holder.banner.getLayoutParams();
        if (bean.getModule_id() == 93) {
            params.height = DimenUtils.dip2px(120);
        }

        LocalImageLoader imageLoader = new LocalImageLoader();
        //样式
        holder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //加载器
        holder.banner.setImageLoader(imageLoader);
        //动画效果
        holder.banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //图片标题
        holder.banner.setBannerTitles(imageTitle);
        //间隔时间
        holder.banner.setDelayTime(5000);
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

            RequestOptions options = new RequestOptions()
                    .error(R.mipmap.new_banner)
                    .placeholder(R.mipmap.new_banner)
                    .bitmapTransform(new RoundedCorners(30));
            Glide.with(mContext)
                    .load((String) path)
                    .apply(options)
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

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;

        public ItemViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.recycleView);
        }
    }

    private class BottomViewHolder extends RecyclerView.ViewHolder {

        public BottomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class BottomListViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView product_recycle;
        private RelativeLayout products_container;
        private TextView see_more;


        public BottomListViewHolder(@NonNull View itemView) {
            super(itemView);
            product_recycle = itemView.findViewById(R.id.product_recycle);
            products_container = itemView.findViewById(R.id.products_container);
            see_more = itemView.findViewById(R.id.see_more_product_all);
            see_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) mContext).setSelect(2);
                }
            });
        }
    }


    private class BannerTopViewHolder extends RecyclerView.ViewHolder {

        private Banner banner;

        public BannerTopViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class TabsViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycleView;

        public TabsViewHolder(View view) {
            super(view);
            recycleView = view.findViewById(R.id.recycleView);
        }
    }
}
