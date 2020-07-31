package com.c.hangxunc.bean.home;

import java.util.List;

public class ProductBean {

    /**
     * product_id : 160
     * thumb : http://c.hangxunc.com/image/cache/catalog/seller_products/2/s01-200x200.jpg
     * name : 玻妞（HOBOT）188擦窗机器人 波妞智能家用擦玻璃清洁机器人电动全自动搽窗户机器人 高层擦玻璃神器
     * description : ..
     * price : ￥237.00
     * special : false
     * flash : false
     * tax : false
     * seller : {"seller_id":"2","seller_group_id":"1","customer_id":"4","store_name":"lei","company":"lei","description":"","country_id":"44","zone_id":"707","city_id":"2346","county_id":"2352","avatar":"catalog/微信截图_20200428183201.png","banner":"catalog/demo/banner/3_en.jpg","alipay":"17615865470","product_validation":"0","status":"1","date_added":"2020-04-28 09:25:59","date_modified":"2020-04-29 09:46:26","product_id":"160","number_sold":"0","approved":"1","sort_order":"0","date_until":"0000-00-00"}
     * minimum : 1
     * rating : 0
     * sales : 2
     * quantity : 99
     * summary : null
     * href : http://c.hangxunc.com/index.php?route=product/product&product_id=160
     * group_discount : 70
     * group_price : ￥165.90
     * groupbuy_list : []
     * groupbuy_list_len : 0
     */

    private int product_id;
    private String thumb;
    private String name;
    private String description;
    private String price;
    private boolean special;
    private boolean flash;
    private boolean tax;
    private int minimum;
    private int rating;
    private int sales;
    private int quantity;
    private Object summary;
    private String href;
    private int group_discount;
    private String group_price;
    private int groupbuy_list_len;
    private List<?> groupbuy_list;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public boolean isFlash() {
        return flash;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    public boolean isTax() {
        return tax;
    }

    public void setTax(boolean tax) {
        this.tax = tax;
    }

//            public SellerBean getSeller() {
//                return seller;
//            }
//
//            public void setSeller(SellerBean seller) {
//                this.seller = seller;
//            }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getGroup_discount() {
        return group_discount;
    }

    public void setGroup_discount(int group_discount) {
        this.group_discount = group_discount;
    }

    public String getGroup_price() {
        return group_price;
    }

    public void setGroup_price(String group_price) {
        this.group_price = group_price;
    }

    public int getGroupbuy_list_len() {
        return groupbuy_list_len;
    }

    public void setGroupbuy_list_len(int groupbuy_list_len) {
        this.groupbuy_list_len = groupbuy_list_len;
    }

    public List<?> getGroupbuy_list() {
        return groupbuy_list;
    }

    public void setGroupbuy_list(List<?> groupbuy_list) {
        this.groupbuy_list = groupbuy_list;
    }


    @Override
    public String toString() {
        return "ProductsBean{" +
                "product_id=" + product_id +
                ", thumb='" + thumb + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", special=" + special +
                ", flash=" + flash +
                ", tax=" + tax +
                ", minimum=" + minimum +
                ", rating=" + rating +
                ", sales=" + sales +
                ", quantity=" + quantity +
                ", summary=" + summary +
                ", href='" + href + '\'' +
                ", group_discount=" + group_discount +
                ", group_price='" + group_price + '\'' +
                ", groupbuy_list_len=" + groupbuy_list_len +
                ", groupbuy_list=" + groupbuy_list +
                '}';
    }
}
