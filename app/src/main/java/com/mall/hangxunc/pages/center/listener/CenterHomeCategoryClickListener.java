package com.mall.hangxunc.pages.center.listener;

import com.mall.hangxunc.bean.home.CategoryBean;

import java.util.List;

public interface CenterHomeCategoryClickListener {

    void onItemClick(List<CategoryBean> bean, int position);

}