package com.c.hangxunc.pages.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CurrencyBean;
import com.c.hangxunc.bean.home.CurrencyListBean;
import com.c.hangxunc.bean.home.LanguageBean;
import com.c.hangxunc.bean.home.LanguageListBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.pages.MessageLocal;
import com.c.hangxunc.utils.CurrencySp;
import com.c.hangxunc.utils.CurrencyType;
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.HangLog;
import com.c.hangxunc.utils.LanguageSp;
import com.c.hangxunc.utils.LanguageType;
import com.c.hangxunc.utils.LanguageUtil;
import com.c.hangxunc.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class BottomDialog extends Dialog {

    private static final String TAG = BottomDialog.class.getSimpleName();

    public BottomDialog(@NonNull Context context) {
        super(context);
        init(context);

    }

    public BottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);

    }

    protected BottomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private TextView title;
    private LinearLayout itemContainer;

    private void init(Context context) {
        View view = View.inflate(context, R.layout.bottom_dialog_layout, null);
        setContentView(view);
        title = findViewById(R.id.title);
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        itemContainer = findViewById(R.id.item_container);
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        setCanceledOnTouchOutside(true);
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
            TextView textView = new TextView(getContext());
            textView.setTextColor(getContext().getResources().getColor(R.color.content_text));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setText(bean.getName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(48));
            params.leftMargin = DimenUtils.dip2px(12);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            itemContainer.addView(textView, params);

            Drawable drawable = null;

            if (TextUtils.equals(bean.getCode(), "en-gb")) {
                drawable = getContext().getResources().getDrawable(R.mipmap.english);
            } else if (TextUtils.equals(bean.getCode(), "ru-ru")) {
                drawable = getContext().getResources().getDrawable(R.mipmap.eluosi);
            } else {
                drawable = getContext().getResources().getDrawable(R.mipmap.china_language);
            }
            drawable.setBounds(0, 0, DimenUtils.dip2px(24), DimenUtils.dip2px(16));
            textView.setCompoundDrawables(drawable, null, null, null);
            textView.setCompoundDrawablePadding(DimenUtils.dip2px(4));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLanguageReport(bean);
                    dismiss();
                }
            });
            if ((i + 1) < languages.size()) {
                View line = new View(getContext());
                line.setBackgroundResource(R.color.line);
                LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(0.5f));
                itemContainer.addView(line, lineParams);
            }
        }
    }

    private void startLanguageReport(LanguageBean oldBean) {
        HangXunBiz.getInstance().setLanguage(oldBean.getCode(), new ResponseListener<LanguageListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFailure");
                onLanguageFail();
            }

            @Override
            public void onSuccess(LanguageListBean bean) {
                HangLog.d(TAG, "response success:" + bean.getCode());
                if (!TextUtils.equals(bean.getCode(), oldBean.getCode())) {

                    LanguageSp.getInstance().saveLanguage(oldBean);
                    LanguageUtil.changeLanguageAndKill(getContext(), oldBean.getCode());
                }
                if (mItemClickListener != null) {
                    mItemClickListener.languageItemClick(bean.getCode(), getLanguageName(bean.getCode()));
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


    private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void languageItemClick(String code, String name);

        void currencyItemClick(String symbol, String title);
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private void startCurrencyReport(String code) {
        HangXunBiz.getInstance().setCurrency(code, new ResponseListener<CurrencyListBean>() {
            @Override
            public void onFail(int code, String message) {
                HangLog.d(TAG, "onFailure");
                onCurrencyFail();
            }

            @Override
            public void onSuccess(CurrencyListBean bean) {
                HangLog.d(TAG, "response success:" + bean);

                if (!TextUtils.equals(bean.getCode(), CurrencySp.getInstance().getCode())) {
                    CurrencySp.getInstance().saveCurrencyList(bean);
                    EventBus.getDefault().post(MessageLocal.getInstance(MessageLocal.CHANGE));
                }

                if (mItemClickListener != null) {
                    mItemClickListener.currencyItemClick(getCurrencySymbol(bean.getCode()), getCurrencyTitle(bean.getCode()));
                }
            }
        });
    }

    private String getCurrencyTitle(String code) {
        if (TextUtils.equals(code, CurrencyType.ENGLISH.getCurrency())) {
            return "US Dollar";
        }
        return getContext().getResources().getString(R.string.rmb);
    }

    private String getCurrencySymbol(String code) {
        if (TextUtils.equals(code, CurrencyType.ENGLISH.getCurrency())) {
            return "$";
        }
        return "￥";
    }

    private void onLanguageFail() {
        ToastUtils.showToast(getContext(), getContext().getString(R.string.update_fail));
    }

    private void onCurrencyFail() {
        ToastUtils.showToast(getContext(), getContext().getString(R.string.update_fail));
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
            TextView textView = new TextView(getContext());
            textView.setTextColor(getContext().getResources().getColor(R.color.content_text));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setText(bean.getSymbol_left() + " " + bean.getTitle());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(48));
            params.leftMargin = DimenUtils.dip2px(12);
            textView.setGravity(Gravity.CENTER_VERTICAL);

            itemContainer.addView(textView, params);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startCurrencyReport(bean.getCode());
                    dismiss();
                }
            });
            if ((i + 1) < currencies.size()) {
                View line = new View(getContext());
                line.setBackgroundResource(R.color.line);
                LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(0.5f));
                itemContainer.addView(line, lineParams);
            }
        }
    }
}
