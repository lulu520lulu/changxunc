package com.c.hangxunc.bean.home;

import java.util.List;

public class HomeCategoryData extends BaseBean{
    List<CategoryBean> data;

    public List<CategoryBean> getData() {
        return data;
    }

    public void setData(List<CategoryBean> data) {
        this.data = data;
    }
}
