package com.mall.hangxunc.bean.home;

import java.util.List;

public class TabsBean {
    /**
     * name : 医疗
     * products : [{"product_id":160,"thumb":"http://b.hangxunc.com/image/cache/catalog/急救包-300x300.png","name":"急救包","description":"..","price":"￥0.25","special":false,"flash":false,"tax":false,"seller":[],"minimum":100,"rating":0,"sales":50040,"quantity":75430,"href":"http://b.hangxunc.com/index.php?route=product/product&amp;product_id=160"},{"product_id":157,"thumb":"http://b.hangxunc.com/image/cache/catalog/绷带-300x300.png","name":"绷带","description":"..","price":"￥0.40","special":false,"flash":false,"tax":false,"seller":[],"minimum":20,"rating":0,"sales":2000,"quantity":36550,"href":"http://b.hangxunc.com/index.php?route=product/product&amp;product_id=157"},{"product_id":161,"thumb":"http://b.hangxunc.com/image/cache/catalog/美容设备-300x300.png","name":"美容设备","description":"..","price":"￥993.00","special":false,"flash":false,"tax":false,"seller":[],"minimum":1,"rating":0,"sales":1,"quantity":236,"href":"http://b.hangxunc.com/index.php?route=product/product&amp;product_id=161"},{"product_id":171,"thumb":"http://b.hangxunc.com/image/cache/catalog/手术设备-300x300.png","name":"手术设备","description":"..","price":"￥11,500.00","special":false,"flash":false,"tax":false,"seller":[],"minimum":1,"rating":0,"sales":1,"quantity":36,"href":"http://b.hangxunc.com/index.php?route=product/product&amp;product_id=171"},{"product_id":154,"thumb":"http://b.hangxunc.com/image/cache/catalog/按摩椅-300x300.png","name":"按摩椅","description":"..","price":"￥800.00","special":false,"flash":false,"tax":false,"seller":[],"minimum":1,"rating":0,"sales":1,"quantity":566,"href":"http://b.hangxunc.com/index.php?route=product/product&amp;product_id=154"},{"product_id":174,"thumb":"http://b.hangxunc.com/image/cache/catalog/牙科手机-300x300.png","name":"牙科手机","description":"..","price":"￥70.00","special":false,"flash":false,"tax":false,"seller":[],"minimum":10,"rating":0,"sales":1,"quantity":12510,"href":"http://b.hangxunc.com/index.php?route=product/product&amp;product_id=174"}]
     * image : http://b.hangxunc.com/image/catalog/icon.png
     */

    private String name;
    private String name_sub;

    private String image;
    private List<ProductBean> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

    public String getNameSub() {
        return name_sub;
    }

    public void settNameSub(String name_sub) {
        this.name_sub = name_sub;
    }
}
