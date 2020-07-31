package com.c.hangxunc.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.c.hangxunc.R;

public class ToastUtils {

    public static void showToast(Context context, String content) {
        if (context == null) {
            return;
        }

        Toast toast = new Toast(context);
        View layout = View.inflate(context, R.layout.toast_layout, null);
        toast.setView(layout);
        TextView text = layout.findViewById(R.id.text);
        text.setText(content);
        toast.setGravity(Gravity.CENTER, 0, 70);
        toast.show();
    }
}
