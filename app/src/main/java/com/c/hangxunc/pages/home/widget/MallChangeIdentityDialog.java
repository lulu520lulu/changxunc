package com.c.hangxunc.pages.home.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.c.hangxunc.R;
import com.c.hangxunc.pages.street.StreetHomeActivity;

public class MallChangeIdentityDialog extends Dialog implements View.OnClickListener {

    private Activity mActivity;
    private TextView mChangeCompany;
    private TextView mBack;

    public MallChangeIdentityDialog(Activity context) {
        super(context);
        this.mActivity = context;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.mall_change_identity_dialog_layout, null);
        setContentView(rootView);
        mBack = (TextView) rootView.findViewById(R.id.back);
        mChangeCompany = (TextView) rootView.findViewById(R.id.change_company_identity);
        mBack.setOnClickListener(this);
        mChangeCompany.setOnClickListener(this);
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                dismiss();
                break;
            case R.id.change_company_identity:
                goCenter();
                break;
        }
        dismiss();
    }

    private void goCenter() {
        if (mActivity == null) {
            return;
        }
        Intent intent = new Intent(mActivity, StreetHomeActivity.class);
        mActivity.startActivity(intent);
    }

}