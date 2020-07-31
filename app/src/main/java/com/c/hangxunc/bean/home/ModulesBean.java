package com.c.hangxunc.bean.home;

import java.util.List;

public class ModulesBean {
    /**
     * banners : [{"title":"sss","link":"index.php?route=product/product&amp;product_id=48","image":"http://c.hangxunc.com/image/catalog/app/banner/app-banner.jpg"},{"title":"sss","link":"index.php?route=product/product&amp;product_id=48","image":"http://c.hangxunc.com/image/catalog/app/banner/app-banner.jpg"}]
     * module : 0
     * items : [{"image":"http://c.hangxunc.com/image/catalog/app/icon/科技创意.png","title":"科技成果","href":"index.php?route=product/tec_creativity_page"},{"image":"http://c.hangxunc.com/image/catalog/app/icon/限时优惠.png","title":"限时优惠","href":"index.php?route=product/special"},{"image":"http://c.hangxunc.com/image/catalog/app/icon/火热拼团.png","title":"火热拼团","href":"index.php?route=product/hot_product_page"},{"image":"http://c.hangxunc.com/image/catalog/app/icon/抗疫必备.png","title":"抗疫必备","href":"index.php?route=product/against_disease_page"},{"image":"http://c.hangxunc.com/image/catalog/app/icon/分类精选.png","title":"分类精选","href":"index.php?route=mobile/categories"}]
     * module_id : 81
     * position : true
     * products : [{"product_id":160,"thumb":"http://c.hangxunc.com/image/cache/catalog/seller_products/2/s01-200x200.jpg","name":"玻妞（HOBOT）188擦窗机器人 波妞智能家用擦玻璃清洁机器人电动全自动搽窗户机器人 高层擦玻璃神器","description":"..","price":"￥237.00","special":false,"flash":false,"tax":false,"seller":{"seller_id":"2","seller_group_id":"1","customer_id":"4","store_name":"lei","company":"lei","description":"","country_id":"44","zone_id":"707","city_id":"2346","county_id":"2352","avatar":"catalog/微信截图_20200428183201.png","banner":"catalog/demo/banner/3_en.jpg","alipay":"17615865470","product_validation":"0","status":"1","date_added":"2020-04-28 09:25:59","date_modified":"2020-04-29 09:46:26","product_id":"160","number_sold":"0","approved":"1","sort_order":"0","date_until":"0000-00-00"},"minimum":1,"rating":0,"sales":2,"quantity":99,"summary":null,"href":"http://c.hangxunc.com/index.php?route=product/product&product_id=160","group_discount":70,"group_price":"￥165.90","groupbuy_list":[],"groupbuy_list_len":0},{"product_id":207,"thumb":"http://c.hangxunc.com/image/cache/catalog/kt01-200x200.png","name":"医用压缩雾化器","description":"..","price":"￥26.00","special":false,"flash":false,"tax":false,"seller":[],"minimum":1,"rating":0,"sales":1,"quantity":100,"summary":null,"href":"http://c.hangxunc.com/index.php?route=product/product&product_id=207","group_discount":70,"group_price":"￥18.20","groupbuy_list":[],"groupbuy_list_len":0},{"product_id":211,"thumb":"http://c.hangxunc.com/image/cache/catalog/ktwhone1-200x200.png","name":"医用一次性雾化器","description":"..","price":"￥45.00","special":false,"flash":false,"tax":false,"seller":[],"minimum":1,"rating":0,"sales":1,"quantity":100,"summary":null,"href":"http://c.hangxunc.com/index.php?route=product/product&product_id=211","group_discount":70,"group_price":"￥31.50","groupbuy_list":[],"groupbuy_list_len":0}]
     * title : 科技成果
     * href : /index.php?route=product/tec_creativity_page
     * limit : null
     */

    private int module;
    private int module_id;
    private boolean position;
    private String title;
    private String subtitle;

    private String href;
    private Object limit;
    private List<BannersBean> banners;
    private List<ItemsBean> items;
    private List<ProductBean> products;
    private List<ProductBean> bottomProducts;

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

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

}
