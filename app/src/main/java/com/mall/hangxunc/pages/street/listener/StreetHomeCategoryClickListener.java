package com.mall.hangxunc.pages.street.listener;


import com.mall.hangxunc.bean.home.CategoryBean;

import java.util.List;

public interface StreetHomeCategoryClickListener {

    void onItemClick(List<CategoryBean> bean, int position);

}