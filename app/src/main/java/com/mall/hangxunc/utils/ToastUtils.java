package com.mall.hangxunc.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mall.hangxunc.R;

public class ToastUtils {

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void showToast(Context context, String content) {
        if (context == null) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast toast = new Toast(context);
                View layout = View.inflate(context, R.layout.mall_toast_layout, null);
                toast.setView(layout);
                TextView text = layout.findViewById(R.id.text);
                text.setText(content);
                toast.setGravity(Gravity.CENTER, 0, 70);
                toast.show();
            }
        });
    }
}
