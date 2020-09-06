package com.mall.hangxunc.pages.center.center;

import java.util.List;

public class NavBarBean {
    /**
     * name : 热门技术
     * products : [{"name":"热门技术1","imgPath":"http://d.hangxunc.com:8081/scocenter/static/index/nav-bar/jishu1.png","urlPath":""},{"name":"热门技术2","imgPath":"http://d.hangxunc.com:8081/scocenter/static/index/nav-bar/jishu2.png","urlPath":""}]
     */

    private String name;
    private List<NavBarChildBean> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NavBarChildBean> getProducts() {
        return products;
    }

    public void setProducts(List<NavBarChildBean> products) {
        this.products = products;
    }

}
