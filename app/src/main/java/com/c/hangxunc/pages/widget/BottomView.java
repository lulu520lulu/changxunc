package com.c.hangxunc.pages.widget;

import android.app.Activity;
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

import com.c.hangxunc.change.ChangeLanguageActivity;
import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CurrencyListBean;
import com.c.hangxunc.bean.home.LanguageListBean;
import com.c.hangxunc.pages.MainActivity;
import com.c.hangxunc.pages.shop.MessageLocal;
import com.c.hangxunc.utils.CurrencySp;
import com.c.hangxunc.utils.LanguageSp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class BottomView extends FrameLayout {

    private static final String TAG = BottomView.class.getSimpleName();

    public BottomView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public BottomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private TextView mSupport;
    private LinearLayout mCurrencyContainer;
    private TextView mCurrencyValue;
    private TextView mCurrency;

    private LinearLayout mLanguageContainer;
    private TextView mLanguage;
    private ImageView mIvLanguage;
    private Context mContext;


    private void initView(Context context) {
        mContext = context;
        inflate(context, R.layout.base_bottom_view, this);
        EventBus.getDefault().register(this);
        mSupport = findViewById(R.id.support);

        mCurrencyContainer = findViewById(R.id.currency_container);
        mCurrencyValue = findViewById(R.id.currency_value);
        mCurrency = findViewById(R.id.currency);
        mCurrencyContainer.setOnClickListener(mCurrencyClickListener);

        mLanguageContainer = findViewById(R.id.language_container);
        mLanguage = findViewById(R.id.language);
        mIvLanguage = findViewById(R.id.iv_language);
        mLanguageContainer.setOnClickListener(mLanguageClickListener);
//        mBottomDialog = new BottomDialog(getContext());
//        mBottomDialog.setItemClickListener(mItemClickListener);
        showData();
    }

    private void showData() {
        LanguageListBean languageBean = LanguageSp.getInstance().getLanguageList();
        CurrencyListBean currencyList = CurrencySp.getInstance().getCurrencyList();
        updateCurrency(currencyList);
        updateLanguage(languageBean);
    }

    private void languageItemClick(String code, String name) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                int drawableRes;
                if (TextUtils.equals(code, "en-gb")) {
                    drawableRes = R.mipmap.english;
                } else if (TextUtils.equals(code, "ru-ru")) {
                    drawableRes = R.mipmap.eluosi;
                } else {
                    drawableRes = R.mipmap.china_language;
                }
                mIvLanguage.setImageResource(drawableRes);
                mLanguage.setText(name);
            }
        });
    }

    private void currencyItemClick(String symbol, String title) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCurrency.setText(symbol);
                mCurrencyValue.setText(title);
            }
        });

    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());


    private void showLanguageDialog() {
        ChangeLanguageActivity.launch(getContext(), ChangeLanguageActivity.LANGUAGE_FLAG);
    }

    private OnClickListener mCurrencyClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showCurrencyDialog();
        }
    };
    private OnClickListener mLanguageClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showLanguageDialog();
        }
    };

    private void showCurrencyDialog() {
        ChangeLanguageActivity.launch(getContext(), ChangeLanguageActivity.CURRENCY_FLAG);
    }

    private void updateCurrency(CurrencyListBean bean) {
        String currencyValue = getContext().getResources().getString(R.string.rmb);
        String currency = "￥";
        if (bean != null) {
            if (TextUtils.equals(bean.getCode(), "USD")) {
                currencyValue = "US Dollar";
                currency = "$";
            } else if (TextUtils.equals(bean.getCode(), "CNY")) {
                currency = "￥";
                currencyValue = getContext().getResources().getString(R.string.rmb);
            }
        }

        mCurrencyValue.setText(currencyValue);
        mCurrency.setText(currency);
    }

    public void updateLanguage(LanguageListBean bean) {
        String language = "简体中文";
        int imageResource = R.mipmap.china_language;

        if (bean != null) {
            if (TextUtils.equals(bean.getCode(), "zh-cn")) {
                language = "简体中文";
                imageResource = R.mipmap.china_language;
            } else if (TextUtils.equals(bean.getCode(), "en-gb")) {
                language = "English";
                imageResource = R.mipmap.english;

            } else if (TextUtils.equals(bean.getCode(), "ru-ru")) {
                language = "Russian";
                imageResource = R.mipmap.eluosi;
            }
        }

        mLanguage.setText(language);
        mIvLanguage.setImageResource(imageResource);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLocal(MessageLocal message) {
        String code = message.getCode();
        String name = message.getName();
        if (TextUtils.equals(message.message, MessageLocal.CURRENCY_CHANGE)) {
            currencyItemClick(code, name);
            showData();
        } else if (TextUtils.equals(message.message, MessageLocal.LANGUAGE_CHANGE)) {
            languageItemClick(code, name);
            showData();
        }
    }

}
