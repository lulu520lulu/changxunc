package com.c.hangxunc.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.c.hangxunc.bean.home.PostsBean;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.pages.MainActivity;
import com.c.hangxunc.pages.home.AllPostActivity;
import com.c.hangxunc.web.HybridActivity;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class JumpUtils {
    public static final String SEE_MORE_POST_DATA = "see_more_post_data";

//    public static void goProductPage(String id) {
//        if (TextUtils.isEmpty(id)) {
//            return;
//        }
//        HybridActivity.openWeb(ApiConstants.BASE_URL + ApiConstants.PRODUCT_PAGE_PATH + id + ApiConstants.CUSTOMER_ID_PATH + LoginUtils.getInstance().getCustomerId());
//    }
//
//    public static void goCategoryDetailPage(String path) {
//        HybridActivity.openWeb(ApiConstants.BASE_URL + ApiConstants.CATEGORY_DETAIL_PATH + path + ApiConstants.CUSTOMER_ID_PATH + LoginUtils.getInstance().getCustomerId());
//    }


    public static void goWeb(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (url.indexOf("amp;") > -1) {
            url = url.replace("amp;", "");
        }
        HybridActivity.openWeb(url + ApiConstants.CUSTOMER_ID_PATH + LoginUtils.getInstance().getCustomerId());
//                + ApiConstants.LANGUAGE_PATH + LanguageUtils.getInstance().getCode());
    }

    public static void goSeeMorePost(Context context, ArrayList<PostsBean> posts) {
        if (posts == null || posts.size() == 0) {
            return;
        }
        Intent intent = new Intent(context, AllPostActivity.class);
        intent.putParcelableArrayListExtra(SEE_MORE_POST_DATA, posts);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void goMain(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
