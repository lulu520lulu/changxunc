package com.c.hangxunc.pages.home;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.bumptech.glide.Glide;
import com.c.hangxunc.bean.home.BannersBean;
import com.c.hangxunc.bean.home.ModuleBean;
import com.c.hangxunc.bean.home.PostsBean;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.bean.home.TabsBean;
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
    private List<ModuleBean> mData = new ArrayList<>();

    private static final int ITEM_TYPE_BANNER = 1;
    private static final int ITEM_TYPE_PRODUCT = 2;
    private static final int ITEM_TYPE_TAB = 3;
    private static final int ITEM_TYPE_POST = 4;
    private static final int ITEM_TYPE_BOTTOM_LIST = 5;
    private static final int ITEM_TYPE_BOTTOM = 6;
    private static final int ITEM_TYPE_BANNER_TOP = 7;

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        ModuleBean bean = mData.get(position);
        if (bean != null) {
            if (bean.getBanners() != null && bean.getBanners().size() > 0) {
                if (position == 0) {
                    return ITEM_TYPE_BANNER_TOP;
                } else {
                    return ITEM_TYPE_BANNER;
                }
            } else if (bean.getProducts() != null && bean.getProducts().size() > 0) {
                return ITEM_TYPE_PRODUCT;
            } else if (bean.getTabs() != null && bean.getTabs().size() > 0) {
                return ITEM_TYPE_TAB;
            } else if (bean.getPosts() != null && bean.getPosts().size() > 0) {
                return ITEM_TYPE_POST;
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

    public void setTopData(List<ModuleBean> data) {
        mData.clear();
        mData.addAll(data);
        ModuleBean bean = new ModuleBean();
        bean.setTitle("bottom");
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setAllData(List<ModuleBean> topData, List<ProductBean> data) {
        mData.clear();
        mData.addAll(topData);

        ModuleBean product = new ModuleBean();
        product.setTitle("product");
        product.setBottomProducts(data);
        mData.add(product);

        ModuleBean bean = new ModuleBean();
        bean.setTitle("bottom");
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setMoreData(List<ProductBean> data) {

        ModuleBean product = new ModuleBean();
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
            case ITEM_TYPE_BANNER_TOP:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_banner_top, parent, false);
                return new HomeListAdapter.BannerTopViewHolder(view);
            case ITEM_TYPE_PRODUCT:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_product, parent, false);
                return new HomeListAdapter.ProductViewHolder(view);
            case ITEM_TYPE_TAB:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_tab, parent, false);
                return new HomeListAdapter.TabViewHolder(view);
            case ITEM_TYPE_POST:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_post, parent, false);
                return new HomeListAdapter.PostViewHolder(view);
            case ITEM_TYPE_BOTTOM_LIST:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_bottom_list, parent, false);
                return new HomeListAdapter.BottomListViewHolder(view);
            case ITEM_TYPE_BOTTOM:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_bottom, parent, false);
                return new HomeListAdapter.BottomViewHolder(view);
            case ITEM_TYPE_BANNER:
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_banner, parent, false);
                return new HomeListAdapter.BannerViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ModuleBean bean = mData.get(position);

        int type = getItemViewType(position);
        switch (type) {
            case ITEM_TYPE_BANNER_TOP:
                handleBannerTop(holder, bean);
                break;
            case ITEM_TYPE_PRODUCT:
                handleProduct(holder, bean);
                break;
            case ITEM_TYPE_TAB:
                handleTab(holder, bean);
                break;
            case ITEM_TYPE_POST:
                handlePost(holder, bean);
                break;
            case ITEM_TYPE_BOTTOM_LIST:
                handleBottomList(holder, bean);
                break;
            case ITEM_TYPE_BOTTOM:
                break;
            case ITEM_TYPE_BANNER:
                handleBanner(holder, bean);
                break;

        }
    }

    private void handleBanner(RecyclerView.ViewHolder viewHolder, ModuleBean bean) {
        List<BannersBean> list = bean.getBanners();
        if (list == null || list.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof BannerViewHolder)) {
            return;
        }
        BannerViewHolder holder = (BannerViewHolder) viewHolder;

        holder.product_recycle.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        BannerAdapter adapter = new BannerAdapter(mContext, list);
        holder.product_recycle.setAdapter(adapter);
    }

    private class BottomViewHolder extends RecyclerView.ViewHolder {

        public BottomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class BottomListViewHolder extends RecyclerView.ViewHolder {
        private TextView product_text;
        private RecyclerView product_recycle;
        private LinearLayout products_container;
        private View space_view;
        private View line;


        public BottomListViewHolder(@NonNull View itemView) {
            super(itemView);
            product_text = itemView.findViewById(R.id.product_text);
            product_recycle = itemView.findViewById(R.id.product_recycle);
            products_container = itemView.findViewById(R.id.products_container);
            space_view = itemView.findViewById(R.id.space_view);
            line = itemView.findViewById(R.id.line);
        }
    }

    private class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView see_more;
        private TextView product_text;
        private RecyclerView product_recycle;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            see_more = itemView.findViewById(R.id.see_more);
            product_text = itemView.findViewById(R.id.product_text);
            product_recycle = itemView.findViewById(R.id.product_recycle);
        }
    }

    private class TabViewHolder extends RecyclerView.ViewHolder {
        private RadioGroup radio_group;
        private RecyclerView tab_recycle;

        public TabViewHolder(@NonNull View itemView) {
            super(itemView);
            radio_group = itemView.findViewById(R.id.radio_group);
            tab_recycle = itemView.findViewById(R.id.tab_recycle);
        }
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView product_text;
        private RecyclerView product_recycle;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            product_recycle = itemView.findViewById(R.id.product_recycle);
            product_text = itemView.findViewById(R.id.product_text);
        }
    }

    private class BannerTopViewHolder extends RecyclerView.ViewHolder {

        private Banner banner;

        public BannerTopViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {

        private TextView product_text;
        private RecyclerView product_recycle;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            product_text = itemView.findViewById(R.id.product_text);
            product_recycle = itemView.findViewById(R.id.product_recycle);
        }
    }

    private void handleBottomList(RecyclerView.ViewHolder viewHolder, ModuleBean bean) {
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
            holder.space_view.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);
        } else {
            holder.products_container.setVisibility(View.GONE);
            holder.space_view.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }
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

    private void handlePost(RecyclerView.ViewHolder viewHolder, ModuleBean bean) {
        List<PostsBean> posts = bean.getPosts();
        if (posts == null || posts.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof PostViewHolder)) {
            return;
        }
        PostViewHolder holder = (PostViewHolder) viewHolder;
        holder.see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goSeeMorePost(mContext, (ArrayList<PostsBean>) posts);
            }
        });
        holder.product_text.setText(bean.getHeading_title());
        holder.product_recycle.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        PostRecycleAdapter recycleAdapter = new PostRecycleAdapter(mContext, posts);
        holder.product_recycle.setAdapter(recycleAdapter);
    }

    private void handleTab(RecyclerView.ViewHolder viewHolder, ModuleBean bean) {
        List<TabsBean> tabs = bean.getTabs();
        if (tabs == null || tabs.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof TabViewHolder)) {
            return;
        }
        TabViewHolder holder = (TabViewHolder) viewHolder;


        holder.radio_group.removeAllViews();
        for (int i = 0; i < tabs.size(); i++) {
            TabsBean tabsBean = tabs.get(i);
            if (tabsBean == null) {
                continue;
            }
            RadioButton radioButton = new RadioButton(mContext);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.MATCH_PARENT, 1);
            radioButton.setButtonDrawable(null);
            radioButton.setId(i + 10);
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.home_tab_hotal);
            if (i == 1) {
                drawable = mContext.getResources().getDrawable(R.mipmap.home_tab_food);
            } else if (i == 2) {
                drawable = mContext.getResources().getDrawable(R.mipmap.home_tab_info);
            } else if (i == 3) {
                drawable = mContext.getResources().getDrawable(R.mipmap.home_tab_product);
            }
            drawable.setBounds(0, 0, DimenUtils.dip2px(16), DimenUtils.dip2px(16));

            radioButton.setCompoundDrawables(drawable, null, null, null);
            radioButton.setCompoundDrawablePadding(DimenUtils.dip2px(4));
            radioButton.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            radioButton.setText(tabsBean.getName());
            radioButton.setPadding(DimenUtils.dip2px(16), 0, DimenUtils.dip2px(12), 0);
            ColorStateList colorStateList = mContext.getResources().getColorStateList(R.color.home_center_tab_color);
            radioButton.setTextColor(colorStateList);
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            if (i == 0) {
                radioButton.setChecked(true);
            } else {
                radioButton.setChecked(false);
            }
            holder.radio_group.addView(radioButton, layoutParams);
            if ((i + 1) < tabs.size()) {
                TextView view = new TextView(mContext);
                RadioGroup.LayoutParams lineLayoutParams = new RadioGroup.LayoutParams(DimenUtils.dip2px(0.5f),
                        DimenUtils.dip2px(16));
                view.setGravity(Gravity.CENTER_VERTICAL);

                layoutParams.gravity = Gravity.CENTER_VERTICAL;
                view.setBackgroundResource(R.color.line);
                holder.radio_group.addView(view, lineLayoutParams);
            }
        }
        TabRecycleAdapter tabRecycleAdapter = new TabRecycleAdapter(mContext);

        holder.tab_recycle.setLayoutManager(new GridLayoutManager(mContext, 2));
        holder.tab_recycle.setAdapter(tabRecycleAdapter);
        TabsBean tabsBean = tabs.get(0);
        List<TabsBean.ProductsBeanX> products = tabsBean.getProducts();
        tabRecycleAdapter.setData(products);

        holder.radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position = checkedId - 10;
                TabsBean tabsBean = tabs.get(position);
                List<TabsBean.ProductsBeanX> products = tabsBean.getProducts();
                tabRecycleAdapter.setData(products);
            }
        });


    }

    private void handleProduct(RecyclerView.ViewHolder viewHolder, ModuleBean bean) {
        List<ProductBean> products = bean.getProducts();
        if (products == null || products.size() == 0) {
            return;
        }
        if (viewHolder == null || !(viewHolder instanceof ProductViewHolder)) {
            return;
        }
        ProductViewHolder holder = (ProductViewHolder) viewHolder;
        String title = bean.getTitle();
        holder.product_text.setText(title);
        holder.product_recycle.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        ListProductAdapter listProductAdapter = new ListProductAdapter(mContext, products);
        holder.product_recycle.setAdapter(listProductAdapter);
    }

    private void handleBannerTop(RecyclerView.ViewHolder viewHolder, ModuleBean bean) {
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
                JumpUtils.goWeb(bannersBean.getLink());
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

}
