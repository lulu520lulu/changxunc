package com.mall.hangxunc.pages.street.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.IsLoginData;
import com.mall.hangxunc.pages.street.StreetHomeActivity;
import com.mall.hangxunc.utils.JumpUtils;

public class StreetChangeIdentityDialog extends Dialog implements View.OnClickListener {

    private Activity mActivity;
    private TextView mChangeIdentity;
    private TextView mCurrentIdentity;
    private IsLoginData mData;

    public StreetChangeIdentityDialog(Activity context, IsLoginData data) {
        super(context);
        this.mActivity = context;
        this.mData = data;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.street_change_identity_dialog_layout, null);
        setContentView(rootView);

        mCurrentIdentity = (TextView) rootView.findViewById(R.id.current_identity);
        TextView identityText = (TextView) rootView.findViewById(R.id.identity_text);
        mChangeIdentity = (TextView) rootView.findViewById(R.id.change_company_identity);


        if (mData == null) {
            dismiss();
            return;
        }
        if (mData.getIs_company() == 1) {
            identityText.setText(R.string.commany_identity);
            mChangeIdentity.setText(R.string.change_person_identity);
            mCurrentIdentity.setText(R.string.commany_identity);
        } else {
            identityText.setText(R.string.person_identity);
            mChangeIdentity.setText(R.string.commany_certification);
            mCurrentIdentity.setText(R.string.person_identity);
        }
        mCurrentIdentity.setOnClickListener(this);
        mChangeIdentity.setOnClickListener(this);
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.current_identity:
                currentIdentity();
                break;
            case R.id.change_company_identity:
                changeIdentity();
                break;
        }
        dismiss();
    }

    private void currentIdentity() {
        if (mData.getIs_company() == 1) {
            JumpUtils.goCenter(getContext());
        } else {
            JumpUtils.goMain(getContext());

        }
        dismiss();
    }

    private void changeIdentity() {
        if (mData.getIs_company() == 1) {
            JumpUtils.goMain(getContext());
        } else {
            JumpUtils.goWeb(mData.getCompanyUrl());

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