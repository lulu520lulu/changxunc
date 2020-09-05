package com.mall.hangxunc.pages.home.listener;

import com.mall.hangxunc.bean.home.CategoryBean;

import java.util.List;

public interface MallCategoryClickListener {

    void onItemClick(List<CategoryBean> bean, int position);

}