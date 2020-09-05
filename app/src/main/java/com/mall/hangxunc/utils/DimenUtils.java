package com.mall.hangxunc.utils;

import com.mall.hangxunc.HangXunApplication;

public class DimenUtils {
    private static final float HALF_PIXEL = 0.5f;

    /**
     * dip转px
     *
     * @param dip
     * @return
     */
    public static int dip2px(float dip) {
        float f = HangXunApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dip * f + HALF_PIXEL);
    }

    /**
     * px转dip
     *
     * @param px
     * @return
     */
    public static int px2dip(float px) {
        float f = HangXunApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (px / f);
    }

    /**
     * sp转px
     *
     * @param sp
     * @return
     */
    public static int sp2dip(float sp) {
        float f = HangXunApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * f + HALF_PIXEL);
    }
}
