package com.c.hangxunc.bean.home;

import java.util.List;

public class ProductBean {

    /**
     * product_id : 217
     * thumb : http://c.hangxunc.com/image/cache/catalog/fk01-300x300.png
     * name : 远红外理疗仪
     * description : ..
     * price : $19.63
     * special : $1.41
     * flash : false
     * tax : false
     * seller : []
     * minimum : 1
     * rating : 0
     * sales : 1
     * quantity : 100
     * summary : 远红外理疗仪
     * href : http://c.hangxunc.com/index.php?route=product/product&product_id=217
     * group_discount : 70
     * group_price : $13.74
     * groupbuy_list : []
     * groupbuy_list_len : 0
     */

    private int product_id;
    private String thumb;
    private String name;
    private String description;
    private String price;
    private String special;
    private boolean flash;
    private boolean tax;
    private String minimum;
    private int rating;
    private int sales;
    private int quantity;
    private String summary;
    private String href;
    private String group_discount;
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

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
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

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getGroup_discount() {
        return group_discount;
    }

    public void setGroup_discount(String group_discount) {
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
}
