package com.c.hangxunc.utils;

import com.c.hangxunc.HandXunApplication;

public class DimenUtils {
    private static final float HALF_PIXEL = 0.5f;

    /**
     * dip转px
     *
     * @param dip
     * @return
     */
    public static int dip2px(float dip) {
        float f = HandXunApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dip * f + HALF_PIXEL);
    }

    /**
     * px转dip
     *
     * @param px
     * @return
     */
    public static int px2dip(float px) {
        float f = HandXunApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (px / f);
    }

    /**
     * sp转px
     *
     * @param sp
     * @return
     */
    public static int sp2dip(float sp) {
        float f = HandXunApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * f + HALF_PIXEL);
    }
}
