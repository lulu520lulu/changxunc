package com.mall.hangxunc.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.mall.hangxunc.HangActivityManager;
import com.mall.hangxunc.HangXunApplication;
import com.mall.hangxunc.bean.home.PostsBean;
import com.mall.hangxunc.change.ChangeLanguageActivity;
import com.mall.hangxunc.http.ApiConstants;
import com.mall.hangxunc.pages.MainActivity;
import com.mall.hangxunc.pages.center.CenterAllPostActivity;
import com.mall.hangxunc.pages.center.CenterHomeActivity;
import com.mall.hangxunc.pages.guide.GuideActivity;
import com.mall.hangxunc.pages.home.MallAllPostActivity;
import com.mall.hangxunc.pages.street.StreetHomeActivity;
import com.mall.hangxunc.web.HybridActivity;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class JumpUtils {
    public static final String SEE_MORE_POST_DATA = "see_more_post_data";


    public static void goWeb(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        } else if (TextUtils.equals(url, "http://b.hangxunc.com/")) {
            JumpUtils.goStreet(HangXunApplication.getInstance());
            return;
        } else if (TextUtils.equals(url, "http://d.hangxunc.com:8081/scocenter/#/")) {
            JumpUtils.goCenter(HangXunApplication.getInstance());
            return;
        } else if (TextUtils.equals(url, "http://c.hangxunc.com/")) {
            JumpUtils.goMall(HangXunApplication.getInstance());
            return;
        }


        if (url.indexOf("amp;") > -1) {
            url = url.replace("amp;", "");
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(url);
        if (LoginUtils.getInstance().isLogin()) {
            buffer.append(ApiConstants.CUSTOMER_ID_PATH + LoginUtils.getInstance().getScoId());
        }
        if (TextUtils.isEmpty(LanguageSp.getInstance().getCode())) {
            buffer.append(ApiConstants.LANGUAGE_PATH + LanguageSp.getInstance().getCode());
        }
        if (TextUtils.isEmpty(CurrencySp.getInstance().getCode())) {
            buffer.append(ApiConstants.CURRENCY_PATH + CurrencySp.getInstance().getCode());
        }

        HybridActivity.openWeb(buffer.toString());
    }

    public static void goSeeMorePost(Context context, ArrayList<PostsBean> posts) {
        if (posts == null || posts.size() == 0) {
            return;
        }
        Intent intent = new Intent(context, MallAllPostActivity.class);
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

    public static void goMall(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void goStreet(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, StreetHomeActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void goCenter(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CenterHomeActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转引导页
     */
    public static void goGuideActivity(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void goTopActivity() {
        Activity topActivity1 = HangActivityManager.getInstance().getTopActivity();
        if (topActivity1 == null) {
            return;
        }
        if (TextUtils.equals(topActivity1.getClass().getSimpleName(), ChangeLanguageActivity.class.getSimpleName())) {
            HangActivityManager.getInstance().finishTopActivity();
        }
        Activity topActivity2 = HangActivityManager.getInstance().getTopActivity();
        if (topActivity2 == null) {
            return;
        }
        HangActivityManager.getInstance().finishTopActivity();
        Intent intent = new Intent(HangXunApplication.getInstance(), topActivity2.getClass());
        if (TextUtils.equals(topActivity1.getClass().getSimpleName(), HybridActivity.class.getSimpleName())) {
            HybridActivity.openWeb(HangXunApplication.getInstance().mHybridUrl);
        }
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        HangXunApplication.getInstance().startActivity(intent);
    }
}