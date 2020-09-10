package com.mall.hangxunc.change;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mall.hangxunc.BaseActivity;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.CurrencyBean;
import com.mall.hangxunc.bean.home.CurrencyListBean;
import com.mall.hangxunc.bean.home.LanguageBean;
import com.mall.hangxunc.bean.home.LanguageListBean;
import com.mall.hangxunc.bean.home.LanguageListData;
import com.mall.hangxunc.http.HangXunBiz;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.message.MessageLocal;
import com.mall.hangxunc.pages.center.widget.CenterLoadingView;
import com.mall.hangxunc.utils.CurrencySp;
import com.mall.hangxunc.utils.CurrencyType;
import com.mall.hangxunc.utils.DimenUtils;
import com.mall.hangxunc.utils.HangLog;
import com.mall.hangxunc.utils.LanguageSp;
import com.mall.hangxunc.utils.LanguageType;
import com.mall.hangxunc.utils.LanguageUtil;
import com.mall.hangxunc.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeLanguageActivity extends BaseActivity {

    private static final String FROM_FRAGMENT = "from_fragment";
    private final String TAG = getClass().getSimpleName();
    public static final int LANGUAGE_FLAG = 0;
    public static final int CURRENCY_FLAG = 1;
    public static final String CHANGE = "change";
    @BindView(R.id.loading)
    CenterLoadingView loading;
    @BindView(R.id.container)
    LinearLayout container;
    private TextView title;
    private LinearLayout itemContainer;

    public static void launch(Context context, int flag, int fromFragment) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ChangeLanguageActivity.class);
        intent.putExtra(CHANGE, flag);
        intent.putExtra(FROM_FRAGMENT, fromFragment);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void launch(Context context, int flag) {
        launch(context, flag, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    private void init() {
        title = findViewById(R.id.title);
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        itemContainer = findViewById(R.id.item_container);
        initData();
    }

    private void initData() {
        int intExtra = getIntent().getIntExtra(CHANGE, LANGUAGE_FLAG);
        if (intExtra == LANGUAGE_FLAG) {
            showLanguageView();
        } else {
            showCurrencyView();
        }
    }

    private void showLanguageView() {
        LanguageListBean languageList = LanguageSp.getInstance().getLanguageList();
        if (languageList != null && languageList.getLanguages() != null && languageList.getLanguages().size() > 0) {
            updateLanguage(languageList);
        } else {
            showLoading();
            HangXunBiz.getInstance().getLanguage(new ResponseListener<LanguageListData>() {
                @Override
                public void onFail(int code, String message) {
                    HangLog.d(TAG, "onFail getLanguage code: " + code + ",message:" + message);
                    hindLoading();
                    finish();
                }

                @Override
                public void onSuccess(LanguageListData data) {
                    hindLoading();
                    if (data != null && data.getData() != null) {
                        LanguageListBean bean = data.getData();
                        HangLog.d(TAG, "onSuccess getLanguage bean: " + bean.toString());
                        LanguageSp.getInstance().saveLanguageList(bean);
                        updateLanguage(bean);
                    } else {
                        finish();
                    }
                }
            });
        }
    }

    private void showLoading() {
        loading.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);
    }

    private void hindLoading() {
        loading.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }

    private void showCurrencyView() {
        CurrencyListBean currencyList = CurrencySp.getInstance().getCurrencyList();
        if (currencyList != null && currencyList.getCurrencies() != null && currencyList.getCurrencies().size() > 0) {
            updateCurrency(currencyList);
        } else {
            showLoading();
            HangXunBiz.getInstance().getCurrency(new ResponseListener<CurrencyListBean>() {
                @Override
                public void onFail(int code, String message) {
                    HangLog.d(TAG, "onFail getCurrency code: " + code + ",message:" + message);
                    hindLoading();
                }

                @Override
                public void onSuccess(CurrencyListBean bean) {
                    hindLoading();
                    if (bean != null) {
                        HangLog.d(TAG, "onSuccess getCurrency bean: " + bean.toString());
                        CurrencySp.getInstance().saveCurrencyList(bean);
                        updateCurrency(bean);
                    }
                }

            });
        }
    }


    public void updateLanguage(LanguageListBean list) {
        if (list == null) {
            return;
        }
        List<LanguageBean> languages = list.getLanguages();
        if (languages == null) {
            return;
        }

        title.setText(R.string.language);
        itemContainer.removeAllViews();
        for (int i = 0; i < languages.size(); i++) {
            LanguageBean bean = languages.get(i);
            if (bean == null) {
                continue;
            }
            TextView textView = new TextView(ChangeLanguageActivity.this);
            textView.setTextColor(ChangeLanguageActivity.this.getResources().getColor(R.color.mall_content_text));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setText(bean.getName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(48));
            params.leftMargin = DimenUtils.dip2px(12);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            itemContainer.addView(textView, params);

            Drawable drawable = null;

            if (TextUtils.equals(bean.getCode(), "en-gb")) {
                drawable = ChangeLanguageActivity.this.getResources().getDrawable(R.mipmap.english);
            } else if (TextUtils.equals(bean.getCode(), "ru-ru")) {
                drawable = ChangeLanguageActivity.this.getResources().getDrawable(R.mipmap.eluosi);
            } else {
                drawable = ChangeLanguageActivity.this.getResources().getDrawable(R.mipmap.china_language);
            }
            drawable.setBounds(0, 0, DimenUtils.dip2px(24), DimenUtils.dip2px(16));
            textView.setCompoundDrawables(drawable, null, null, null);
            textView.setCompoundDrawablePadding(DimenUtils.dip2px(4));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLanguageReport(bean.getCode(), list.getCode());
                    finish();
                }
            });
            if ((i + 1) < languages.size()) {
                View mall_line = new View(ChangeLanguageActivity.this);
                mall_line.setBackgroundResource(R.color.mall_line);
                LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(0.5f));
                itemContainer.addView(mall_line, lineParams);
            }
        }
    }

    private void startLanguageReport(String newCode, String oldCode) {
        HangXunBiz.getInstance().setLanguage(newCode, new ResponseListener<LanguageListData>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFailure");
                onLanguageFail();
            }

            @Override
            public void onSuccess(LanguageListData data) {
                if (data != null && data.getData() != null) {
                    LanguageListBean bean = data.getData();
                    HangLog.d(TAG, "response success:" + bean.getCode());
                    if (!TextUtils.equals(bean.getCode(), oldCode)) {
                        LanguageSp.getInstance().saveLanguageList(bean);

                        MessageLocal instance = MessageLocal.getInstance(MessageLocal.LANGUAGE_CHANGE);
                        instance.setCode(bean.getCode());
                        instance.setName(getLanguageName(bean.getCode()));
                        EventBus.getDefault().post(instance);
                        finish();
                        LanguageUtil.changeLanguage(bean);
                    }
                }
            }
        });
    }

    private String getLanguageName(String code) {
        if (TextUtils.equals(code, LanguageType.ENGLISH.getLanguage())) {
            return "English";
        } else if (TextUtils.equals(code, LanguageType.RU.getLanguage())) {
            return "Russian";
        }
        return "简体中文";
    }

    private void startCurrencyReport(String newCode, String oldCode) {
        HangXunBiz.getInstance().setCurrency(newCode, new ResponseListener<CurrencyListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFailure");
                onCurrencyFail();
            }

            @Override
            public void onSuccess(CurrencyListBean bean) {
                if (bean != null) {
                    HangLog.d(TAG, "response success:" + bean);

                    if (!TextUtils.equals(bean.getCode(), oldCode)) {
                        CurrencySp.getInstance().saveCurrencyList(bean);

                        MessageLocal instance = MessageLocal.getInstance(MessageLocal.CURRENCY_CHANGE);
                        instance.setCode(getCurrencySymbol(bean.getCode()));
                        instance.setName(getCurrencyTitle(bean.getCode()));
                        EventBus.getDefault().post(instance);
                        finish();
                    }

                }
            }
        });
    }

    private String getCurrencyTitle(String code) {
        if (TextUtils.equals(code, CurrencyType.ENGLISH.getCurrency())) {
            return "US Dollar";
        }
        return ChangeLanguageActivity.this.getResources().getString(R.string.rmb);
    }

    private String getCurrencySymbol(String code) {
        if (TextUtils.equals(code, CurrencyType.ENGLISH.getCurrency())) {
            return "$";
        }
        return "￥";
    }

    private void onLanguageFail() {
        ToastUtils.showToast(ChangeLanguageActivity.this, ChangeLanguageActivity.this.getString(R.string.update_fail));
    }

    private void onCurrencyFail() {
        ToastUtils.showToast(ChangeLanguageActivity.this, ChangeLanguageActivity.this.getString(R.string.update_fail));
    }

    public void updateCurrency(CurrencyListBean list) {
        if (list == null) {
            return;
        }
        List<CurrencyBean> currencies = list.getCurrencies();
        if (currencies == null) {
            return;
        }
        title.setText(R.string.currency);
        itemContainer.removeAllViews();
        for (int i = 0; i < currencies.size(); i++) {
            CurrencyBean bean = currencies.get(i);
            if (bean == null) {
                continue;
            }
            TextView textView = new TextView(ChangeLanguageActivity.this);
            textView.setTextColor(ChangeLanguageActivity.this.getResources().getColor(R.color.mall_content_text));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setText(bean.getSymbol_left() + " " + bean.getTitle());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(48));
            params.leftMargin = DimenUtils.dip2px(12);
            textView.setGravity(Gravity.CENTER_VERTICAL);

            itemContainer.addView(textView, params);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startCurrencyReport(bean.getCode(), list.getCode());
                    finish();
                }
            });
            if ((i + 1) < currencies.size()) {
                View mall_line = new View(ChangeLanguageActivity.this);
                mall_line.setBackgroundResource(R.color.mall_line);
                LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(0.5f));
                itemContainer.addView(mall_line, lineParams);
            }
        }
    }

}
