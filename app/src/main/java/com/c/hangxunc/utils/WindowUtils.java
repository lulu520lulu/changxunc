package com.c.hangxunc.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class WindowUtils {

    public static int getWindowWidth(Activity context) {
        if (context == null) {
            return 0;
        }
        Display defaultDisplay = context.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x;
    }

    public static int getWindowHeight(Activity context) {
        if (context == null) {
            return 0;
        }
        Display defaultDisplay = context.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.y;
    }
}
