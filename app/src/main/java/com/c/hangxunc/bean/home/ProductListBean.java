package com.c.hangxunc.bean.home;

import java.util.List;

public class ProductListBean {

    private List<ProductBean> products;

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "AllProductBean{" +
                "products=" + products +
                '}';
    }
}
