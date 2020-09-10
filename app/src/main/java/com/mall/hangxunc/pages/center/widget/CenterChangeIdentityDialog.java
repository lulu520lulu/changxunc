package com.mall.hangxunc.pages.center.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.IsLoginData;
import com.mall.hangxunc.pages.center.center.CenterIsLoginData;
import com.mall.hangxunc.utils.JumpUtils;


public class CenterChangeIdentityDialog extends Dialog implements View.OnClickListener {

    private Activity mActivity;
    private TextView mChangeIdentity;
    private CenterIsLoginData mData;

    public CenterChangeIdentityDialog(Activity context, CenterIsLoginData data) {
        super(context);
        this.mActivity = context;
        this.mData = data;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.center_change_identity_dialog_layout, null);
        setContentView(rootView);

        TextView back = (TextView) rootView.findViewById(R.id.back);
        mChangeIdentity = (TextView) rootView.findViewById(R.id.change_person_identity);
        TextView identity_text = rootView.findViewById(R.id.identity_text);

        if (mData == null || mData.getUser() == null) {
            dismiss();
            return;
        }
        CenterIsLoginData.UserBean user = mData.getUser();
        if (user.getEnterpriseStatus() == 1) {
            //企业
            identity_text.setText(R.string.commany_identity);
            mChangeIdentity.setText(R.string.change_person_identity);

            back.setText(R.string.back);
        } else {
            //个人
            identity_text.setText(R.string.person_identity);

            mChangeIdentity.setText(R.string.change_person_identity);

            back.setText(R.string.commany_certification);
        }


        back.setOnClickListener(this);
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
            case R.id.back:

                CenterIsLoginData.UserBean user = mData.getUser();
                if (user.getEnterpriseStatus() == 1) {
                    //企业
                } else {
                    //个人
                    JumpUtils.goWeb(mData.getEnterpriseLink());
                }

                break;
            case R.id.change_person_identity:
                changeIdentity();
                break;
        }
        dismiss();
    }


    private void changeIdentity() {
        JumpUtils.goMall(getContext());
    }
}