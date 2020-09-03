package com.c.hangxunc.pages.home.listener;

import com.c.hangxunc.bean.home.CategoryBean;

import java.util.List;

public interface MallCategoryClickListener {

    void onItemClick(List<CategoryBean> bean, int position);

}