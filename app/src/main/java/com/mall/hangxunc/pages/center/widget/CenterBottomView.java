package com.mall.hangxunc.pages.center.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.LanguageListBean;
import com.mall.hangxunc.change.ChangeLanguageActivity;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.pages.widget.BottomView;
import com.mall.hangxunc.utils.LanguageSp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CenterBottomView extends FrameLayout {

    private static final String TAG = CenterBottomView.class.getSimpleName();

    public CenterBottomView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public CenterBottomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private LinearLayout mLanguageContainer;
    private TextView mLanguage;
    private ImageView mArrow;
    private Context mContext;


    private void initView(Context context) {
        mContext = context;
        inflate(context, R.layout.center_bottom_view, this);
        EventBus.getDefault().register(this);
        mLanguageContainer = findViewById(R.id.language_container);
        mLanguage = findViewById(R.id.language);
        mArrow = findViewById(R.id.arrow);
        mLanguageContainer.setOnClickListener(mLanguageClickListener);
        showData();
    }

    private void showData() {
        LanguageListBean languageBean = LanguageSp.getInstance().getLanguageList();
        updateLanguage(languageBean);
    }

    private void languageItemClick(String code, String name) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mLanguage.setText(name);
                mArrow.setImageResource(R.mipmap.center_top_arrow);
            }
        });
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());


    private void showLanguageDialog() {
        ChangeLanguageActivity.launch(getContext(), ChangeLanguageActivity.LANGUAGE_FLAG);
    }


    private OnClickListener mLanguageClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showLanguageDialog();
        }
    };

    public void updateLanguage(LanguageListBean bean) {
        String language = "简体中文";

        if (bean != null) {
            if (TextUtils.equals(bean.getCode(), "zh-cn")) {
                language = "简体中文";
            } else if (TextUtils.equals(bean.getCode(), "en-gb")) {
                language = "English";
            } else if (TextUtils.equals(bean.getCode(), "ru-ru")) {
                language = "Russian";
            }
        }

        mLanguage.setText(language);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLocal(MessageLocal message) {
        String code = message.getCode();
        String name = message.getName();
        if (TextUtils.equals(message.message, MessageLocal.LANGUAGE_CHANGE)) {
            languageItemClick(code, name);
            showData();
        }
    }

}
