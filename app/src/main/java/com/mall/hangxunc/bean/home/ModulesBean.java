package com.mall.hangxunc.bean.home;


import java.util.List;

public class ModulesBean {

    private int module;
    private int module_id;
    private boolean position;
    private String title;
    private String subtitle;
    private String module_type;
    private String href;
    private Object limit;
    private List<BannersBean> banners;
    private List<ItemsBean> items;
    private List<TabsBean> tabs;
    private List<ProductBean> products;
    private List<ProductBean> bottomProducts;
    private String heading_title;

    private List<PostsBean> posts;

    public String getHeading_title() {
        return heading_title;
    }

    public void setHeading_title(String heading_title) {
        this.heading_title = heading_title;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public String getModule_type() {
        return module_type;
    }

    public void setModule_type(String module_type) {
        this.module_type = module_type;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<ProductBean> getBottomProducts() {
        return bottomProducts;
    }

    public void setBottomProducts(List<ProductBean> bottomProducts) {
        this.bottomProducts = bottomProducts;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public boolean isPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Object getLimit() {
        return limit;
    }

    public void setLimit(Object limit) {
        this.limit = limit;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public List<TabsBean> getTabs() {
        return tabs;
    }

    public void setTabs(List<TabsBean> tabs) {
        this.tabs = tabs;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

}
