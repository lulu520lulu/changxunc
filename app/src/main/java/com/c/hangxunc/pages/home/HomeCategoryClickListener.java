package com.c.hangxunc.pages.home;

import com.c.hangxunc.bean.home.CategoryBean;

import java.util.List;

public interface HomeCategoryClickListener {

    void onItemClick(List<CategoryBean> bean, int position);

}