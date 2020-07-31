package com.c.hangxunc.pages.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CurrencyListBean;
import com.c.hangxunc.bean.home.LanguageListBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.utils.HangLog;


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

    private void initView(Context context) {
        inflate(context, R.layout.base_bottom_view, this);
        mSupport = findViewById(R.id.support);

        mCurrencyContainer = findViewById(R.id.currency_container);
        mCurrencyValue = findViewById(R.id.currency_value);
        mCurrency = findViewById(R.id.currency);
        mCurrencyContainer.setOnClickListener(mCurrencyClickListener);

        mLanguageContainer = findViewById(R.id.language_container);
        mLanguage = findViewById(R.id.language);
        mIvLanguage = findViewById(R.id.iv_language);
        mLanguageContainer.setOnClickListener(mLanguageClickListener);
        mBottomDialog = new BottomDialog(getContext());
        mBottomDialog.setItemClickListener(mItemClickListener);
        getData();

    }

    private BottomDialog.ItemClickListener mItemClickListener = new BottomDialog.ItemClickListener() {
        @Override
        public void languageItemClick(String code, String name) {
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

        @Override
        public void currencyItemClick(String currency, String currencyValue) {
            mCurrency.setText(currency);
            mCurrencyValue.setText(currencyValue);
        }
    };

    private BottomDialog mBottomDialog;

    private void showLanguageDialog() {
        mBottomDialog.updateLanguage(mLanguageListBean);
        mBottomDialog.show();
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
        mBottomDialog.updateCurrency(mCurrencyListBean);
        mBottomDialog.show();
    }


    private void getData() {
        HangXunBiz.getInstance().getLanguage(new ResponseListener<LanguageListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getHomeBottom code: " + code + ",message:" + message);

            }

            @Override
            public void onSuccess(LanguageListBean bean) {
                HangLog.d(TAG, "onSuccess getHomeBottom bean: " + bean.toString());
                updateLanguage(bean);
            }
        });


        HangXunBiz.getInstance().getCurrency(new ResponseListener<CurrencyListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFail getHomeBottom code: " + code + ",message:" + message);

            }

            @Override
            public void onSuccess(CurrencyListBean bean) {
                HangLog.d(TAG, "onSuccess getHomeBottom bean: " + bean.toString());
                updateCurrency(bean);
            }
        });
    }

    private void updateCurrency(CurrencyListBean bean) {
        if (bean == null) {
            return;
        }
        mCurrencyListBean = bean;


        String currencyValue = "人民币";
        String currency = "$";

        if (TextUtils.equals(bean.getCode(), "USD")) {
            currencyValue = "US Dollar";
            currency = "$";
        } else if (TextUtils.equals(bean.getCode(), "CNY")) {
            currency = "￥";
            currencyValue = "人民币";
        }
        mCurrencyValue.setText(currencyValue);
        mCurrency.setText(currency);
    }

    private CurrencyListBean mCurrencyListBean;
    private LanguageListBean mLanguageListBean;

    public void updateLanguage(LanguageListBean bean) {
        if (bean == null) {
            return;
        }
        mLanguageListBean = bean;
        String language = "简体中文";
        int imageResource = R.mipmap.china_language;
        if (TextUtils.equals(bean.getCode(), "zh-cn")) {
            language = "简体中文";
        } else if (TextUtils.equals(bean.getCode(), "en-gb")) {
            language = "English";
        } else if (TextUtils.equals(bean.getCode(), "ru-ru")) {
            language = "Russian";
        }
        mLanguage.setText(language);
        mIvLanguage.setImageResource(imageResource);
    }

    public String getLanguageCode() {
        if (mLanguageListBean != null) {
            return mLanguageListBean.getCode();
        }
        return "";
    }

    public String getCellCode() {
        if (mLanguageListBean != null) {
            if (TextUtils.equals(mLanguageListBean.getCode(), "en-gb")) {
                return "001";
            } else if (TextUtils.equals(mLanguageListBean.getCode(), "ru-ru")) {
                return "007";
            }
        }
        return "86";

    }
}
