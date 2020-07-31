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
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.HangLog;
import com.c.hangxunc.utils.LanguageUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BottomDialog extends Dialog {

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
                    startReport(bean.getHref());
                    LanguageUtils.getInstance().updateLocale(bean.getCode());
                    dismiss();
                    if (mItemClickListener != null) {
                        mItemClickListener.languageItemClick(bean.getCode(), bean.getName());
                    }
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

    private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void languageItemClick(String code, String name);

        void currencyItemClick(String currency, String currencyValue);

    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private void startReport(String href) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url(href)
                .method("GET", null)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                HangLog.d("response", data);
            }
        });

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
                    startReport(bean.getHref());
                    dismiss();
                    if (mItemClickListener != null) {
                        mItemClickListener.currencyItemClick(bean.getSymbol_left(), bean.getTitle());
                    }
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
