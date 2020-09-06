package com.mall.hangxunc.pages.center.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mall.hangxunc.R;
import com.mall.hangxunc.utils.JumpUtils;


public class CenterChangeIdentityDialog extends Dialog implements View.OnClickListener {

    private Activity mActivity;
    private TextView mChangeIdentity;

    public CenterChangeIdentityDialog(Activity context) {
        super(context);
        this.mActivity = context;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.center_change_identity_dialog_layout, null);
        setContentView(rootView);

        TextView back = (TextView) rootView.findViewById(R.id.back);
        mChangeIdentity = (TextView) rootView.findViewById(R.id.change_person_identity);

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
                break;
            case R.id.change_person_identity:
                changeIdentity();
                break;
        }
        dismiss();
    }


    private void changeIdentity() {
        JumpUtils.goStreet(getContext());
    }
}